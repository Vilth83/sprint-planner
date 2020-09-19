package fr.vilth.sprintplanner.api.services.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.CustomUserJpaRepository;
import fr.vilth.sprintplanner.api.repositories.RoleJpaRepository;
import fr.vilth.sprintplanner.api.services.CustomUserDetailsService;
import fr.vilth.sprintplanner.configuration.api.AbstractService;
import fr.vilth.sprintplanner.configuration.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserAuthDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserManagementDto;
import fr.vilth.sprintplanner.domain.entities.CustomUser;
import fr.vilth.sprintplanner.domain.entities.Role;
import fr.vilth.sprintplanner.security.CustomUserDetails;

/**
 * Implementation of {@code CustomUserDetailsService}
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class CustomUserDetailsServiceImpl extends AbstractService
	implements CustomUserDetailsService {

    private final CustomUserJpaRepository userRepository;

    private final RoleJpaRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    protected CustomUserDetailsServiceImpl(
	    CustomUserJpaRepository userRepository,
	    RoleJpaRepository roleRepository,
	    PasswordEncoder passwordEncoder) {
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
    }

    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	CustomUserAuthDto user = userRepository.findByUsername(username)
		.map(customUser -> convert(customUser, CustomUserAuthDto.class))
		.orElseThrow(() -> new UsernameNotFoundException(
			"no user found with username: " + username));
	return new CustomUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public CustomUserInfoDto getCurrentUserInfo(Long id) {
	return userRepository.getById(id)
		.map(customUser -> convert(customUser, CustomUserInfoDto.class))
		.orElseThrow(
			() -> new ResourceNotFoundException("with id:" + id));
    }

    @Override
    public void create(CustomUserCreateDto dto) {
	String encoded = passwordEncoder.encode(dto.getPassword());
	Role role = roleRepository.findByDefaultRoleTrue();
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	dto.setPassword(encoded);
	CustomUser customUser = new CustomUser(encoded, dto.getUsername(),
		dto.getFirstname(), dto.getLastname(), roles);
	userRepository.save(customUser);
    }

    @Override
    public boolean usernameIsUnique(String username) {
	return !userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public List<CustomUserManagementDto> findAll() {
	List<CustomUser> users = userRepository.findAll();
	return users.stream().map(this::enrichUser)
		.collect(Collectors.toList());
    }

    private CustomUserManagementDto enrichUser(CustomUser user) {
	CustomUserManagementDto dto = convert(user,
		CustomUserManagementDto.class);
	dto.setActivated(user.isAccountNonExpired() && user.isAccountNonLocked()
		&& user.isCredentialsNonExpired() && user.isEnabled());
	dto.setRole(user.getRoles().stream().findFirst()
		.orElse(roleRepository.findByDefaultRoleTrue()));
	return dto;
    }

    @Override
    public void toggleAccountActivation(Long id,
	    CustomUserManagementDto inputs) {
	Optional<CustomUser> customUser = userRepository.findById(id);
	customUser.ifPresent(enrichAndSave(inputs));
    }

    private Consumer<CustomUser> enrichAndSave(
	    CustomUserManagementDto inputs) {
	return user -> {
	    user.setAccountNonExpired(!inputs.isActivated());
	    user.setAccountNonLocked(!inputs.isActivated());
	    user.setEnabled(!inputs.isActivated());
	    user.setCredentialsNonExpired(!inputs.isActivated());
	    userRepository.save(user);
	};
    }

    @Override
    public void toggleAdminRole(Long id, CustomUserManagementDto inputs) {
	Optional<CustomUser> customUser = userRepository.findById(id);
	customUser.ifPresent(user -> {
	    if (user.getRoles().stream()
		    .anyMatch(role -> role.getCode().equals("ROLE_ADMIN"))) {
		changeRole(user, "ROLE_USER");
	    } else {
		changeRole(user, "ROLE_ADMIN");
	    }
	    userRepository.save(user);
	});
    }

    private void changeRole(CustomUser user, String code) {
	Role role = roleRepository.findByCode(code);
	Set<Role> roles = new HashSet<>();
	roles.add(role);
	user.setRoles(roles);
    }
}
