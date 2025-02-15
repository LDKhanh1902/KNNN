package vn.DA_KNNN.Components;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;

public class LineChart extends JPanel {
    private static final long serialVersionUID = 1L;

    private int[] values;
    private String[] columnNames;
    private String series;
    private String title;
    private String titleX;
    private String titleY;

    // Default Constructor
    public LineChart() {
        this.values = new int[]{10, 30, 20, 40};
        this.columnNames = new String[]{"A", "B", "C", "D"};
        this.series = "Series 1";
        this.title = "Doanh thu từ 5 năm trước đến nay";
        this.titleX = "Category";
        this.titleY = "Value";

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(createChartPanel());
    }

    // Setters for dynamic updates
    public void setValues(int[] values) {
        this.values = values;
        updateChart();
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        updateChart();
    }

    public void setSeries(String series) {
        this.series = series;
        updateChart();
    }

    public void setTitle(String title) {
        this.title = title;
        updateChart();
    }

    public void setTitleX(String titleX) {
        this.titleX = titleX;
        updateChart();
    }

    public void setTitleY(String titleY) {
        this.titleY = titleY;
        updateChart();
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getTitleX() {
        return titleX;
    }

    public String getTitleY() {
        return titleY;
    }

    public int[] getValues() {
        return values;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String getSeries() {
        return series;
    }

    // Create the chart panel with dynamic values
    private JPanel createChartPanel() {
        // Create dataset for the line chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < values.length; i++) {
            dataset.addValue(values[i], series, columnNames[i]);
        }

        // Create line chart from dataset
        JFreeChart chart = ChartFactory.createLineChart(
                this.title,       // Title
                this.titleX,      // X-axis label
                this.titleY,      // Y-axis label
                dataset,          // Dataset
                org.jfree.chart.plot.PlotOrientation.VERTICAL,  // Orientation
                true,             // Show legend
                true,             // Show tooltips
                false             // No URLs
        );

        // Customize chart appearance
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(0, 0, 255)); // Blue color for line
        renderer.setSeriesShapesVisible(0, true); // Show circle markers on the line
        plot.setRenderer(renderer);

        // Create ChartPanel and return it as JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        return chartPanel;
    }

    // Update chart when any value changes
    private void updateChart() {
        removeAll(); // Remove old chart
        add(createChartPanel(), BorderLayout.CENTER); // Add updated chart
        revalidate();
        repaint();
    }
}
