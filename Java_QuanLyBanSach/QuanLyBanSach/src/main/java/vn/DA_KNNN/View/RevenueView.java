package vn.DA_KNNN.View;

import javax.swing.*;

import vn.DA_KNNN.Components.AppHelper;
import vn.DA_KNNN.Components.ColumnChart;
import vn.DA_KNNN.Components.LineChart;
import vn.DA_KNNN.Components.PieChart;
import vn.DA_KNNN.Components.TableHelper;

import java.awt.*;

public class RevenueView extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private JTable revenueTable;
	private JButton exportPdfButton;
	private PieChart pieChart;
	private ColumnChart columnChart;
	private LineChart lineChart;

	public RevenueView() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(10, 10));

		// **Main Grid Panel (2x2 layout)**
		JPanel gridPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		gridPanel.setBackground(Color.WHITE);
		add(gridPanel, BorderLayout.CENTER);

		// **Panel for ComboBox and PieChart**
		JPanel piePanel = new JPanel(new BorderLayout(5, 5));
		comboBox = new JComboBox<>(new String[] { "Tùy chọn 1", "Tùy chọn 2" });
		comboBox.setPreferredSize(new Dimension(WIDTH, 40));
		piePanel.add(comboBox, BorderLayout.NORTH);
		pieChart = new PieChart();
		pieChart.setTitle("biểu đồ tròn");
		pieChart.setBackground(Color.WHITE);
		piePanel.add(pieChart, BorderLayout.CENTER);
		gridPanel.add(piePanel);

		// **Column Chart**
		columnChart = new ColumnChart();
		columnChart.setValues(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
		columnChart.setColumnNames(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
		gridPanel.add(columnChart);

		// **Line Chart**
		lineChart = new LineChart();
		lineChart.setSeries("VND");
		lineChart.setTitleX("Năm");
		lineChart.setTitleY("Doanh thu");
		lineChart.setColumnNames(new String[] {"A", "B", "C", "D"});
		gridPanel.add(lineChart);

		// **Revenue Table**
		revenueTable = new TableHelper(SwingConstants.CENTER, 30);
		gridPanel.add(new JScrollPane(revenueTable));

		// **Panel for Buttons**
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		exportPdfButton = new JButton("Xuất PDF",AppHelper.setSizeImage("/images/export.png", 40, 40));
		exportPdfButton.setForeground(Color.WHITE);
		exportPdfButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exportPdfButton.setBackground(new Color(158, 158, 158));
		exportPdfButton.setOpaque(true);
		exportPdfButton.setBorderPainted(false);
		buttonPanel.add(exportPdfButton);

		// **Bottom Panel**
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public JTable getRevenueTable() {
		return revenueTable;
	}

	public JButton getExportPdfButton() {
		return exportPdfButton;
	}

	public PieChart getPieChart() {
		return pieChart;
	}

	public ColumnChart getColumnChart() {
		return columnChart;
	}

	public LineChart getLineChart() {
		return lineChart;
	}
}
