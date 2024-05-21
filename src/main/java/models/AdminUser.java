package models;

public class AdminUser {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String room;
    private String gender;
    private String role;

    public AdminUser(String id, String firstName, String lastName, String email, String room, String gender, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.room = room;
        this.gender = gender;
        this.role = role;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
