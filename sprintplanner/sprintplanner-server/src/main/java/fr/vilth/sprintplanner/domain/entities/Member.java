package fr.vilth.sprintplanner.domain.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;
import fr.vilth.sprintplanner.domain.types.Shift;

/**
 * A representation of a team {@code Member}.
 * <p>
 * Invariants are :
 * <ul>
 * <li>firstname, lastname and email cannot be blank
 * <li>email must be unique
 * <li>email must be shaped as an email
 * </ul>
 * 
 * @author Thierry VILLEPREUX
 */
@Entity
@Table(name = "members", uniqueConstraints = @UniqueConstraint(name = "members_email_UNIQUE", columnNames = "email"))
public class Member extends AbstractEntity {

    private static final long serialVersionUID = 2229240326079763123L;

    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false, length = 110)
    private String email;

    @Column(nullable = false, columnDefinition = "ENUM('PAR', 'BGL')")
    @Enumerated(value = EnumType.STRING)
    private Shift shift = Shift.PAR;

    /**
     * Default empty no-args constructor.
     */
    protected Member() {
	//
    }

    @Override
    public int hashCode() {
	return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Member)) {
	    return false;
	}
	Member other = (Member) obj;
	return Objects.equals(email, other.email);
    }

    @Override
    public String toString() {
	return "{id=" + super.getId() + ", firstname=" + firstname
		+ ", lastname=" + lastname + ", email=" + email + ", shift="
		+ shift + "}";
    }
}
