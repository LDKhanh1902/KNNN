package vn.DA_KNNN.Components;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableHelper extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableHelper(int alignment,int rowHeight) {
		// TODO Auto-generated constructor stub
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Tahoma",Font.PLAIN,16));
		getTableHeader().setDefaultRenderer(headerRenderer);
		setRowHeight(rowHeight);
	}
	
	
}
