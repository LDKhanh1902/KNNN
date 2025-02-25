package vn.DA_KNNN.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.View.AuthorView;

public class AuthorController {
	private AuthorView view;
	private final String QUERY_INSERT_AUTHOR = "INSERT INTO author (AuthorId, Name, BirthDate, Nationality) VALUES ('%s', '%s', '%s', '%s')";
	private final String QUERY_UPDATE_AUTHOR = "UPDATE author SET Name = '%s', BirthDate = '%s', Nationality = '%s' WHERE AuthorId = '%s'";
	private final String QUERY_DELETE_AUTHOR = "DELETE FROM author WHERE AuthorId = '%s'";
	private final String QUERY_CHECK_AUTHOR_ID = "SELECT AuthorId FROM author WHERE AuthorId = '%s'";
	private final String QUERY_GET_NEW_AUTHOR_ID = "SELECT MAX(AuthorId) + 1 FROM author";
	private String query = "SELECT `AuthorId` as 'Mã tác giả', `Name` as 'Tên tác giả', `BirthDate` as 'Ngày sinh',"
			+ "`Nationality` as 'Quóc tịch',(SELECT COUNT(*) FROM book WHERE book.AuthorId = author.AuthorId) AS 'Số tác phẩm'  FROM `author`";

	public AuthorController(AuthorView _view) {
		this.view = _view;

		setupEventListeners();
		loadData(query);
	}

	private void loadData(String sql) {
		DefaultTableModel model = AppHelper.loadDataTable(sql);
		tableRowClick(view.getAuthorTable(), model);
		view.getAuthorTable().setModel(model);
	}

	private void setupEventListeners() {
		view.getBtnAdd().addActionListener(e -> addAuthor());
		view.getBtnEdit().addActionListener(e -> editAuthor());
		view.getBtnDelete().addActionListener(e -> deleteAuthor());
		view.getBtnCreateId().addActionListener(e -> createAuthorId());
		view.getSearchPanel().getBtnSearch().addActionListener(e -> findAuthorData());
		view.getSearchPanel().getBtnRefresh().addActionListener(e -> {
			clearFields();
			view.getSearchPanel().getTxtSearch().setText("");
			loadData(query);
		});
	}

	private void findAuthorData() {
		String id =  view.getSearchPanel().getTxtSearch().getText();
		String name = view.getSearchPanel().getTxtSearch().getText();

		boolean isNumeric = id.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

		String sql;
		if (isNumeric) {
			sql = query + " WHERE AuthorId = " + id + " OR Name LIKE '%" + name + "%'";
		} else {
			sql = query + " WHERE Name LIKE '%" + name + "%'";
		}

		loadData(sql);
	}

	public String getNewAuthorId() {
		try (ResultSet rs = DataProvider.getInstance().view(QUERY_GET_NEW_AUTHOR_ID)) {
			if (rs.next()) {
				return rs.getString(1); // Trả về ID tiếp theo
			}
		} catch (Exception e) {
			e.printStackTrace(); // In lỗi nếu có
		}
		return ""; // Trả về chuỗi rỗng nếu không có kết quả hoặc có lỗi
	}

	/** ✅ Tạo mã sách tự động */
	private void createAuthorId() {
		String nextId = getNewAuthorId();
		view.getTxtAuthorId().setText(nextId);
	}

	private void deleteAuthor() {
		if (view.getTxtAuthorId().getText().isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng nhập mã sách cần xóa", "Thông báo", JOptionPane.OK_OPTION);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xoá sách này?", "Xác nhận xoá",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		// ✅ Lấy BookId từ TextField
		String AuthorId = view.getTxtAuthorId().getText().trim();
		String sql = String.format(QUERY_DELETE_AUTHOR, AuthorId);
		if (DataProvider.getInstance().delete(sql)) {
			JOptionPane.showMessageDialog(view, "Xóa sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			loadData(query);
			clearFields();
		} else {
			JOptionPane.showMessageDialog(view, "Lỗi khi xoá sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 8. Kiểm tra xem tác giả đã tồn tại trong cơ sở dữ liệu chưa
	public boolean existingAuthor(String authorId) {
		String sql = String.format(QUERY_CHECK_AUTHOR_ID, authorId);
		try {
			return DataProvider.getInstance().view(sql).first(); // Kiểm tra sự tồn tại của tác giả
		} catch (SQLException e) {
			e.printStackTrace(); // In lỗi nếu có
		}
		return false; // Trả về false nếu có lỗi hoặc không tìm thấy tác giả
	}

	private void editAuthor() {
		try {
			// ✅ Lấy dữ liệu từ giao diện
			String authorId = view.getTxtAuthorId().getText().trim();
			String authorName = view.getTxtAuthorName().getText().trim();
			String birthDate = view.getTxtBirthDate().getText().trim();
			String nationality = view.getTxtNationality().getText().trim();

			// ✅ Kiểm tra dữ liệu đầu vào
			if (authorId.isEmpty() || authorName.isEmpty() || birthDate.isEmpty() || nationality.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!existingAuthor(authorId)) {
				JOptionPane.showMessageDialog(view, "Mã tác giả không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// ✅ Xác nhận cập nhật dữ liệu
			int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn cập nhật thông tin tác giả này?",
					"Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION) {
				return;
			}

			String sql = String.format(QUERY_UPDATE_AUTHOR, authorName, birthDate, nationality, authorId);

			if (DataProvider.getInstance().update(sql)) {
				JOptionPane.showMessageDialog(view, "Cập nhật tác giả thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // ✅ Cập nhật lại bảng sau khi sửa
				clearFields(); // ✅ Xóa dữ liệu nhập sau khi sửa
			} else {
				JOptionPane.showMessageDialog(view, "Cập nhật tác giả thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addAuthor() {
		try {
			// ✅ Lấy dữ liệu từ giao diện
			String authorId = view.getTxtAuthorId().getText().trim();
			String authorName = view.getTxtAuthorName().getText().trim();
			String birthDate = view.getTxtBirthDate().getText().trim();
			String nationality = view.getTxtNationality().getText().trim();

			// ✅ Kiểm tra dữ liệu đầu vào
			if (authorId.isEmpty() || authorName.isEmpty() || birthDate.isEmpty() || nationality.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (existingAuthor(authorId)) {
				JOptionPane.showMessageDialog(view, "Mã tác giả đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String sql = String.format(QUERY_INSERT_AUTHOR, authorId, authorName, birthDate, nationality);

			if (DataProvider.getInstance().insert(sql)) {
				JOptionPane.showMessageDialog(view, "Thêm tác giả thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // ✅ Cập nhật lại bảng sau khi thêm
				clearFields(); // ✅ Xóa dữ liệu nhập sau khi thêm
			} else {
				JOptionPane.showMessageDialog(view, "Thêm tác giả thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void tableRowClick(JTable table, DefaultTableModel model) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				int rowCount = model.getRowCount();

				if (selectedRow != -1 && selectedRow < rowCount) { // Ensure row is within bounds
					view.getTxtAuthorId().setText(model.getValueAt(selectedRow, 0).toString());
					view.getTxtAuthorName().setText(model.getValueAt(selectedRow, 1).toString());
					view.getTxtBirthDate().setText(model.getValueAt(selectedRow, 2).toString());
					view.getTxtNationality().setText(model.getValueAt(selectedRow, 3).toString());
				}
			}

		});
	}

	private void clearFields() {
		view.getTxtAuthorId().setText("");
		view.getTxtAuthorName().setText("");
		view.getTxtBirthDate().setText("");
		view.getTxtNationality().setText("");
	}
}
