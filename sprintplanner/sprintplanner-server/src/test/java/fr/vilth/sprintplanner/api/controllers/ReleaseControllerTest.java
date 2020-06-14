package fr.vilth.sprintplanner.api.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import fr.vilth.sprintplanner.SetupIntTest;
import fr.vilth.sprintplanner.domain.dtos.EntityIdDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseCreateDto;
import fr.vilth.sprintplanner.domain.dtos.release.ReleaseViewDto;

/**
 * Integration test of ReleaseController
 * 
 * @author Thierry VILLEPREUX
 */
public class ReleaseControllerTest extends SetupIntTest {

    @Autowired
    private ReleaseController controller;

    @Test
    @WithMockUser(username = "user", password = "pwd", roles = "USER")
    void should_return_all_releases() {
	List<ReleaseViewDto> releases = controller.findAllOrdered();
	assertEquals(3, releases.size());
    }

    @Test
    @WithMockUser(username = "user", password = "pwd", roles = "USER")
    void should_return_last_release() {
	ReleaseViewDto release = controller.findLastRelease();
	Assertions.assertAll(() -> assertEquals(2, release.getWeek()),
		() -> assertEquals(1, release.getSprint()),
		() -> assertEquals(2, release.getPi()));
    }

    @Test
    @WithMockUser(username = "user", password = "pwd", roles = "USER")
    void should_increment_release() {
	List<ReleaseViewDto> releases = controller.findAllOrdered();
	controller.incrementReleaseVersion();
	List<ReleaseViewDto> actual = controller.findAllOrdered();
	assertNotEquals(releases.size(), actual.size());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/releaseCreation.csv", delimiter = ';')
    @WithMockUser(username = "user", password = "pwd", roles = "USER")
    void should_save_release(String json) {
	ReleaseCreateDto release = jsonConvert(json, ReleaseCreateDto.class);
	EntityIdDto tested = controller.save(release);
	assertNotNull(tested);
    }
}
