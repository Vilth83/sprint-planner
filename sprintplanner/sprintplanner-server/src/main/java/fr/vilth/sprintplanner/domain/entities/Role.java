package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;
import fr.vilth.sprintplanner.commons.utils.BooleanConverter;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "roles_code_UNIQUE", columnNames = "code"))
public class Role extends AbstractEntity {

    @Column(length = 256, nullable = false)
    private String code;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean defaultRole = false;

    protected Role() {
	// Empty no-arg constructor for JPA
    }

    public Role(String code) {
	setCode(code);
    }

    public String getCode() {
	return code;
    }

    private void setCode(String code) {
	this.code = code;
    }

    public boolean isDefaultRole() {
	return defaultRole;
    }

    @Override
    public String toString() {
	return "{id=" + getId() + ", code=" + code + "}";
    }
}
