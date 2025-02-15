package vn.DA_KNNN.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;
import vn.DA_KNNN.Components.SearchHelper;
import vn.DA_KNNN.Components.TableHelper;

import java.awt.*;

public class PublisherView extends JPanel {

	private static final long serialVersionUID = 1L;

	// Khai báo các thành phần giao diện
	private JLabel lblPublisherId, lblPublisherName, lblAddress, lblContact;
	private JTextField txtPublisherId, txtPublisherName, txtAddress, txtContact;
	private JTable publisherTable;
	private JButton btnAdd, btnEdit, btnDelete, btnCreateId;
	private JPanel panelPublisherId;
	private SearchHelper searchPanel;

	public JTextField getTxtPublisherId() {
		return txtPublisherId;
	}

	public JTextField getTxtPublisherName() {
		return txtPublisherName;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public JTextField getTxtContact() {
		return txtContact;
	}

	public SearchHelper getSearchPanel() {
		return searchPanel;
	}

	public JTable getPublisherTable() {
		return publisherTable;
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
	
	public PublisherView() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		// Panel trên cùng chứa các trường nhập liệu
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông tin nhà xuất bản",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), Color.BLACK));
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
		gbl_inputPanel.columnWeights = new double[] { 0.0, 1.0 };
		inputPanel.setLayout(gbl_inputPanel);

		// Các nhãn và trường nhập liệu
		lblPublisherId = new JLabel("Mã nhà xuất bản:");
		lblPublisherId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPublisherName = new JLabel("Tên nhà xuất bản:");
		lblPublisherName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPublisherName = new JTextField(20);
		txtPublisherName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddress = new JLabel("Địa chỉ:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAddress = new JTextField(20);
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContact = new JLabel("Liên hệ:");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtContact = new JTextField(20);
		txtContact.setFont(new Font("Tahoma", Font.PLAIN, 20));

		// Sắp xếp các thành phần trong inputPanel
		GridBagConstraints gbc;

		// Dòng 1: Mã nhà xuất bản
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 0; // Dòng đầu tiên
		inputPanel.add(lblPublisherId, gbc);

		panelPublisherId = new JPanel();
		GridBagConstraints gbc_panelPublisherId = new GridBagConstraints();
		gbc_panelPublisherId.insets = new Insets(0, 0, 5, 0);
		gbc_panelPublisherId.fill = GridBagConstraints.BOTH;
		gbc_panelPublisherId.gridx = 1;
		gbc_panelPublisherId.gridy = 0;
		inputPanel.add(panelPublisherId, gbc_panelPublisherId);
		panelPublisherId.setLayout(new BoxLayout(panelPublisherId, BoxLayout.X_AXIS));

		txtPublisherId = new JTextField();
		txtPublisherId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelPublisherId.add(txtPublisherId);
		txtPublisherId.setColumns(10);

		btnCreateId = new JButton("Tạo");
		btnCreateId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelPublisherId.add(btnCreateId);

		// Dòng 2: Tên nhà xuất bản
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 1; // Dòng thứ hai
		inputPanel.add(lblPublisherName, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 1; // Dòng thứ hai
		inputPanel.add(txtPublisherName, gbc);

		// Dòng 3: Địa chỉ
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 2; // Dòng thứ ba
		inputPanel.add(lblAddress, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 2; // Dòng thứ ba
		inputPanel.add(txtAddress, gbc);

		// Dòng 4: Liên hệ
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; // Cột đầu tiên
		gbc.gridy = 3; // Dòng thứ tư
		inputPanel.add(lblContact, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1; // Cột thứ hai
		gbc.gridy = 3; // Dòng thứ tư
		inputPanel.add(txtContact, gbc);

		publisherTable = new TableHelper( SwingConstants.CENTER, 30);
		JScrollPane scrollTable = new JScrollPane(publisherTable);

		// Thêm các thành phần vào panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(Color.WHITE);
		topPanel.add(inputPanel, BorderLayout.CENTER);
		topPanel.add(createFunctionPanel(), BorderLayout.EAST);

		searchPanel = new SearchHelper();
		topPanel.add(searchPanel, BorderLayout.SOUTH);
		
		add(topPanel, BorderLayout.NORTH);
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
