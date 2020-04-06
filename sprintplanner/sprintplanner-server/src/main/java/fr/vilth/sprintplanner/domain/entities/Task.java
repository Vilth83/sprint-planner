package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;

/**
 * Representation of a {@code Task}.
 * <p>
 * A task is an action fullfilled by a {@code Candidate} at a certain time.
 * 
 * @author Thierry VILLEPREUX
 */
@Entity
@Table(name = "tasks", indexes = @Index(name = "tasks_member_id_IDX", columnList = "manager_id"))
public class Task extends AbstractEntity {

    private static final long serialVersionUID = -3742414978040482584L;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = true, length = 255)
    private String description;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "tasks_member_id_FK"))
    private Member manager;

    protected Task() {
	//
    }
}
