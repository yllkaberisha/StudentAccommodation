package models.dto;

public class ApplicationDto {
    private int ID;
    private String faculty;

    private Integer yearsOfStudies;
    private String major;
    private double averageGrade;
    private String status;
    private int userID;

    public ApplicationDto(int id, String faculty, Integer yearsOfStudies, String major, double averageGrade, String status, int userID) {
        this.ID = id;
        this.faculty = faculty;
        this.yearsOfStudies = yearsOfStudies;
        this.major = major;
        this.averageGrade = averageGrade;
        this.status = status;
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFaculty() {
        return faculty;
    }

    public Integer getYearsOfStudies() {
        return yearsOfStudies;
    }

    public String getMajor() {
        return major;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getID() {
        return ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
