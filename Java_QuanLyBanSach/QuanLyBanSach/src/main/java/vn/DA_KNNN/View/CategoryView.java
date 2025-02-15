package vn.DA_KNNN.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;
import vn.DA_KNNN.Components.SearchHelper;
import vn.DA_KNNN.Components.TableHelper;

import java.awt.*;

public class CategoryView extends JPanel {

	private static final long serialVersionUID = 1L;

	// Khai báo các thành phần giao diện
	private JLabel lblCategoryId, lblCategoryName, lblCategoryDescription;
	private JTextField txtCategoryName, txtCategoryId;
	private JTextArea txtCategoryDescription;
	private JTable categoryTable;
	private JButton btnAdd, btnEdit, btnDelete, btnCreateId;
	private JPanel panelCategoryId;
	private SearchHelper searchPanel;

	public JTextField getTxtCategoryName() {
		return txtCategoryName;
	}

	public JTextField getTxtCategoryId() {
		return txtCategoryId;
	}

	public JTextArea getTxtCategoryDescription() {
		return txtCategoryDescription;
	}

	public SearchHelper getSearchPanel() {
		return searchPanel;
	}

	public JTable getCategoryTable() {
		return categoryTable;
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
	
	public CategoryView() {
		setLayout(new BorderLayout());

		// Panel trên cùng chứa các trường nhập liệu
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông tin thể loại",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.rowWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_inputPanel.columnWeights = new double[] { 0.0, 1.0 };
		inputPanel.setLayout(gbl_inputPanel);

		// Các nhãn và trường nhập liệu
		lblCategoryId = new JLabel("Mã thể loại:");
		lblCategoryId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCategoryName = new JLabel("Tên thể loại:");
		lblCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCategoryName = new JTextField(20);
		txtCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategoryDescription = new JLabel("Mô tả:");
		lblCategoryDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategoryDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCategoryDescription = new JTextArea(5, 20);
		txtCategoryDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JScrollPane scrollCategoryDescription = new JScrollPane(txtCategoryDescription);

		// Sắp xếp các thành phần trong inputPanel
		GridBagConstraints gbc;

		// Dòng 1: Mã thể loại
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 0; // Dòng đầu tiên
		inputPanel.add(lblCategoryId, gbc);

		panelCategoryId = new JPanel();
		GridBagConstraints gbc_panelCategoryId = new GridBagConstraints();
		gbc_panelCategoryId.insets = new Insets(0, 0, 5, 0);
		gbc_panelCategoryId.fill = GridBagConstraints.BOTH;
		gbc_panelCategoryId.gridx = 1;
		gbc_panelCategoryId.gridy = 0;
		inputPanel.add(panelCategoryId, gbc_panelCategoryId);
		panelCategoryId.setLayout(new BoxLayout(panelCategoryId, BoxLayout.X_AXIS));

		txtCategoryId = new JTextField();
		txtCategoryId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelCategoryId.add(txtCategoryId);
		txtCategoryId.setColumns(10);

		btnCreateId = new JButton("Tạo");
		btnCreateId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCategoryId.add(btnCreateId);

		// Dòng 2: Tên thể loại
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 1; // Dòng thứ hai
		inputPanel.add(lblCategoryName, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 1; // Dòng thứ hai
		inputPanel.add(txtCategoryName, gbc);

		// Dòng 3: Mô tả
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 2; // Dòng thứ ba
		inputPanel.add(lblCategoryDescription, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 2; // Dòng thứ ba
		inputPanel.add(scrollCategoryDescription, gbc);

		categoryTable = new TableHelper( SwingConstants.CENTER, 30);

		// Set font cho nội dung bảng
		JScrollPane scrollCategoryTable = new JScrollPane(categoryTable);

		// Thêm các thành phần vào panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);
		topPanel.add(inputPanel, BorderLayout.CENTER);
		topPanel.add(createFunctionPanel(), BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);

		searchPanel = new SearchHelper();
		topPanel.add(searchPanel, BorderLayout.SOUTH);
		add(scrollCategoryTable, BorderLayout.CENTER);
	}

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

}
