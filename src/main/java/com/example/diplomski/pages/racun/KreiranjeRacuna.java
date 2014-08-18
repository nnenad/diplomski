package com.example.diplomski.pages.racun;

import java.util.Calendar;
import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.FizickoLice;
import com.example.diplomski.entities.PravnoLice;
import com.example.diplomski.entities.Proizvod;
import com.example.diplomski.entities.ProizvodUsluga;
import com.example.diplomski.entities.Racun;
import com.example.diplomski.entities.StavkaRacuna;
import com.example.diplomski.entities.TipProizvoda;
import com.example.diplomski.entities.Usluga;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.poslovnipartner.PregledPoslovnihPartnera;
import com.example.diplomski.pages.zaposleni.ShowZaposleni;
import com.example.diplomski.pomocne_operacije.GetAllEntities;
import com.example.diplomski.pomocne_operacije.SelectProizvodUsluga;
import com.example.diplomski.so.PromeniStanjeProizvodaUsluge;

public class KreiranjeRacuna {
	
	@Persist
	private PravnoLice pravnoLice;
	
	@Persist
	private FizickoLice fizickoLice;
	
	@InjectComponent
	private Form racunForm;
	@Persist
	private boolean isForPravnoLice;
	
	private TipProizvoda tipProizvoda;
	
	@Property
	private String confirmMessage;
	
    @Persist
	private Racun racun;
	
	//One ce se popunjavati
	private List<StavkaRacuna> stavkeRacuna;
	
	@SessionState
	@Property
	private Zaposleni zaposleni;

	@Property
	@Persist(PersistenceConstants.FLASH)
	private List<Proizvod> sviProizvodi;
	
	@Property
	private Proizvod proizvodRow;
	
	@Property
	private Usluga uslugaRow;
	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private List<Usluga> sveUsluge;
	
	@Persist(PersistenceConstants.FLASH)  
    private Block blockToRender; 
	
	@InjectComponent
	private  Zone zoneStavke;
	
	@InjectComponent
	private Zone artiklConfirm;
	 
	@InjectComponent
	private Zone prikazProizvodaZone;
	
	@InjectComponent
	private Zone prikazUslugaZone;
	
	@Property
	@Persist
	private boolean showPGrid;
	
	@Property
	@Persist
	private boolean showUGrid;
	
	@InjectPage
	private IzdavanjeRacuna izdavanjeRacuna;
	
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	 @Property
	 @Persist(PersistenceConstants.FLASH)
	 StavkaRacuna tekucaStavka;
	 
	 public Block getBlockToRender(){  
         return blockToRender;  
     }  
     
       
     // This is called when button1 is clicked.  
     @SuppressWarnings("unchecked")
	 @OnEvent(component="prikaziProizvode", value="selected")  
     void selectBlock1() throws Exception  
     {  
    	 showPGrid = true;
    	 showUGrid = false;
    	 GetAllEntities getAllEntities = new GetAllEntities();
    	 sviProizvodi = (List<Proizvod>) getAllEntities.izvrsiSO(new Proizvod());
    	 //return prikazProizvodaZone.getBody();
        // blockToRender = prikazProizvoda;  
     }  
       
     //This is called when button2 is clicked.  
     @SuppressWarnings("unchecked")
	 @OnEvent(component="prikaziUsluge", value="selected")  
     void selectBlock2() throws Exception  
     {  
    	 showPGrid = false;
    	 showUGrid = true;
    	 GetAllEntities getAllEntities = new GetAllEntities();
    	 sveUsluge = (List<Usluga>) getAllEntities.izvrsiSO(new Usluga());
    	 //return prikazUslugaZone;
         //blockToRender = prikazUsluga;  
     }  

	
	
	public TipProizvoda getTipProizvoda() {
		return tipProizvoda;
	}

	public void setTipProizvoda(TipProizvoda tipProizvoda) {
		this.tipProizvoda = tipProizvoda;
	}

	public PravnoLice getPravnoLice() {
		return pravnoLice;
	}

	public void setPravnoLice(PravnoLice pravnoLice) {
		this.pravnoLice = pravnoLice;
	}

	public FizickoLice getFizickoLice() {
		return fizickoLice;
	}

	public void setFizickoLice(FizickoLice fizickoLice) {
		this.fizickoLice = fizickoLice;
	}

	public boolean getIsForPravnoLice() {
		return isForPravnoLice;
	}

