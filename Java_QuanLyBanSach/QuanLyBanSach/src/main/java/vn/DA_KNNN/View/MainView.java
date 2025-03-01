package vn.DA_KNNN.View;

import javax.swing.*;
import vn.DA_KNNN.Components.PieChart;
import vn.DA_KNNN.Components.ColumnChart;
import vn.DA_KNNN.Components.AppHelper;

import java.awt.*;

public class MainView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel userInfo, bookCountValue, staffCountValue, soldBooksValue, revenueValue;
    private PieChart pieChart;
    private ColumnChart columnChart;

    public MainView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // **Header Panel**
        JPanel headerPanel = createHeader();
        add(headerPanel, BorderLayout.NORTH);

        // **Main Content Panel**
        JPanel mainContent = new JPanel(new BorderLayout());
        add(mainContent, BorderLayout.CENTER);

        // **Info Panel**
        JPanel infoPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainContent.add(infoPanel, BorderLayout.NORTH);

        // **Book Count Panel**
        JPanel bookPanel = new JPanel(new GridLayout(2, 1));
        bookPanel.setBackground(new Color(33, 150, 243));
        JLabel bookLabel = new JLabel("Số lượng sách", AppHelper.setSizeImage("/images/book.png"), SwingConstants.CENTER);
        bookLabel.setForeground(Color.WHITE);
        bookLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        bookCountValue = new JLabel("120", SwingConstants.CENTER);
        bookCountValue.setForeground(Color.WHITE);
        bookCountValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        bookPanel.add(bookLabel);
        bookPanel.add(bookCountValue);
        infoPanel.add(bookPanel);

        // **Staff Count Panel**
        JPanel staffPanel = new JPanel(new GridLayout(2, 1));
        staffPanel.setBackground(new Color(76, 175, 80));
        JLabel staffLabel = new JLabel("Số nhân viên", AppHelper.setSizeImage("/images/staff.png") ,SwingConstants.CENTER);
        staffLabel.setForeground(Color.WHITE);
        staffLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        staffCountValue = new JLabel("10", SwingConstants.CENTER);
        staffCountValue.setForeground(Color.WHITE);
        staffCountValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        staffPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        staffPanel.add(staffLabel);
        staffPanel.add(staffCountValue);
        infoPanel.add(staffPanel);

     // **Sold Books Panel**
        JPanel soldBooksPanel = new JPanel(new GridLayout(2, 1));
        soldBooksPanel.setBackground(new Color(255, 159, 64));
        JLabel soldBooksLabel = new JLabel("Số sách đã bán", AppHelper.setSizeImage("/images/receipt.png"), SwingConstants.CENTER);
        soldBooksLabel.setForeground(Color.WHITE);
        soldBooksLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        soldBooksValue = new JLabel("1,200", SwingConstants.CENTER); // Cập nhật số thực tế
        soldBooksValue.setForeground(Color.WHITE);
        soldBooksValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        soldBooksPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        soldBooksPanel.add(soldBooksLabel);
        soldBooksPanel.add(soldBooksValue);
        infoPanel.add(soldBooksPanel);

        // **Revenue Panel**
        JPanel revenuePanel = new JPanel(new GridLayout(2, 1));
        revenuePanel.setBackground(new Color(221, 173, 85));
        revenuePanel.setPreferredSize(new Dimension(WIDTH, 100));
        JLabel revenueLabel = new JLabel("Tổng doanh thu", AppHelper.setSizeImage("/images/money.png"), SwingConstants.CENTER);
        revenueLabel.setForeground(Color.WHITE);
        revenueLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        revenueValue = new JLabel("5.000.000 VNĐ", SwingConstants.CENTER);
        revenueValue.setForeground(Color.WHITE);
        revenueValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        revenuePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        revenuePanel.add(revenueLabel);
        revenuePanel.add(revenueValue);
        infoPanel.add(revenuePanel);

        // **Chart Panel**
        JPanel chartPanel = new JPanel();
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pieChart = new PieChart();
        pieChart.setValues(new int[] {10, 30, 50, 40});
        pieChart.setBackground(Color.WHITE);
        columnChart = new ColumnChart();
        columnChart.setBackground(Color.WHITE);
        pieChart.setPreferredSize(new Dimension(600, 600));  // Thay setSize thành setPreferredSize
        columnChart.setPreferredSize(new Dimension(600, 600)); // Thay setSize thành setPreferredSize

        columnChart.setValues(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        columnChart.setColumnNames(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        chartPanel.setLayout(new GridLayout(0, 2, 0, 0));
        chartPanel.add(pieChart);
        chartPanel.add(columnChart);

        mainContent.add(chartPanel, BorderLayout.CENTER);

        // **Footer**
        JPanel footerPanel = createFooter();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(30, 144, 255));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel title = new JLabel("Hệ thống Quản lý Bán Sách", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        headerPanel.add(title, BorderLayout.CENTER);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        userPanel.setOpaque(false);
        userInfo = new JLabel("Xin chào, Nguyễn Văn A", AppHelper.setSizeImage("/images/person.png"),SwingConstants.CENTER);
        userInfo.setForeground(Color.WHITE);
        userInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userPanel.add(userInfo);

        headerPanel.add(userPanel, BorderLayout.EAST);
        return headerPanel;
    }

    private JPanel createFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(0, 50));
        footerPanel.setBackground(new Color(70, 70, 70));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel footerLabel = new JLabel("© 2025 Hệ thống Quản lý Bán Sách - Nhóm 8");
        footerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        footerLabel.setForeground(Color.WHITE);
        footerPanel.add(footerLabel);

        return footerPanel;
    }

    public JLabel getUserInfo() { return userInfo; }
    public JLabel getBookCountValue() { return bookCountValue; }
    public JLabel getStaffCountValue() { return staffCountValue; }
    public JLabel getSoldBooksValue() { return soldBooksValue; }
    public JLabel getRevenueValue() { return revenueValue; }
    public PieChart getPieChart() { return pieChart; }
    public ColumnChart getColumnChart() { return columnChart; }
}
