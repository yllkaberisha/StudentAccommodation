package services;

import models.AdminUser;
import models.filter.AdminUserFilter;
import repositories.AdminRepository;

import java.util.List;

public class AdminService {

        private AdminRepository repository = new AdminRepository();

        public List<AdminUser> getAllUsers() {
            return repository.getAllUsers();
        }
    public List<AdminUser> getByFilter(AdminUserFilter filter) {
        return repository.getByFilter(filter);
    }

    public void updateUser(AdminUser selectedUser) {
        repository.updateUser(selectedUser);
    }

    public void deleteUser(AdminUser selectedUser) {
        repository.deleteUser(selectedUser);
    }
}

