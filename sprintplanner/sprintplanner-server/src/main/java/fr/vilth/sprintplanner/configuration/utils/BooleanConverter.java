package fr.vilth.sprintplanner.configuration.utils;

import javax.persistence.AttributeConverter;

/**
 * A custom converter to convert entity {@code Boolean} attribute state into
 * database column representationand back again.
 * <p>
 * This implementation converts {@code Boolean} from/to {@code String}s such as:
 * <ul>
 * <li>{@code Boolean.TRUE} from/to {@code "T"}
 * <li>{@code Boolean.FALSE} from/to {@code "F"}
 * <li>
 * </ul>
 */
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {
	if (Boolean.TRUE.equals(value)) {
	    return "T";
	}
	return "F";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
	return "T".equals(value);
    }
}
