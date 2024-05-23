package services;

import models.Student;
import models.dto.AdminApplicationDto;
import repositories.AdminApplicationRepository;

import java.util.List;

public class AdminApplicationService {
    private final AdminApplicationRepository repository = new AdminApplicationRepository();

    public void addAllocation(AdminApplicationDto newAdminAppDto) {
         repository.addAllocation(newAdminAppDto);
    }

    public List<Student> getAllApplications() {
        return repository.getAllApplications();
    }
    public boolean allocationExists(int applicationID) {
        return repository.allocationExists(applicationID);
    }
    public void updateAllocation(AdminApplicationDto newAdminAppDto) {
        repository.updateAllocation(newAdminAppDto);
    }
}
