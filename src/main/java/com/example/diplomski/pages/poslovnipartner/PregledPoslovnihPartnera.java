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

import com.example.diplomski.entities.FizickoLice;
import com.example.diplomski.entities.PoslovniPartner;
import com.example.diplomski.entities.PravnoLice;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.racun.KreiranjeRacuna;
import com.example.diplomski.pomocne_operacije.AutocompleteFilickaLicaPrezime;
import com.example.diplomski.pomocne_operacije.AutocompleteFizickoLicePrezimeMesto;
import com.example.diplomski.pomocne_operacije.AutocompletePravnaLicaNaziv;
import com.example.diplomski.pomocne_operacije.GetAllZaposleniMesta;
import com.example.diplomski.so.KreirajRacun;
import com.example.diplomski.so.PretragaFizickihLica;
import com.example.diplomski.so.PretragaPravnihLica;


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
	
	@SuppressWarnings("unchecked")
	void onActivate() throws Exception{
		AutocompletePravnaLicaNaziv autoPl = new AutocompletePravnaLicaNaziv();
		AutocompleteFilickaLicaPrezime autoFl= new AutocompleteFilickaLicaPrezime();
		pravnaLicaAutocomplete = (List<String>) autoPl.izvrsiSO(null);
		fizickaLicaAutocomplete = (List<String>) autoFl.izvrsiSO(null);
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
	
	@SuppressWarnings("unchecked")
	@OnEvent(value = "provideCompletions")
    public List<String> autoComplete(String start) throws Exception
    {
        List<String> strings = new ArrayList<String>();
        
        if(fizikoLicaVisible){
        	if(mesto != null && !mesto.equals("sva")){
        		AutocompleteFizickoLicePrezimeMesto atocompletePrezimeMesto = new AutocompleteFizickoLicePrezimeMesto();
        		FizickoLice fizickoLIcePretragaMesto = new FizickoLice();
        		fizickoLIcePretragaMesto.setMesto(mesto);
        		autocompleteList = (List<String>) atocompletePrezimeMesto.izvrsiSO(fizickoLIcePretragaMesto);
			 
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
	
	public String[] getMesta() throws Exception{
		GetAllZaposleniMesta zaposleniMesta = new GetAllZaposleniMesta();
		@SuppressWarnings("unchecked")
		List<String> listaMesta = (List<String>) zaposleniMesta.izvrsiSO(null);
		String[] nizMesta = new String[listaMesta.size()+1];
		for(int i=0; i < listaMesta.size(); i++){
			nizMesta[i] = listaMesta.get(i);
		}
		nizMesta[listaMesta.size()] = "sva";
		return nizMesta;
	}
	
	@SuppressWarnings("unchecked")
	@OnEvent(value="submit", component="searchForm")
	void onSearchSubmit() throws Exception{
		
		if(fizikoLicaVisible){
			PretragaFizickihLica pretragaFL = new PretragaFizickihLica();
			FizickoLice fizickoLiceZaPretragu = new FizickoLice();
			
			fizickoLiceZaPretragu.setPrezime(this.searchPhrase);
			fizickoLiceZaPretragu.setMesto(this.mesto);
			
			List<FizickoLice> fzList = (List<FizickoLice>) pretragaFL.izvrsiSO(fizickoLiceZaPretragu);
			this.fzList = fzList;
			this.pPList = null;
		}else{
			PravnoLice pravnoLiceZaPretragu = new PravnoLice();
			pravnoLiceZaPretragu.setNaziv(this.searchPhrase);
			PretragaPravnihLica pretragaPL = new PretragaPravnihLica();
			
			pPList = (List<PravnoLice>) pretragaPL.izvrsiSO(pravnoLiceZaPretragu);
		
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
	
	
	Object onActionFromSellToFZ() throws Exception{
		zaposleni.startCratingRacun(fzValue);
		KreirajRacun kreirajRacun = new KreirajRacun();
		kreirajRacun.izvrsiSO(zaposleni.getCurrent());
		
		kreiranjeRacuna.setForPravnoLice(false); 
		
		
		return kreiranjeRacuna;
	}
	
	
	Object onActionFromsellToPP() throws Exception{
		zaposleni.startCratingRacun(ppValue);
		KreirajRacun kreirajRacun = new KreirajRacun();
		kreirajRacun.izvrsiSO(zaposleni.getCurrent());
		
		kreiranjeRacuna.setForPravnoLice(true); 
		
		return kreiranjeRacuna;
		
	}
	
}
