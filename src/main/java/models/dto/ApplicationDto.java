package models.dto;

public class ApplicationDto {
    private int ID;
    private String faculty;
    private Integer yearsOfStudies;
    private String major;
    private double averageGrade;

    public ApplicationDto(int id, String faculty, Integer yearsOfStudies, String major, double averageGrade) {
        this.ID = id;
        this.faculty = faculty;
        this.yearsOfStudies = yearsOfStudies;
        this.major = major;
        this.averageGrade = averageGrade;
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
}
