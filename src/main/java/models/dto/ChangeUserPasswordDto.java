package models.dto;

public class ChangeUserPasswordDto {
    private String email;
    private String passwordHash;
    private String salt;

    public ChangeUserPasswordDto(String email, String passwordHash, String salt) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }
}
