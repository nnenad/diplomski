package com.example.diplomski.pomocne_operacije;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.OpstiDomenskiObjekat;
import com.example.diplomski.entities.ProizvodUsluga;
import com.example.diplomski.so.OpstaSO;

public class SelectProizvodUsluga extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		ProizvodUsluga proizvodUsluga =  (ProizvodUsluga) odo;
		return DbBroker.getInstance().getEntityById(proizvodUsluga, proizvodUsluga.idColumName(), proizvodUsluga.getIdProizvodUsluga());
	}

}
