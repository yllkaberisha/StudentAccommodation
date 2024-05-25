package models;

public class AdminUser {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String room;
    private String gender;
    private String role;
    private String roomID;
    private String roomType;
    private String capacity;
    private String floor;

    public AdminUser(String id, String firstName, String lastName, String email, String room, String gender, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.room = room;
        this.gender = gender;
        this.role = role;

    }

    public AdminUser(String roomID, String roomType, String capacity, String floor) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.capacity = capacity;
        this.floor = floor;
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

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
