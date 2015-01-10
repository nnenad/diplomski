package com.example.diplomski.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

import com.example.diplomski.commons.MenagePageNames;
import com.example.diplomski.entities.Zaposleni;
import com.example.diplomski.pages.Index;

/**
 * Layout component for pages of application diplomski.
 */
@Import(stylesheet = "context:layout/layout.css")
public class Layout
{
    /**
     * The page title, for the <title> element and the <h1> element.
     */
	@SessionState
	private Zaposleni user;
	
	
	private boolean isLogedIn;
	
	
	
	public boolean isLogedIn() {
		if(user != null){
	    	if(user.getTip() != null && user.getTip().equals(1)){
	    		return  true;
	    		
	    	}else if(user.getTip() != null && user.getTip().equals(2)){
	    		return true;
	    		
	    	}else{
	    		return false;
	    		
	    	}
    	}
		return false;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}
	@Persist
	private String korisnickoIme;
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	@InjectPage
	private Index index;

	public void setKorisnickoIme(String korisnickoIme) {
		if(user != null && user.getKorisnickoIme() != null)
			this.korisnickoIme = user.getKorisnickoIme();
		this.korisnickoIme = "guest";
	}
	
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    private String pageLogicName;
    
    public String getPageLogicName() {
		return MenagePageNames.getPageLogicName(pageName);
	}

	public void setPageLogicName(String pageName) {
		this.pageName = pageName;
	}
	@Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;


    public Zaposleni getUser() {
		return user;
	}

	public void setUser(Zaposleni user) {
		this.user = user;
	}

	public String getClassForPageName()
    {
        return resources.getPageName().equalsIgnoreCase(MenagePageNames.getPageLogicName(pageName))
                ? "current_page_item"
                : null;
    }

    public String[] getPageNames()
    {
    	
    	if(user != null){
	    	if(user.getTip() != null && user.getTip().equals(1)){
	    		isLogedIn = true;
	    		return new String[]{"Profil","Prikaz racuna", "Novi zaposleni","obrada zaposlenih", "Pretraga"};
	    	}else if(user.getTip() != null && user.getTip().equals(2)){
	    		isLogedIn = true;
	    		return new String[]{"Profil", "Pretraga"};
	    	}else{
	    		isLogedIn = false;
	    		return new String[]{"Index"};
	    	}
    	}else{
    		 return new String[]{"Index", "About"};
    	}
    }
    
    @OnEvent(value="action", component="logOut")
    Object logOut()
    {
        index.setUser(null);
        return index;
    }
}
