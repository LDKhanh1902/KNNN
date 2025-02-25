package vn.DA_KNNN.Components;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

public class SearchHelper extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtSearch ;
	private JButton btnSearch = new ButtonHelper("Tìm",AppHelper.setSizeImage("/images/search.png",30,30),new Color(33, 150, 243));
	private JButton btnRefresh = new ButtonHelper("",AppHelper.setSizeImage("/images/refresh.png",30,30),new Color(255,255, 255));
	
	public JTextField getTxtSearch() {
		return txtSearch;
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
	public JButton getBtnRefresh() {
		return btnRefresh;
	}
	public SearchHelper() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		add(txtSearch);
		add(btnSearch);
		add(btnRefresh);
	}	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		SearchHelper s = new SearchHelper();
		f.getContentPane().add(s);

		
		f.setVisible(true);
		
		
	}
}
