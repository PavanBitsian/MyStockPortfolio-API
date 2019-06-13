package com.nanosoft.mystockportfolio.model;


public class Stock implements Comparable<Stock>{
	private String companyName;
	private double high;
	private double low;
	private int volume;
	private int numOfShares;
	
	public Stock(){
		
	}
	
	public Stock(String companyName,int numOfShares){
		this.companyName = companyName;
		this.numOfShares = numOfShares;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getNumOfShares() {
		return numOfShares;
	}
	public void setNumOfShares(int numOfShares) {
		this.numOfShares = numOfShares;
	}

	@Override
	public int compareTo(Stock s) {
		// TODO Auto-generated method stub
		return this.companyName.compareTo(s.companyName);
	}
	
	

}
