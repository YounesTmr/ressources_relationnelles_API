package fr.cesi.cubes.resourceRelationnelles.servicesImpl.member;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.exceptions.member.MemberNotFoundException;
import fr.cesi.cubes.resourceRelationnelles.exceptions.member.MemberValidationException;
import fr.cesi.cubes.resourceRelationnelles.repository.member.MemberRepository;
import fr.cesi.cubes.resourceRelationnelles.request.member.CreateMemberForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberPasswordForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberStatusForm;
import fr.cesi.cubes.resourceRelationnelles.services.comment.CommentServices;
import fr.cesi.cubes.resourceRelationnelles.services.friendship.FriendshipServices;
import fr.cesi.cubes.resourceRelationnelles.services.historic.HistoricServices;
import fr.cesi.cubes.resourceRelationnelles.services.interaction.InteractionServices;
import fr.cesi.cubes.resourceRelationnelles.services.member.MemberServices;
import fr.cesi.cubes.resourceRelationnelles.services.share.ShareServices;

@Service(value = "member")
public class MemberServicesImpl implements MemberServices {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private HistoricServices historicServices;
	@Autowired
	private InteractionServices interactionServices;
	@Autowired
	private ShareServices shareServices;
	@Autowired
	private FriendshipServices friendshipServices;
	@Autowired
	private CommentServices commentServices;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// contructor
	public MemberServicesImpl() {
	}

	// ***********************
	// DATA PROCESSING
	// ***********************
	@Override
	public Member registerMember(CreateMemberForm createMemberForm) {

		Member member = new Member();

		try {

			// validation of attributes
			validationEmail(createMemberForm.getEmail());
			member.setEmail(createMemberForm.getEmail());
			validationUsername(createMemberForm.getUsername());
			member.setUsername(createMemberForm.getUsername());
			member.setPassword(this.encoder.encode(createMemberForm.getPassword()));
			// save in database
			memberRepository.save(member);

		} catch (Exception e) {
			e.getMessage();
			return null;
		}

		return member;

	}

	@Override
	public Member updatePassword(long id, UpdateMemberPasswordForm updateMemberPasswordForm) {

		// get member
		Member member = this.getMemberById(id);

		// validate password and encrypt it
		try {
			member.setPassword(this.encoder.encode(updateMemberPasswordForm.getPassword()));
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

		// update of password in database
		memberRepository.updatePassword(id, member.getPassword());
		System.out.println("Password correctly modified");

		return member;

	}

	@Override
	public Member updateMemberProfile(long id, UpdateMemberForm updateMemberForm) {

		Member member = this.getMemberById(id);
		String countyName;
		String countryName;

		// SQL query needs strings -> null values management
		if (updateMemberForm.getCounty() != null)
			countyName = updateMemberForm.getCounty().getName();
		else
			countyName = "NULL";
		if (updateMemberForm.getCountry() != null)
			countryName = updateMemberForm.getCountry().name();
		else
			countryName = "NULL";

		// attributes updated for the return object and database updated
		if (updateMemberForm.getUsername() != null)
			member.setUsername(updateMemberForm.getUsername());
		if (updateMemberForm.getCounty() != null)
			member.setCounty(updateMemberForm.getCounty());
		if (updateMemberForm.getCountry() != null)
			member.setCountry(updateMemberForm.getCountry());
		if (updateMemberForm.getBirthDate() != null)
			member.setBirthDate(updateMemberForm.getBirthDate());
		memberRepository.updateProfile(id, countyName, countryName, updateMemberForm.getBirthDate());

		return member;
	}

	@Override
	public Member switchAccountStatus(long id) {

		Member member = this.getMemberById(id);

		member.setActivatedAccount(!member.isActivatedAccount());
		memberRepository.updateActivatedAccount(id, member.isActivatedAccount());

		return member;
	}

	@Override
	public Member updateStatus(long id, UpdateMemberStatusForm updateMemberStatusForm) {

		Member member = this.getMemberById(id);

		member.setStatus(updateMemberStatusForm.getStatus());
		memberRepository.updateStatus(id, member.getStatus().name()); // SQL query needs strings

		return member;
	}

	@Override
	public Member getMemberById(long id) throws MemberNotFoundException {

		Member member = memberRepository.findById(id);

		if (member == null) {
			throw new MemberNotFoundException("Le membre n° " + id + " est introuvable");
		}

		return member;
	}

	@Override
	public Member getMemberByUserName(String username) {
		return this.memberRepository.findByUsername(username);
	}

	@Override
	public List<Member> getMembers() throws MemberNotFoundException {

		List<Member> members = memberRepository.findAll();

		if (members == null) {
			throw new MemberNotFoundException("Aucun membre trouvé");
		}

		return members;
	}

	@Override
	public List<Friendship> getMemberFriendshipsById(long id) {

		Member member = getMemberById(id);
		return member.getFriendships();
	}

	@Override
	@Transactional
	public void deleteById(long id) {

		// update of historic records referencing the resource
		historicServices.deleteByMember1(id);
		historicServices.deleteByMember2(id);
		System.out.println("historic deleted !");

		// deletion of interaction records referencing the resource
		interactionServices.deleteInteractionByMember(id);
		System.out.println("interactions deleted !");

		// deletion of share records referencing the resource
		shareServices.deleteShareBySender(id);
		shareServices.deleteShareByRecipient(id);
		System.out.println("shares deleted !");

		// deletion of friendships records referencing the resource
		friendshipServices.deleteFriendshipByMember1(id);
		friendshipServices.deleteFriendshipByMember2(id);
		System.out.println("friendship deleted !");

		// modification of comments records referencing the resource
		commentServices.removeMemberFromComment(id);
		System.out.println("comment modified !");

		this.memberRepository.deleteById(id);

	}

	// ***********************
	// DATA VALIDATION METHODS
	// ***********************

	private void validationEmail(String email) throws MemberValidationException {

		if (email != null && memberRepository.findByEmail(email) != null) {

			throw new MemberValidationException("Cette adresse mail est déjà utilisée, merci d'en choisir une autre.");

		}
	}

	private void validationUsername(String username) throws MemberValidationException {

		if (username != null && memberRepository.findByUsername(username) != null) {

			throw new MemberValidationException("Ce pseudo est déjà utilisé, merci d'en choisir un autre.");

		}

	}

	@Override
	public Member getByEmail(String principal) {
		return this.memberRepository.findByEmail(principal);
	}

}
