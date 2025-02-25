package vn.DA_KNNN.Components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PopupMenuHelper extends JPopupMenu {

    private static final long serialVersionUID = 1L;

    /**
     * Tạo popup menu từ danh sách tên item và sự kiện xử lý tương ứng
     * @param items Tên của các menu item
     * @param listeners Sự kiện tương ứng với từng item
     */
    public PopupMenuHelper(String[] items, ActionListener[] listeners) {
        if (items.length != listeners.length) {
            throw new IllegalArgumentException("Số lượng items và listeners phải bằng nhau!");
        }
        
        for (int i = 0; i < items.length; i++) {
            JMenuItem menuItem = new JMenuItem(items[i]);
            menuItem.addActionListener(listeners[i]); // Gán sự kiện cho từng item
            add(menuItem);
        }
    }
}
