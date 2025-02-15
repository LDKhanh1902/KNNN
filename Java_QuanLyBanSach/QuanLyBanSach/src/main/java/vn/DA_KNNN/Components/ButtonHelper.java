package vn.DA_KNNN.Components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonHelper extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor giúp tạo JButton với các thuộc tính đã được thiết lập sẵn
    public ButtonHelper(String text, ImageIcon icon, Color backgroundColor) {
        // Gọi constructor của JButton với text
        super(text);
        
        // Đặt icon cho nút
        this.setIcon(icon);  // Giả sử bạn có một phương thức setSizeImage trong AppHelper
        
        // Đặt căn chỉnh cho văn bản và icon
        this.setHorizontalAlignment(SwingConstants.LEFT);  // Căn trái cho văn bản và icon
        
        // Đặt font chữ cho nút
        this.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        // Đặt màu nền cho nút
        this.setBackground(backgroundColor);
        this.setForeground(Color.WHITE);
        // Đảm bảo nền của nút là màu nền, không có border
        this.setOpaque(true);
        this.setBorderPainted(false);
    }
}
