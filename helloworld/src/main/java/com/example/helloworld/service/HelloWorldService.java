package com.example.helloworld.service;

import com.example.helloworld.exception.InvalidInputException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class HelloWorldService {

    public String createMessage(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException();
        }

        String givenName = name.trim();
        char firstLetter = Character.toUpperCase(givenName.charAt(0));

        if (firstLetter < 'A' || firstLetter > 'M') {
            throw new InvalidInputException();
        }

        givenName = givenName.toLowerCase(Locale.ROOT);

        String formattedName = Character.toUpperCase(givenName.charAt(0))
                        + givenName.substring(1);

        return "Hello " + formattedName;
    }
}
