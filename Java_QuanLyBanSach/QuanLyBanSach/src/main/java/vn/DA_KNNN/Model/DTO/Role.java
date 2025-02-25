package vn.DA_KNNN.Model.DTO;


public class Role {
    // Static method to get Role by ID
    public static String checkRoleById(int roleId) {
        // In a real app, this data might be fetched from the database
        switch(roleId) {
            case 1:
                return  "Quản lý cửa hàng";
            case 2:
                return  "Nhân viên bán hàng";
            case 3:
                return "Thủ kho";

        }
		return null;
    }
}
