package models;

public class Student {

    private int applicationID;
    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private Integer room;
    private String faculty;
    private String major;
    private double averageGrade;
    private int year;

    public Student(int applicationID, int userId, String firstName, String lastName, String gender, String status, Integer room, String faculty, String major, double averageGrade, int year) {
        this.applicationID = applicationID;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.status = status;
        this.room = room;
        this.faculty = faculty;
        this.major = major;
        this.averageGrade = averageGrade;
        this.year = year;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
