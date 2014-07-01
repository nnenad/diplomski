package com.example.diplomski.components;

import java.util.List;

import org.apache.tapestry5.annotations.SessionState;

import com.example.diplomski.entities.ProizvodUsluga;
import com.example.diplomski.entities.Zaposleni;

public class StavkeRacuna {

	@SessionState
	private Zaposleni zaposleni;
	
//	public List<ProizvodUsluga> getStavkeRacuna(){
//		zaposleni.getCurrent().getStavkaRacunas();
//	}
}