	public void setForPravnoLice(boolean isForPravnoLice) {
		this.isForPravnoLice = isForPravnoLice;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

	public List<StavkaRacuna> getStavkeRacuna() {
		return stavkeRacuna;
	}

	public void setStavkeRacuna(List<StavkaRacuna> stavkeRacuna) {
		this.stavkeRacuna = stavkeRacuna;
	}
	
	
	 @Log
	    public Object onActivate()
	    {
	        racun = zaposleni.getCurrent();

	        if (racun == null)
	        {
	            return PregledPoslovnihPartnera.class;
	        }
	        
	        return null;
//	        else
//	        {
//	            confirmationStep = racun.getStatus();
//	            return null;
//	        }
	    }
	
	 @OnEvent(value = EventConstants.VALIDATE, component = "racunForm")
	    public void validateRacun()
	    {
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DAY_OF_MONTH, -1);
	        if (!zaposleni.getCurrent().getDatumKreiranja().before(zaposleni.getCurrent().getDatumIzdavanja()))
	        {
	        	racunForm.recordError("Datum dospeca mora biti nakon daruma kreiranja");
	            return;
	        }
	    }
	 
	  
	    @Log
	    Object onActionFromcancelRacun() throws Exception
	    {
	    	for(StavkaRacuna stavka : zaposleni.getCurrent().getStavkaRacunas()){
	    		 if(stavka.getProizvodUsluga() instanceof Proizvod){
	    			 
	    			 Proizvod prSel = (Proizvod) stavka.getProizvodUsluga();
	    			 SelectProizvodUsluga proizvodUslugaSO = new SelectProizvodUsluga();
	    			 
	    			 Proizvod proizvodSelektuj = (Proizvod) proizvodUslugaSO.izvrsiSO(prSel);
	    		 
	    			
	    			 PromeniStanjeProizvodaUsluge promeniStanjeProizvoda = new PromeniStanjeProizvodaUsluge();
	
	    			 prSel.setKolicina(proizvodSelektuj.getKolicina() + stavka.getKolicina()+1);
	    			 
	    			 promeniStanjeProizvoda.izvrsiSO(prSel);
	    			 
	    		 }
	    	}
	    	
	        zaposleni.setCurrent(null);


	        return PregledPoslovnihPartnera.class;
	    }
	 
	 @OnEvent(value= "submit" , component="racunForm")
	 public Object kreirajRacun(){
		 izdavanjeRacuna.setRacun(zaposleni.getCurrent());
		 return izdavanjeRacuna;
	 }

	 
	 Object onActionFromKupiUslugu(Integer idUsluge) throws Exception{
		 SelectProizvodUsluga selectProizvodUslugaSO = new SelectProizvodUsluga();
		 Usluga pronadjiUslugu = new Usluga();
		 pronadjiUslugu.setIdProizvodUsluga(idUsluge);
			Usluga usl = (Usluga) selectProizvodUslugaSO.izvrsiSO(pronadjiUslugu);
			if(usl!= null){
				StavkaRacuna sr = new StavkaRacuna();
				sr.setRacun(zaposleni.getCurrent());
				sr.setProizvodUsluga(usl);
				zaposleni.getCurrent().addStavka(sr);
				
				return zoneStavke;
			}else{
				confirmMessage = "Usluga nije trenutno dostupna!";
				return artiklConfirm.getBody();
			}
	}
	 
	 Object onActionFromkupiProizvod(Integer idProizvoda) throws Exception{
		    SelectProizvodUsluga selectProizvodUslugaSO = new SelectProizvodUsluga();
		    Proizvod pronadjiProizvod = new Proizvod();
		    pronadjiProizvod.setIdProizvodUsluga(idProizvoda);
		 	Proizvod proi = (Proizvod) selectProizvodUslugaSO.izvrsiSO(pronadjiProizvod);
		 	if(proi != null){
		 		
		 		if(proi.getKolicina() == 0){
		 			confirmMessage = "Proizvoda nema vise na stanju!";
		 			
					return artiklConfirm.getBody();
		 		}else{
				 	StavkaRacuna sr = new StavkaRacuna();
					sr.setRacun(zaposleni.getCurrent());
					sr.setProizvodUsluga(proi);
					zaposleni.getCurrent().addStavka(sr);
					
					PromeniStanjeProizvodaUsluge promeniStanjeProizvoda = new PromeniStanjeProizvodaUsluge();
					promeniStanjeProizvoda.izvrsiSO(proi);
					
					selectBlock1();
					ajaxResponseRenderer.addRender("prikazProizvodaZone",prikazProizvodaZone);
					
					return zoneStavke;
				}
		 		
			}else{
				confirmMessage = "Proizvod nije trenutno dostupan!";
				return artiklConfirm.getBody();
			}
	}
	
	 Object onActionFrompotvrdiZaArtikl(){
		 return zoneStavke;
	 }
	
	 Object onActionFromobrisiStavku(Integer idProizvodUsluga ) throws Exception{
		//Integer idProizvodUsluga = 3;
		 SelectProizvodUsluga proizvodUslugaSO = new SelectProizvodUsluga();
		 ProizvodUsluga proizvodUslugaSelektuj = new ProizvodUsluga();
		 proizvodUslugaSelektuj.setIdProizvodUsluga(idProizvodUsluga);
		 
		 ProizvodUsluga selektovaniPU = (ProizvodUsluga) proizvodUslugaSO.izvrsiSO(proizvodUslugaSelektuj);
		 if(selektovaniPU instanceof Proizvod){
			 Proizvod prSel =  (Proizvod) selektovaniPU;
			
			 PromeniStanjeProizvodaUsluge promeniStanjeProizvoda = new PromeniStanjeProizvodaUsluge();
				
			 StavkaRacuna stavkaZProizvod =  this.zaposleni.getCurrent().findStavkaRacuna(idProizvodUsluga);
			 prSel.setKolicina(prSel.getKolicina() + stavkaZProizvod.getKolicina()+1);
			 
			 promeniStanjeProizvoda.izvrsiSO(prSel);
			 
			 this.zaposleni.getCurrent().removStavkaRacuna(idProizvodUsluga);
		 }else{
			 this.zaposleni.getCurrent().removStavkaRacuna(idProizvodUsluga);
			 
		 }
		 return zoneStavke.getBody();
	 }
}
