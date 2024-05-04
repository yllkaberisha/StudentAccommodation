package models;

public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private String role;
    private String email;
    private String salt;
    private String passwordHash;

    public User(String firstName, String lastName, String gender, String role, String email, String salt, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = role;
        this.email = email;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
