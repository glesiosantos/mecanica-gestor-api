package br.com.autorevise.mecanicagestor.api.utils.conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class UpperCaseConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String atributo) {
        return atributo != null ? atributo.toUpperCase() : null;
    }

    @Override
    public String convertToEntityAttribute(String data) {
        return data != null ? data.toUpperCase() : null;
    }
}
