package fr.vilth.sprintplanner.domain.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.vilth.sprintplanner.commons.entities.AbstractEntity;
import fr.vilth.sprintplanner.commons.utils.BooleanConverter;

@Entity
@Table(name = "jobs")
public class Job extends AbstractEntity {

    private static final long serialVersionUID = 3578977239388137220L;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false, length = 10)
    private String task;

    @Column
    @Convert(converter = BooleanConverter.class)
    private boolean active;

    public boolean isActive() {
	return active;
    }
}
