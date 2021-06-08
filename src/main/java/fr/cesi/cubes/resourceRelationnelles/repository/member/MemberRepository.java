package fr.cesi.cubes.resourceRelationnelles.repository.member;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.cesi.cubes.resourceRelationnelles.entities.member.Member;
import fr.cesi.cubes.resourceRelationnelles.request.statistics.CountKPI;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUsername(String username);

	Member findById(long id);

	Member findByEmail(String email);

	@Transactional
	void deleteById(long id);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET password = :password WHERE Id_Member = :id")
	void updatePassword(@Param(value = "id") long id, @Param(value = "password") String password);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET username = :username WHERE Id_Member = :id")
	void updateUsername(@Param(value = "id") long id, @Param(value = "username") String username);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET county = :county, country = :country, birth_date = :birth_date WHERE Id_Member = :id")
	void updateProfile(@Param(value = "id") long id, @Param(value = "county") String county,
			@Param(value = "country") String country, @Param(value = "birth_date") Timestamp birthDate);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET activated_account = :activated_account WHERE Id_Member = :id")
	void updateActivatedAccount(@Param(value = "id") long id,
			@Param(value = "activated_account") boolean activatedAccount);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE members SET status = :status WHERE Id_Member = :id")
	void updateStatus(@Param(value = "id") long id, @Param(value = "status") String status);

	@Query(nativeQuery = true, value = "SELECT count(Id_Member) AS total FROM members")
	CountKPI countMembers();

}
