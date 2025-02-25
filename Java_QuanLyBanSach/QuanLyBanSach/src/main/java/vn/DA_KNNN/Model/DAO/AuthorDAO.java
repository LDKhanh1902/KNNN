package vn.DA_KNNN.Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import vn.DA_KNNN.Model.DTO.DataProvider;

public class AuthorDAO {

    // 1. Tạo đối tượng AuthorDAO duy nhất (Singleton Pattern)
    private static AuthorDAO instance;

    // 2. Các câu lệnh SQL cho các thao tác CRUD với cơ sở dữ liệu
    private final String QUERY_INSERT_AUTHOR = "INSERT INTO author (AuthorId, Name, BirthDate, Nationality) VALUES ('%s', '%s', '%s', '%s')";
    private final String QUERY_UPDATE_AUTHOR = "UPDATE author SET Name = '%s', BirthDate = '%s', Nationality = '%s' WHERE AuthorId = '%s'";
    private final String QUERY_DELETE_AUTHOR = "DELETE FROM author WHERE AuthorId = '%s'"; 
    private final String QUERY_CHECK_AUTHOR_ID = "SELECT AuthorId FROM author WHERE AuthorId = '%s'";
    private final String QUERY_GET_NEW_AUTHOR_ID = "SELECT MAX(AuthorId) + 1 FROM author";

    // 3. Constructor private để không cho phép tạo đối tượng ngoài lớp
    private AuthorDAO() {}

    // 4. Phương thức tĩnh trả về đối tượng duy nhất AuthorDAO (Singleton)
    public static AuthorDAO getInstance() {
        if (instance == null) {
            instance = new AuthorDAO();  // Tạo đối tượng nếu chưa có
        }
        return instance;
    }

    // 5. Thêm một tác giả vào cơ sở dữ liệu
    public boolean insertAuthor(String authorId, String name, String birthDate, String nationality) {
        String sql = String.format(QUERY_INSERT_AUTHOR, authorId, name, birthDate, nationality);
        return DataProvider.getInstance().insert(sql);  // Thực hiện câu lệnh SQL
    }

    // 6. Cập nhật thông tin tác giả trong cơ sở dữ liệu
    public boolean updateAuthor(String authorId, String name, String birthDate, String nationality) {
        String sql = String.format(QUERY_UPDATE_AUTHOR, authorId, name, birthDate, nationality);
        return DataProvider.getInstance().update(sql);  // Thực hiện câu lệnh SQL
    }

    // 7. Xóa tác giả khỏi cơ sở dữ liệu
    public boolean deleteAuthor(String authorId) {
        String sql = String.format(QUERY_DELETE_AUTHOR, authorId);
        return DataProvider.getInstance().delete(sql);  // Thực hiện câu lệnh SQL
    }

    // 8. Kiểm tra xem tác giả đã tồn tại trong cơ sở dữ liệu chưa
    public boolean existingAuthor(String authorId) {
        String sql = String.format(QUERY_CHECK_AUTHOR_ID, authorId);
        try {
            return DataProvider.getInstance().view(sql).first();  // Kiểm tra sự tồn tại của tác giả
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return false;  // Trả về false nếu có lỗi hoặc không tìm thấy tác giả
    }

    // 9. Lấy ID tác giả tiếp theo từ cơ sở dữ liệu
    public String getNewAuthorId() {
        try (ResultSet rs = DataProvider.getInstance().view(QUERY_GET_NEW_AUTHOR_ID)) {
            if (rs.next()) {
                return rs.getString(1);  // Trả về ID tiếp theo
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return "";  // Trả về chuỗi rỗng nếu không có kết quả hoặc có lỗi
    }

    public String getQuerySearch(String query, String keyword) {
        // Kiểm tra xem từ khóa có phải là số không
        boolean isNumeric = keyword.matches("\\d+"); 

        String sql = "";

        // Xử lý điều kiện tìm kiếm
        if (isNumeric) {
            // Tìm kiếm theo AuthorId (số) hoặc Name (chứa keyword)
            sql = String.format("%s WHERE AuthorId = %s OR Name LIKE '%%s%'", query, keyword, keyword);
        } else {
            // Tìm kiếm chỉ theo Name chứa keyword
            sql = String.format("%s WHERE Name LIKE '%%s%'", query, keyword);
        }

        return sql;
    }

}
