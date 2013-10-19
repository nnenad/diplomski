package com.example.diplomski.celebrities.data;

import java.util.List;

import com.example.diplomski.celebrity.model.Celebrity;

public interface IDataStructure {
	
	List<Celebrity> getAllCelebrities();
	Celebrity getCelebrityById(long id);
	void addCelebrity(Celebrity c);
}
