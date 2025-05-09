package br.com.autorevise.mecanicagestor.api.utils.conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class CPFConversor implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String atributo) {
        return atributo != null ? padraoCpf(atributo) : null;
    }

    @Override
    public String convertToEntityAttribute(String atributo) {
        return atributo != null ? padraoCpf(atributo) : null;
    }

    public String padraoCpf(String cpf) {
        return cpf.replace(".","").replace("-", "").trim();
    }
}
