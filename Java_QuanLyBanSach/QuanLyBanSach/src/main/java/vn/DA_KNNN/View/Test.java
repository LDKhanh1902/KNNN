package vn.DA_KNNN.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JDialog {
    private static final long serialVersionUID = 1L;

    public Test(JFrame parent) {
        super(parent, "Hóa Đơn", true);  // Tạo JDialog modal

        setSize(600, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Panel chính
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(240, 255, 255));  // Màu nền nhẹ

        // Tiêu đề
        JLabel lblTitle = new JLabel("HÓA ĐƠN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setForeground(new Color(0, 102, 204));
        contentPanel.add(lblTitle);

        // Ngày và mã hóa đơn
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new GridLayout(1, 2));
        JLabel lblSaleDate = new JLabel("Ngày: {{sale_date}}");
        JLabel lblInvoiceId = new JLabel("Số hóa đơn: {{invoice_id}}");
        panelHeader.add(lblSaleDate);
        panelHeader.add(lblInvoiceId);
        contentPanel.add(panelHeader);

        // Địa chỉ người nhận
        JLabel lblAddress = new JLabel("Địa chỉ nhận hóa đơn:");
        contentPanel.add(lblAddress);
        JLabel lblAddressDetail = new JLabel("Địa chỉ, thành phố, mã ZIP...");
        contentPanel.add(lblAddressDetail);

        // Tạo bảng cho các mục hóa đơn
        String[] columnNames = {"STT", "Tên sách", "Giá tiền", "Số lượng", "Tổng tiền"};
        Object[][] data = {
            {"1", "{{book_1}}", "{{price_1}}", "{{qty_1}}", "{{total_1}}"},
            {"2", "{{book_2}}", "{{price_2}}", "{{qty_2}}", "{{total_2}}"},
            // Add more rows here
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane);

        // Tổng tiền
        JPanel panelTotal = new JPanel();
        JLabel lblGrandTotal = new JLabel("Tổng tiền: {{grand_total}} VND");
        panelTotal.add(lblGrandTotal);
        contentPanel.add(panelTotal);

        // Thêm nút Đóng
        JButton btnClose = new JButton("Đóng");
        btnClose.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Đóng dialog khi nhấn nút
            }
        });
        contentPanel.add(btnClose);

        // Thêm vào cửa sổ dialog
        setContentPane(contentPanel);
    }

    public static void main(String[] args) {
        // Tạo JFrame chính để test JDialog
        JFrame frame = new JFrame("Main Frame");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một nút để mở Dialog
        JButton btnShowDialog = new JButton("Hiển thị hóa đơn");
        btnShowDialog.addActionListener(e -> {
            Test dialog = new Test(frame);
            dialog.setVisible(true);  // Hiển thị dialog
        });

        frame.add(btnShowDialog, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);  // Căn giữa JFrame
        frame.setVisible(true);
    }
}
