package vn.DA_KNNN.View;

import javax.swing.*;
import java.awt.*;
import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ButtonHelper;

public class BookCatalogView extends JPanel {
    private static final long serialVersionUID = 1L;

    private JPanel pane;
    private JButton btnBook, btnCategory, btnAuthor, btnPublisher;

    public JButton getBtnBook() {
        return btnBook;
    }

    public JButton getBtnCategory() {
        return btnCategory;
    }

    public JButton getBtnAuthor() {
        return btnAuthor;
    }

    public JButton getBtnPublisher() {
        return btnPublisher;
    }

    public BookCatalogView() {
        setLayout(new BorderLayout());
        initializePanel();
        initializeButtons();
        setLookAndFeel();
    }

    // Phương thức khởi tạo Panel
    private void initializePanel() {
        pane = new JPanel(new GridLayout(2, 2, 10, 10));
        pane.setForeground(Color.WHITE);
        pane.setBackground(Color.WHITE);
        add(pane, BorderLayout.CENTER);
    }

    // Phương thức khởi tạo các nút bấm
    private void initializeButtons() {
        btnAuthor = new ButtonHelper("Tác giả", AppHelper.setSizeImage("/images/person.png"), new Color(33, 150, 243));
        btnAuthor.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnAuthor.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnBook = new ButtonHelper("Sách", AppHelper.setSizeImage("/images/book.png"), new Color(76, 175, 80));
        btnBook.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnBook.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnCategory = new ButtonHelper("Thể loại", AppHelper.setSizeImage("/images/category.png"), new Color(255, 159, 64));
        btnCategory.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnCategory.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnPublisher = new ButtonHelper("Nhà xuất bản", AppHelper.setSizeImage("/images/publisher.png"), new Color(158, 158, 158));
        btnPublisher.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnPublisher.setHorizontalAlignment(SwingConstants.CENTER);

        // Thêm các nút vào panel
        pane.add(btnAuthor);
        pane.add(btnBook);
        pane.add(btnCategory);
        pane.add(btnPublisher);
    }

    // Phương thức để thiết lập Look and Feel của hệ thống
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace(); // Có thể hiển thị thông báo lỗi cho người dùng ở đây
        }
    }

    /**
     * Hiển thị nội dung mới trong panel
     */
    public void showContent(JPanel content) {
        pane.removeAll(); // Xóa nội dung cũ
        pane.setLayout(new BorderLayout()); // Chuyển sang BorderLayout để hiển thị panel con
        pane.add(content, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
