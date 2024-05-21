package models.filter;

public class AdminUserFilter extends Filter {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String room;
    private String gender;
    private String role;

    public AdminUserFilter(String id, String firstName, String lastName, String email, String room, String gender, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.room = room;
        this.gender = gender;
        this.role = role;
    }

    public String getBuildQuery() {
        String query = "";

        if (id != null && !id.isEmpty()) {
            query += " AND u.id = '" + id + "'";
        }
        if (firstName != null && !firstName.isEmpty()) {
            query += " AND u.firstname LIKE '" + firstName + "%'";
        }
        if (lastName != null && !lastName.isEmpty()) {
            query += " AND u.lastName LIKE '" + lastName + "%'";
        }
        if (email != null && !email.isEmpty()) {
            query += " AND u.email LIKE '" + email + "%'";
        }
        if (room != null && !room.isEmpty()) {
            query += " AND r.roomType LIKE '" + room + "%'";
        }
        if (gender != null && !gender.isEmpty()) {
            query += " AND u.gender = '" + gender + "'";
        }
        if (role != null && !role.isEmpty()) {
            query += " AND u.role LIKE '" + role + "%'";
        }

        return query;
    }
}
