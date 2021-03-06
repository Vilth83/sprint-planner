package fr.vilth.sprintplanner.domain.dtos.release;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import fr.vilth.sprintplanner.SetupUnitTest;

/**
 * Unit Test pof ReleaseViewDto.
 * 
 * @author Thierry VILLEPREUX
 */
public class ReleaseViewDtoTest extends SetupUnitTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/releaseViewDto.csv", delimiter = ';')
    void should_return_toString(String json) {
	ReleaseViewDto actual = jsonConvert(json, ReleaseViewDto.class);
	String expected = "{id=1, pi=1, sprint=1, week=1, releaser=test}";
	assertEquals(expected, actual.toString());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/releaseViewDto.csv", delimiter = ';')
    void should_return_release_version(String json) {
	ReleaseViewDto actual = jsonConvert(json, ReleaseViewDto.class);
	String expected = "v1.1.1";
	assertEquals(expected, actual.getReleaseVersion());
    }
}
