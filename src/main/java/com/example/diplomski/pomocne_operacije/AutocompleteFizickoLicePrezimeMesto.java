package com.example.diplomski.pomocne_operacije;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.FizickoLice;
import com.example.diplomski.so.OpstaSO;

public class AutocompleteFizickoLicePrezimeMesto extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		FizickoLice fizickoLiceAuto = (FizickoLice) odo;
		return DbBroker.getInstance().getAutocompleteFizickoLicePrezimeMesto(fizickoLiceAuto.getMesto());
	}

}
