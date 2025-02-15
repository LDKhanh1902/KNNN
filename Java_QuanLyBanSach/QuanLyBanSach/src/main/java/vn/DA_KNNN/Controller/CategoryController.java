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
import vn.DA_KNNN.View.CategoryView;

public class CategoryController {
	private CategoryView view;
	private String query = "SELECT `CategoryId` as 'Mã thể loại', `CategoryName` as 'Tên thể loại', `Description` as 'Mô tả', (SELECT COUNT(*) FROM book WHERE book.CategoryId = category.CategoryId) AS 'Số tác phẩm' FROM category";

	public CategoryController(CategoryView _view) {
		view = _view;
		setupEventListeners();
		loadData(query);
	}

	private void loadData(String sql) {
		DefaultTableModel model = AppHelper.loadDataTable(sql);
		tableRowClick(view.getCategoryTable(), model);
		view.getCategoryTable().setModel(model);
	}

	private void setupEventListeners() {
		view.getBtnAdd().addActionListener(e -> addCategory());
		view.getBtnEdit().addActionListener(e -> editCategory());
		view.getBtnDelete().addActionListener(e -> deleteCategory());
		view.getBtnCreateId().addActionListener(e -> createCategoryId());
		view.getSearchPanel().getBtnSearch().addActionListener(e -> findCategoryData());
		view.getSearchPanel().getBtnRefresh().addActionListener(e->{
			clearFields();
			view.getSearchPanel().getTxtSearch().setText("");
		});
	}

	private void findCategoryData() {
		String id = view.getSearchPanel().getTxtSearch().getText();
		String name = view.getSearchPanel().getTxtSearch().getText();

		boolean isNumeric = id.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

		String sql;
		if (isNumeric) {
			sql = query + " WHERE CategoryId = " + id + " OR CategoryName LIKE '%" + name + "%'";
		} else {
			sql = query + " WHERE CategoryName LIKE '%" + name + "%'";
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

	private void createCategoryId() {
		String nextId = fetchSingleValue("SELECT MAX(CategoryId) + 1 FROM category", "MAX(CategoryId) + 1");
		view.getTxtCategoryId().setText(nextId);
	}

	private void deleteCategory() {
		try {
			// Lấy CategoryId từ TextField
			String categoryId = view.getTxtCategoryId().getText().trim();

			if (categoryId.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng nhập mã thể loại cần xóa!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Xác nhận xóa thể loại
			int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa thể loại này?", "Xác nhận xóa",
					JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION) {
				return;
			}

			// Câu lệnh SQL xóa thể loại
			String sql = String.format("DELETE FROM category WHERE CategoryId = '%s'", categoryId);

			if (DataProvider.getInstance().delete(sql)) {
				JOptionPane.showMessageDialog(view, "Xóa thể loại thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // Cập nhật lại dữ liệu sau khi xóa
				clearFields(); // Xóa các trường nhập liệu
			} else {
				JOptionPane.showMessageDialog(view, "Xóa thể loại thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void editCategory() {
		try {
			// Lấy dữ liệu từ giao diện
			String categoryId = view.getTxtCategoryId().getText().trim();
			String categoryName = view.getTxtCategoryName().getText().trim();
			String categoryDescription = view.getTxtCategoryDescription().getText().trim();

			if (categoryId.isEmpty() || categoryName.isEmpty() || categoryDescription.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Xác nhận cập nhật thông tin thể loại
			int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn cập nhật thông tin thể loại này?",
					"Xác nhận cập nhật", JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION) {
				return;
			}

			// Câu lệnh SQL cập nhật thể loại
			String sql = String.format(
					"UPDATE category SET CategoryName = '%s', Description = '%s' WHERE CategoryId = '%s'", categoryName,
					categoryDescription, categoryId);

			if (DataProvider.getInstance().update(sql)) {
				JOptionPane.showMessageDialog(view, "Cập nhật thể loại thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // Cập nhật lại bảng sau khi sửa
				clearFields(); // Xóa dữ liệu nhập sau khi sửa
			} else {
				JOptionPane.showMessageDialog(view, "Cập nhật thể loại thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addCategory() {
		try {
			// Lấy dữ liệu từ giao diện
			String categoryId = view.getTxtCategoryId().getText().trim();
			String categoryName = view.getTxtCategoryName().getText().trim();
			String categoryDescription = view.getTxtCategoryDescription().getText().trim();

			if (categoryId.isEmpty() || categoryName.isEmpty() || categoryDescription.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Kiểm tra xem `CategoryId` đã tồn tại trong cơ sở dữ liệu chưa
			String existingCategory = fetchSingleValue(
					"SELECT CategoryId FROM category WHERE CategoryId = '" + categoryId + "'", "CategoryId");
			if (!existingCategory.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Mã thể loại đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Câu lệnh SQL thêm thể loại mới
			String sql = String.format(
					"INSERT INTO category (CategoryId, CategoryName, Description) VALUES ('%s', '%s', '%s')",
					categoryId, categoryName, categoryDescription);

			if (DataProvider.getInstance().insert(sql)) {
				JOptionPane.showMessageDialog(view, "Thêm thể loại thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData(query); // Cập nhật lại bảng sau khi thêm
				clearFields(); // Xóa dữ liệu nhập sau khi thêm
			} else {
				JOptionPane.showMessageDialog(view, "Thêm thể loại thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void tableRowClick(JTable table, DefaultTableModel model) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Đảm bảo có hàng được chọn
					view.getTxtCategoryId().setText(model.getValueAt(selectedRow, 0).toString());
					view.getTxtCategoryName().setText(model.getValueAt(selectedRow, 1).toString());
					view.getTxtCategoryDescription().setText(model.getValueAt(selectedRow, 2).toString());
				}
			}
		});
	}

	private void clearFields() {
		view.getTxtCategoryId().setText("");
		view.getTxtCategoryName().setText("");
		view.getTxtCategoryDescription().setText("");
	}
}
