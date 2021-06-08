package fr.cesi.cubes.resourceRelationnelles.services.member;

import java.util.List;

import fr.cesi.cubes.resourceRelationnelles.entities.friendship.Friendship;
import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.exceptions.member.MemberNotFoundException;
import fr.cesi.cubes.resourceRelationnelles.request.member.CreateMemberForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberPasswordForm;
import fr.cesi.cubes.resourceRelationnelles.request.member.UpdateMemberStatusForm;

public interface MemberServices {

    public Member registerMember( CreateMemberForm createMemberForm );

    public Member updatePassword( long id, UpdateMemberPasswordForm updateMemberPasswordForm );

    public Member updateMemberProfile( long id, UpdateMemberForm updateMemberForm );

    public Member switchAccountStatus( long id );

    public Member updateStatus( long id, UpdateMemberStatusForm updateMemberStatusForm );

    public Member getMemberById( long id ) throws MemberNotFoundException;

    public Member getMemberByUserName( String username );

    public List<Member> getMembers() throws MemberNotFoundException;

    public List<Friendship> getMemberFriendshipsById( long id );

    public void deleteById( long id );

	public Member getByEmail(String principal);

}
