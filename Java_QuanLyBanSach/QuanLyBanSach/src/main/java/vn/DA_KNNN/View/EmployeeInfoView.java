package vn.DA_KNNN.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class EmployeeInfoView extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField txtEmployeeId, txtLastName, txtFirstName, txtHireDate, txtBirthDate,
            txtEmail, txtPhone, txtAddress, txtSalaryFactor, txtPosition;
    private JButton btnChangePassword;

    // Khai báo các JLabel
    private JLabel lblEmployeeId, lblLastName, lblFirstName, lblHireDate, lblBirthDate,
            lblEmail, lblPhone, lblAddress, lblSalaryFactor, lblPosition;

    public EmployeeInfoView() {
    	setModal(true);
        setTitle("Thông tin nhân viên");
        setSize(600, 450);
        setMinimumSize(new Dimension(650, 550));
        setLocationRelativeTo(null);

        // Tạo panel chính với viền tiêu đề
        JPanel mainPanel = new JPanel(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder("Thông tin nhân viên");
        border.setTitleFont(new Font("Arial", Font.BOLD, 26));
        mainPanel.setBorder(border);

        // Panel chứa các trường thông tin (JTextField, JComboBox)
        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10)); // 10 hàng, 2 cột
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Lề cho panel

        // Khởi tạo các trường thông tin và JLabel
        lblEmployeeId = new JLabel("Mã nhân viên:");
        lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblLastName = new JLabel("Họ:");
        lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblFirstName = new JLabel("Tên:");
        lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblHireDate = new JLabel("Ngày thuê:");
        lblHireDate.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblBirthDate = new JLabel("Ngày sinh:");
        lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblPhone = new JLabel("Số điện thoại:");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblSalaryFactor = new JLabel("Hệ số lương:");
        lblSalaryFactor.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblPosition = new JLabel("Chức vụ:");
        lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 22));

        txtEmployeeId = new JTextField();
        txtEmployeeId.setEnabled(false);
        txtEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtLastName = new JTextField();
        txtLastName.setEnabled(false);
        txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtFirstName = new JTextField();
        txtFirstName.setEnabled(false);
        txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtHireDate = new JTextField();
        txtHireDate.setEnabled(false);
        txtHireDate.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtBirthDate = new JTextField();
        txtBirthDate.setEnabled(false);
        txtBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtEmail = new JTextField();
        txtEmail.setEnabled(false);
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtPhone = new JTextField();
        txtPhone.setEnabled(false);
        txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtAddress = new JTextField();
        txtAddress.setEnabled(false);
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtSalaryFactor = new JTextField();
        txtSalaryFactor.setEnabled(false);
        txtSalaryFactor.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtPosition = new JTextField(); // TextField cho chức vụ
        txtPosition.setEnabled(false);
        txtPosition.setFont(new Font("Tahoma", Font.PLAIN, 22));

        // Thêm các trường vào panel
        panel.add(lblEmployeeId); panel.add(txtEmployeeId);
        panel.add(lblLastName); panel.add(txtLastName);
        panel.add(lblFirstName); panel.add(txtFirstName);
        panel.add(lblBirthDate); panel.add(txtBirthDate);
        panel.add(lblHireDate); panel.add(txtHireDate);
        panel.add(lblEmail); panel.add(txtEmail);
        panel.add(lblPhone); panel.add(txtPhone);
        panel.add(lblAddress); panel.add(txtAddress);
        panel.add(lblPosition); panel.add(txtPosition);
        panel.add(lblSalaryFactor); panel.add(txtSalaryFactor);

        // Thêm panel chứa các trường vào panel chính
        mainPanel.add(panel, BorderLayout.CENTER);

        // Panel phía dưới chứa nút "Đổi mật khẩu"
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(Box.createHorizontalGlue()); 
        btnChangePassword = new JButton("Đổi mật khẩu");
        btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bottomPanel.add(btnChangePassword);

        // Thêm panel dưới vào panel chính
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Thêm panel chính vào cửa sổ
        getContentPane().add(mainPanel);

    }

    // Các phương thức get để lấy JTextField
    public JTextField getEmployeeIdField() {
        return txtEmployeeId;
    }

    public JTextField getLastNameField() {
        return txtLastName;
    }

    public JTextField getFirstNameField() {
        return txtFirstName;
    }

    public JTextField getHireDateField() {
        return txtHireDate;
    }

    public JTextField getBirthDateField() {
        return txtBirthDate;
    }

    public JTextField getEmailField() {
        return txtEmail;
    }

    public JTextField getPhoneField() {
        return txtPhone;
    }

    public JTextField getAddressField() {
        return txtAddress;
    }

    public JTextField getSalaryFactorField() {
        return txtSalaryFactor;
    }

    public JTextField getPositionField() {
        return txtPosition;
    }
    
    public JButton getBtnChangPassword(){
    	return btnChangePassword;
    }
}
