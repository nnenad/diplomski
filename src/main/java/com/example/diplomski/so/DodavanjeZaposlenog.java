package com.example.diplomski.so;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;

public class DodavanjeZaposlenog extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object odo) throws Exception {
		if(!DbBroker.getInstance().doesZaposleniExists((Zaposleni)odo)){
			((Zaposleni)odo).setIdZaposlenog(-1);
		}

	}

	@Override
	protected Object izvrsiOperaciju(Object odo) throws Exception {
		Object idZap = ((Zaposleni) odo).getIdZaposlenog();
		if(idZap == null || !idZap.equals(-1)){
			return DbBroker.getInstance().saveEntity(odo);
		}else{
			return odo;
		}
	}

}
