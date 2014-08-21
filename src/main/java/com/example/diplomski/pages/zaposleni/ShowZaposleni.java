package com.example.diplomski.pages.zaposleni;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;

import com.example.diplomski.commons.IntegerValueEncoder;
import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.FizickoLice;
import com.example.diplomski.entities.PoslovniPartner;
import com.example.diplomski.entities.PravnoLice;
import com.example.diplomski.entities.Racun;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.Index;
import com.example.diplomski.pomocne_operacije.AutocompleteFilickaLicaPrezime;
import com.example.diplomski.pomocne_operacije.AutocompletePravnaLicaNaziv;
import com.example.diplomski.pomocne_operacije.DajSvaFizickaLica;
import com.example.diplomski.pomocne_operacije.GetAllEntities;
import com.example.diplomski.pomocne_operacije.GetAllZaposleniMesta;
import com.example.diplomski.so.PronadjiRacun;
import com.example.diplomski.util.ZaposleniSource;

public class ShowZaposleni{

	@SessionState(create=false)
	private Zaposleni user;
	
	
	private String poslovniPartner;
	
	@Property 
	private boolean showFizickoLIce;
	
	private List<PravnoLice> listaPravnihLica = new ArrayList<PravnoLice>();
	
	private List<FizickoLice> listaFizickihLica = new ArrayList<FizickoLice>();;
	
	private String hiddenSign;
	
	@InjectComponent
	private Zone eqnZone;
	
	@InjectComponent
	private Zone prikazRacuna;
	
	@Property
	private Integer poslovniParnerLista;
	
	private PoslovniPartner selektovaniPoslovniPartner;
	
	@Property
	private Racun racun;
	
	@Property
	private List<Racun> listaRacunaZaPArtnera;
	
	public PoslovniPartner getSelektovaniPoslovniPartner() {
		if(selektovaniPoslovniPartner == null){
			return new PoslovniPartner();
		}else{
			return selektovaniPoslovniPartner;
		}
	}

	public void setSelektovaniPoslovniPartner(
			PoslovniPartner selektovaniPoslovniPartner) {
		this.selektovaniPoslovniPartner = selektovaniPoslovniPartner;
	}

	public String getPoslovniPartner() {
		if(poslovniPartner == null){
			poslovniPartner = "PravnoLice";
		}
		return poslovniPartner;
	}

	public void setPoslovniPartner(String poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}

	public String getHiddenSign() {
		if(hiddenSign == null){
			return "hidden";
		}else{
			return hiddenSign;
		}
	}

	public void setHiddenSign(String hiddenSign) {
		this.hiddenSign = hiddenSign;
	}

	@SuppressWarnings("unchecked")
	void onActivate() throws Exception{
		
		GetAllEntities dajSveEntitete = new GetAllEntities();
		
		listaPravnihLica = (List<PravnoLice>) dajSveEntitete.izvrsiSO(new PravnoLice());
		
		listaFizickihLica = (List<FizickoLice>) dajSveEntitete.izvrsiSO(new FizickoLice());
	}
	
	public SelectModel getPoslovniPartneri() throws Exception{
		List<OptionModel> optionsModel = new ArrayList<OptionModel>();
		if(showFizickoLIce){
			for(FizickoLice fl: listaFizickihLica){
				OptionModelImpl omtM = new OptionModelImpl(fl.toString(), fl.getIdPoslovnogPartnera());  
				optionsModel.add(omtM);
			}
		}else{
			for(PravnoLice pl: listaPravnihLica){
				OptionModelImpl omtM = new OptionModelImpl(pl.toString(), pl.getIdPoslovnogPartnera());  
				optionsModel.add(omtM);
			}
		}
		
		return new SelectModelImpl(null, optionsModel);
	}
	
	 public ValueEncoder<Integer> getValueEncoder(){  
	        return new IntegerValueEncoder();  
	    }  
	
	 public Object onValueChangedFromPoslovniPartner(String poslPart) {  
		 if(poslPart.equals("FizickoLice")){
			 setHiddenSign("none");
			 showFizickoLIce = true;
		 }else{
			 setHiddenSign("none");
			 showFizickoLIce = false;
		 }
		 
		 
		 return eqnZone.getBody();
	 }  
	 
	 Object onValueChangedFromposlovniPartnerLista(Integer poslPart) throws Exception{
		 if(showFizickoLIce){
				for(FizickoLice fl: listaFizickihLica){
					if(fl.getIdPoslovnogPartnera().equals(poslPart)){
						selektovaniPoslovniPartner = fl;
						PronadjiRacun pronadjiRacun = new PronadjiRacun();
						listaRacunaZaPArtnera = (List<Racun>) pronadjiRacun.izvrsiSO(fl);
						break;
					}
				}
			}else{
				for(PravnoLice pl: listaPravnihLica){
					if(pl.getIdPoslovnogPartnera().equals(poslPart)){
						selektovaniPoslovniPartner = pl;
						PronadjiRacun pronadjiRacun = new PronadjiRacun();
						listaRacunaZaPArtnera = (List<Racun>) pronadjiRacun.izvrsiSO(pl);
						break;
					}
				}
			}
		 
		 return prikazRacuna.getBody();
	 }
}
