package models.dto;

public class AdminRoomDto {
    private String roomID;
    private String roomType;
    private String capacity;
    private String floor;

    public AdminRoomDto(String roomID, String roomType, String capacity, String floor) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.capacity = capacity;
        this.floor = floor;
    }

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
