package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.vilth.sprintplanner.commons.utils.BooleanConverter;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false, unique = true)
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

    public Long getId() {
	return id;
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
	return "{id=" + id + ", code=" + code + "}";
    }
}
