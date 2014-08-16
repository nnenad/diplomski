package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.FizickoLice;

public class PretragaFizickihLica extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		
	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		FizickoLice fizickoLiceZaPretragu = (FizickoLice) odo;
		return DbBroker.getInstance().gettAllFizickoLiceByNameAndMesto(fizickoLiceZaPretragu.getPrezime(), fizickoLiceZaPretragu.getMesto());

	}

}
