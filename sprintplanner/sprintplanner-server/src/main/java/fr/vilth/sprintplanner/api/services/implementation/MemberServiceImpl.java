package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.repositories.MemberJpaRepository;
import fr.vilth.sprintplanner.api.services.MemberService;
import fr.vilth.sprintplanner.commons.api.AbstractService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberCreateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberDeleteDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberUpdateDto;
import fr.vilth.sprintplanner.domain.dtos.member.MemberViewDto;
import fr.vilth.sprintplanner.domain.entities.Member;

/**
 * Default concrete implementation of {@code MemberService}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class MemberServiceImpl extends AbstractService
	implements MemberService {

    private final MemberJpaRepository memberRepository;

    /**
     * Protected constructor to autowire needed bean.
     * <p>
     * injects {@code MemberRepository} interface to persist {@code Member}.
     * </p>
     * 
     * @param memberRepository the injected {@code MemberRepository}
     */
    protected MemberServiceImpl(MemberJpaRepository memberRepository) {
	this.memberRepository = memberRepository;
    }

    @Override
    public EntityIdDto save(MemberCreateDto member) {
	Member entityToPersist = convert(member, Member.class);
	Member persistedEntity = memberRepository.save(entityToPersist);
	return convert(persistedEntity, EntityIdDto.class);
    }

    @Override
    public Set<MemberViewDto> findAll() {
	List<Member> members = memberRepository.findAll();
	return convertToSet(members, MemberViewDto.class);
    }

    @Override
    public void update(MemberUpdateDto member) {
	Member entityToPersist = convert(member, Member.class);
	memberRepository.save(entityToPersist);
    }

    @Override
    public void delete(MemberDeleteDto member) {
	Member deleted = convert(member, Member.class);
	memberRepository.delete(deleted);
    }

    // Utility Classes
    @Override
    public boolean existsByEmail(String email) {
	return memberRepository.existsByEmail(email);
    }

    @Override
    public Set<MemberViewDto> findAllNonCandidatesByTask(String task) {
	List<Member> members = memberRepository
		.findAllNonCandidatesByTask(task);
	return convertToSet(members, MemberViewDto.class);
    }
}
