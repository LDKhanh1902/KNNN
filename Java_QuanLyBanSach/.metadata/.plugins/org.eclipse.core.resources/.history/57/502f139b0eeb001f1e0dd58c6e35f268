package vn.DA_KNNN.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnExit, btnShow;
	private JLabel lblTitle, lblUserName, lblPassword;

	public String getUserName() {
		return txtUserName.getText();
	}

	public String getPassword() {
		return new String(txtPassword.getPassword());
	}

	public LoginView() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/imgIcon.jpeg"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Đăng nhập");
		setSize(600, 300);
		setMinimumSize(new Dimension(600, 300)); // Đặt kích thước tối thiểu
		setLocationRelativeTo(null);

		// Thay đổi Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Panel chứa giao diện
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);

		// Khởi tạo các thành phần giao diện
		lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(0, 102, 204));

		lblUserName = new JLabel("Tên đăng nhập :");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtUserName = new JTextField(20);
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblPassword = new JLabel("Mật khẩu :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtPassword = new JPasswordField(20);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnShow = new JButton("Show");
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtPassword.getEchoChar() == '\u0000') {
					txtPassword.setEchoChar('*');
					btnShow.setText("Show");
				} else {
					txtPassword.setEchoChar('\u0000');
					btnShow.setText("Hide");
				}
			}
		});

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));

		// Sử dụng GroupLayout để bố trí giao diện
		GroupLayout layout = new GroupLayout(contentPane);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(lblTitle)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(lblUserName)
								.addComponent(lblPassword))
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(txtUserName)
								.addComponent(txtPassword))
						.addComponent(btnShow))
				.addGroup(layout.createSequentialGroup().addComponent(btnLogin).addComponent(btnExit)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addComponent(lblTitle).addGap(30)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblUserName).addComponent(
						txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(20)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShow))
				.addGap(20).addGroup(
						layout.createParallelGroup(Alignment.BASELINE).addComponent(btnLogin).addComponent(btnExit))));
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnExit() {
		return btnExit;
	}
}
