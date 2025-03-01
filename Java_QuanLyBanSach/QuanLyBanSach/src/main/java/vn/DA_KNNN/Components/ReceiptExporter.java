package vn.DA_KNNN.Components;

import org.apache.poi.xwpf.usermodel.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class ReceiptExporter {
    private String templatePath; // Đường dẫn file mẫu Word

    public ReceiptExporter(String templatePath) {
        this.templatePath = templatePath;
    }

    /**
     * Tạo hóa đơn Word từ template với dữ liệu đầu vào.
     * @param data: Map chứa dữ liệu thay thế
     * @param outputPath: Đường dẫn file Word đầu ra
     */
    public boolean generateInvoice(Map<String, String> data, String outputPath) {
        try {
            // 1️⃣ Mở file Word mẫu
            FileInputStream fis = new FileInputStream(templatePath);
            XWPFDocument document = new XWPFDocument(fis);

            // 2️⃣ Thay thế dữ liệu trong đoạn văn bản
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                replaceTextInParagraph(paragraph, data);
            }

            // 3️⃣ Chèn dữ liệu sản phẩm vào bảng
            for (XWPFTable table : document.getTables()) {
                if (table.getRows().size() > 1) { // Đảm bảo có ít nhất một dòng tiêu đề
                    List<XWPFTableRow> rows = table.getRows();
                    int rowCountTable = rows.size() - 1;
                    int rowCount = data.size() / 4; // Số lượng sản phẩm
                    
                    // Duyệt qua các ô trong bảng và thay thế dữ liệu cho các placeholder
                    for (int i = 1; i <= rowCountTable; i++) {
                        XWPFTableRow row = rows.get(i); // Lấy dòng tương ứng trong bảng
                        
                        if(i <= rowCount) {
                            // Thay thế các placeholder trong từng ô
                            replaceTableCellText(row.getCell(0), String.valueOf(i)); // STT
                            replaceTableCellText(row.getCell(1), data.getOrDefault("book_" + i, "")); // Tên sách
                            replaceTableCellText(row.getCell(2), data.getOrDefault("price_" + i, "")); // Giá
                            replaceTableCellText(row.getCell(3), data.getOrDefault("qty_" + i, "")); // Số lượng
                            replaceTableCellText(row.getCell(4), data.getOrDefault("total_" + i, "")); // Tổng tiền
                        } else {
                            // Nếu không có đủ sản phẩm, giữ nguyên các ô còn lại
                            replaceTableCellText(row.getCell(0), ""); // Xóa dữ liệu thừa nếu không có sản phẩm
                            replaceTableCellText(row.getCell(1), "");
                            replaceTableCellText(row.getCell(2), "");
                            replaceTableCellText(row.getCell(3), "");
                            replaceTableCellText(row.getCell(4), "");
                        }
                    }
                }
            }

            // 4️⃣ Lưu file mới sau khi thay thế dữ liệu
            FileOutputStream fos = new FileOutputStream(outputPath);
            document.write(fos);
            fos.close();
            document.close();
            fis.close();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    // ✅ Hàm thay thế văn bản trong đoạn văn bản Word
    private void replaceTextInParagraph(XWPFParagraph paragraph, Map<String, String> data) {
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null) {
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    text = text.replace("{{" + entry.getKey() + "}}", entry.getValue());
                }
                run.setText(text, 0);
            }
        }
    }

    // ✅ Hàm thay thế văn bản trong ô của bảng
    private void replaceTableCellText(XWPFTableCell cell, String text) {
        if (cell != null) {
            for (XWPFParagraph paragraph : cell.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String cellText = run.getText(0);
                    if (cellText != null && cellText.contains("{{")) {
                        run.setText(text, 0);
                    }
                }
            }
        }
    }
}
