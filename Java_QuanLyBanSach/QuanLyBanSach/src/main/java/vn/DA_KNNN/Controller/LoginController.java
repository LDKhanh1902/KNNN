package vn.DA_KNNN.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.EncodeAESE;
import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.Model.DTO.Employee;
import vn.DA_KNNN.Model.DTO.User;
import vn.DA_KNNN.View.LayoutView;
import vn.DA_KNNN.View.LoginView;

public class LoginController {
	private LoginView loginView;

	public LoginController(LoginView view) {
		view.getBtnLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (login(view.getUserName(), view.getPassword())) {
					LayoutView layoutView = new LayoutView(); // Tạo LayoutView
					new LayoutController(layoutView); // Tạo LayoutController và gắn LayoutView
					layoutView.setVisible(true); // Hiển thị giao diện chính
					view.dispose(); // Đóng cửa sổ đăng nhập
				} else {
					JOptionPane.showMessageDialog(loginView, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi đăng nhập",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		view.getBtnExit().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

	public Boolean login(String username, String password) {
		try (ResultSet data = DataProvider.getInstance().view("SELECT * FROM `employee` WHERE 1")) {
		    while (data.next()) {
		        Employee emp = new Employee();
		        emp.setEmployeeId(data.getInt("EmployeeId"));
		        emp.setFirstName(data.getString("FirstName"));
		        emp.setLastName(data.getString("LastName"));
		        emp.setBirthDate(data.getDate("BirthDate"));
		        emp.setHireDate(data.getDate("HireDate"));
		        emp.setEmail(data.getString("Email"));
		        emp.setPhoneNumber(data.getString("PhoneNumber"));
		        emp.setAddress(data.getString("Address"));
		        emp.setPositionId(data.getInt("PositionId"));
		        emp.setPassword(data.getString("Password"));

		        try {
		            if (username.equals(emp.getPhoneNumber()) && EncodeAESE.encrypt(password, AppHelper.EncodeKey).equals(emp.getPassword())) {
		            	emp.setPassword(password);
		                User.setUser(emp);
		                return true; // Đăng nhập thành công
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}


		return false; // Đăng nhập không thành công
	}
}
