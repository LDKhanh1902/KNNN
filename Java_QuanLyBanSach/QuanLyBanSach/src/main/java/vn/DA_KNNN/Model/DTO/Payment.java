package vn.DA_KNNN.Model.DTO;


import java.sql.*;
import java.time.LocalDate;

public class Payment {
    private String id;            // Mã thanh toán (INTEGER)
    private double amount;     // Số tiền thanh toán
    private LocalDate paymentDate;  // Ngày thanh toán (DATE)

    // ✅ Constructor không tham số (Dùng khi cần khởi tạo đối tượng trống)
    public Payment() {}

    // ✅ Constructor đầy đủ
    public Payment(double amount) {
        this.id = generateNewId(); // Tự động tạo PaymentId
        this.amount = amount;
        this.paymentDate = LocalDate.now(); // Lấy ngày hiện tại
    }

    private String generateNewId() {
        String newId = "PAY0000001"; // Mặc định bắt đầu từ P000000001
        String query = "SELECT MAX(PaymentId) FROM payment";
        
        try (ResultSet rs = DataProvider.getInstance().view(query)) {
            if (rs.next()) {
                String maxId = rs.getString(1); // Lấy giá trị lớn nhất của PaymentId
                
                if (maxId != null) {
                    // Lấy phần số sau chữ "P"
                    String numberPart = maxId.substring(3); 
                    
                    // Tăng số lên 1
                    int nextId = Integer.parseInt(numberPart) + 1;
                    
                    // Tạo ID mới với định dạng có 9 chữ số
                    newId = "PAY" + String.format("%07d", nextId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }


    // ✅ Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

 // ✅ Ghi dữ liệu vào database (Thêm thanh toán mới)
    public boolean saveToDatabase() {
        String sql = String.format("INSERT INTO payment (PaymentId, Amount, PaymentDate) VALUES ('', '', '')",this.id,this.amount,this.paymentDate);
        return DataProvider.getInstance().insert(sql);
    }
    
    // ✅ Hiển thị thông tin đối tượng (Dùng để debug/log dữ liệu)
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
