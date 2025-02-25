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
import vn.DA_KNNN.View.PublisherView;

public class PublisherController {
    private PublisherView view;
    private String query = "SELECT `PublisherId` as 'Mã nhà xuất bản', `PublisherName` as 'Tên nhà xuất bản', `Address` as 'Địa chỉ', `Contact` as 'Liên hệ', (SELECT COUNT(*) FROM book WHERE book.PublisherId = publisher.PublisherId) AS 'Số tác phẩm' FROM publisher";
    private final String QUERY_INSERT_PUBLISHER = "INSERT INTO publisher (PublisherId, PublisherName, Address, Contact) VALUES ('%s', '%s', '%s', '%s')";
    private final String QUERY_UPDATE_PUBLISHER = "UPDATE publisher SET PublisherName = '%s', Address = '%s', Contact = '%s' WHERE PublisherId = '%s'";
    private final String QUERY_DELETE_PUBLISHER = "DELETE FROM publisher WHERE PublisherId = '%s'"; 
    private final String QUERY_CHECK_PUBLISHER_ID = "SELECT PublisherId FROM publisher WHERE PublisherId = '%s'";
    private final String QUERY_GET_NEW_PUBLISHER_ID = "SELECT MAX(PublisherId) + 1 FROM publisher";

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
			loadData(query);
		});
	}

    public boolean existingPublisher(String publisherId) {
        String sql = String.format(QUERY_CHECK_PUBLISHER_ID, publisherId);
        try {
            return DataProvider.getInstance().view(sql).first();  // Kiểm tra sự tồn tại của nhà xuất bản
        } catch (SQLException e) {
            e.printStackTrace();  // In lỗi nếu có
        }
        return false;  // Trả về false nếu có lỗi hoặc không tìm thấy nhà xuất bản
    }
    
    private void addPublisher() {
        String publisherId = view.getTxtPublisherId().getText().trim();
        String publisherName = view.getTxtPublisherName().getText().trim();
        String address = view.getTxtAddress().getText().trim();
        String contact = view.getTxtContact().getText().trim();

        if (publisherId.isEmpty() || publisherName.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (existingPublisher(publisherId)) {
			JOptionPane.showMessageDialog(view, "Mã tác giả đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
        
        String sql = String.format(QUERY_INSERT_PUBLISHER, publisherId, publisherName,address,contact);
        
        // Thực hiện thao tác thêm dữ liệu thông qua PublisherDAO
        if (DataProvider.getInstance().insert(sql)) {
            JOptionPane.showMessageDialog(view, "Chèn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            clearFields(); 
            loadData(query); 
        } else {
            JOptionPane.showMessageDialog(view, "Chèn thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editPublisher() {
        String publisherId = view.getTxtPublisherId().getText().trim();
        String publisherName = view.getTxtPublisherName().getText().trim();
        String address = view.getTxtAddress().getText().trim();
        String contact = view.getTxtContact().getText().trim();

        if (publisherId.isEmpty() || publisherName.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Hiển thị hộp thoại xác nhận
        int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn cập nhật thông tin nhà xuất bản?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
        	String sql = String.format(QUERY_UPDATE_PUBLISHER,publisherName,address,contact, publisherId);
            if (DataProvider.getInstance().update(sql)) {
                JOptionPane.showMessageDialog(view, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadData(query); 
                clearFields(); 
            } else {
                JOptionPane.showMessageDialog(view, "Cập nhật thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deletePublisher() {
        String publisherId = view.getTxtPublisherId().getText();

        if (publisherId.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn một nhà xuất bản để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Hiển thị hộp thoại xác nhận
        int option = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nhà xuất bản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
        	String sql = String.format(QUERY_DELETE_PUBLISHER, publisherId);
            if (DataProvider.getInstance().delete(sql)) {
                JOptionPane.showMessageDialog(view, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadData(query); 
                clearFields(); 
            } else {
                JOptionPane.showMessageDialog(view, "Xóa thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void findPublisherData() {
        String keywork = view.getSearchPanel().getTxtSearch().getText();
        boolean isNumeric = keywork.matches("\\d+"); // Kiểm tra xem id chỉ chứa số hay không

        String sql = "";
        if (isNumeric) {
            sql = query + " WHERE PublisherId = " + keywork + " OR PublisherName LIKE '%" + keywork + "%'";
        } else {
            sql = query + " WHERE PublisherName LIKE '%" + keywork + "%'";
        }
        loadData(sql);
    }
    
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

    private void createPublisherId() {
        String nextId = getNewPublisherId();
        view.getTxtPublisherId().setText(nextId);
    }

    private void tableRowClick(JTable table, DefaultTableModel model) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                int rowCount = model.getRowCount();

                if (selectedRow != -1 && selectedRow < rowCount) {
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
