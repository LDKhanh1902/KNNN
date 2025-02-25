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
import vn.DA_KNNN.View.CategoryView;

public class CategoryController {
	private CategoryView view;
	private String query = "SELECT `CategoryId` as 'Mã thể loại', `CategoryName` as 'Tên thể loại', `Description` as 'Mô tả', (SELECT COUNT(*) FROM book WHERE book.CategoryId = category.CategoryId) AS 'Số tác phẩm' FROM category";
	private final String QUERY_INSERT_CATEGORY = "INSERT INTO category (CategoryId, CategoryName, Description) VALUES ('%s', '%s', '%s')";
    private final String QUERY_UPDATE_CATEGORY = "UPDATE category SET CategoryName = '%s', Description = '%s' WHERE CategoryId = '%s'";
    private final String QUERY_DELETE_CATEGORY = "DELETE FROM category WHERE CategoryId = '%s'"; 
    private final String QUERY_CHECK_CATEGORY_ID = "SELECT CategoryId FROM category WHERE CategoryId = '%s'";
    private final String QUERY_GET_NEW_CATEGORY_ID = "SELECT MAX(CategoryId) + 1 FROM category";

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
			loadData(query);
		});
	}

	private void findCategoryData() {
		String keywork = view.getSearchPanel().getTxtSearch().getText();
		
		boolean isNumeric = keywork.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

        String sql = "";
        if (isNumeric) {
            sql = "SELECT * FROM category WHERE CategoryId = " + keywork + " OR CategoryName LIKE '%" + keywork + "%'";
        } else {
            sql = "SELECT * FROM category WHERE CategoryName LIKE '%" + keywork + "%'";
        }
		
		loadData(sql);
	}

	public boolean existingCategory(String categoryId) {
        String sql = String.format(QUERY_CHECK_CATEGORY_ID, categoryId);
        try (ResultSet rs = DataProvider.getInstance().view(sql)){
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu số lượng dòng trả về lớn hơn 0, nghĩa là đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return false;  // Trả về false nếu có lỗi hoặc không tìm thấy thể loại
    }
	
	public String getNewCategoryId() {
        try (ResultSet rs = DataProvider.getInstance().view(QUERY_GET_NEW_CATEGORY_ID)) {
            if (rs.next()) {
                return rs.getString(1);  // Trả về ID tiếp theo
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return "";  // Trả về chuỗi rỗng nếu không có kết quả hoặc có lỗi
    }

	private void createCategoryId() {
		String nextId = getNewCategoryId();
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

			String sql = String.format(QUERY_DELETE_CATEGORY, categoryId);
			
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
			
			String sql = String.format(QUERY_UPDATE_CATEGORY,categoryName,categoryDescription, categoryId);
			
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

			if (existingCategory(categoryId)) {
				JOptionPane.showMessageDialog(view, "Mã thể loại đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String sql = String.format(QUERY_INSERT_CATEGORY, categoryId,categoryName,categoryDescription);
			
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
			    int rowCount = model.getRowCount();
			    
			    if (selectedRow != -1 && selectedRow < rowCount) { // Ensure row is within bounds
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
