package vn.DA_KNNN.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;
import vn.DA_KNNN.Components.TableHelper;

import java.awt.*;

public class SaleView extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField searchField, quantityField;
	private JTable bookTable, cartTable;
	private DefaultTableModel bookModel, cartModel;
	private JLabel totalPriceLabel;
	private JButton payButton, addButton, removeButton;

	public SaleView() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(10, 10));

		// **🔎 Search Panel**
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.WHITE);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		JLabel lblTmKimSch = new JLabel(" Tìm kiếm sách : ");
		lblTmKimSch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchPanel.add(lblTmKimSch);
		searchField = new JTextField(20);
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchPanel.add(searchField);
		add(searchPanel, BorderLayout.NORTH);

		bookTable = new TableHelper(SwingConstants.CENTER, 30);
		JScrollPane bookScrollPane = new JScrollPane(bookTable);

		JPanel bookPanel = new JPanel(new BorderLayout());
		bookPanel.setBackground(Color.WHITE);
		bookPanel.add(bookScrollPane, BorderLayout.CENTER);

		JPanel addPanel = new JPanel();
		addPanel.setBackground(Color.WHITE);
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.X_AXIS));
		quantityField = new JTextField(5);
		quantityField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addButton = new ButtonHelper("Thêm vào giỏ",AppHelper.setSizeImage("/images/add.png",40,40),new Color(0, 128, 192));
		
		JLabel lblSLng = new JLabel(" Số lượng:");
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addPanel.add(lblSLng);
		addPanel.add(quantityField);
		addPanel.add(addButton);

		bookPanel.add(addPanel, BorderLayout.SOUTH);

		// **🛒 Cart Panel with Table**
		JPanel cartPanel = new JPanel(new BorderLayout());
		cartPanel.setBackground(Color.WHITE);
		String[] cartColumns = { "Tên sách", "Giá (VNĐ)", "Số lượng", "Tổng giá" };
		cartModel = new DefaultTableModel(cartColumns, 0);
		cartTable = new TableHelper(SwingConstants.CENTER, 30);
		cartTable.setModel(cartModel);
		JScrollPane cartScrollPane = new JScrollPane(cartTable);
		cartPanel.add(cartScrollPane, BorderLayout.CENTER);

		removeButton = new ButtonHelper("Xóa mục đã chọn", AppHelper.setSizeImage("/images/delete.png", 40, 40), new Color(240, 74, 66));

		// Tạo panel cho nút và sử dụng BoxLayout để căn phải
		JPanel removePanel = new JPanel();
		removePanel.setBackground(Color.WHITE);
		removePanel.setLayout(new BoxLayout(removePanel, BoxLayout.X_AXIS));
		removePanel.add(Box.createHorizontalGlue()); 
		removePanel.add(removeButton);
		cartPanel.add(removePanel, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		panel.add(bookPanel);
		panel.add(cartPanel);
		add(panel, BorderLayout.CENTER);
		
		
		// **💰 Total & Buttons**
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(Color.WHITE);
		JPanel totalPanel = new JPanel();
		totalPanel.setBackground(Color.WHITE);
		totalPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		totalPanel.add(lblNewLabel_1);
		totalPriceLabel = new JLabel("Tổng tiền: 0 VNĐ",AppHelper.setSizeImage("/images/money_bag.png",40,40),SwingConstants.RIGHT);
		totalPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalPanel.add(totalPriceLabel);
		bottomPanel.add(totalPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		payButton = new ButtonHelper("Thanh Toán",AppHelper.setSizeImage("/images/pay.png",40,40),new Color(76, 175, 80));
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(payButton);
		bottomPanel.add(buttonPanel, BorderLayout.EAST);

		add(bottomPanel, BorderLayout.SOUTH);
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public JTextField getQuantityField() {
		return quantityField;
	}

	public JTable getBookTable() {
		return bookTable;
	}

	public JTable getCartTable() {
		return cartTable;
	}

	public DefaultTableModel getBookModel() {
		return bookModel;
	}
	
	public void setBookModel(DefaultTableModel data) {
		bookTable.setModel(data);
	}

	public DefaultTableModel getCartModel() {
		return cartModel;
	}

	public JLabel getTotalPriceLabel() {
		return totalPriceLabel;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getPayButton() {
		return payButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}
}