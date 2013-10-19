package com.example.diplomski.pages;

import java.text.Format;

import org.apache.tapestry5.annotations.Persist;

import com.example.diplomski.celebrities.util.Formats;
import com.example.diplomski.celebrity.model.Celebrity;

public class Details {
	@Persist
	private Celebrity celebrity;

	public void setCelebrity(Celebrity c) {
		this.celebrity = c;
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public Format getDateFormat() {
		return Formats.getDateFormat();
	}
}
