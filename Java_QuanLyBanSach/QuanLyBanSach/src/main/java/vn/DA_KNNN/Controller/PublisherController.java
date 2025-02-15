package vn.DA_KNNN.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Model.DataProvider;
import vn.DA_KNNN.View.PublisherView;

public class PublisherController {
	private PublisherView view;
	private String query = "SELECT `PublisherId` as 'Mã nhà xuất bản', `PublisherName` as 'Tên nhà xuất bản', `Address` as 'Địa chỉ', `Contact` as 'Liên hệ', (SELECT COUNT(*) FROM book WHERE book.PublisherId = publisher.PublisherId) AS 'Số tác phẩm' FROM publisher";

	public PublisherController(PublisherView _view) {
		this.view = _view;

		setupEventListeners();
		loadData(query);
	}

	private void loadData(String sql) {
		DefaultTableModel model = AppHelper.loadDataTable(sql);
		view.getPublisherTable().setModel(model);
		tableRowClick(view.getPublisherTable(), model);
	}

	private void setupEventListeners() {
		view.getBtnAdd().addActionListener(e -> addPublisher());
		view.getBtnEdit().addActionListener(e -> editPublisher());
		view.getBtnDelete().addActionListener(e -> deletePublisher());
		view.getBtnCreateId().addActionListener(e -> createPublisherId());
		view.getSearchPanel().getBtnSearch().addActionListener(e -> findPublisherData());
		view.getSearchPanel().getBtnRefresh().addActionListener(e->{
			clearFields();
			view.getSearchPanel().getTxtSearch().setText("");
		});
	}

	private void addPublisher() {
		String publisherId = view.getTxtPublisherId().getText().trim();
		String publisherName = view.getTxtPublisherName().getText().trim();
		String address = view.getTxtAddress().getText().trim();
		String contact = view.getTxtContact().getText().trim();

		// Kiểm tra xem các trường dữ liệu có bị bỏ trống không
		if (publisherId.isEmpty() || publisherName.isEmpty() || address.isEmpty() || contact.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Kiểm tra nếu PublisherId là số hợp lệ (nếu có yêu cầu như vậy)
		try {
			Integer.parseInt(publisherId); // Kiểm tra nếu publisherId là số
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(view, "Mã nhà xuất bản phải là một số hợp lệ", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Xây dựng câu lệnh SQL với String.format
		String sql = String.format(
				"INSERT INTO publisher (PublisherId, PublisherName, Address, Contact) VALUES (%s, '%s', '%s', '%s')",
				publisherId, publisherName, address, contact);

		// Thực thi câu lệnh SQL và kiểm tra kết quả
		if (DataProvider.getInstance().insert(sql)) {
			JOptionPane.showMessageDialog(view, "Chèn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			loadData(query); // Reload dữ liệu bảng sau khi thêm mới
			clearFields(); // Xóa dữ liệu trên giao diện
		} else {
			JOptionPane.showMessageDialog(view, "Chèn thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void editPublisher() {
		String publisherId = view.getTxtPublisherId().getText();
		String publisherName = view.getTxtPublisherName().getText();
		String address = view.getTxtAddress().getText();
		String contact = view.getTxtContact().getText();

		if (publisherId.isEmpty() || publisherName.isEmpty() || address.isEmpty() || contact.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin.", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Hiển thị hộp thoại xác nhận
		int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn cập nhật thông tin nhà xuất bản?",
				"Xác nhận", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			String sql = "UPDATE publisher SET PublisherName = '" + publisherName + "', Address = '" + address + "', "
					+ "Contact = '" + contact + "' WHERE PublisherId = '" + publisherId + "'";

			// Thực thi câu lệnh SQL và kiểm tra kết quả
			if (DataProvider.getInstance().update(sql)) {
				JOptionPane.showMessageDialog(view, "Cập nhật thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // Reload dữ liệu bảng sau khi cập nhật
				clearFields(); // Xóa dữ liệu trên giao diện
			} else {
				JOptionPane.showMessageDialog(view, "Cập nhật thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void deletePublisher() {
		String publisherId = view.getTxtPublisherId().getText();

		if (publisherId.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn một nhà xuất bản để xóa.", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Hiển thị hộp thoại xác nhận
		int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nhà xuất bản này?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			String sql = "DELETE FROM publisher WHERE PublisherId = '" + publisherId + "'";

			// Thực thi câu lệnh SQL và kiểm tra kết quả
			if (DataProvider.getInstance().delete(sql)) {
				JOptionPane.showMessageDialog(view, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // Reload dữ liệu bảng sau khi xóa
				clearFields(); // Xóa dữ liệu trên giao diện
			} else {
				JOptionPane.showMessageDialog(view, "Xóa thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void findPublisherData() {
		// TODO Auto-generated method stub
		String id = view.getSearchPanel().getTxtSearch().getText();
		String name = view.getSearchPanel().getTxtSearch().getText();

		boolean isNumeric = id.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

		String sql;
		if (isNumeric) {
			sql = query + " WHERE PublisherId = " + id + " OR PublisherName LIKE '%" + name + "%'";
		} else {
			sql = query + " WHERE PublisherName LIKE '%" + name + "%'";
		}

		loadData(sql);
	}

	/** 🔹 Hàm dùng chung để lấy giá trị duy nhất */
	private String fetchSingleValue(String sql, String columnName) {
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			if (rs.next()) {
				return rs.getString(columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void createPublisherId() {
		// TODO Auto-generated method stub
		String nextId = fetchSingleValue("SELECT MAX(PublisherId) + 1 FROM publisher", "MAX(PublisherId) + 1");
		view.getTxtPublisherId().setText(nextId);
	}

	private void tableRowClick(JTable table, DefaultTableModel model) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Đảm bảo có hàng được chọn
					view.getTxtPublisherId().setText(model.getValueAt(selectedRow, 0).toString());
					view.getTxtPublisherName().setText(model.getValueAt(selectedRow, 1).toString());
					view.getTxtAddress().setText(model.getValueAt(selectedRow, 2).toString());
					view.getTxtContact().setText(model.getValueAt(selectedRow, 3).toString());
				}
			}
		});
	}

	private void clearFields() {
		view.getTxtPublisherId().setText("");
		view.getTxtPublisherName().setText("");
		view.getTxtAddress().setText("");
		view.getTxtContact().setText("");
	}
}
