package com.being_infinity.sp_beginner.models;

/**
 * MODEL = Blueprint of a User
 *
 * Think of this like a table in a database:
 *   - Each field here is like a "column" in a table
 *   - Each object (instance) of this class is like a "row" in that table
 *
 * Fields:
 *   id     → unique identifier for each user
 *   name   → full name of the user
 *   gender → gender of the user
 *   image  → path to the user's profile image
 */
public class User {

    private Integer id;
    private String name;
    private String gender;
    private String image;

    // Default (no-argument) constructor
    // Spring needs this to convert incoming JSON → User object
    public User() {
    }

    // Parameterized constructor — used when creating users in UserService
    public User(Integer id, String name, String gender, String image) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.image = image;
    }

    // ─── Getters & Setters ─────────────────────────────────────────────────────
    // Getters allow Spring to READ the fields and convert User → JSON
    // Setters allow Spring to WRITE into the fields when converting JSON → User

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
