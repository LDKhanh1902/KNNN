package vn.DA_KNNN.Model;

public class User{
    private static Employee loggedInUser;

    public static void setUser(Employee user) {
        loggedInUser = user;
    }

    public static Employee getUser() {
        return loggedInUser;
    }

    public static void logout() {
        loggedInUser = null;
    }

    public static String getName() {
        return String.format("%s %s", loggedInUser.getFirstName(), loggedInUser.getLastName());
    }

    // Method to get the role of the logged-in user
    public static String getUserRole() {
        return loggedInUser != null ? loggedInUser.getRoleName() : "No Role Assigned";
    }

 // Phương thức để kiểm tra quyền truy cập dựa trên vai trò
    public static boolean hasRole(String roleName) {
        if (loggedInUser == null) {
            return false;
        }
        return loggedInUser.getRoleName().equals(roleName);
    }

    // Phương thức kiểm tra nếu người dùng là quản lý cửa hàng
    public static boolean isAdmin() {
        return hasRole("Quản lý cửa hàng");
    }

    // Phương thức kiểm tra nếu người dùng là nhân viên bán hàng
    public static boolean isSalesPerson() {
        return hasRole("Nhân viên bán hàng");
    }

    // Phương thức kiểm tra nếu người dùng là thủ kho
    public static boolean isWarehouseKeeper() {
        return hasRole("Thủ kho");
    }
}
