package br.com.autorevise.mecanicagestor.api.web.handler;

import br.com.msoficinas.api.services.exceptions.ObjetoJaRegistradoException;
import br.com.msoficinas.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.msoficinas.api.web.response.ErroResponse;
import br.com.msoficinas.api.web.response.ErroValidacaoResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> error400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(field -> new ErroValidacaoResponse(field.getField(), field.getDefaultMessage())).toList());
    }

    @ExceptionHandler({ObjetoNaoEncontradoException.class, ObjetoJaRegistradoException.class, UsernameNotFoundException.class})
    public ResponseEntity<?> errosCustomizado(Exception exception, HttpServletRequest request) {
        var error = new ErroResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), request.getServletPath());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({HttpClientErrorException.class, HttpMessageNotReadableException.class, NullPointerException.class, IllegalArgumentException.class })
    public ResponseEntity<?> clientCustomException(Exception exception, HttpServletRequest request) {
        var error = new ErroResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), request.getServletPath());
        return ResponseEntity.badRequest().body(error);
    }
}

