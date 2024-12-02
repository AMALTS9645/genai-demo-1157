```java
//code-start
package com.example.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class, args);
    }
}

// Controller
package com.example.loginapi.controller;

import com.example.loginapi.model.LoginRequest;
import com.example.loginapi.model.LoginResponse;
import com.example.loginapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Endpoint for user login.
     *
     * @param loginRequest the login request containing username and password
     * @return a response entity with login response
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.authenticateUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}

// Service
package com.example.loginapi.service;

import com.example.loginapi.model.LoginRequest;
import com.example.loginapi.model.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    /**
     * Authenticates the user based on the provided login request.
     *
     * @param loginRequest the login request containing username and password
     * @return the login response with authentication result
     */
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        // Validate user input (Security)
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new RuntimeException("Invalid input");
        }

        // Perform authentication (Dummy implementation)
        if ("user".equals(loginRequest.getUsername()) && "pass".equals(loginRequest.getPassword())) {
            return new LoginResponse("Success", "Authentication successful");
        } else {
            return new LoginResponse("Failure", "Authentication failed");
        }
    }
}

// Model
package com.example.loginapi.model;

public class LoginRequest {

    private String username;
    private String password;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.loginapi.model;

public class LoginResponse {

    private String status;
    private String message;

    public LoginResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

// Application Properties
// Ensure sensitive information is not hardcoded and managed securely. (Security)
application.properties:
server.port=8080

// Maven Dependencies (pom.xml)
<!-- Spring Boot Dependencies -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
//code-end
```