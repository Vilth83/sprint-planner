package fr.vilth.sprintplanner.domain.dtos;

/**
 * A DTO representing the {@code id} of an entity.
 * 
 * @author Thierry VILLEPREUX
 */
public class EntityIdDto {

    /**
     * The id of the given entity.
     */
    private Long id;

    /**
     * Default empty no-arg constructor
     */
    protected EntityIdDto() {
	//
    }

    @Override
    public String toString() {
	return "{id=" + id + "}";
    }

    /**
     * Returns the id of given {@code EntityIdDto}
     * 
     * @return an id
     */
    public Long getId() {
	return id;
    }
}
