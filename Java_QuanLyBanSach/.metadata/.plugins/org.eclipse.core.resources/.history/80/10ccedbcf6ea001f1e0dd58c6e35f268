package vn.DA_KNNN.Model;

public class Role {
    private int roleId;
    private String roleName;
    private double salary;

    // Constructor
    public Role(int roleId, String roleName, double salary) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.salary = salary;
    }

    // Getters and Setters
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Static method to get Role by ID
    public static Role getRoleById(int roleId) {
        // In a real app, this data might be fetched from the database
        switch(roleId) {
            case 1:
                return new Role(1, "Quản lý cửa hàng", 10000000.00);
            case 2:
                return new Role(2, "Nhân viên bán hàng", 8000000.00);
            case 3:
                return new Role(3, "Thủ kho", 7500000.00);
            default:
                return null;
        }
    }
}
