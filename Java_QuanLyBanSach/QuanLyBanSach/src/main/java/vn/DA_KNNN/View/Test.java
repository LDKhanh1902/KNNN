package vn.DA_KNNN.View;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.BackgroundPanel;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;

public class Test extends JPanel {

	private static final long serialVersionUID = 1L;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
	/**
	 * Create the panel.
	 */
	public Test() {
		setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelBanner = new BackgroundPanel(getClass().getResource("/images/add.png").getPath());
		add(panelBanner);
		panelBanner.setLayout(new BorderLayout(0, 0));
		
		 // Panel bên phải chứa form đăng nhập
        JPanel panelControl = new JPanel();
        panelControl.setBackground(new Color(255, 255, 255));
        panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.Y_AXIS));
        panelControl.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Tiêu đề đăng nhập
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204));
        panelControl.add(lblTitle);
        
        // Thêm khoảng cách giữa tiêu đề và form
        panelControl.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Tài khoản
        JPanel panelUsername = new JPanel();
        panelUsername.setLayout(new GridLayout(1, 2, 0, 0));
        JLabel lblUsername = new JLabel("Tài khoản:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelUsername.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelUsername.add(txtUsername);
        panelControl.add(panelUsername);
        
        // Mật khẩu
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new GridLayout(1, 2));
        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelPassword.add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelPassword.add(txtPassword);
        panelControl.add(panelPassword);
        
        // Thêm khoảng cách giữa mật khẩu và nút đăng nhập
        panelControl.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Nút Đăng nhập
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnLogin.setBackground(new Color(0, 123, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(150, 40));
        panelControl.add(btnLogin);
        
        add(panelControl);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().add(new Test());
		f.setVisible(true);
	}

}
