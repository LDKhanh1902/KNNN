package vn.DA_KNNN.View;

import javax.swing.*;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.EncodeAESE;
import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.Model.DTO.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangPasswordView extends JDialog {
    private static final long serialVersionUID = 1L;
    
    // Các trường JTextField cho mật khẩu
    private JPasswordField txtCurrentPassword, txtNewPassword, txtConfirmPassword;
    private JButton btnChangePassword;
    private JPanel panel;

    public ChangPasswordView() {
    	setModal(true);
        setSize(400, 200);
        setLocationRelativeTo(null);
        
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        // Thêm button "Đổi mật khẩu"
        btnChangePassword = new JButton("Đổi mật khẩu");
        panel.add(btnChangePassword);
        btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
                // Xử lý sự kiện khi nhấn nút "Đổi mật khẩu"
                btnChangePassword.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        changePassword();
                    }
                });

        // Tạo panel chính với layout BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
        
        // Các label và text field
        JLabel lblCurrentPassword = new JLabel("Mật khẩu hiện tại:");
        lblCurrentPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtCurrentPassword = new JPasswordField();
        txtCurrentPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblNewPassword = new JLabel("Mật khẩu mới:");
        lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtNewPassword = new JPasswordField();
        txtNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblConfirmPassword = new JLabel("Xác nhận mật khẩu:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

        // Thêm các component vào panel
        mainPanel.add(lblCurrentPassword);
        mainPanel.add(txtCurrentPassword);
        mainPanel.add(lblNewPassword);
        mainPanel.add(txtNewPassword);
        mainPanel.add(lblConfirmPassword);
        mainPanel.add(txtConfirmPassword);

        // Thêm panel chính vào cửa sổ
        getContentPane().add(mainPanel);

        // Hiển thị cửa sổ
        setVisible(true);
    }  

    private void changePassword() {
        // Lấy giá trị từ các trường mật khẩu
        String currentPassword = new String(txtCurrentPassword.getPassword());
        String newPassword = new String(txtNewPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

     // Kiểm tra mật khẩu hiện tại
        if (!currentPassword.equals(User.getUser().getPassword())) {
            JOptionPane.showMessageDialog(this, "Mật khẩu sai", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kiểm tra mật khẩu xác nhận
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
			if(DataProvider.getInstance().update(String.format("UPDATE `employee` SET `Password`='%s' WHERE `EmployeeId` = '%s'",EncodeAESE.encrypt(newPassword, AppHelper.EncodeKey),User.getUser().getEmployeeId()))) {
				User.getUser().setPassword(newPassword);
				JOptionPane.showMessageDialog(this,"Đổi mật khẩu thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
