package vn.DA_KNNN.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;
import vn.DA_KNNN.Components.SearchHelper;
import vn.DA_KNNN.Components.TableHelper;

import java.awt.*;

public class EmployeeView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblEmployeeId, lblFirstName, lblLastName, lblBirthDate, lblHireDate, lblEmail, lblPhoneNumber,
			lblAddress, lblPositionId;
	private JTextField txtFirstName, txtLastName, txtBirthDate, txtHireDate, txtEmail, txtPhoneNumber, txtAddress,
			txtEmployeeId;
	private JButton btnAdd, btnEdit, btnDelete, btnCreateId;
	private JComboBox<String> cmbPositionName;
	private JTable table;
	private JPanel panelEmployeeId;
	private JButton btnExport;
	private SearchHelper searchPanel;

	public EmployeeView() {
		setLayout(new BorderLayout(10, 10));

		// Tạo panel chính chứa Thông tin nhân viên và Chức năng
		JPanel topPanel = new JPanel(new BorderLayout(10, 10));
		topPanel.setBackground(Color.WHITE);

		// Thêm panel Thông tin nhân viên
		JPanel employeeInfoPanel = createEmployeeInfoPanel();
		topPanel.add(employeeInfoPanel, BorderLayout.CENTER);
		// Thêm panel Chức năng
		JPanel functionPanel = createFunctionPanel();
		topPanel.add(functionPanel, BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);

		searchPanel = new SearchHelper();
		topPanel.add(searchPanel, BorderLayout.SOUTH);
		 
		// Tạo panel Bảng hiển thị nhân viên
		JPanel tablePanel = createTablePanel();
		add(tablePanel, BorderLayout.CENTER);
	}

	private JPanel createEmployeeInfoPanel() {
		JPanel employeeInfoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
		employeeInfoPanel.setBackground(Color.WHITE);
		employeeInfoPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông tin nhân viên",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.BLACK));

		lblEmployeeId = new JLabel("Mã nhân viên:");
		lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirstName = new JLabel("Họ:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFirstName = new JTextField(10);
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName = new JLabel("Tên:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLastName = new JTextField(10);
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBirthDate = new JLabel("Ngày sinh:");
		lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBirthDate = new JTextField(10);
		txtBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHireDate = new JLabel("Ngày thuê:");
		lblHireDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHireDate = new JTextField(10);
		txtHireDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail = new JTextField(10);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNumber = new JLabel("Số điện thoại:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPhoneNumber = new JTextField(10);
		txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress = new JLabel("Địa chỉ:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAddress = new JTextField(10);
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPositionId = new JLabel("Chức vụ:");
		lblPositionId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbPositionName = new JComboBox<String>();
		cmbPositionName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		employeeInfoPanel.add(lblEmployeeId);

		panelEmployeeId = new JPanel();
		employeeInfoPanel.add(panelEmployeeId);
		panelEmployeeId.setLayout(new BoxLayout(panelEmployeeId, BoxLayout.X_AXIS));

		txtEmployeeId = new JTextField();
		txtEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelEmployeeId.add(txtEmployeeId);
		txtEmployeeId.setColumns(10);

		btnCreateId = new JButton("Tạo");
		btnCreateId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelEmployeeId.add(btnCreateId);
		employeeInfoPanel.add(lblFirstName);
		employeeInfoPanel.add(txtFirstName);
		employeeInfoPanel.add(lblLastName);
		employeeInfoPanel.add(txtLastName);
		employeeInfoPanel.add(lblBirthDate);
		employeeInfoPanel.add(txtBirthDate);
		employeeInfoPanel.add(lblHireDate);
		employeeInfoPanel.add(txtHireDate);
		employeeInfoPanel.add(lblEmail);
		employeeInfoPanel.add(txtEmail);
		employeeInfoPanel.add(lblPhoneNumber);
		employeeInfoPanel.add(txtPhoneNumber);
		employeeInfoPanel.add(lblAddress);
		employeeInfoPanel.add(txtAddress);
		employeeInfoPanel.add(lblPositionId);
		employeeInfoPanel.add(cmbPositionName);
		
		return employeeInfoPanel;
	}

	private JPanel createFunctionPanel() {
		JPanel functionPanel = new JPanel();
		functionPanel.setBackground(Color.WHITE);
		functionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Chức năng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.BLACK));

		functionPanel.setPreferredSize(new Dimension(160, 0));
		functionPanel.setLayout(new GridLayout(4, 1, 5, 5));

		btnAdd = new ButtonHelper("Thêm",AppHelper.setSizeImage("/images/add.png",40,40),new Color(139, 195, 74));	
		btnEdit = new ButtonHelper("Sửa",AppHelper.setSizeImage("/images/edit.png",40,40),new Color(255, 159, 64));
		btnDelete = new ButtonHelper("Xóa",AppHelper.setSizeImage("/images/delete.png",40,40),new Color(244, 67, 54));
		btnExport = new ButtonHelper("Export", AppHelper.setSizeImage("/images/export.png",40,40),new Color(158, 158, 158));
		
		functionPanel.add(btnAdd);
		functionPanel.add(btnEdit);
		functionPanel.add(btnDelete);
		functionPanel.add(btnExport);

		return functionPanel;
	}

	private JPanel createTablePanel() {
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBackground(Color.WHITE);
		tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table = new TableHelper(SwingConstants.CENTER, 30);

		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		return tablePanel;
	}

	// Getter methods
	public JTextField getTxtEmployeeId() {
		return txtEmployeeId;
	}

	public JTextField getTxtFirstName() {
		return txtFirstName;
	}

	public JTextField getTxtLastName() {
		return txtLastName;
	}

	public JTextField getTxtBirthDate() {
		return txtBirthDate;
	}

	public JTextField getTxtHireDate() {
		return txtHireDate;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtPhoneNumber() {
		return txtPhoneNumber;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public JComboBox<String> getCmbPositionName() {
		return cmbPositionName;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}
	
	public SearchHelper getSearchPanel() {
		return searchPanel;
	}

	public JButton getBtnExport() {
		return btnExport;
	}

	public JButton getBtnCreateId() {
		return btnCreateId;
	}
	
	public JTable getTable() {
		return table;
	}

	public void setCmbPositionName(String[] data) {
		this.cmbPositionName.removeAllItems();
		for (String item : data) {
			cmbPositionName.addItem(item);
		}
		revalidate();
		repaint();
	}

	
}
