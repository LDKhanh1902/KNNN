package vn.DA_KNNN.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vn.DA_KNNN.View.AuthorView;
import vn.DA_KNNN.View.BookCatalogView;
import vn.DA_KNNN.View.BookView;
import vn.DA_KNNN.View.CategoryView;
import vn.DA_KNNN.View.PublisherView;

public class BookCatalogController {
	private BookCatalogView view;

	public BookCatalogController(BookCatalogView _view) {
		this.view = _view;

		view.getBtnBook().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookView book = new BookView();
				new BookController(book);

				view.showContent(book);
			}
		});

		view.getBtnAuthor().addActionListener(e -> {
			AuthorView view = new AuthorView();
			new AuthorController(view);
			this.view.showContent(view);
		});

		view.getBtnCategory().addActionListener(e -> {
			CategoryView view = new CategoryView();
			new CategoryController(view);
			this.view.showContent(view);
		});

		view.getBtnPublisher().addActionListener(e -> {
			PublisherView view = new PublisherView();
			new PublisherController(view);
			this.view.showContent(view);
		});
	}
}
