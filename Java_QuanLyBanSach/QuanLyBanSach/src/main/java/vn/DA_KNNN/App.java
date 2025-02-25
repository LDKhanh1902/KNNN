package vn.DA_KNNN;

import vn.DA_KNNN.Controller.LoginController;
import vn.DA_KNNN.View.LoginView;

public class App {
	public static void main(String[] args) {
		LoginView view = new LoginView();
		new LoginController(view);
		view.setVisible(true);
	}
}
