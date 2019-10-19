package fr.vilth.sprintplanner.commons.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class representing the identifier of an {@code Entity}.
 * <p>
 * Provides primary key to each {@code Entity} that extends it.
 * 
 * @author Thierry VILLEPREUX
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Returns the identifier of an {@code Entity} extending
     * {@linkplain AbstractEntity}.
     * 
     * @return an identifier
     */
    protected final Long getId() {
	return id;
    }

    @Override
    public String toString() {
	return "id=" + id;
    }
}
