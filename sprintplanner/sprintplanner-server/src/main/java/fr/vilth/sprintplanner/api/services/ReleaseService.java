package fr.vilth.sprintplanner.api.services;

import java.util.List;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;

/**
 * Service to handle {@code Release}
 * 
 * @author Thierry VILLEPREUX
 *
 */
public interface ReleaseService {

	/**
	 * Retrieves all {@code release} ordered by PI, sprint and week descending.
	 * 
	 * @return a {@code List} of {@code Release}
	 */
	List<ReleaseViewDto> findAllOrdered();

	/**
	 * Finds last {@code Release}.
	 * <p>
	 * Last release is defined by ordering by PI, sprint and week descending and
	 * taking the first result.
	 * 
	 * @return the last {@code Release}
	 */

	ReleaseViewDto findLastRelease();

	/**
	 * Persist given {@code ReleaseCreateDto} representing a {@code Release}
	 * 
	 * @param inputs the given {@code ReleaseCreateDto}
	 * @return an {@code EntityIdDto} representing the id of the persisted
	 *         {@code Release}
	 */
	EntityIdDto save(ReleaseCreateDto inputs);

	/**
	 * Increments the {@code Release} version, persisting a new {@code Release}.
	 */
	void incrementReleaseVersion();
}
