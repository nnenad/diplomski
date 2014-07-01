package com.example.diplomski.pages.poslovnipartner;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Events;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.example.diplomski.dbbroker.DbBroker;
import com.example.diplomski.entities.FizickoLice;
import com.example.diplomski.entities.PoslovniPartner;
import com.example.diplomski.entities.PravnoLice;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.racun.KreiranjeRacuna;


@Events(value="provideCompletions")
public class PregledPoslovnihPartnera {

	@SessionState
	private Zaposleni zaposleni;
	@InjectPage
	private KreiranjeRacuna kreiranjeRacuna;
	
	private String searchPhrase ;
	
	@Property
	private String selectedEqn;
	
	@Persist
	@Property
	private String nesto;
	
	@Persist
	 private String eqnType; 
	
	@Persist
	private List<PravnoLice> pPList;
	
	@Persist
	private List<FizickoLice> fzList;

	@Persist
	private PravnoLice ppValue;
	
	@Persist
	private FizickoLice fzValue;
	
	private List<String> autocompleteList;
	
	private List<String> fizickaLicaAutocomplete;
	
	private List<String> pravnaLicaAutocomplete;
	
	 @Persist            
	 @Property  
	 private boolean fizikoLicaVisible; 
	 
	 @Persist            
	 @Property  
	 private String mesto; 
	 
    @InjectComponent  
    private Zone eqnZone; 
    
    
	public String getEqnType() {
		if(eqnType == null){
			eqnType = "FizickoLice";
		}
		return eqnType;
	}
	public void setEqnType(String eqnType) {
		this.eqnType = eqnType;
	}
	public List<String> getAutocompleteList() {
		return autocompleteList;
	}
	public void setAutocompleteList(List<String> autocompleteList) {
		this.autocompleteList = autocompleteList;
	}
	void onPartnerFChanged(){
		this.autocompleteList = fizickaLicaAutocomplete;
	}
	
	void onPartnerPChanged(){
		this.autocompleteList = pravnaLicaAutocomplete;
	}
	void onActivate(){
		pravnaLicaAutocomplete = DbBroker.getInstance().getAutocompetePravnoLiceNaziv();
		fizickaLicaAutocomplete = DbBroker.getInstance().getAutocompleteFizickoLicePrezime();
	}
	
	public String getSearchPhrase() {
		return searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public FizickoLice getFzValue() {
		return fzValue;
	}

	public void setFzValue(FizickoLice pzValue) {
		this.fzValue = pzValue;
	}

	public List<FizickoLice> getFzList() {
		return fzList;
	}

	public void setFzList(List<FizickoLice> fzList) {
		this.fzList = fzList;
	}

	public List<PravnoLice> getpPList() {
		return pPList;
	}

	public void setpPList(List<PravnoLice> pPList) {
		this.pPList = pPList;
	}

	public PravnoLice getPpValue() {
		return ppValue;
	}

	public void setPpValue(PravnoLice ppValue) {
		this.ppValue = ppValue;
	}
	
	@OnEvent(value = "provideCompletions")
    public List<String> autoComplete(String start)
    {
        List<String> strings = new ArrayList<String>();
        
        if(fizikoLicaVisible){
        	if(mesto != null && !mesto.equals("sva")){
			 autocompleteList = DbBroker.getInstance().getAutocompleteFizickoLicePrezimeMesto(mesto);
        		}else{
        			autocompleteList = fizickaLicaAutocomplete;
        		}
		 }else{
			 autocompleteList = pravnaLicaAutocomplete;
		 }
        if (start != null)
        {
            for(String value : autocompleteList){
                if(value.toUpperCase().startsWith(start.toUpperCase())) 
                    strings.add(value);
            }
        }
        
        return strings;
        
    }
	
	public String[] getMesta(){
		List<String> listaMesta = DbBroker.getInstance().getAllMestaFilickoLice();
		String[] nizMesta = new String[listaMesta.size()+1];
		for(int i=0; i < listaMesta.size(); i++){
			nizMesta[i] = listaMesta.get(i);
		}
		nizMesta[listaMesta.size()] = "sva";
		return nizMesta;
	}
	
	@OnEvent(value="submit", component="searchForm")
	void onSearchSubmit(){
		
		if(fizikoLicaVisible){
			List<FizickoLice> fzList = DbBroker.getInstance().gettAllFizickoLiceByNameAndMesto(this.searchPhrase, this.mesto);
			this.fzList = fzList;
			this.pPList = null;
		}else{
			List<PravnoLice> pPListBaza = DbBroker.getInstance().gettAllPravnoLiceByName(this.searchPhrase);
			pPList = pPListBaza;
			this.fzList = null;
		}
		
	}
	
//	 public Object onValueChangedFromEquationType(String eqnType) {  
//		 this.selectedEqn = eqnType;
//		 if(eqnType.equals("FizickoLice")){
//			 autocompleteList = fizickaLicaAutocomplete;
//		 }else{
//			 autocompleteList = pravnaLicaAutocomplete;
//		 }
//		 
//		 return null;
	// }  
	
	 public boolean getShowPGrid(){
		 if(!fizikoLicaVisible){
			 return true;
		 }else {
			 return false;
		 }
	 }
	 
	 public boolean getShowFGrid(){
		 if(fizikoLicaVisible){
			 return true;
		 }else {
			 return false;
		 }
	 }
	@OnEvent(component="showPPDetals")
	Object showPPValue(String ime){
		//about.setPoslovniPartner(ppValue);
		return null;
	}
	
	
	Object onActionFromSellToFZ(){
		zaposleni.startCratingRacun(fzValue);
		DbBroker.getInstance().saveEntity(zaposleni.getCurrent());
		kreiranjeRacuna.setForPravnoLice(false); 
		
		
		return kreiranjeRacuna;
	}
	
	
	Object onActionFromsellToPP(){
		zaposleni.startCratingRacun(ppValue);
		DbBroker.getInstance().saveEntity(zaposleni.getCurrent());
		kreiranjeRacuna.setForPravnoLice(true); 
		
		return kreiranjeRacuna;
		
	}
	
}
