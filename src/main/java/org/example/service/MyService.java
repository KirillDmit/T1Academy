package org.example.service;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String someMethod() {
        System.out.println("Executing someMethod...");
        return "Hello from someMethod";
    }

    public void throwingMethod() throws Exception {
        System.out.println("Executing throwingMethod...");
        throw new Exception("Test exception");
    }
}

