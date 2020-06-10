package fr.vilth.sprintplanner.api.services;

import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectCreateDto;
import fr.vilth.sprintplanner.domain.dtos.project.ProjectViewDto;

/**
 * Service to handle {@code Projet}
 * 
 * @author Thierry VILLEPREUX
 *
 */
public interface ProjectService {

	/**
	 * Persists given {@code Project}.
	 * 
	 * @param inputs
	 * @return an {@code EntityIdDto} encapsulating the identifier of the persisted
	 *         {@code Project}
	 */
	EntityIdDto save(ProjectCreateDto inputs);

	/**
	 * Returns the project informations.
	 * <p>
	 * As the application only deals with one project for the time being, no id is
	 * needed to retrieve the project. In a future release, if this behavior has to
	 * change, this method will change to take the trigram of the needed project.
	 * 
	 * @return a {@code ProjectViewDto} representing the {@code Project}
	 */
	ProjectViewDto getProject();
}
