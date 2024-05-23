package models.dto;

public class AdminApplicationDto {
    private int RoomID;
    private String Status;

    private int applicationID;

    public AdminApplicationDto(int roomID, String status, int applicationID) {
        RoomID = roomID;
        Status = status;
        this.applicationID = applicationID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
