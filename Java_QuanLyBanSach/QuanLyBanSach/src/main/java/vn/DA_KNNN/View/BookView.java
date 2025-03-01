package vn.DA_KNNN.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;
import vn.DA_KNNN.Components.SearchHelper;
import vn.DA_KNNN.Components.TableHelper;

import java.awt.*;

public class BookView extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblBookId, lblBookName, lblEntryDate, lblPublisher, lblPublicationYear, lblQuantity, lblAuthor,
			lblPrice, lblCategory, lblPurchasePrice;
	private JTextField txtBookId, txtBookName, txtEntryDate, txtPublicationYear, txtQuantity, txtAuthor, txtPrice,
			txtPurchasePrice;
	private JComboBox<String> cmbCategory, cmbPublisher;
	private JButton btnAdd, btnEdit, btnDelete, btnCreateId;
	private JTable table;
	private SearchHelper searchPanel;
	private String[] categories;
	private String[] publisher;

	public BookView() {
		setLayout(new BorderLayout(10, 10));

		// Tạo panel chứa thông tin sách và chức năng
		JPanel topPanel = new JPanel(new BorderLayout(10, 10));
		topPanel.setBackground(Color.WHITE);

		// Panel thông tin sách
		JPanel bookInfoPanel = createBookInfoPanel();
		topPanel.add(bookInfoPanel, BorderLayout.CENTER);

		// Panel chức năng
		JPanel functionPanel = createFunctionPanel();
		topPanel.add(functionPanel, BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);

		// Panel bảng hiển thị sách
		JPanel tablePanel = createTablePanel();

		searchPanel = new SearchHelper();
		topPanel.add(searchPanel, BorderLayout.SOUTH);
		
		add(tablePanel, BorderLayout.CENTER);

	}

	/**
	 * Tạo panel chứa thông tin sách
	 */
	private JPanel createBookInfoPanel() {
		JPanel bookInfoPanel = new JPanel(new GridLayout(5, 2, 10, 10));
		bookInfoPanel.setBackground(Color.WHITE);
		bookInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Thông tin sách", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.BLACK));

		lblBookId = new JLabel("Mã sách:");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JPanel panelTexrBookId = new JPanel();
		btnCreateId = new JButton("Tạo");
		btnCreateId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTexrBookId.setLayout(new BoxLayout(panelTexrBookId, BoxLayout.X_AXIS));
		txtBookId = new JTextField(10);
		txtBookId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTexrBookId.add(txtBookId);
		panelTexrBookId.add(btnCreateId);
		lblBookName = new JLabel("Tên sách:");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBookName = new JTextField(10);
		txtBookName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEntryDate = new JLabel("Ngày nhập:");
		lblEntryDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEntryDate = new JTextField(10);
		txtEntryDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPublicationYear = new JLabel("Năm xuất bản:");
		lblPublicationYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPublicationYear = new JTextField(10);
		txtPublicationYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantity = new JLabel("Số lượng:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtQuantity = new JTextField(10);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAuthor = new JLabel("Tác giả:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAuthor = new JTextField(10);
		txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice = new JLabel("Giá bán:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrice = new JTextField(10);
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategory = new JLabel("Thể loại:");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbCategory = new JComboBox<>();
		cmbCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPurchasePrice = new JLabel("Giá nhập:");
		lblPurchasePrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPurchasePrice = new JTextField(10);
		txtPurchasePrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPublisher = new JLabel("Nhà xuất bản:");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbPublisher = new JComboBox<>();
		cmbPublisher.setFont(new Font("Tahoma", Font.PLAIN, 20));

		bookInfoPanel.add(lblBookId);
		bookInfoPanel.add(panelTexrBookId);
		bookInfoPanel.add(lblBookName);
		bookInfoPanel.add(txtBookName);
		bookInfoPanel.add(lblEntryDate);
		bookInfoPanel.add(txtEntryDate);
		bookInfoPanel.add(lblPublicationYear);
		bookInfoPanel.add(txtPublicationYear);
		bookInfoPanel.add(lblQuantity);
		bookInfoPanel.add(txtQuantity);
		bookInfoPanel.add(lblAuthor);
		bookInfoPanel.add(txtAuthor);
		bookInfoPanel.add(lblPrice);
		bookInfoPanel.add(txtPrice);
		bookInfoPanel.add(lblCategory);
		bookInfoPanel.add(cmbCategory);
		bookInfoPanel.add(lblPurchasePrice);
		bookInfoPanel.add(txtPurchasePrice);
		bookInfoPanel.add(lblPublisher);
		bookInfoPanel.add(cmbPublisher);

		return bookInfoPanel;
	}

	/**
	 * Tạo panel chứa các chức năng (Thêm, Sửa, Xóa)
	 */
	private JPanel createFunctionPanel() {
		JPanel functionPanel = new JPanel();
		functionPanel.setBackground(Color.WHITE);
		functionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Chức năng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.BLACK));

		functionPanel.setPreferredSize(new Dimension(160, 0));
		functionPanel.setLayout(new GridLayout(3, 1, 5, 5));

		btnAdd = new ButtonHelper("Thêm",AppHelper.setSizeImage("/images/add.png",40,40),new Color(139, 195, 74));	
		btnEdit = new ButtonHelper("Sửa",AppHelper.setSizeImage("/images/edit.png",40,40),new Color(255, 159, 64));
		btnDelete = new ButtonHelper("Xóa",AppHelper.setSizeImage("/images/delete.png",40,40),new Color(244, 67, 54));

		functionPanel.add(btnAdd);
		functionPanel.add(btnEdit);
		functionPanel.add(btnDelete);

		return functionPanel;
	}

	/**
	 * Tạo bảng hiển thị sách
	 */
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
	public JTextField getTxtBookId() {
		return txtBookId;
	}

	public JTextField getTxtBookName() {
		return txtBookName;
	}

	public JTextField getTxtEntryDate() {
		return txtEntryDate;
	}

	public JTextField getTxtPublicationYear() {
		return txtPublicationYear;
	}

	public JTextField getTxtQuantity() {
		return txtQuantity;
	}

	public JTextField getTxtAuthor() {
		return txtAuthor;
	}

	public JTextField getTxtPrice() {
		return txtPrice;
	}

	public JTextField getTxtPurchasePrice() {
		return txtPurchasePrice;
	}

	public JComboBox<String> getCmbCategory() {
		return cmbCategory;
	}

	public JComboBox<String> getCmbPublisher() {
		return cmbPublisher;
	}

	public JTable getTable() {
		return table;
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

	public JButton getBtnCreateId() {
		return btnCreateId;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
		updateComboBox(cmbCategory, categories);
	}

	public String[] getPublisher() {
		return publisher;
	}

	public void setPublisher(String[] publisher) {
		this.publisher = publisher;
		updateComboBox(cmbPublisher, publisher);
	}

	public SearchHelper getSearchPanel() {
		return searchPanel;
	}
	// ==========================
	// HELPER METHODS
	// ==========================

	private void updateComboBox(JComboBox<String> comboBox, String[] data) {
		comboBox.removeAllItems();
		for (String item : data) {
			comboBox.addItem(item);
		}
		revalidate();
		repaint();
	}
}
