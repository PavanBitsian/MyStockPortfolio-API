package com.nanosoft.mystockportfolio.model;

public class BuyOrSellTransaction {
	private int userId;
	private String companyName;
	private int numOfShares;
	private double eachSharePurchaseValue;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getNumOfShares() {
		return numOfShares;
	}
	public void setNumOfShares(int numOfShares) {
		this.numOfShares = numOfShares;
	}
	public double getEachSharePurchaseValue() {
		return eachSharePurchaseValue;
	}
	public void setEachSharePurchaseValue(double eachSharePurchaseValue) {
		this.eachSharePurchaseValue = eachSharePurchaseValue;
	}
	
	

}
