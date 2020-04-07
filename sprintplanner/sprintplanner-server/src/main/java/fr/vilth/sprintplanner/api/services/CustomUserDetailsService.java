package fr.vilth.sprintplanner.api.services;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserCreateDto;
import fr.vilth.sprintplanner.domain.dtos.custom_user.CustomUserInfoDto;

public interface CustomUserDetailsService extends UserDetailsService {

    CustomUserInfoDto getCurrentUserInfo(Long id);

    boolean usernameIsUnique(String username);

    void create(@Valid CustomUserCreateDto inputs);
}
