package vn.DA_KNNN.Controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.DA_KNNN.Model.DTO.Role;
import vn.DA_KNNN.Model.DTO.User;
import vn.DA_KNNN.View.BookCatalogView;
import vn.DA_KNNN.View.EmployeeView;
import vn.DA_KNNN.View.LayoutView;
import vn.DA_KNNN.View.LoginView;
import vn.DA_KNNN.View.MainView;
import vn.DA_KNNN.View.RevenueView;
import vn.DA_KNNN.View.SaleView;

public class LayoutController {
	private LayoutView layout;
	private JButton selectedButton = null;

	public LayoutController(LayoutView _layout) {
		this.layout = _layout;
		
		layout.getBtnHome().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView view = new MainView();
				new MainController(view);
				layout.showContent(view);
			}
		});

		layout.getBtnBook().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(User.isWarehouseKeeper()) {
					JOptionPane.showMessageDialog(layout,"Không thể thực hiện","Thông báo",JOptionPane.WARNING_MESSAGE);
					return;
				}
				// TODO Auto-generated method stub
				BookCatalogView view = new BookCatalogView();
				new BookCatalogController(view);
				layout.showContent(view);
			}
		});

		layout.getBtnSale().addActionListener(e -> {
			SaleView view = new SaleView();
			new SaleController(view);
			layout.showContent(view);
		});

		layout.getBtnEmployee().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!User.isAdmin()) {
					JOptionPane.showMessageDialog(layout,"Không thể thực hiện","Thông báo",JOptionPane.WARNING_MESSAGE);
					return;
				}
				// TODO Auto-generated method stub
				EmployeeView view = new EmployeeView();
				new EmployeeController(view);
				layout.showContent(view);
			}
		});

		layout.getBtnRevenue().addActionListener(e -> {
			if(!User.isAdmin()) {
				JOptionPane.showMessageDialog(layout,"Không thể thực hiện" + Role.checkRoleById(User.getUser().getPositionId()),"Thông báo",JOptionPane.WARNING_MESSAGE);
				return;
			}
			RevenueView view = new RevenueView();
			new RevenueController(view);
			layout.showContent(view);
		});

		layout.getBtnLogout().addActionListener(e -> {
			layout.dispose();
			User.logout();
			LoginView view = new LoginView();
			new LoginController(view);
			view.setVisible(true);
		});

		for (Component comp : layout.getMenuButton().getComponents()) {
			// Kiểm tra xem component có phải là JButton không
			if (comp instanceof JButton) {
				JButton button = (JButton) comp;
				// Thay đổi màu sắc của JButton
				addButtonListener(button);
			}
		}
	}
	

	private void addButtonListener(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Nếu đã có nút được chọn trước đó, đặt lại màu nền
				if (selectedButton != null) {
					selectedButton.setBackground(new Color(117, 117, 117));
				}

				// Đặt màu mới cho nút được click
				button.setBackground(new Color(25, 118, 210));
				button.setOpaque(true);
				button.setBorderPainted(false);
				// Cập nhật nút hiện tại là nút đã chọn
				selectedButton = button;
			}
		});
	}
}
