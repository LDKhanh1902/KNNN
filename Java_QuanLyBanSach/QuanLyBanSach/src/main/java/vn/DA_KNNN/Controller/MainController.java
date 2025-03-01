package vn.DA_KNNN.Controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import vn.DA_KNNN.Components.ColumnChart;
import vn.DA_KNNN.Components.PieChart;
import vn.DA_KNNN.Components.PopupMenuHelper;
import vn.DA_KNNN.Model.DTO.DataProvider;
import vn.DA_KNNN.Model.DTO.User;
import vn.DA_KNNN.View.EmployeeInfoView;
import vn.DA_KNNN.View.MainView;

public class MainController {
	private MainView view;

	public MainController(MainView _view) {
		this.view = _view;

		this.view.getUserInfo().setText(User.getName());
		this.view.getUserInfo().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) { 
		        showPopup(e); 
		    }
		    
		    @Override
		    public void mouseReleased(MouseEvent e) { 
		        showPopup(e); 
		    }

		    private void showPopup(MouseEvent e) {
		        if (e.isPopupTrigger()) { // Kiểm tra nếu chuột phải
		            String[] items = {"Thông tin cá nhân"};
		            ActionListener[] listeners = {event -> showInfo()}; // Đổi tên tham số lambda từ 'e' sang 'event'

		            // Tạo và hiển thị popup menu
		            PopupMenuHelper popupMenu = new PopupMenuHelper(items, listeners);
		            popupMenu.show(e.getComponent(), e.getX(), e.getY()); // Hiển thị popup tại vị trí chuột
		        }
		    }
		});
		
		this.view.getBookCountValue().setText(getBookCount());

		this.view.getStaffCountValue().setText(getStaffCount());

		this.view.getStaffCountValue().setText(getStaffCount());

		this.view.getSoldBooksValue().setText(getSoldBooks());

		this.view.getRevenueValue().setText(getRevenue());

		setPieChartValue();
		
		setColumnChart();
	}
	
	private void showInfo() {
		EmployeeInfoView em = new EmployeeInfoView();
		new EmployeeInfoController(em);
		em.setVisible(true);
	}
	
	private String getBookCount() {
		String str = "";
		try (ResultSet rs = DataProvider.getInstance().view("SELECT COUNT(*) FROM book")) {
			if (rs.next())
				str = rs.getString(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return str + " cuốn";
	}

	private String getStaffCount() {
		String str = "";
		try (ResultSet rs = DataProvider.getInstance().view("SELECT COUNT(*) FROM employee")) {
			if (rs.next())
				str = rs.getString(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return str + " người";
	}

	private String getSoldBooks() {
		String str = "";
		String sql = "SELECT SUM(`Quantity`) AS 'Tổng' FROM `paydetails`";
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			if (rs.next())
				str = rs.getString(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return str + " cuốn";
	}

	private String getRevenue() {
		String str = "0";
		String sql = "SELECT SUM(`Amount`) FROM `payment`";
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			if (rs.next())
				str = rs.getString(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return str + " VND";
	}

	private void setPieChartValue() {
		int i = 0;
		PieChart pie = view.getPieChart();
		String[] columns = new String[100];
		int[] values = new int[100];

		String sql = "SELECT `category`.`CategoryName`, SUM(`paydetails`.`Quantity`) AS `TotalQuantity` FROM `book` "
				+ "JOIN `category` ON `category`.`CategoryId` = `book`.`CategoryId` "
				+ "JOIN `paydetails` ON `paydetails`.`BookId` = `book`.`BookId` "
				+ "GROUP BY `category`.`CategoryName`";

		Map<String, Integer> data = getDataPayment(sql);

		if (data != null) {
			for (Map.Entry<String, Integer> entry : data.entrySet()) {
				columns[i] = entry.getKey();
				values[i] = entry.getValue();
				i++;
			}
		}
		pie.setTitle("Thể loại sách đã bán");
		pie.setColumnNames(Arrays.copyOf(columns, i));
		pie.setValues(Arrays.copyOf(values, i));

		pie.updateChart(); // ✅ Gọi để vẽ lại biểu đồ
	}

	private void setColumnChart() {
	    String sql = "SELECT MONTH(PaymentDate) AS Month, SUM(Amount) AS TotalRevenue " +
	                 "FROM payment WHERE YEAR(PaymentDate) = YEAR(CURDATE()) " +
	                 "GROUP BY MONTH(PaymentDate) ORDER BY MONTH(PaymentDate);";
	    ColumnChart column = view.getColumnChart();
	    int[] values = new int[12]; // 12 tháng

	    Map<String, Integer> data = getDataPayment(sql);

	    if (data != null) {
	        for (Map.Entry<String, Integer> entry : data.entrySet()) {
	            int month = Integer.parseInt(entry.getKey()); // Lấy tháng (1-12)
	            values[month - 1] = entry.getValue(); // Gán vào mảng theo index tháng
	        }
	    }
	    column.setTitle("Doanh thu trong năm");
	    column.setValues(values);
	    column.updateChart(); // ✅ Cập nhật biểu đồ
	}


	private Map<String, Integer> getDataPayment(String sql) {
		Map<String, Integer> data = new HashMap<String, Integer>();
		try (ResultSet rs = DataProvider.getInstance().view(sql)) {
			while (rs.next()) {
				data.put(rs.getString(1), rs.getInt(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
}
