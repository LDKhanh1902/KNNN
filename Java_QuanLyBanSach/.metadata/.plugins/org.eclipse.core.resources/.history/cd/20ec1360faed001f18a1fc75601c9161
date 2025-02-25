package vn.DA_KNNN.Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import vn.DA_KNNN.Model.DTO.DataProvider;

public class CategoryDAO {

    // 1. Tạo đối tượng CategoryDAO duy nhất (Singleton Pattern)
    private static CategoryDAO instance;

    // 2. Các câu lệnh SQL cho các thao tác CRUD với cơ sở dữ liệu
    private final String QUERY_INSERT_CATEGORY = "INSERT INTO category (CategoryId, CategoryName, CategoryDescription) VALUES ('%s', '%s', '%s')";
    private final String QUERY_UPDATE_CATEGORY = "UPDATE category SET CategoryName = '%s', CategoryDescription = '%s' WHERE CategoryId = '%s'";
    private final String QUERY_DELETE_CATEGORY = "DELETE FROM category WHERE CategoryId = '%s'"; 
    private final String QUERY_CHECK_CATEGORY_ID = "SELECT CategoryId FROM category WHERE CategoryId = '%s'";
    private final String QUERY_GET_NEW_CATEGORY_ID = "SELECT MAX(CategoryId) + 1 FROM category";

    // 3. Constructor private để không cho phép tạo đối tượng ngoài lớp
    private CategoryDAO() {}

    // 4. Phương thức tĩnh trả về đối tượng duy nhất CategoryDAO (Singleton)
    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();  // Tạo đối tượng nếu chưa có
        }
        return instance;
    }

    // 5. Thêm một thể loại vào cơ sở dữ liệu
    public boolean insertCategory(String categoryId, String categoryName, String categoryDescription) {
        String sql = String.format(QUERY_INSERT_CATEGORY, categoryId, categoryName, categoryDescription);
        return DataProvider.getInstance().insert(sql);  // Thực hiện câu lệnh SQL
    }

    // 6. Cập nhật thông tin thể loại trong cơ sở dữ liệu
    public boolean updateCategory(String categoryId, String categoryName, String categoryDescription) {
        String sql = String.format(QUERY_UPDATE_CATEGORY, categoryName, categoryDescription, categoryId);
        return DataProvider.getInstance().update(sql);  // Thực hiện câu lệnh SQL
    }

    // 7. Xóa thể loại khỏi cơ sở dữ liệu
    public boolean deleteCategory(String categoryId) {
        String sql = String.format(QUERY_DELETE_CATEGORY, categoryId);
        return DataProvider.getInstance().delete(sql);  // Thực hiện câu lệnh SQL
    }

    // 8. Kiểm tra xem thể loại đã tồn tại trong cơ sở dữ liệu chưa
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

    // 9. Lấy ID thể loại tiếp theo từ cơ sở dữ liệu
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

    // 10. Tìm kiếm thể loại theo tên hoặc ID
    public String getQuerySearch(String keywork) {
        boolean isNumeric = keywork.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

        String sql = "";
        if (isNumeric) {
            sql = "SELECT * FROM category WHERE CategoryId = " + keywork + " OR CategoryName LIKE '%" + keywork + "%'";
        } else {
            sql = "SELECT * FROM category WHERE CategoryName LIKE '%" + keywork + "%'";
        }

        return sql;
    }
}
