package fr.vilth.sprintplanner.api;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.CustomUserJpaRepository;
import fr.vilth.sprintplanner.api.services.CustomUserDetailsService;
import fr.vilth.sprintplanner.commons.exceptions.ResourceNotFoundException;
import fr.vilth.sprintplanner.commons.security.CustomUserDetails;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserAuthDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final CustomUserJpaRepository repo;

    protected CustomUserDetailsServiceImpl(CustomUserJpaRepository repo) {
	this.repo = repo;
    }

    // Throws UsernameNotFoundException (Spring contract)
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	CustomUserAuthDto user = repo.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException(
			"no user found with username: " + username));
	return new CustomUserDetails(user);
    }

    // Throws ResourceNotFoundException (restful practice)
    @Override
    public CustomUserInfoDto getCurrentUserInfo(Long id) {
	return repo.getById(id).orElseThrow(
		() -> new ResourceNotFoundException("with id:" + id));
    }
}
