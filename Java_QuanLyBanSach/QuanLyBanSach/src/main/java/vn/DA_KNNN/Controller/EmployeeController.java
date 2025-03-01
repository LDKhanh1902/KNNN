package vn.DA_KNNN.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vn.DA_KNNN.Components.ExcelExporter;
import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.View.EmployeeView;

public class EmployeeController {
	private EmployeeView view;
	private String query = "SELECT `EmployeeId` AS 'Mã nhân viên',`FirstName` AS 'Tên',`LastName` AS 'Họ',`BirthDate` AS 'Ngày sinh',"
			+ "`HireDate` AS 'Ngày tuyển dụng',`Email` AS 'Email',`PhoneNumber` AS 'Số điện thoại',`Address` AS 'Địa chỉ',"
			+ "CAST(`salary` AS DECIMAL(10,0)) * CAST(`Coefficient` AS DECIMAL(10,0)) AS 'Lương', `PositionName` AS 'Chức vụ' FROM `employee` "
			+ "JOIN `position` ON `position`.`PositionId` = `employee`.`PositionId`";

	public EmployeeController(EmployeeView _view) {
		this.view = _view;
		setupEventListeners();
		view.setCmbPositionName(loadPositionName());
		loadData(query+" ORDER BY `EmployeeId` ;");
		view.getSearchPanel().getBtnRefresh().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearFields();
				view.getSearchPanel().getTxtSearch().setText("");
				loadData(query);
			}
		});
	}

	private void loadData(String sql) {
		DefaultTableModel model = AppHelper.loadDataTable(sql);
		tableRowClick(view.getTable(), model);
		view.getTable().setModel(model);
	}

	/** 🔹 Thiết lập các sự kiện cho các nút */
	private void setupEventListeners() {
		view.getBtnAdd().addActionListener(e -> addEmployee());
		view.getBtnEdit().addActionListener(e -> editEmployee());
		view.getBtnDelete().addActionListener(e -> deleteEmployee());
		view.getBtnCreateId().addActionListener(e -> createEmployeeId());
		view.getSearchPanel().getBtnSearch().addActionListener(e -> findEmployeeData());
		view.getBtnExport().addActionListener(e->exportListEmployee());
		view.getSearchPanel().getBtnRefresh().addActionListener(e->{
			clearFields();
			view.getSearchPanel().getTxtSearch().setText("");
		});
	}

	private void exportListEmployee() {
		// TODO Auto-generated method stub
        String templatePath = getClass().getResource("/templates/Employee_Template.xlsx").getPath();
        String outputPath = Paths.get(System.getProperty("user.home"), "Documents", String.format("\\DanhSachNhanVien_%s.xlsx", LocalDate.now())).toString();
        String sql = query + " ORDER BY `EmployeeId`";
        
		ExcelExporter.updateExcelTemplate(view, templatePath, outputPath, AppHelper.loadDataTable(sql));
	}

	private void addEmployee() {
		String employeeId = view.getTxtEmployeeId().getText().trim();
		String firstName = view.getTxtFirstName().getText().trim();
		String lastName = view.getTxtLastName().getText().trim();
		String birthDate = view.getTxtBirthDate().getText().trim();
		String hireDate = view.getTxtHireDate().getText().trim();
		String email = view.getTxtEmail().getText().trim();
		String phoneNumber = view.getTxtPhoneNumber().getText().trim();
		String address = view.getTxtAddress().getText().trim();
		String positionName = view.getCmbPositionName().getSelectedItem().toString();

		// Kiểm tra xem các trường có bị trống hay không
		if (employeeId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthDate.isEmpty()
				|| hireDate.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Câu lệnh SQL để thêm nhân viên
		String sql = String.format(
				"INSERT INTO employee (EmployeeId, FirstName, LastName, BirthDate, HireDate, Email, PhoneNumber, Address, PositionId) "
						+ "VALUES (%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', (SELECT PositionId FROM position WHERE PositionName = '%s'))",
				employeeId, firstName, lastName, birthDate, hireDate, email, phoneNumber, address, positionName);

		// Thực thi câu lệnh SQL để thêm nhân viên
		if (DataProvider.getInstance().insert(sql)) {
			JOptionPane.showMessageDialog(view, "Thêm nhân viên thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			
			clearFields(); // Xóa các trường nhập liệu
		} else {
			JOptionPane.showMessageDialog(view, "Thêm nhân viên thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
		loadData(query); // Làm mới dữ liệu bảng
	}

	private void editEmployee() {
		String employeeId = view.getTxtEmployeeId().getText().trim();
		String firstName = view.getTxtFirstName().getText().trim();
		String lastName = view.getTxtLastName().getText().trim();
		String birthDate = view.getTxtBirthDate().getText().trim();
		String hireDate = view.getTxtHireDate().getText().trim();
		String email = view.getTxtEmail().getText().trim();
		String phoneNumber = view.getTxtPhoneNumber().getText().trim();
		String address = view.getTxtAddress().getText().trim();
		String positionName = view.getCmbPositionName().getSelectedItem().toString();

		// Kiểm tra xem các trường có bị trống hay không
		if (employeeId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthDate.isEmpty()
				|| hireDate.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Câu lệnh SQL để cập nhật thông tin nhân viên
		String sql = String.format(
				"UPDATE employee SET FirstName = '%s', LastName = '%s', BirthDate = '%s', HireDate = '%s', "
						+ "Email = '%s', PhoneNumber = '%s', Address = '%s', PositionId = (SELECT PositionId FROM position WHERE PositionName = '%s') "
						+ "WHERE EmployeeId = %s",
				firstName, lastName, birthDate, hireDate, email, phoneNumber, address, positionName, employeeId);

		// Thực thi câu lệnh SQL để cập nhật nhân viên
		if (DataProvider.getInstance().update(sql)) {
			JOptionPane.showMessageDialog(view, "Cập nhật nhân viên thành công", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			loadData(query); // Làm mới dữ liệu bảng
			clearFields(); // Xóa các trường nhập liệu
		} else {
			JOptionPane.showMessageDialog(view, "Cập nhật nhân viên thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteEmployee() {
		String employeeId = view.getTxtEmployeeId().getText().trim();

		if (employeeId.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên để xóa", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Hiển thị hộp thoại xác nhận
		int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			// Câu lệnh SQL để xóa nhân viên
			String sql = "DELETE FROM employee WHERE EmployeeId = " + employeeId;

			// Thực thi câu lệnh SQL để xóa nhân viên
			if (DataProvider.getInstance().delete(sql)) {
				JOptionPane.showMessageDialog(view, "Xóa nhân viên thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // Làm mới dữ liệu bảng
				clearFields(); // Xóa các trường nhập liệu
			} else {
				JOptionPane.showMessageDialog(view, "Xóa nhân viên thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/** 🔹 Hàm dùng chung để lấy giá trị duy nhất */
	private String fetchSingleValue(String sql, String columnName) {
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			if (rs.next()) {
				return rs.getString(columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void createEmployeeId() {
		// TODO Auto-generated method stub
		String nextId = fetchSingleValue("SELECT MAX(EmployeeId) + 1 FROM employee", "MAX(EmployeeId) + 1");
		view.getTxtEmployeeId().setText(nextId);
	}

	private void findEmployeeData() {
		// TODO Auto-generated method stub
		String id = view.getSearchPanel().getTxtSearch().getText();
		String name = view.getSearchPanel().getTxtSearch().getText();

		boolean isNumeric = id.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

		String sql;
		if (isNumeric) {
			// Kiểm tra nếu id là số, tìm theo EmployeeId
			sql = query + " WHERE EmployeeId = " + id + " OR FirstName LIKE '%" + name + "%' OR LastName LIKE '%" + name+ "%' ORDER BY `EmployeeId`";
		} else {
			// Nếu id không phải là số, tìm theo FirstName hoặc LastName
			sql = query + " WHERE FirstName LIKE '%" + name + "%' OR LastName LIKE '%" + name + "%' ORDER BY `EmployeeId`";
		}

		loadData(sql);
	}
	
	/** ✅ Load danh sách nhà xuất bản */
	private String[] loadPositionName() {
		return fetchColumnData("SELECT `PositionName` FROM `position`", "PositionName");
	}

	/** 🔹 Hàm dùng chung để lấy dữ liệu từ một cột */
	private String[] fetchColumnData(String sql, String columnName) {
		List<String> dataList = new ArrayList<>();
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			while (rs.next()) {
				dataList.add(rs.getString(columnName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList.toArray(new String[0]);
	}

	private void tableRowClick(JTable table, DefaultTableModel model) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    int selectedRow = table.getSelectedRow();
			    int rowCount = model.getRowCount();
			    
			    if (selectedRow != -1 && selectedRow < rowCount) { // Ensure row is within bounds
					view.getTxtEmployeeId().setText(model.getValueAt(selectedRow, 0).toString()); // Mã nhân viên
					view.getTxtFirstName().setText(model.getValueAt(selectedRow, 1).toString()); // Tên
					view.getTxtLastName().setText(model.getValueAt(selectedRow, 2).toString()); // Họ
					view.getTxtBirthDate().setText(model.getValueAt(selectedRow, 3).toString()); // Ngày sinh
					view.getTxtHireDate().setText(model.getValueAt(selectedRow, 4).toString()); // Ngày tuyển dụng
					view.getTxtEmail().setText(model.getValueAt(selectedRow, 5).toString()); // Email
					view.getTxtPhoneNumber().setText(model.getValueAt(selectedRow, 6).toString()); // Số điện thoại
					view.getTxtAddress().setText(model.getValueAt(selectedRow, 7).toString()); // Địa chỉ
					view.getCmbPositionName().setSelectedItem(model.getValueAt(selectedRow, 9).toString()); // Chức vụ
				}
			}
		});
	}

	private void clearFields() {
		// Xóa trắng tất cả JTextField
		view.getTxtEmployeeId().setText("");
		view.getTxtFirstName().setText("");
		view.getTxtLastName().setText("");
		view.getTxtBirthDate().setText("");
		view.getTxtHireDate().setText("");
		view.getTxtEmail().setText("");
		view.getTxtPhoneNumber().setText("");
		view.getTxtAddress().setText("");
		view.getCmbPositionName().setSelectedItem(0);
	}

}
