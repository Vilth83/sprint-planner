package fr.vilth.sprintplanner.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Test on CustomUser.
 * 
 * @author Thierry VILLEPREUX
 *
 */
public class CustomUserTest extends SetupUnitTest {

	@Test
	void should_construct() {
		CustomUser customUser = new CustomUser("pwd", "usr", "firstname", "lastname", new HashSet<Role>());
		assertNotNull(customUser);
	}

	@Test
	void should_construct_enabled_user() {
		CustomUser customUser = new CustomUser("pwd", "usr", "firstname", "lastname", new HashSet<Role>(), true);
		assertNotNull(customUser);
	}

	@Test
	void should_return_getters() {
		Set<Role> roles = new HashSet<Role>();
		CustomUser actual = new CustomUser("pwd", "usr", "firstname", "lastname", roles, true);
		Assertions.assertAll(() -> assertEquals("pwd", actual.getPassword()),
				() -> assertEquals("usr", actual.getUsername()), () -> assertEquals("firstname", actual.getFirstname()),
				() -> assertEquals("lastname", actual.getLastname()), () -> assertEquals(roles, actual.getRoles()),
				() -> assertEquals(false, actual.isAccountNonExpired()),
				() -> assertEquals(false, actual.isAccountNonLocked()),
				() -> assertEquals(false, actual.isCredentialsNonExpired()),
				() -> assertEquals(true, actual.isEnabled()));
	}

	@Test
	void should_return_toString() {
		Set<Role> roles = new HashSet<Role>();
		CustomUser actual = new CustomUser("pwd", "usr", "firstname", "lastname", roles, true);
		String expected = "{id=" + null + ", username=usr" + ", password=[PROTECTED], roles=[], enabled=true"
				+ ", accountNonExpired=false" + ", accountNonLocked=false" + ", credentialsNonExpired=false"
				+ ", firstname=firstname, lastname=lastname}";
		assertEquals(expected, actual.toString());
	}
}
