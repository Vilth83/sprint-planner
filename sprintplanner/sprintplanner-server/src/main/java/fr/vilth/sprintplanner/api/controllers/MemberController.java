package fr.vilth.sprintplanner.api.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.MemberService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.MemberCreateDto;

/**
 * a {@code RestController} to handle {@code Member}.
 * 
 * @author Thierry VILLEPREUX
 *
 */
@RestController
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	/**
	 * Protected constructor to autowire needed bean.
	 * <p>
	 * injects {@code MemberService} interface
	 * 
	 * @param memberService the injected {@code MemberService}.
	 */
	protected MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * Persists a {@code MemberCreateDto}.
	 *
	 * @param member the {@code MemberCreateDto} to persist.
	 * @return the attributed id encapsulated in a {@code EntityIdDto}.
	 */
	@PostMapping
	public EntityIdDto save(@Valid @RequestBody MemberCreateDto member) {
		return memberService.save(member);
	}

	/**
	 * Returns a {@code Set} of {@code MemberViewDto}.
	 * <p>
	 * As Members are defined with unique email, there can be no duplicates,
	 * therefore the {@code Collection} type can be a {@code Set}.
	 * 
	 * @return {@code List} of {@code Member}
	 */

	@GetMapping
	public Set<MemberViewDto> findAll() {
		return memberService.findAll();
	}
}
