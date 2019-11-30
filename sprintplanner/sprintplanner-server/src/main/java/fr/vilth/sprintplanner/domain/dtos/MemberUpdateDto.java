package fr.vilth.sprintplanner.domain.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO representing a {@code Member} to be persisted in database.
 * 
 * @author Thierry VILLEPREUX
 */
/**
 * @author Thierry VILLEPREUX
 */
public class MemberUpdateDto {

    @NotNull
    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Email
    @NotBlank
    private String email;
}
