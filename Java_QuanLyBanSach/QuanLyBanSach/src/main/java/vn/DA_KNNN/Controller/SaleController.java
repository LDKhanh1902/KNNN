package vn.DA_KNNN.Controller;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ReceiptExporter;
import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.Model.DTO.Payment;
import vn.DA_KNNN.Model.DTO.User;
import vn.DA_KNNN.View.SaleView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SaleController {
    private SaleView view;

    public SaleController(SaleView _view) {
        this.view = _view;
        setupEventListeners();
    }

    // Setup all event listeners for the SaleView
    private void setupEventListeners() {
        view.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { searchBook(); }
            @Override public void removeUpdate(DocumentEvent e) { searchBook(); }
            @Override public void changedUpdate(DocumentEvent e) { searchBook(); }
        });

        view.getBookTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) fillBookInfo();
            }
        });

        view.getAddButton().addActionListener(e -> addRowCartTable());
        view.getPayButton().addActionListener(e -> pay());
        view.getRemoveButton().addActionListener(e -> remove());
        view.setBookModel(AppHelper.loadDataTable("SELECT `Title`, `Price`, `Quantity` FROM `book`"));
    }

    // Remove selected book from cart
    private void remove() {
        JTable cartTable = view.getCartTable();
        int selectedRow = cartTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn một sách để xóa khỏi giỏ hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.removeRow(selectedRow);
    }

    // Search books in the database and update bookTable
    private void searchBook() {
        String keyword = view.getSearchField().getText();
        String sql = "SELECT `Title` as 'Tên sách', `Price` as 'Giá tiền', `Quantity` as 'Số lượng' FROM `book` WHERE `Title` LIKE '%" + keyword + "%'";

        DefaultTableModel model = new DefaultTableModel();
        try (ResultSet rs = DataProvider.getInstance().view(sql)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            // Set column headers
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = meta.getColumnLabel(i);
            }
            model.setColumnIdentifiers(columnNames);

            // Fetch data
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.getBookTable().setModel(model);
    }

    // Fill book info when a book is selected
    private void fillBookInfo() {
        int selectedRow = view.getBookTable().getSelectedRow();
        if (selectedRow >= 0) {
            view.getQuantityField().setText("1");  // Default quantity is 1
        }
    }

    private void addRowCartTable() {
        JTable bookTable = view.getBookTable();
        JTable cartTable = view.getCartTable();

        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn một sách để thêm vào giỏ hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin sách
        String name = bookTable.getValueAt(selectedRow, 0).toString();
        int price = Integer.parseInt(bookTable.getValueAt(selectedRow, 1).toString());
        int availableQuantity = Integer.parseInt(bookTable.getValueAt(selectedRow, 2).toString());

        // Lấy số lượng nhập vào
        int quantity;
        try {
            quantity = Integer.parseInt(view.getQuantityField().getText());
            if (quantity <= 0 || quantity > availableQuantity) {
                JOptionPane.showMessageDialog(view, "Số lượng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel cartModel = (DefaultTableModel) cartTable.getModel();
        boolean found = false;

        // Cập nhật số lượng nếu sách đã có trong giỏ hàng
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            if (cartModel.getValueAt(i, 0).toString().equals(name)) {
                int existingQuantity = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
                int newQuantity = existingQuantity + quantity;
                if (newQuantity > availableQuantity) {
                    JOptionPane.showMessageDialog(view, "Số lượng vượt quá tồn kho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cartModel.setValueAt(newQuantity, i, 2);
                cartModel.setValueAt(newQuantity * price, i, 3);
                found = true;
                break;
            }
        }

        // Nếu sách chưa có trong giỏ hàng, thêm dòng mới
        if (!found) {
            cartModel.addRow(new Object[]{name, price, quantity, quantity * price});
        }

        // Tính tổng tiền sau khi thêm dòng mới
        double grandTotal = calculateGrandTotal(cartModel);

        // Cập nhật tổng tiền vào giao diện
        view.getTotalPriceLabel().setText(String.format("Tổng tiền: %.0f VNĐ", grandTotal));

    }

    // Clean cart data
    private void cleanData() {
        DefaultTableModel cartModel = (DefaultTableModel) view.getCartTable().getModel();
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Giỏ hàng đã trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        cartModel.setRowCount(0);
        view.getQuantityField().setText("1");
        view.setBookModel(AppHelper.loadDataTable("SELECT `Title`, `Price`, `Quantity` FROM `book`"));
        JOptionPane.showMessageDialog(view, "Đã xóa toàn bộ giỏ hàng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    // Handle payment and generate invoice
    private void pay() {
        if (view.getCartTable().getRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Giỏ hàng trống, không thể in hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) view.getCartTable().getModel();

        double amount = calculateGrandTotal(model);
        Payment payment = new Payment(amount);
        
        // Insert payment data
        DataProvider.getInstance().insert(String.format("INSERT INTO `payment`(`PaymentId`, `Amount`, `PaymentDate`, `EmployeeId`) VALUES ('%s', '%s', '%s', %d)", 
                payment.getId(), payment.getAmount(), payment.getPaymentDate().toString(), User.getUser().getEmployeeId()));

        // Insert payment details and update book quantities
        for (int i = 0; i < model.getRowCount(); i++) {
            String bookId = getBookId(model.getValueAt(i, 0).toString());
            String quantity = model.getValueAt(i, 2).toString();
            DataProvider.getInstance().insert(String.format("INSERT INTO `paydetails`(`BookId`, `PaymentId`, `Quantity`, `Price`) VALUES ('%s', '%s', '%s', '%s')", 
                    bookId, payment.getId(), quantity, model.getValueAt(i, 1).toString()));
            DataProvider.getInstance().update(String.format("UPDATE `book` SET `Quantity`= `Quantity` - %s WHERE `BookId` = '%s'", 
                    quantity, bookId));
        }

        // Export the receipt and clean cart
        String templatePath = getClass().getResource("/templates/template_bill.docx").getPath();
        String outPath = Paths.get(System.getProperty("user.home"), "Documents", String.format("HoaDon_%s_%s.docx", payment.getId(),LocalDate.now())).toString();
        ReceiptExporter report = new ReceiptExporter(templatePath);
        report.generateInvoice( getTableData(payment.getId()),outPath);
        JOptionPane.showMessageDialog(view, "Thanh toán thành công! Hóa đơn đã được in ra.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        cleanData();
    }

 // Calculate grand total price from cart data
    private double calculateGrandTotal(DefaultTableModel model) {
        double grandTotal = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object priceObj = model.getValueAt(i, 3); // Get the total price for the row
            if (priceObj instanceof Integer) {
                grandTotal += (Integer) priceObj; // Handle Integer case
            } else if (priceObj instanceof Double) {
                grandTotal += (Double) priceObj; // Handle Double case
            }
        }
        return grandTotal;
    }
    // Get BookId from book title
    private String getBookId(String title) {
        String bookId = null;
        String sql = "SELECT `BookId` FROM `book` WHERE `Title` = '" + title + "'";

        try (ResultSet rs = DataProvider.getInstance().view(sql)) {
            if (rs.next()) {
                bookId = rs.getString("BookId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookId;
    }
    
    // ✅ Lấy dữ liệu từ giỏ hàng để đưa vào hóa đơn
    private Map<String, String> getTableData(String invoidId) {
        Map<String, String> data = new HashMap<>();
        data.put("invoice_id", "HD" + invoidId);
        data.put("sale_date", java.time.LocalDate.now().toString());

        JTable cartTable = view.getCartTable();
        DefaultTableModel cartModel = (DefaultTableModel) cartTable.getModel();
        double grandTotal = 0;

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            data.put("book_" + (i + 1), cartModel.getValueAt(i, 0).toString());
            data.put("price_" + (i + 1), cartModel.getValueAt(i, 1).toString());
            data.put("qty_" + (i + 1), cartModel.getValueAt(i, 2).toString());
            data.put("total_" + (i + 1), cartModel.getValueAt(i, 3).toString());
            grandTotal += Double.parseDouble(cartModel.getValueAt(i, 3).toString());
        }

        data.put("grand_total", String.format("%.0f", grandTotal));
        return data;
    }
}
