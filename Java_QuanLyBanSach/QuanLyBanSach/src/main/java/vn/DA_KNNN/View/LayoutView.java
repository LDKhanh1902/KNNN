package vn.DA_KNNN.View;

import javax.swing.*;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;
import vn.DA_KNNN.Controller.MainController;

import java.awt.*;

public class LayoutView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JButton btnHome;
	private JButton btnCategory;
	private JButton btnBook;
	private JButton btnCustomer;
	private JButton btnSale;
	private JButton btnEmployee;
	private JButton btnRevenue;
	private JPanel panel, buttonPanel;
	private JButton btnLogout;

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JButton getBtnHome() {
		return btnHome;
	}

	public JButton getBtnCategory() {
		return btnCategory;
	}

	public JButton getBtnBook() {
		return btnBook;
	}

	public JButton getBtnSale() {
		return btnSale;
	}

	public JButton getBtnCustomer() {
		return btnCustomer;
	}

	public JButton getBtnEmployee() {
		return btnEmployee;
	}

	public JButton getBtnRevenue() {
		return btnRevenue;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	public JPanel getMenuButton() {
		return this.buttonPanel;
	}

	public LayoutView() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Đăng nhập");
		setSize(1000, 600);
		setMinimumSize(new Dimension(1300, 700));
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		JPanel sidebar = createSidebar();
		getContentPane().add(sidebar, BorderLayout.WEST);

		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		MainView view = new MainView();
		new MainController(view);
		showContent(view);
	}

	private JPanel createSidebar() {
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new BorderLayout());
		sidebar.setPreferredSize(new Dimension(200, 0));
		sidebar.setBackground(Color.WHITE);

		JPanel buttonPanel = createMenuButtons();
		sidebar.add(buttonPanel, BorderLayout.CENTER);

		panel = new JPanel();
		sidebar.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		btnLogout = new JButton("Đăng xuất",AppHelper.setSizeImage("/images/logout.png"));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(new Color(244, 67, 54));
		btnLogout.setOpaque(true);
		btnLogout.setBorderPainted(false);
		btnLogout.setPreferredSize(new Dimension(WIDTH, 50));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnLogout);
		
		JLabel titleLabel = new JLabel("",AppHelper.setSizeImage("/images/logo.png", 100, 100) ,SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setPreferredSize(new Dimension(WIDTH, 100));
		sidebar.add(titleLabel, BorderLayout.NORTH);

		return sidebar;
	}

	private JPanel createMenuButtons() {
		buttonPanel = new JPanel(new GridLayout(10, 1, 0, 0));
		buttonPanel.setBackground(new Color(51, 51, 51));
		
		Color colorButton = new Color(117, 117, 117);
		btnHome = new ButtonHelper("Home", AppHelper.setSizeImage("/images/home.png"),colorButton);
		btnBook = new ButtonHelper("Book", AppHelper.setSizeImage("/images/book.png"),colorButton);	
		btnSale = new ButtonHelper("Sale", AppHelper.setSizeImage("/images/receipt.png"),colorButton);	
		btnEmployee = new ButtonHelper("Employee", AppHelper.setSizeImage("/images/staff.png"),colorButton);	
		btnRevenue = new ButtonHelper("Revenue", AppHelper.setSizeImage("/images/revenue.png"),colorButton);
		
		buttonPanel.add(btnHome);
		buttonPanel.add(btnBook);
		buttonPanel.add(btnSale);
		buttonPanel.add(btnEmployee);
		buttonPanel.add(btnRevenue);

		return buttonPanel;
	}
	

	public void showContent(JPanel content) {
		contentPanel.removeAll();
		contentPanel.add(content, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
