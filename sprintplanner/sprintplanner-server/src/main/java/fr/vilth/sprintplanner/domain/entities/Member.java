package fr.vilth.sprintplanner.domain.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

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
@Table(name = "member")
public class Member extends AbstractEntity {

    private static final long serialVersionUID = 2229240326079763123L;

    @Column(nullable = false, length = 255)
    private String firstname;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Column(nullable = false, length = 100, unique=true)
    private String email;

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
	return "{" + super.toString() + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
		+ email + "}";
    }
}
