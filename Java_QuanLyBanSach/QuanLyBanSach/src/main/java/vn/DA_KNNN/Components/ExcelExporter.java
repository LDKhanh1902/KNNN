package vn.DA_KNNN.Components;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ExcelExporter {
    public static void updateExcelTemplate(JPanel view, String templatePath, String outputPath, DefaultTableModel data) {
        try (FileInputStream fis = new FileInputStream(templatePath);
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(outputPath)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            int startRow = 3; // Dữ liệu bắt đầu từ dòng 4 (index 3)
            System.out.println(data.getColumnCount());
            System.out.println(data.getRowCount());
            for (int i = 0; i < data.getRowCount(); i++) {
                Row row = sheet.getRow(startRow + i);
                if (row == null) {
                    row = sheet.createRow(startRow + i);
                }

                for (int j = 0; j < data.getColumnCount(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        cell = row.createCell(j);
                    }
                    
                    Object value = data.getValueAt(i, j);
                    if (value != null) {
                    	if (j >= 1) { // Cột số: Tổng đơn hàng, Tổng số lượng, Doanh thu
                    	    try {
                    	        String valueStr = value.toString().replace(",", "").trim();
                    	        if (!valueStr.isEmpty()) {
                    	            double numericValue = Double.parseDouble(valueStr);
                    	            cell.setCellValue(numericValue);
                    	            cell.setCellStyle(getNumberCellStyle(workbook));
                    	        } else {
                    	            cell.setCellValue(0); // Nếu không phải số, gán giá trị 0
                    	        }
                    	    } catch (NumberFormatException e) {
                    	        // Nếu không thể chuyển đổi, gán giá trị chuỗi gốc
                    	        cell.setCellValue(value.toString());
                    	    }
                    	} else {
                    	    cell.setCellValue(value.toString());
                    	}

                    }
                }
            }

            // ✅ Kích hoạt tính toán công thức khi mở file
            workbook.setForceFormulaRecalculation(true);

            // ✅ Lưu file Excel sau khi cập nhật dữ liệu
            workbook.write(fos);
            JOptionPane.showMessageDialog(view, "Xuẩt file thành công!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Lỗi cập nhật dữ liệu vào file Excel!");
        }
    }

    // 🛠 Tạo kiểu định dạng số có dấu phẩy
    private static CellStyle getNumberCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("#,##0"));
        return cellStyle;
    }
}
