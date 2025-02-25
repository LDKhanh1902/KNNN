package vn.DA_KNNN.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.Model.DTO.User;
import vn.DA_KNNN.View.ChangPasswordView;
import vn.DA_KNNN.View.EmployeeInfoView;

public class EmployeeInfoController {
	private EmployeeInfoView view;

	public EmployeeInfoController(EmployeeInfoView view) {
		this.view = view;
		setEmployeeInfo();
		this.view.getBtnChangPassword().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ChangPasswordView();
			}
		});
	}
	
	private void setEmployeeInfo() {
	    String query = "SELECT `EmployeeId`, `FirstName`, `LastName`, `BirthDate`, `HireDate`, `Email`, `PhoneNumber`, `Address`, `PositionName`, "
	            + "CAST(`Coefficient` * `salary` AS DECIMAL(10,0)) AS `salary` FROM `employee` "
	            + "JOIN position ON `position`.`PositionId` = `employee`.`PositionId` "
	            + "WHERE `EmployeeId` = '" + User.getUser().getEmployeeId()+"'"; // Dùng ? thay vì hard-coding giá trị EmployeeId = 1

	    try (ResultSet rs = DataProvider.getInstance().view(query)) {
	            if (rs.next()) {
	                // Lấy các dữ liệu từ ResultSet và hiển thị vào các JTextField
	                view.getEmployeeIdField().setText(rs.getString("EmployeeId"));
	                view.getFirstNameField().setText(rs.getString("FirstName"));
	                view.getLastNameField().setText(rs.getString("LastName"));
	                view.getBirthDateField().setText(rs.getString("BirthDate"));
	                view.getHireDateField().setText(rs.getString("HireDate"));
	                view.getEmailField().setText(rs.getString("Email"));
	                view.getPhoneField().setText(rs.getString("PhoneNumber"));
	                view.getAddressField().setText(rs.getString("Address"));
	                view.getPositionField().setText(rs.getString("PositionName"));
	                view.getSalaryFactorField().setText(rs.getString("salary"));
	            }
	        
	    } catch (Exception e) {
	        // Xử lý ngoại lệ, có thể in ra chi tiết để dễ dàng debug
	        e.printStackTrace();  // In chi tiết lỗi ra console
	        JOptionPane.showMessageDialog(view, "Có lỗi xảy ra khi lấy thông tin nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
