package com.example.diplomski.commons;

import java.util.ArrayList;

public class MenagePageNames {
	
	private static ArrayList<PageNames> pageList = new ArrayList<PageNames>();
	
	public static void createPageNames(){
		PageNames pn = new PageNames("Show all", "zaposleni/Show");
		pageList.add(pn);
		pn = new PageNames("Create", "zaposleni/Create");
		pageList.add(pn);
		pn = new PageNames("Pretraga", "poslovnipartner/pregledposlovnihpartnera");
		pageList.add(pn);
	}
	
	public static String getPageLogicName(String pageMenuName){
		createPageNames();
		for(PageNames pn: pageList){
			if(pn.getMenuName().equals(pageMenuName)){
				return pn.getLogicalName();
			}
		}
		
		return pageMenuName;
		
	}
}
