package vn.DA_KNNN.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;
import vn.DA_KNNN.Components.SearchHelper;
import vn.DA_KNNN.Components.TableHelper;

import java.awt.*;

public class AuthorView extends JPanel {

	private static final long serialVersionUID = 1L;

	// Khai báo các thành phần giao diện
	private JLabel lblAuthorId, lblAuthorName, lblBirthDate, lblNationality;
	private JTextField txtAuthorName, txtBirthDate, txtNationality;
	private JTable authorTable;
	private JButton btnAdd, btnEdit, btnDelete,btnCreateId;
	private SearchHelper searchPanel;
	private JPanel paneAuthorId;
	private JTextField txtAuthorId;

	public JTextField getTxtAuthorId() {
		return txtAuthorId;
	}

	public JTextField getTxtAuthorName() {
		return txtAuthorName;
	}

	public JTextField getTxtBirthDate() {
		return txtBirthDate;
	}

	public JTextField getTxtNationality() {
		return txtNationality;
	}

	public JTable getAuthorTable() {
		return authorTable;
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
	
	public SearchHelper getSearchPanel(){
		return searchPanel;
	}
	
	public AuthorView() {
		setLayout(new BorderLayout());

		// Panel trên cùng chứa các trường nhập liệu
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông tin tác giả",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
		gbl_inputPanel.columnWeights = new double[] { 0.0, 1.0 };
		inputPanel.setLayout(gbl_inputPanel);

		// Các nhãn và trường nhập liệu
		lblAuthorId = new JLabel("Mã tác giả:");
		lblAuthorId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthorName = new JLabel("Tên tác giả:");
		lblAuthorName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAuthorName = new JTextField(20);
		txtAuthorName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBirthDate = new JLabel("Ngày sinh:");
		lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBirthDate = new JTextField(20);
		txtBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNationality = new JLabel("Quốc tịch:");
		lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNationality = new JTextField(20);
		txtNationality.setFont(new Font("Tahoma", Font.PLAIN, 20));

		// Sắp xếp các thành phần trong inputPanel
		GridBagConstraints gbc;

		// Dòng 1: Mã tác giả
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 0; // Dòng đầu tiên
		inputPanel.add(lblAuthorId, gbc);

		paneAuthorId = new JPanel();
		GridBagConstraints gbc_paneAuthorId = new GridBagConstraints();
		gbc_paneAuthorId.insets = new Insets(0, 0, 5, 0);
		gbc_paneAuthorId.fill = GridBagConstraints.BOTH;
		gbc_paneAuthorId.gridx = 1;
		gbc_paneAuthorId.gridy = 0;
		inputPanel.add(paneAuthorId, gbc_paneAuthorId);
		paneAuthorId.setLayout(new BoxLayout(paneAuthorId, BoxLayout.X_AXIS));

		txtAuthorId = new JTextField();
		txtAuthorId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		paneAuthorId.add(txtAuthorId);
		txtAuthorId.setColumns(10);

		btnCreateId = new JButton("Tạo");
		btnCreateId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		paneAuthorId.add(btnCreateId);

		// Dòng 2: Tên tác giả
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 1; // Dòng thứ hai
		inputPanel.add(lblAuthorName, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 1; // Dòng thứ hai
		inputPanel.add(txtAuthorName, gbc);

		// Dòng 3: Ngày sinh
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 2; // Dòng thứ ba
		inputPanel.add(lblBirthDate, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 2; // Dòng thứ ba
		inputPanel.add(txtBirthDate, gbc);

		// Dòng 4: Quốc tịch
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 3; // Dòng thứ tư
		inputPanel.add(lblNationality, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 3; // Dòng thứ tư
		inputPanel.add(txtNationality, gbc);

		authorTable = new TableHelper(SwingConstants.CENTER, 30);
		JScrollPane scrollTable = new JScrollPane(authorTable);

		// Thêm các thành phần vào panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);
		topPanel.add(inputPanel, BorderLayout.CENTER);
		topPanel.add(createFunctionPanel(), BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);
		
		searchPanel = new SearchHelper();
		topPanel.add(searchPanel, BorderLayout.SOUTH);
		add(scrollTable, BorderLayout.CENTER);
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
