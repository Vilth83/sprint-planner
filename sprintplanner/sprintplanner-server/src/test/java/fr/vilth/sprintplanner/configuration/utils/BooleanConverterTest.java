package fr.vilth.sprintplanner.configuration.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BooleanConverterTest {

    @Test
    void should_convert_true_boolean() {
	BooleanConverter tested = new BooleanConverter();
	String expected = tested.convertToDatabaseColumn(true);
	Assertions.assertEquals(expected, "T");
    }

    @Test
    void should_convert_false_boolean() {
	BooleanConverter tested = new BooleanConverter();
	String expected = tested.convertToDatabaseColumn(false);
	Assertions.assertEquals(expected, "F");
    }

    @Test
    void should_convert_T_string() {
	BooleanConverter tested = new BooleanConverter();
	boolean expected = tested.convertToEntityAttribute("T");
	Assertions.assertTrue(expected);
    }

    @Test
    void should_convert_F_string() {
	BooleanConverter tested = new BooleanConverter();
	boolean expected = tested.convertToEntityAttribute("F");
	Assertions.assertFalse(expected);
    }

    @Test
    void should_convert_wrong_input_string() {
	BooleanConverter tested = new BooleanConverter();
	boolean expected = tested.convertToEntityAttribute("WRONG INPUT");
	Assertions.assertFalse(expected);
    }
}
