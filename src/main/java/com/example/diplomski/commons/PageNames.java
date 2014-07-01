package com.example.diplomski.commons;

public class PageNames {
	private String menuName;
	private String logicalName;
	
	public PageNames() {
	}
	
	public PageNames(String menuName, String logicalName) {
		this.menuName = menuName;
		this.logicalName = logicalName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getLogicalName() {
		return logicalName;
	}
	public void setLogicalName(String logicalName) {
		this.logicalName = logicalName;
	}
}
