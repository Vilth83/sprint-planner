package fr.vilth.sprintplanner.api.services.implementation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.vilth.sprintplanner.api.controllers.MemberViewDto;
import fr.vilth.sprintplanner.api.repositories.MemberRepository;
import fr.vilth.sprintplanner.api.services.MemberService;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.MemberCreateDto;
import fr.vilth.sprintplanner.domain.dtos.MemberUpdateDto;
import fr.vilth.sprintplanner.domain.entities.Member;

/**
 * Default concrete implementation of {@code MemberService}.
 * 
 * @author Thierry VILLEPREUX
 */
@Service
public class MemberServiceImpl implements MemberService {

	private final ModelMapper modelMapper;

	private final MemberRepository memberRepository;

	/**
	 * Protected constructor to autowire needed bean.
	 * <p>
	 * injects {@code MemberRepository} interface to persist {@code Member} and
	 * {@code ModelMapper} to ease mapping between DTO and entities.
	 * 
	 * @param modelMapper      the injected {@code ModelMapper}
	 * @param memberRepository the injected {@code MemberRepository}
	 */
	protected MemberServiceImpl(ModelMapper modelMapper, MemberRepository memberRepository) {
		super();
		this.modelMapper = modelMapper;
		this.memberRepository = memberRepository;
	}

	@Override
	public EntityIdDto save(MemberCreateDto member) {
		Member entityToPersist = modelMapper.map(member, Member.class);
		Member persistedEntity = memberRepository.save(entityToPersist);
		return modelMapper.map(persistedEntity, EntityIdDto.class);
	}

	@Override
	public Set<MemberViewDto> findAll() {
		List<Member> members = memberRepository.findAll();
		return members.stream().map(member -> modelMapper.map(member, MemberViewDto.class)).collect(Collectors.toSet());
	}

	@Override
	public void update(MemberUpdateDto member) {
		Member entityToPersist = modelMapper.map(member, Member.class);
		memberRepository.save(entityToPersist);
	}

	@Override
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

	// Utility Classes
	@Override
	public boolean existsByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

}
