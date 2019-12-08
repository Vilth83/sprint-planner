package fr.vilth.sprintplanner.api.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vilth.sprintplanner.api.services.MemberService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberCreateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberViewDto;

/**
 * a {@code RestController} to handle {@code Member}.
 * 
 * @author Thierry VILLEPREUX
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

    /**
     * Perists a {@code MemberUpdateDto}.
     * <p>
     * the given {@code MemberUpdateDto} id must match an existing
     * {@code Member} in database.
     * 
     * @param member
     */
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody MemberUpdateDto member) {
	memberService.update(member);
    }

    /**
     * Delete a {@code Member} by id.
     * 
     * @param id the identifier of the {@code Member} to be deleted.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
	memberService.delete(id);
    }
}
