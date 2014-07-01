package com.example.diplomski.util;

import java.util.List;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;

public class ZaposleniSource implements GridDataSource {
	
	private List<Zaposleni> zaposleni;
	private int indexFrom;

	
	public ZaposleniSource() {
		
	}

	public int getAvailableRows() {
		// TODO Auto-generated method stub
		return DbBroker.getInstance().getAllZaposleni().size();
	}

	public void prepare(int startIndex, int endIndex,
			List<SortConstraint> sortConstraints) {
		System.out.println("Preparing selection.");
		System.out.println("Index from " + indexFrom + " to " + endIndex);
		
		//System.out.println("Sorting order ascending: " + sortConstraints.);
		zaposleni = (List<Zaposleni>) DbBroker.getInstance().getZaposleniRange(startIndex, endIndex);
		this.indexFrom = startIndex;
		
	}

	public Object getRowValue(int index) {
		System.out.println("Getting value for row " + index);
		return zaposleni.get(index - this.indexFrom);
	}

	public Class getRowType() {
		return Zaposleni.class;
	}

}
