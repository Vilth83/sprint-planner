package fr.vilth.sprintplanner.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.vilth.sprintplanner.domain.entities.Member;

/**
 * {@code JpaRepository} to handle {@code Member} persistence.
 * 
 * @author Thierry VILLEPREUX
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Return either the {@code email} is unique or not.
     * 
     * @param email the tested {@code email}
     * @return {@code true} if {@code email} exists; {@code false} otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Returns a {@code List} of {@code MemberViewDto} who respects one of the
     * following conditions :
     * <ul>
     * <li>He is not present in {@code Candidate} table.
     * <li>He is present in {@code Candidate} table for another {@code Task} but
     * not for the given one.
     * </ul>
     * 
     * @param task the given {@code Task} name.
     * @return {@code List} of {@code Member}
     */
    @Query("select m from Member m where m.id not in "
	    + "(select mem.id from Member mem left join Candidate c "
	    + "ON mem.id = c.member.id left join Task t "
	    + "on c.task.id = t.id where t.name = :task)")
    List<Member> findAllNonCandidatesByTask(@Param("task") String task);
}
