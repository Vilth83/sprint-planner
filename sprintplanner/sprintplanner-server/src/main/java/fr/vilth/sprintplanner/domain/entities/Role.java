package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.vilth.sprintplanner.configuration.entities.AbstractEntity;
import fr.vilth.sprintplanner.configuration.utils.BooleanConverter;

/**
 * Entity representing a {@code Role}.
 * <p>
 * Role define {@code CustomUser} atuhorizations.
 * 
 * @author Thierry VILLEPREUX
 *
 */
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "roles_code_UNIQUE", columnNames = "code"))
public class Role extends AbstractEntity {

	private static final long serialVersionUID = -7736465755017636243L;

	@Column(length = 256, nullable = false)
	private String code;

	@Convert(converter = BooleanConverter.class)
	@Column(length = 1, nullable = false)
	private boolean defaultRole = false;

	protected Role() {
		// protected empty no-arg constructor
	}

	/**
	 * Create a new {@code Role} with given code
	 * 
	 * @param code the given code
	 */
	public Role(String code) {
		setCode(code);
	}

	/**
	 * Getter for code
	 * 
	 * @return a code
	 */
	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter for defaultRole.
	 * 
	 * @return {@code true} if is default {@code Role}; {@code false} otherwise
	 */
	public boolean isDefaultRole() {
		return defaultRole;
	}

	@Override
	public String toString() {
		return "{id=" + getId() + ", code=" + code + "}";
	}
}
