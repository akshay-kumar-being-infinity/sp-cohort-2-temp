package com.being_infinity.sp_beginner.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.being_infinity.sp_beginner.models.User;
import com.being_infinity.sp_beginner.services.UserService;

/**
 * CONTROLLER = Entry Point / Traffic Controller
 *
 * This class defines ALL the API endpoints (URLs) for User operations.
 * Its ONLY job is to:
 *   1. Receive the HTTP request
 *   2. Call the appropriate Service method
 *   3. Return the HTTP response
 *
 * It does NOT contain any business logic — that lives in UserService.
 *
 * @RestController → Combination of @Controller + @ResponseBody
 *                   Tells Spring: "This class handles REST API requests
 *                   and return values are automatically converted to JSON"
 *
 * @RequestMapping("/api/users") → All endpoints in this class start with /api/users
 *                                  This is the "base path" for all User APIs
 *
 * Dependency Injection:
 *   Spring automatically creates UserService and "injects" it here via constructor.
 *   We don't write: UserService userService = new UserService();
 *   Spring handles the lifecycle — this is called Inversion of Control (IoC).
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor Injection — Spring automatically passes UserService here
    UserController(UserService userService) {
        this.userService = userService;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // GET /api/users
    // Returns a list of ALL users as JSON array
    // ─────────────────────────────────────────────────────────────────────────
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // GET /api/users/random
    // Returns ONE random user from our in-memory list
    // IMPORTANT: This must be declared BEFORE /{id} to avoid route conflicts
    // ─────────────────────────────────────────────────────────────────────────
    @GetMapping("/random")
    public ResponseEntity<User> getRandomUser() {
        User user = userService.getRandomUser();
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // GET /api/users/{id}
    // @PathVariable → extracts the {id} value from the URL
    // Example: GET /api/users/1 → id = 1
    // Returns 200 OK with user if found, or 404 Not Found if not
    // ─────────────────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // POST /api/users
    // @RequestBody → converts the incoming JSON body into a User object
    // The client sends: { "name": "Alice", "gender": "Female", "image": "/images/alice.png" }
    // The id is NOT sent by the client — our service assigns it automatically
    // Returns the newly created user (with the assigned id)
    // ─────────────────────────────────────────────────────────────────────────
    @PostMapping
    public User addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // PUT /api/users/{id}
    // Updates an existing user identified by {id}
    // The client sends the NEW values for name/gender/image in the request body
    // Returns 200 OK with updated user, or 404 Not Found if id doesn't exist
    // ─────────────────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // DELETE /api/users/{id}
    // Deletes the user with the given id
    // Returns 200 OK with true if deleted, or 404 Not Found if id doesn't exist
    // ─────────────────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}
