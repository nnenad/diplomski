package com.example.diplomski.pages.zaposleni;

import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.Index;
import com.example.diplomski.util.ZaposleniSource;

public class ShowZaposleni implements GridDataSource{

	@SessionState(create=false)
	private Zaposleni user;
	
	@InjectPage
	private Index index;
	
	@Property
	private Zaposleni zaposleni;
	
	private List<Zaposleni> selection;
	private int startIndex;
	
	public List<Zaposleni> getZaposleniSource()
	{
		System.out.println("Getting all zaposleni...");
		
		return DbBroker.getInstance().getAllZaposleni();
	}
	
	
	
	Object onActivate()
	{
		if(user == null)
			return index;
		return null;
	}



	public int getAvailableRows() {
		Integer brojZaposlenih = DbBroker.getInstance().countZaposleni();
		return brojZaposlenih;
	}



	public void prepare(int startIndex, int endIndex,
			List<SortConstraint> sortConstraints) {
			selection =  DbBroker.getInstance().getZaposleniRange(startIndex, endIndex);
			this.startIndex = startIndex; 
	}



	public Object getRowValue(int index) {
		return DbBroker.getInstance().getZaposleniById(index);
	}



	public Class getRowType() {
		return Zaposleni.class;
	}
}
