package jsonb.model;

import com.google.gson.Gson;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;

@Converter
public class DataConverter implements AttributeConverter<Map<String, Object>, byte[]> {
    private static final Gson GSON = new Gson();

    @Override
    public byte[] convertToDatabaseColumn(final Map<String, Object> attribute) {
        if (attribute == null) {
            return null;
        }

        return GSON.toJson(attribute).getBytes();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> convertToEntityAttribute(final byte[] dbData) {
        if (dbData == null || dbData.length == 0) {
            return null;
        }

        return GSON.fromJson(new String(dbData), Map.class);
    }
}

