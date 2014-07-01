package com.example.diplomski.util;

import com.example.diplomski.celebrity.model.User;
import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.Zaposleni;

public class Security {

	public static Zaposleni authenticate(String userName, String password) {
		Zaposleni zaposleni = DbBroker.getInstance().getZaposleniByUserNameAndPass(userName, password);
		if (zaposleni != null) {
			return zaposleni;
		}
		return null;
	}
}
