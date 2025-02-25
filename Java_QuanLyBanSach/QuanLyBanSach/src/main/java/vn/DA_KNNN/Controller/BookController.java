package vn.DA_KNNN.Controller;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.View.BookView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookController {
	private final BookView view;
	private String query = "SELECT book.BookId as 'Mã Sách', book.Title as 'Tên sách', author.Name AS 'Tác giả', publisher.PublisherName AS 'Nhà Xuất bản', category.CategoryName as 'Thể loại', "
			+ "book.PublicationDate as 'Năm xuất bản', book.PurchasePrice as 'Giá nhập', book.Price as 'Giá bán', book.Quantity as 'Số lượng', book.EntryDate as 'Ngày nhập' "
			+ "FROM book JOIN category ON book.CategoryId = category.CategoryId "
			+ "JOIN publisher ON book.PublisherId = publisher.PublisherId "
			+ "JOIN author ON author.AuthorId = book.AuthorId";
	private final String QUERY_INSERT_BOOK = "INSERT INTO book (BookId, Title, PublisherId, CategoryId, PublicationDate, Price, AuthorId, EntryDate, PurchasePrice, Quantity) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"; 
	private final String QUERY_UPDATE_BOOK = "UPDATE book SET Title = '%s', AuthorId = (SELECT AuthorId FROM author WHERE Name = '%s'), PublisherId = (SELECT PublisherId FROM publisher WHERE PublisherName = '%s'), CategoryId = (SELECT CategoryId FROM category WHERE CategoryName = '%s'), PublicationDate = '%s', PurchasePrice = '%s', Price = '%s', Quantity = '%s', EntryDate = '%s' WHERE BookId = '%s'"; 
	private final String QUERY_DELETE_BOOK = "DELETE FROM book WHERE BookId = '%s'"; 
	private final String QUERY_GET_NEW_BOOK_ID = "SELECT MAX(BookId) + 1 FROM book"; 
	private final String QUERY_GET_AUTHOR_ID = "SELECT `AuthorId` FROM `author` WHERE `Name` = '%s'";
	private final String QUERY_GET_CATEGORY_ID = "SELECT CategoryId FROM category WHERE CategoryName = '%s'";
	private final String QUERY_GET_PUBLISHER_ID = "SELECT PublisherId FROM publisher WHERE PublisherName = '%s'";

	public BookController(BookView _view) {
		this.view = _view;
		setupEventListeners();
		loadComboBoxData();
		loadData(query);
	}

	private void loadData(String sql) {
		DefaultTableModel model = AppHelper.loadDataTable(sql);
		tableRowClick(view.getTable(), model);
		view.getTable().setModel(model);
	}

	/** 🔹 Thiết lập các sự kiện cho các nút */
	private void setupEventListeners() {
		view.getBtnAdd().addActionListener(e -> addBook());
		view.getBtnEdit().addActionListener(e -> editBook());
		view.getBtnDelete().addActionListener(e -> deleteBook());
		view.getBtnCreateId().addActionListener(e -> createBookId());
		view.getSearchPanel().getBtnSearch().addActionListener(e -> findBookData());
		view.getSearchPanel().getBtnRefresh().addActionListener(e->{
			clearFields();
			view.getSearchPanel().getTxtSearch().setText("");
			loadData(query);
		});
	}

	/** 🔹 Load dữ liệu thể loại & nhà xuất bản vào combobox */
	private void loadComboBoxData() {
		view.setCategories(getCategories());
		view.setPublisher(getPublishers());
	}
	
	public String[] getCategories() {
		return fetchColumnData("SELECT CategoryName FROM category", "CategoryName");
	}

	/** ✅ Load danh sách nhà xuất bản */
	public String[] getPublishers() {
		return fetchColumnData("SELECT PublisherName FROM publisher", "PublisherName");
	}
	
	/** 🔹 Hàm dùng chung để lấy dữ liệu từ một cột */
	private String[] fetchColumnData(String sql, String columnName) {
		List<String> dataList = new ArrayList<>();
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			while (rs.next()) {
				dataList.add(rs.getString(columnName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataList.toArray(new String[0]);
	}
	
	/** ✅ Thêm sách vào cơ sở dữ liệu */
	private void addBook() {
		try {
			// Lấy dữ liệu từ giao diện
			String bookId = view.getTxtBookId().getText();
			String title = view.getTxtBookName().getText();
			String entryDate = view.getTxtEntryDate().getText();
			String quantity = view.getTxtQuantity().getText();
			String publicationDate = view.getTxtPublicationYear().getText();
			String price = view.getTxtPrice().getText();
			String purchasePrice = view.getTxtPurchasePrice().getText();
			String publisherId = getPublisherId(view.getCmbPublisher().getSelectedItem().toString());
			String categoryId = getCategoryId(view.getCmbCategory().getSelectedItem().toString());
			String authorId = getAuthorId(view.getTxtAuthor().getText());

			if (authorId.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng kiểm tra tên tác giả hoặc không có tác giả này!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
			// Kiểm tra dữ liệu nhập vào
			if (bookId.isEmpty() || title.isEmpty() || entryDate.isEmpty() || publisherId.isEmpty()
					|| publicationDate.isEmpty() || quantity.isEmpty() || authorId.isEmpty() || price.isEmpty()
					|| purchasePrice.isEmpty() || categoryId.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String sql = String.format(QUERY_INSERT_BOOK, bookId,title,publisherId,categoryId,publicationDate,price,authorId,entryDate,purchasePrice,quantity);
			
			if (DataProvider.getInstance().insert(sql)) {
				JOptionPane.showMessageDialog(view, "Chèn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				clearFields();
			} else {
				JOptionPane.showMessageDialog(view, "Chèn không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		loadData(query);
	}

	/** ✅ Chỉnh sửa sách */
	private void editBook() {
		// Kiểm tra nếu mã sách rỗng
		if (view.getTxtBookId().getText().isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng nhập mã sách cần chỉnh sửa!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn chỉnh sửa sách này?",
				"Xác nhận chỉnh sửa", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		// ✅ Lấy dữ liệu từ các TextField
		String bookId = view.getTxtBookId().getText();
		String title = view.getTxtBookName().getText();
		String publisherId = getPublisherId(view.getCmbPublisher().getSelectedItem().toString());
		String categoryId = getCategoryId(view.getCmbCategory().getSelectedItem().toString());
		String authorId = getAuthorId(view.getTxtAuthor().getText());
		String year = view.getTxtPublicationYear().getText();
		String purchasePrice = view.getTxtPurchasePrice().getText();
		String price = view.getTxtPrice().getText();
		String quantity = view.getTxtQuantity().getText();
		String entryDate = view.getTxtEntryDate().getText();

		String sql = String.format(QUERY_UPDATE_BOOK,title,publisherId,categoryId,year,price,authorId,entryDate,purchasePrice,quantity,bookId);
		
		// ✅ Cập nhật dữ liệu trong database
		if (DataProvider.getInstance().update(sql)) {
			JOptionPane.showMessageDialog(view, "Cập nhật sách thành công!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			loadData(query);
			clearFields();
		} else {
			JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** ✅ Xóa sách */
	private void deleteBook() {
		if (view.getTxtBookId().getText().isEmpty()) {
			JOptionPane.showMessageDialog(view, "Vui lòng nhập mã sách cần xóa", "Thông báo", JOptionPane.OK_OPTION);
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xoá sách này?", "Xác nhận xoá",
				JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) {
			return;
		}

		String bookId = view.getTxtBookId().getText().trim();

		String sql = String.format(QUERY_DELETE_BOOK, bookId);
		
		if (DataProvider.getInstance().delete(sql)) {
			JOptionPane.showMessageDialog(view, "Xóa sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			loadData(query);
			clearFields();
		} else {
			JOptionPane.showMessageDialog(view, "Lỗi khi xoá sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getNewBookId() {
        // Tạo câu lệnh SQL để lấy ID cuốn sách tiếp theo
        String sql = QUERY_GET_NEW_BOOK_ID;
        // Lấy kết quả từ cơ sở dữ liệu
        ResultSet rs = DataProvider.getInstance().view(sql);
        try {
            if (rs.next()) {
                return rs.getString(1); // Trả về ID cuốn sách tiếp theo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Nếu không có kết quả, trả về null
    }
	
	// Lấy AuthorId từ tên tác giả
    public String getAuthorId(String authorName) {
        // Tạo câu lệnh SQL để lấy AuthorId từ tên tác giả
        String sql = String.format(QUERY_GET_AUTHOR_ID, authorName);
        ResultSet rs = DataProvider.getInstance().view(sql);
        try {
            if (rs.next()) {
                return rs.getString("AuthorId"); // Trả về AuthorId nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";  // Nếu không tìm thấy, trả về chuỗi rỗng
    }

    // Lấy CategoryId từ tên danh mục
    public String getCategoryId(String categoryName) {
        // Tạo câu lệnh SQL để lấy CategoryId từ tên danh mục
        String sql = String.format(QUERY_GET_CATEGORY_ID, categoryName);
        ResultSet rs = DataProvider.getInstance().view(sql);
        try {
            if (rs.next()) {
                return rs.getString("CategoryId"); // Trả về CategoryId nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";  // Nếu không tìm thấy, trả về chuỗi rỗng
    }
    
    public String getPublisherId(String publisherName) {
        // Tạo câu lệnh SQL để lấy BookId từ tên nhà xuất bản
        String sql = String.format(QUERY_GET_PUBLISHER_ID, publisherName);
        ResultSet rs = DataProvider.getInstance().view(sql);
        try {
            if (rs.next()) {
                return rs.getString("PublisherId"); // Trả về PublisherId nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";  // Nếu không tìm thấy, trả về chuỗi rỗng
    }
	
	/** ✅ Tạo mã sách tự động */
	private void createBookId() {
		String nextId = getNewBookId();
		view.getTxtBookId().setText(nextId);
	}

	private void findBookData() {
		String keywork = view.getSearchPanel().getTxtSearch().getText();
		boolean isNumeric = keywork.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

        String sql = "";
        
        if (isNumeric) {
            sql = query + " WHERE BookId = " + keywork + " OR Title LIKE '%" + keywork + "%'";
        } else {
            sql = query + " WHERE Title LIKE '%" + keywork + "%'";
        }
        loadData(sql);
	}

	private void tableRowClick(JTable table, DefaultTableModel model) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    int selectedRow = table.getSelectedRow();
			    int rowCount = model.getRowCount();
			    
			    if (selectedRow != -1 && selectedRow < rowCount) { // Ensure row is within bounds
					view.getTxtBookId().setText(model.getValueAt(selectedRow, 0).toString());
					view.getTxtBookName().setText(model.getValueAt(selectedRow, 1).toString());
					view.getTxtAuthor().setText(model.getValueAt(selectedRow, 2).toString());
					view.getCmbPublisher().setSelectedItem(model.getValueAt(selectedRow, 3).toString());
					view.getCmbCategory().setSelectedItem(model.getValueAt(selectedRow, 4).toString());
					view.getTxtPublicationYear().setText(model.getValueAt(selectedRow, 5).toString());
					view.getTxtPurchasePrice().setText(model.getValueAt(selectedRow, 6).toString());
					view.getTxtPrice().setText(model.getValueAt(selectedRow, 7).toString());
					view.getTxtQuantity().setText(model.getValueAt(selectedRow, 8).toString());
					view.getTxtEntryDate().setText(model.getValueAt(selectedRow, 9).toString());
				}
			}
		});
	}

	private void clearFields() {
		// Xóa trắng tất cả JTextField
		view.getTxtBookId().setText("");
		view.getTxtBookName().setText("");
		view.getTxtAuthor().setText("");
		view.getTxtPublicationYear().setText("");
		view.getTxtPurchasePrice().setText("");
		view.getTxtPrice().setText("");
		view.getTxtQuantity().setText("");
		view.getTxtEntryDate().setText("");

		// Đặt JComboBox về giá trị mặc định (phần tử đầu tiên)
		view.getCmbPublisher().setSelectedIndex(0);
		view.getCmbCategory().setSelectedIndex(0);
	}

}
