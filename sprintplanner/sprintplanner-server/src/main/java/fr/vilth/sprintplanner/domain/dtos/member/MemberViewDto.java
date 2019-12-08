package fr.vilth.sprintplanner.domain.dtos.member;

/**
 * A DTO representation of a {@code Member}.
 * <p>
 * this DTO give all informations about a {@code Member}.
 * 
 * @author Thierry VILLEPREUX
 */
public class MemberViewDto {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    protected MemberViewDto() {
	// private empty no-arg constructor
    }

    @Override
    public String toString() {
	return "{id=" + id + ", firstname=" + firstname + ", lastname="
		+ lastname + ", email=" + email + "}";
    }
}
