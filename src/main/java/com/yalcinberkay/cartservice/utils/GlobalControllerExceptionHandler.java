package com.yalcinberkay.cartservice.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.yalcinberkay.cartservice.exceptions.BaseException;
import com.yalcinberkay.cartservice.exceptions.BusinessException;
import com.yalcinberkay.cartservice.models.DTOs.ErrorDTO;
import com.yalcinberkay.cartservice.models.DTOs.ErrorDetailDTO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    private static final Locale TR = new Locale("tr");

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException exception) {
        LOGGER.warn("Cart API: ", exception);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("Cart API");
        errorDTO.addError(getErrorDetailDTO(exception));
        LOGGER.warn("Cart API Caused By:{}", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        LOGGER.warn("MethodArgumentNotValidException: ", methodArgumentNotValidException);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("MethodArgumentNotValidException");
        prepareBindingResult(methodArgumentNotValidException.getBindingResult(), errorDTO);
        LOGGER.warn("Field validation failed. Caused By:{}", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        LOGGER.warn("IllegalArgumentException: ", illegalArgumentException);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("IllegalArgumentException");
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setKey("cart.api.illegalArgumentException");
        errorDetailDTO.setMessage(illegalArgumentException.getMessage());
        errorDTO.getErrors().add(errorDetailDTO);
        LOGGER.warn("IllegalArgumentException Caused By:{}", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException exception) {
        LOGGER.warn("EntityNotFoundException: ", exception);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("EntityNotFoundException");
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setKey("cart.api.entityNotFoundException");
        errorDetailDTO.setMessage(exception.getMessage());
        errorDTO.getErrors().add(errorDetailDTO);
        LOGGER.warn("EntityNotFoundException Caused By:{}", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException exception) {
        LOGGER.warn("HibernateConstraintViolationException: ", exception);
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setKey(String.format("constraint.violation.%s", exception.getConstraintName()));
        errorDetailDTO.setMessage(getMessage(errorDetailDTO.getKey(), null, exception.getMessage()));
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("HibernateConstraintViolationException");
        errorDTO.setErrors(List.of(errorDetailDTO));
        LOGGER.warn("Constraint validation failed. Caused By:{}", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorDTO> handleInvalidFormatException(InvalidFormatException exception) {
        LOGGER.warn("InvalidFormatException: ", exception);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("InvalidFormatException");
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setKey("cart.api.invalidFormatException");
        errorDetailDTO.setMessage(exception.getMessage());
        errorDTO.getErrors().add(errorDetailDTO);
        LOGGER.error("InvalidFormatException Caused By:{}", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorDTO> handleJsonParseException(JsonParseException exception) {
        LOGGER.warn("JsonParseException: ", exception);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException("JsonParseException");
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setKey("cart.api.jsonParseException");
        errorDetailDTO.setMessage(exception.getMessage());
        errorDTO.getErrors().add(errorDetailDTO);
        LOGGER.error("JsonParseException Caused By:{}", errorDTO);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    private ErrorDetailDTO getErrorDetailDTO(BaseException exception) {
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        errorDetailDTO.setKey(exception.getKey());
        errorDetailDTO.setMessage(getMessage(exception.getKey(), exception.getArgs(), exception.getMessage()));
        errorDetailDTO.setArgs(exception.getArgs());
        return errorDetailDTO;
    }

    private String getMessage(String key, Object[] args, String defaultMessage) {
        return Optional.of(getMessage(() -> messageSource.getMessage(key, args, TR), defaultMessage))
                .filter(StringUtils::isNotBlank)
                .orElse(defaultMessage);
    }

    private String getMessage(Supplier<String> supplier, String defaultMessage) {
        String message = StringUtils.EMPTY;
        try {
            message = supplier.get();
        } catch (Exception exception) {
            if (Objects.isNull(defaultMessage)) {
                LOGGER.error("MessageResource Not found.", exception);
            }
        }
        return message;
    }

    private void prepareBindingResult(BindingResult bindingResult, ErrorDTO errorDTO) {
        bindingResult.getFieldErrors().forEach(i -> {
            ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
            errorDetailDTO.setMessage(getMessage(i.getDefaultMessage(), i.getArguments(), StringUtils.EMPTY));
            errorDetailDTO.setKey(i.getDefaultMessage());
            errorDTO.addError(errorDetailDTO);
        });
    }
}