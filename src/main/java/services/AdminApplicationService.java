package services;

import models.Student;
import repositories.AdminApplicationRepository;

import java.util.List;

public class AdminApplicationService {
    private final AdminApplicationRepository repository = new AdminApplicationRepository();

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public void updateStudent(Student student) {
        repository.update(student);
    }

    public void deleteStudent(Student student) {
        repository.delete(student);
    }
}
