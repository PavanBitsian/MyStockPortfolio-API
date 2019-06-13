package com.nanosoft.mystockportfolio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class User {
	private String userName;
	private int userId;
	private double currentCashBalance;
	private double netTotalAssetValue;
	private TreeSet<Stock> stocksHolded;
	private List<Transaction> listOfCashTransactions;
	
	User(){
		
	}
	
	public User(String userName,int userId,double currentCashBalance,double netTotalAssetValue){
		this.userName = userName;
		this.userId = userId;
		this.currentCashBalance = currentCashBalance;
		this.netTotalAssetValue = netTotalAssetValue;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getCurrentCashBalance() {
		return currentCashBalance;
	}
	public void setCurrentCashBalance(double currentCashBalance) {
		this.currentCashBalance = currentCashBalance;
	}
	public double getNetTotalAssetValue() {
		return netTotalAssetValue;
	}
	public void setNetTotalAssetValue(double netTotalAssetValue) {
		this.netTotalAssetValue = netTotalAssetValue;
	}
	public TreeSet<Stock> getStocksHolded() {
		return stocksHolded==null?new TreeSet<Stock>():stocksHolded;
	}
	public void setStocksHolded(TreeSet<Stock> stocksHolded) {
		this.stocksHolded = stocksHolded;
	}
	public List<Transaction> getListOfCashTransactions() {
		return this.listOfCashTransactions == null ? new ArrayList<Transaction>():listOfCashTransactions;
	}
	public void setListOfCashTransactions(List<Transaction> listOfCashTransactions) {
		this.listOfCashTransactions = listOfCashTransactions;
	}
	
	
	
}
