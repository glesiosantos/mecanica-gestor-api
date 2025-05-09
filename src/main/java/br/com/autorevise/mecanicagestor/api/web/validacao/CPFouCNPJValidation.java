package br.com.autorevise.mecanicagestor.api.web.validacao;

import br.com.autorevise.mecanicagestor.api.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class CPFouCNPJValidation implements ConstraintValidator<CPFouCNPJ, String> {

    @Override
    public void initialize(CPFouCNPJ constraintAnnotation) {}

    @Override
    public boolean isValid(String documento, ConstraintValidatorContext context) {
        if(!StringUtils.hasText(documento)) return false;

        var cpfOuCnpj = documento.replaceAll("[^0-9]", "");

        return BR.isValidCPF(cpfOuCnpj) || BR.isValidCNPJ(cpfOuCnpj);
    }
}
