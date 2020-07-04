package fr.vilth.sprintplanner.security;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserAuthDto;
import fr.vilth.sprintplanner.domain.entities.Role;

/**
 * A custom {@code UserDetails} for Spring authentication contract and custom
 * properties we want in the token (such as the id).
 */
public class CustomUserDetails extends User {

	private static final long serialVersionUID = 5803283930339051994L;

	private Long id;

	/**
	 * Creates a new CustomUserDetails.
	 * 
	 * @param user the {@code CustomUserAuthDto} containing CustomUserDetailsInfo
	 */
	public CustomUserDetails(CustomUserAuthDto user) {
		super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
				user.isCredentialsNonExpired(), user.isAccountNonLocked(), buildAuthorities(user.getRoles()));
		id = user.getId();
	}

	private static Set<GrantedAuthority> buildAuthorities(Set<Role> roles) {
		Set<GrantedAuthority> authorities = roles.stream().map(r -> new SimpleGrantedAuthority(r.getCode()))
				.collect(Collectors.toSet());
		return Collections.unmodifiableSet(authorities);
	}

	/**
	 * Getter for id.
	 * 
	 * @return an identifier
	 */
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", authorities=" + getAuthorities() + ", password=[PROTECTED], username=" + getUsername()
				+ ", enabled=" + isEnabled() + ", accountNonExpired=" + isAccountNonExpired() + ", accountNonLocked="
				+ isAccountNonLocked() + ", credentialsNonExpired=" + isCredentialsNonExpired() + "}";
	}
}
