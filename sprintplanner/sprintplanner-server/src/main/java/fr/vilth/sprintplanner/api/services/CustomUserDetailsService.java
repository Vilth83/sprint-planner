package fr.vilth.sprintplanner.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;

public interface CustomUserDetailsService extends UserDetailsService {

    CustomUserInfoDto getCurrentUserInfo(Long id);
}
