package vn.DA_KNNN.Controller;

import java.nio.file.Paths;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import vn.DA_KNNN.Components.ColumnChart;
import vn.DA_KNNN.Components.ExcelExporter;
import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.LineChart;
import vn.DA_KNNN.Components.PieChart;
import vn.DA_KNNN.Model.DataProvider;
import vn.DA_KNNN.View.RevenueView;

public class RevenueController {
	private RevenueView view;

	public RevenueController(RevenueView _view) {
		this.view = _view;
		this.view.getExportPdfButton().addActionListener(e -> exportRevenue());
		setPieChartValue(String.valueOf(LocalDate.now().getMonthValue()));
		setColumnChart();
		setLineChart();

		String query = "SELECT category.CategoryName as 'Thể loại', COALESCE(SUM(payment.Amount), 0) AS 'Tổng tiền đã bán' FROM category LEFT JOIN book ON book.CategoryId = category.CategoryId LEFT JOIN paydetails ON paydetails.BookId = book.BookId \r\n"
				+ "LEFT JOIN payment ON payment.PaymentId = paydetails.PaymentId\r\n"
				+ "GROUP BY category.CategoryName;\r\n";
		view.getRevenueTable().setModel(AppHelper.loadDataTable(query));
		view.getComboBox().addActionListener(e->{
			setPieChartValue(String.valueOf(view.getComboBox().getSelectedIndex() + 1));
		});
		
		view.getComboBox().removeAllItems();
		for (int i = 1; i <= 12; i++) {
			view.getComboBox().addItem("Tháng "+String.valueOf(i));
		}
	}

	private void setPieChartValue(String month) {
		int i = 0;
		PieChart pie = view.getPieChart();
		String[] columns = new String[100];
		int[] values = new int[100];

		String sql = "SELECT category.CategoryName, COALESCE(SUM(payment.Amount), 0) AS Total FROM category "
				+ "LEFT JOIN book ON book.CategoryId = category.CategoryId "
				+ "LEFT JOIN paydetails ON paydetails.BookId = book.BookId "
				+ "LEFT JOIN payment ON payment.PaymentId = paydetails.PaymentId "
				+ "WHERE MONTH(payment.PaymentDate) = '" + month + "' " + "GROUP BY category.CategoryName";

		Map<String, Integer> data = getDataPayment(sql);

		if (data != null) {
			for (Map.Entry<String, Integer> entry : data.entrySet()) {
				columns[i] = entry.getKey();
				values[i] = entry.getValue();
				i++;
			}
		}
		pie.setTitle("Thể loại sách đã bán trong tháng");
		pie.setColumnNames(Arrays.copyOf(columns, i));
		pie.setValues(Arrays.copyOf(values, i));
		
		pie.updateChart();
	}

	private void setColumnChart() {
		String sql = "SELECT MONTH(PaymentDate) AS Month, SUM(Amount) AS TotalRevenue "
				+ "FROM payment WHERE YEAR(PaymentDate) = YEAR(CURDATE()) "
				+ "GROUP BY MONTH(PaymentDate) ORDER BY MONTH(PaymentDate);";
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

	private void setLineChart() {
		LineChart line = view.getLineChart();
		int year = LocalDate.now().getYear();

		// ✅ Khởi tạo mảng 5 phần tử vì chỉ lấy dữ liệu 5 năm gần nhất
		String[] columns = new String[5];
		int[] values = new int[5];

		// ✅ Tạo danh sách năm từ hiện tại -4 năm trước
		for (int j = 0; j < 5; j++) {
			columns[j] = String.valueOf(year - (4 - j)); // Lấy thứ tự từ cũ → mới
		}

		// ✅ Câu lệnh SQL lấy doanh thu của 5 năm gần nhất
		String sql = "SELECT YEAR(PaymentDate) AS Year, SUM(Amount) AS TotalRevenue "
				+ "FROM payment WHERE YEAR(PaymentDate) >= YEAR(CURDATE()) - 4 "
				+ "GROUP BY YEAR(PaymentDate) ORDER BY YEAR(PaymentDate) ASC;";

		Map<String, Integer> data = getDataPayment(sql);

		// ✅ Gán dữ liệu vào `values[]`
		if (data != null) {
			for (int j = 0; j < 5; j++) {
				String yearKey = columns[j];
				values[j] = data.getOrDefault(yearKey, 0); // Nếu không có dữ liệu -> gán 0
			}
		}

		// ✅ Cập nhật biểu đồ
		line.setTitle("Doanh thu từ 5 năm trở lại đây");
		line.setColumnNames(columns);
		line.setValues(values);
		line.setSeries("Doanh thu");
	}

	private void exportRevenue() {
		String templatePath = getClass().getResource("/templates/template_revenue.xlsx").getPath();
		String outputPath = Paths.get(System.getProperty("user.home"), "Documents",
				String.format("BaoCaoDoanhThu_%s.xlsx", LocalDate.now())).toString();

		// SQL Query
		String sql = "CALL GetMonthlyReport()";
		// Lấy dữ liệu từ SQL
		DefaultTableModel data = AppHelper.loadDataTable(sql);

		// Gửi dữ liệu vào hàm xuất Excel
		ExcelExporter.updateExcelTemplate(view, templatePath, outputPath, data);
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
