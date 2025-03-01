package vn.DA_KNNN.Components;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import vn.DA_KNNN.Model.DTO.DataProvider;

public class AppHelper{
	
	public static String EncodeKey = "1234567890123456";
	
	public static ImageIcon setSizeImage(String url) {
    	ImageIcon icon = new ImageIcon(AppHelper.class.getResource(url));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        return scaledIcon;
    }
	
	public static ImageIcon setSizeImage(String url, int width, int height) {
	    ImageIcon icon = new ImageIcon(AppHelper.class.getResource(url)); // Lấy icon từ URL
	    Image img = icon.getImage(); // Lấy đối tượng Image từ ImageIcon
	    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Thay đổi kích thước
	    ImageIcon scaledIcon = new ImageIcon(scaledImg); // Tạo ImageIcon từ hình đã thay đổi kích thước
	    return scaledIcon;
	}

	
	public static DefaultTableModel loadDataTable(String sql) {
		DefaultTableModel model = new DefaultTableModel();
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			// ✅ Khởi tạo và lấy tên cột từ metadata
			String[] columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = meta.getColumnLabel(i);
			}
			model.setColumnIdentifiers(columnNames); // Gán tên cột cho bảng

			// ✅ Lặp qua từng dòng dữ liệu và thêm vào bảng
			while (rs.next()) {
				Object[] rowData = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					rowData[i - 1] = rs.getObject(i);
				}
				model.addRow(rowData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}
	

}
