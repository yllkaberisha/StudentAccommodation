package models.dto;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String role;
    private String email;
    private String passwordHash;
    private String salt;


    public CreateUserDto(String firstName, String lastName, String gender, String role, String email, String passwordHash, String salt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = role;
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
