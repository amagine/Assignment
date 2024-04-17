package at.rewe.assignment.utility;

import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class ListStringConverter implements AttributeConverter<List<String>, String> {

    private final String delimiter = ",";

    @Override
    public String convertToDatabaseColumn(List<String> value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        return String.join(delimiter, value);
    }

    @Override
    public List<String> convertToEntityAttribute(String value) {
        return value != null ? Arrays.asList(value.split(delimiter)) : emptyList();
    }
}
