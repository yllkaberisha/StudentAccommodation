package services;

import models.AdminUser;
import models.filter.AdminUserFilter;
import repositories.AdminUserRepository;

import java.util.List;

public class AdminUserService {


    // user
        private AdminUserRepository repository = new AdminUserRepository();

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

        // application

}

