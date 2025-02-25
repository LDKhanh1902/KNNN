package vn.DA_KNNN.Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.DA_KNNN.Model.DTO.DataProvider;

public class BookDAO {
	
    // 1. Tạo đối tượng DAO duy nhất (Singleton Pattern)
    private static BookDAO instance;
	
	private final String QUERY_INSERT_BOOK = "INSERT INTO book (BookId, Title, PublisherId, CategoryId, PublicationDate, Price, AuthorId, EntryDate, PurchasePrice, Quantity) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"; 
	private final String QUERY_UPDATE_BOOK = "UPDATE book SET Title = '%s', AuthorId = (SELECT AuthorId FROM author WHERE Name = '%s'), PublisherId = (SELECT PublisherId FROM publisher WHERE PublisherName = '%s'), CategoryId = (SELECT CategoryId FROM category WHERE CategoryName = '%s'), PublicationDate = '%s', PurchasePrice = '%s', Price = '%s', Quantity = '%s', EntryDate = '%s' WHERE BookId = '%s'"; 
	private final String QUERY_DELETE_BOOK = "DELETE FROM book WHERE BookId = '%s'"; 
	private final String QUERY_CHECK_BOOK_ID = "SELECT * FROM book WHERE BookId = '%s'"; 
	private final String QUERY_GET_NEW_BOOK_ID = "SELECT MAX(BookId) + 1 FROM book"; 
	private final String QUERY_GET_AUTHOR_ID = "SELECT `AuthorId` FROM `author` WHERE `Name` = '%s'";
	private final String QUERY_GET_CATEGORY_ID = "SELECT CategoryId FROM category WHERE CategoryName = '%s'";
	private final String QUERY_GET_PUBLISHER_ID = "SELECT PublisherId FROM publisher WHERE PublisherName = '%s'";

	private BookDAO() {}

    // 4. Phương thức tĩnh trả về đối tượng duy nhất BookDAO (Singleton)
    public static BookDAO getInstance() {
        if (instance == null) {
            instance = new BookDAO();  // Tạo đối tượng nếu chưa có
        }
        return instance;
    }
    
    // Thêm một cuốn sách mới vào cơ sở dữ liệu
    public boolean insertBook(String bookId, String title, String categoryId, String publicationDate, String price, String authorId, String entryDate, String purchasePrice, String quantity) {
        // Tạo câu lệnh SQL với các tham số đầu vào
        String sql = String.format(QUERY_INSERT_BOOK, bookId, title, categoryId, publicationDate, price, authorId, entryDate, purchasePrice, quantity);
        // Thực thi câu lệnh SQL và trả về kết quả (true nếu thành công)
        return DataProvider.getInstance().insert(sql);
    }

    // Cập nhật thông tin của cuốn sách
    public boolean updateBook(String bookId, String title, String author, String publisher, String category, String publicationDate, String purchasePrice, String price, String quantity, String entryDate) {
        // Tạo câu lệnh SQL để cập nhật thông tin cuốn sách
        String sql = String.format(QUERY_UPDATE_BOOK, title, author, publisher, category, publicationDate, purchasePrice, price, quantity, entryDate, bookId);
        // Thực thi câu lệnh SQL và trả về kết quả (true nếu thành công)
        return DataProvider.getInstance().update(sql);
    }

    // Xóa một cuốn sách theo ID
    public boolean deleteBook(String bookId) {
        // Tạo câu lệnh SQL để xóa cuốn sách
        String sql = String.format(QUERY_DELETE_BOOK, bookId);
        // Thực thi câu lệnh SQL và trả về kết quả (true nếu thành công)
        return DataProvider.getInstance().delete(sql);
    }

    // Kiểm tra xem cuốn sách có tồn tại trong cơ sở dữ liệu hay không
    public boolean checkBookIdExists(String bookId) {
        // Tạo câu lệnh SQL để kiểm tra sự tồn tại của sách
        String sql = String.format(QUERY_CHECK_BOOK_ID, bookId);
        // Lấy kết quả từ cơ sở dữ liệu
        ResultSet rs = DataProvider.getInstance().view(sql);
        try {
            return rs.next();  // Trả về true nếu có kết quả, tức là sách tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Nếu có lỗi xảy ra, trả về false
        }
    }

    // Lấy ID của cuốn sách tiếp theo (dùng cho việc tạo ID tự động)
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

    public String getQuerySearch(String query, String keyword) {
        boolean isNumeric = keyword.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

        String sql = "";
        
        if (isNumeric) {
            sql = query + " WHERE BookId = " + keyword + " OR Title LIKE '%" + keyword + "%'";
        } else {
            sql = query + " WHERE Title LIKE '%" + keyword + "%'";
        }
        return sql;
    }
    
    // Lấy BookId từ tên nhà xuất bản
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
    
	/** ✅ Load danh sách thể loại */
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
}
