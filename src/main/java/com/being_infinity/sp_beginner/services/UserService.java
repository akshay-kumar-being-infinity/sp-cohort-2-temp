package com.being_infinity.sp_beginner.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.being_infinity.sp_beginner.models.User;

/**
 * SERVICE = Business Logic Layer
 *
 * This class contains ALL the logic for working with Users.
 * It does NOT know anything about HTTP — that is the Controller's job.
 *
 * @Service → tells Spring "this is a Service bean, manage it for me"
 *            Spring creates ONE instance of this class and reuses it everywhere
 *            (this is called a Singleton)
 *
 * In-Memory Database:
 *   We use a List<User> as our "database table".
 *   When the app restarts, all data is reset — just like a fresh DB.
 *   This keeps things simple while teaching backend concepts.
 */
@Service
public class UserService {

    // Our in-memory "table" — holds all users
    private List<User> allUsers;

    // Auto-incrementing ID counter — mimics how a real DB generates primary keys
    private int nextId = 3;

    // Constructor — runs once when Spring starts the app
    // Pre-loads 2 users so we have something to work with immediately
    public UserService() {
        allUsers = new ArrayList<>();
        allUsers.add(new User(1, "John Doe", "Male", "/images/john.png"));
        allUsers.add(new User(2, "Jane Doe", "Female", "/images/jane.png"));
    }

    // ─────────────────────────────────────────────────────────────────────────
    // READ — GET all users
    // Corresponds to: GET /api/users
    // ─────────────────────────────────────────────────────────────────────────
    public List<User> getAllUsers() {
        return allUsers;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // READ — GET single user by id
    // Corresponds to: GET /api/users/{id}
    // Returns null if no user found with that id
    // ─────────────────────────────────────────────────────────────────────────
    public User getUserById(int id) {
        for (User user : allUsers) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // user not found
    }

    // ─────────────────────────────────────────────────────────────────────────
    // CREATE — Add a new user
    // Corresponds to: POST /api/users
    // The client sends name/gender/image; we assign the id here
    // ─────────────────────────────────────────────────────────────────────────
    public User addUser(User newUser) {
        newUser.setId(nextId);
        nextId++;
        allUsers.add(newUser);
        return newUser;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // UPDATE — Modify an existing user by id
    // Corresponds to: PUT /api/users/{id}
    // Returns null if no user found with that id
    // ─────────────────────────────────────────────────────────────────────────
    public User updateUser(int id, User updatedUser) {
        for (User user : allUsers) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setGender(updatedUser.getGender());
                user.setImage(updatedUser.getImage());
                return user;
            }
        }
        return null; // user not found
    }

    // ─────────────────────────────────────────────────────────────────────────
    // DELETE — Remove a user by id
    // Corresponds to: DELETE /api/users/{id}
    // Returns true if deleted, false if user not found
    // ─────────────────────────────────────────────────────────────────────────
    public boolean deleteUser(int id) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getId() == id) {
                allUsers.remove(i);
                return true;
            }
        }
        return false; // user not found
    }

    // ─────────────────────────────────────────────────────────────────────────
    // BONUS (Day 4 Exercise) — Return a random user from the list
    // Corresponds to: GET /api/users/random
    // Used to connect frontend with OUR backend (instead of external API)
    // ─────────────────────────────────────────────────────────────────────────
    public User getRandomUser() {
        if (allUsers.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(allUsers.size());
        return allUsers.get(randomIndex);
    }
}
