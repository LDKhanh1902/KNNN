package vn.DA_KNNN.Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import vn.DA_KNNN.Model.DTO.DataProvider;

public class PublisherDAO {

    // 1. Tạo đối tượng PublisherDAO duy nhất (Singleton Pattern)
    private static PublisherDAO instance;

    // 2. Các câu lệnh SQL cho các thao tác CRUD với cơ sở dữ liệu
    private final String QUERY_INSERT_PUBLISHER = "INSERT INTO publisher (PublisherId, PublisherName, Address, Contact) VALUES ('%s', '%s', '%s', '%s')";
    private final String QUERY_UPDATE_PUBLISHER = "UPDATE publisher SET PublisherName = '%s', Address = '%s', Contact = '%s' WHERE PublisherId = '%s'";
    private final String QUERY_DELETE_PUBLISHER = "DELETE FROM publisher WHERE PublisherId = '%s'"; 
    private final String QUERY_CHECK_PUBLISHER_ID = "SELECT PublisherId FROM publisher WHERE PublisherId = '%s'";
    private final String QUERY_GET_NEW_PUBLISHER_ID = "SELECT MAX(PublisherId) + 1 FROM publisher";

    // 3. Constructor private để không cho phép tạo đối tượng ngoài lớp
    private PublisherDAO() {}

    // 4. Phương thức tĩnh trả về đối tượng duy nhất PublisherDAO (Singleton)
    public static PublisherDAO getInstance() {
        if (instance == null) {
            instance = new PublisherDAO();  // Tạo đối tượng nếu chưa có
        }
        return instance;
    }

    // 5. Thêm một nhà xuất bản vào cơ sở dữ liệu
    public boolean insertPublisher(String publisherId, String publisherName, String address, String contact) {
        String sql = String.format(QUERY_INSERT_PUBLISHER, publisherId, publisherName, address, contact);
        return DataProvider.getInstance().insert(sql);  // Thực hiện câu lệnh SQL
    }

    // 6. Cập nhật thông tin nhà xuất bản trong cơ sở dữ liệu
    public boolean updatePublisher(String publisherId, String publisherName, String address, String contact) {
        String sql = String.format(QUERY_UPDATE_PUBLISHER, publisherName, address, contact, publisherId);
        return DataProvider.getInstance().update(sql);  // Thực hiện câu lệnh SQL
    }

    // 7. Xóa nhà xuất bản khỏi cơ sở dữ liệu
    public boolean deletePublisher(String publisherId) {
        String sql = String.format(QUERY_DELETE_PUBLISHER, publisherId);
        return DataProvider.getInstance().delete(sql);  // Thực hiện câu lệnh SQL
    }

    // 8. Kiểm tra xem nhà xuất bản đã tồn tại trong cơ sở dữ liệu chưa
    public boolean existingPublisher(String publisherId) {
        String sql = String.format(QUERY_CHECK_PUBLISHER_ID, publisherId);
        try {
            return DataProvider.getInstance().view(sql).first();  // Kiểm tra sự tồn tại của nhà xuất bản
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return false;  // Trả về false nếu có lỗi hoặc không tìm thấy nhà xuất bản
    }

    // 9. Lấy ID nhà xuất bản tiếp theo từ cơ sở dữ liệu
    public String getNewPublisherId() {
        try (ResultSet rs = DataProvider.getInstance().view(QUERY_GET_NEW_PUBLISHER_ID)) {
            if (rs.next()) {
                return rs.getString(1);  // Trả về ID tiếp theo
            }
        } catch (Exception e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return "";  // Trả về chuỗi rỗng nếu không có kết quả hoặc có lỗi
    }

    // 10. Tìm kiếm nhà xuất bản theo tên hoặc ID
    public String getQuerySearch(String query, String keywork) {
        boolean isNumeric = keywork.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

        String sql = "";
        if (isNumeric) {
            sql = query + " WHERE PublisherId = " + keywork + " OR PublisherName LIKE '%" + keywork + "%'";
        } else {
            sql = query + " WHERE PublisherName LIKE '%" + keywork + "%'";
        }

        return sql;
    }
}
