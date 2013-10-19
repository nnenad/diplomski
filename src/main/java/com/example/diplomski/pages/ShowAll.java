package com.example.diplomski.pages;

import java.text.Format;
import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;

import com.example.diplomski.celebrities.data.MockDataSource;
import com.example.diplomski.celebrities.util.Formats;
import com.example.diplomski.celebrity.model.Celebrity;
import com.example.diplomski.celebrity.model.User;

public class ShowAll {

	@SessionState
	private User user;
	private boolean userExists;

	@SessionState
	private MockDataSource dataSource;
	@InjectPage
	private Details detailsPage;
	private Celebrity celebrity;

	String onActivate() {
		if (!userExists)
			return "Start";
		return null;
	}

	@OnEvent(component = "detailsLink")
	Object onShowDetails(long id) {
		Celebrity celebrity = dataSource.getCelebrityById(id);
		detailsPage.setCelebrity(celebrity);
		return detailsPage;
	}

	public List<Celebrity> getAllCelebrities() {
		return dataSource.getAllCelebrities();
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}

	public Format getDateFormat() {
		return Formats.getDateFormat();
	}
}
