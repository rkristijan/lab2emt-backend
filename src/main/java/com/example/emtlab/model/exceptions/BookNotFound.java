package com.example.emtlab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFound extends RuntimeException{

    public BookNotFound(Long id) {
        super(String.format("Book with id: %d was not found",id));
    }

    public BookNotFound(String name){
        super(String.format("Book with name %s was not found",name));
    }
}
