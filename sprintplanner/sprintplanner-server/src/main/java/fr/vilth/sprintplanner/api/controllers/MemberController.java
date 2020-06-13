package fr.vilth.sprintplanner.api.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
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
import fr.vilth.sprintplanner.domain.dtos.member.MemberDeleteDto;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EntityIdDto save(@Valid @RequestBody MemberCreateDto member) {
	return memberService.save(member);
    }

    /**
     * Returns a {@code Set} of {@code MemberViewDto}.
     * <p>
     * As Members are defined with unique email, there can be no duplicates,
     * therefore the {@code Collection} type can be a {@code Set}.
     * 
     * @return {@code Set} of {@code Member}
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Set<MemberViewDto> findAll() {
	return memberService.findAll();
    }

    /**
     * Returns a {@code Set} of {@code MemberViewDto} who are not
     * {@code candidates} for given {@code Task}.
     * <p>
     * a {@code Member} is considered not {@code Candidate} for a given
     * {@code Task} if :
     * <ul>
     * <li>He is not present in {@code Candidate} table.
     * <li>He is present in {@code Candidate} table for another {@code Task} but
     * not for the given one.
     * </ul>
     * 
     * @param task the given {@code Task} name.
     * @return {@code Set} of {@code Member}
     */
    @GetMapping("/{task}/nonCandidates")
    public Set<MemberViewDto> findAllNonCandidates(@PathVariable String task) {
	return memberService.findAllNonCandidatesByTask(task);
    }

    /**
     * Perists a {@code MemberUpdateDto}.
     * <p>
     * the given {@code MemberUpdateDto} id must match an existing
     * {@code Member} in database.
     * 
     * @param member the {@code MemberUpdateDto} to update
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@Valid @RequestBody MemberUpdateDto member) {
	memberService.update(member);
    }

    /**
     * Delete a {@code MemberDeleteDto} by his id.
     * <p>
     * a member cannot be deleted if he is candidate. Hence
     * {@code MemberDeleteDto} is validated.
     * 
     * @param member the given identifier of the {@code Member} encapsulated in
     *        a {@code MemberDeleteDto}.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@Valid @RequestBody MemberDeleteDto member) {
	memberService.delete(member);
    }
}
