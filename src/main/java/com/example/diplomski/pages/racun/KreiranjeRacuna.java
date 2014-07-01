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
import com.example.diplomski.entities.Racun;
import com.example.diplomski.entities.StavkaRacuna;
import com.example.diplomski.entities.TipProizvoda;
import com.example.diplomski.entities.Usluga;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.poslovnipartner.PregledPoslovnihPartnera;
import com.example.diplomski.pages.zaposleni.ShowZaposleni;

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
	@Persist
	private List<Proizvod> sviProizvodi;
	
	@Property
	private Proizvod proizvodRow;
	
	@Property
	private Usluga uslugaRow;
	@Property
	@Persist
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
	 StavkaRacuna tekucaStavka;
	 public Block getBlockToRender(){  
         return blockToRender;  
     }  
     
       
     // This is called when button1 is clicked.  
     @OnEvent(component="prikaziProizvode", value="selected")  
     void selectBlock1()  
     {  
    	 showPGrid = true;
    	 showUGrid = false;
    	 sviProizvodi = DbBroker.getInstance().getAllProizvods();
    	 //return prikazProizvodaZone.getBody();
        // blockToRender = prikazProizvoda;  
     }  
       
     //This is called when button2 is clicked.  
     @OnEvent(component="prikaziUsluge", value="selected")  
     void selectBlock2()  
     {  
    	 showPGrid = false;
    	 showUGrid = true;
    	 sveUsluge = DbBroker.getInstance().getAllUslugas();
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
	        if (racun.getDatumKreiranja().before(calendar.getTime()))
	        {
	        	racunForm.recordError("before date");
	            return;
	        }
	        else if (!racun.getDatumKreiranja().before(racun.getDatumIzdavanja()))
	        {
	        	racunForm.recordError("wrong date");
	            return;
	        }
	    }
	 
	 @OnEvent(value = "cancelRacun")
	    @Log
	    public Object cancelBooking()
	    {
	        zaposleni.setCurrent(null);


	        return PregledPoslovnihPartnera.class;
	    }
	 
	 @OnEvent(value= "submit" , component="racunForm")
	 public Object kreirajRacun(){
		 izdavanjeRacuna.setRacun(zaposleni.getCurrent());
		 return izdavanjeRacuna;
	 }

	 
	 Object onActionFromKupiUslugu(Integer idUsluge){
			Usluga usl = (Usluga) DbBroker.getInstance().getProizvodUslugaById(idUsluge);
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
	 
	 Object onActionFromkupiProizvod(Integer idProizvoda){
		 	Proizvod proi = (Proizvod) DbBroker.getInstance().getProizvodUslugaById(idProizvoda);
		 	if(proi != null){
		 		
		 		if(proi.getKolicina() == 0){
		 			confirmMessage = "Proizvoda nema vise na stanju!";
		 			
					return artiklConfirm.getBody();
		 		}else{
				 	StavkaRacuna sr = new StavkaRacuna();
					sr.setRacun(zaposleni.getCurrent());
					sr.setProizvodUsluga(proi);
					zaposleni.getCurrent().addStavka(sr);
					
					DbBroker.getInstance().smaniKolicinuProizvoda(proi);
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
}
