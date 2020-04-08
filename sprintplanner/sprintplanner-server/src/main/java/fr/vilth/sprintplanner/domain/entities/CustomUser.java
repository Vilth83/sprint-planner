package fr.vilth.sprintplanner.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.vilth.sprintplanner.commons.utils.BooleanConverter;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "users_username_UNIQUE", columnNames = "username"))
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String username;

    @Column(length = 256, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    // @formatter:off
    @JoinTable(name = "users_roles", indexes = {
	    @Index(name = "users_roles_user_id_IDX", columnList = "user_id"),
	    @Index(name = "users_roles_role_id_IDX", columnList = "role_id") }, joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "users_roles_user_id_FK")), inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "users_roles_role_id_FK")))
    // @formatter:on
    private Set<Role> roles;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean enabled;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonExpired;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean accountNonLocked;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean credentialsNonExpired;

    @Column(length = 256, nullable = false)
    private String firstname;

    @Column(length = 256, nullable = false)
    private String lastname;

    protected CustomUser() {
	// Empty no-arg constructor (Hibernate)
    }

    /**
     * Creates a new enabled custom user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles some roles
     */
    public CustomUser(String password, String username, String firstname,
	    String lastname, Set<Role> roles) {
	this(password, username, firstname, lastname, roles, true);
    }

    /**
     * Creates a new custom user.
     *
     * @param password an encrypted password
     * @param username a unique username
     * @param roles some roles
     * @param enabled {@code true} if enabled; {@code false} otherwise
     */
    public CustomUser(String password, String username, String firstname,
	    String lastname, Set<Role> roles,
	    boolean enabled) {
	this.password = password;
	this.username = username;
	this.firstname = firstname;
	this.lastname = lastname;
	this.roles = roles;
	this.enabled = enabled;
    }

    public Long getId() {
	return id;
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }

    public String getFirstname() {
	return firstname;
    }

    public String getLastname() {
	return lastname;
    }

    @Override
    public String toString() {
	// password=[PROTECTED] for not displaying in logs
	return "{id=" + id + ", username=" + username
		+ ", password=[PROTECTED], roles=" + roles + ", enabled="
		+ enabled + ", accountNonExpired=" + accountNonExpired
		+ ", accountNonLocked=" + accountNonLocked
		+ ", credentialsNonExpired=" + credentialsNonExpired
		+ ", firstname=" + firstname + ", lastname=" + lastname + "}";
    }
}
