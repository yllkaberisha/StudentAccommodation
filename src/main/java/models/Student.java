package models;

public class Student {
    private String firstName;
    private String lastName;
    private String gender;
    private String faculty;
    private String major;
    private String averageGrade;
    private String status;
    private String year;
    private String userId;

    public Student(String firstName, String lastName, String gender, String faculty, String major, String averageGrade, String status, String year, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.faculty = faculty;
        this.major = major;
        this.averageGrade = averageGrade;
        this.status = status;
        this.year = year;
        this.userId = userId;
    }

    // Getters and Setters for each field
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

    public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
