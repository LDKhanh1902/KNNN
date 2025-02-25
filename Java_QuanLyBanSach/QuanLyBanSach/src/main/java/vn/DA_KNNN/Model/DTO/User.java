package vn.DA_KNNN.Model.DTO;


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

    // Phương thức kiểm tra nếu người dùng là quản lý cửa hàng
    public static boolean isAdmin() {
        return Role.checkRoleById(loggedInUser.getPositionId()) == "Quản lý cửa hàng";
    }

    // Phương thức kiểm tra nếu người dùng là nhân viên bán hàng
    public static boolean isSalesPerson() {
        return Role.checkRoleById(loggedInUser.getPositionId()) == "Nhân viên bán hàng";
    }

    // Phương thức kiểm tra nếu người dùng là thủ kho
    public static boolean isWarehouseKeeper() {
        return Role.checkRoleById(loggedInUser.getPositionId()) == "Thủ kho";
    }
}
