package at.rewe.assignment.utility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListStringConverterTest {

    @Autowired
    private ListStringConverter converter;

    @Test
    void testConvertToDatabaseColumn() {
        List<String> inputList = Arrays.asList("value1", "value2", "value3");
        String expectedString = "value1,value2,value3";
        String result = converter.convertToDatabaseColumn(inputList);
        assertEquals(expectedString, result);
    }

    @Test
    void testConvertToDatabaseColumnWithEmptyList() {
        List<String> inputList = List.of();
        assertNull(converter.convertToDatabaseColumn(inputList));
    }

    @Test
    void testConvertToEntityAttribute() {
        String inputString = "value1,value2,value3";
        List<String> expectedList = Arrays.asList("value1", "value2", "value3");
        List<String> result = converter.convertToEntityAttribute(inputString);
        assertEquals(expectedList, result);
    }

    @Test
    void testConvertToEntityAttributeWithNull() {
        List<String> expectedList = List.of();
        List<String> result = converter.convertToEntityAttribute(null);
        assertEquals(expectedList, result);
    }

    @Test
    void testConvertToEntityAttributeWithEmptyString() {
        List<String> expectedList = List.of();
        List<String> result = converter.convertToEntityAttribute("");
        assertEquals(expectedList, result);
    }
}