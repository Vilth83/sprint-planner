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
 */
public class CustomUserTest extends SetupUnitTest {

    private static final String LASTNAME = "lastname";

    private static final String FIRSTNAME = "firstname";

    private static final String USER = "usr";

    private static final String PASSWORD = "pwd";

    @Test
    void should_construct() {
	CustomUser customUser = new CustomUser(PASSWORD, USER, FIRSTNAME,
		LASTNAME, new HashSet<Role>());
	assertNotNull(customUser);
    }

    @Test
    void should_construct_enabled_user() {
	CustomUser customUser = new CustomUser(PASSWORD, USER, FIRSTNAME,
		LASTNAME, new HashSet<Role>(), true);
	assertNotNull(customUser);
    }

    @Test
    void should_return_getters() {
	Set<Role> roles = new HashSet<>();
	CustomUser actual = new CustomUser(PASSWORD, USER, FIRSTNAME, LASTNAME,
		roles, true);
	Assertions.assertAll(() -> assertEquals(PASSWORD, actual.getPassword()),
		() -> assertEquals(USER, actual.getUsername()),
		() -> assertEquals(FIRSTNAME, actual.getFirstname()),
		() -> assertEquals(LASTNAME, actual.getLastname()),
		() -> assertEquals(roles, actual.getRoles()),
		() -> assertEquals(false, actual.isAccountNonExpired()),
		() -> assertEquals(false, actual.isAccountNonLocked()),
		() -> assertEquals(false, actual.isCredentialsNonExpired()),
		() -> assertEquals(true, actual.isEnabled()));
    }

    @Test
    void should_return_toString() {
	Set<Role> roles = new HashSet<>();
	CustomUser actual = new CustomUser(PASSWORD, USER, FIRSTNAME, LASTNAME,
		roles, true);
	String expected = "{id=" + null + ", username=usr"
		+ ", password=[PROTECTED], roles=[], enabled=true"
		+ ", accountNonExpired=false" + ", accountNonLocked=false"
		+ ", credentialsNonExpired=false"
		+ ", firstname=firstname, lastname=lastname}";
	assertEquals(expected, actual.toString());
    }
}
