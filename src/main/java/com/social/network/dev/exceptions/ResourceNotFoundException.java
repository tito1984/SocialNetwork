package com.social.network.dev.exceptions;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String nameOfTheRecurse;
    private String nameOfField;
    private long valueOfField;

    public ResourceNotFoundException(String nameOfTheRecurse, String nameOfField, long valueOfField) {
        super(String.format("%s not founded with : %s : '%s'", nameOfTheRecurse, nameOfField, valueOfField));
        this.nameOfTheRecurse = nameOfTheRecurse;
        this.nameOfField = nameOfField;
        this.valueOfField = valueOfField;
    }
}
