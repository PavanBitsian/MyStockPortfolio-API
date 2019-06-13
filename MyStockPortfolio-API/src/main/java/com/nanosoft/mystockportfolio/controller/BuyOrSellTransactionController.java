package com.nanosoft.mystockportfolio.controller;

import java.util.Date;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nanosoft.mystockportfolio.model.BuyOrSellTransaction;
import com.nanosoft.mystockportfolio.model.Stock;
import com.nanosoft.mystockportfolio.model.Transaction;
import com.nanosoft.mystockportfolio.model.User;

@RestController
@RequestMapping("/transact")
public class BuyOrSellTransactionController {
	
	@RequestMapping(method=RequestMethod.POST,value="/buy/{companyName}")
	public boolean purchaseStock(@RequestBody BuyOrSellTransaction buyOrSellTransaction,@PathVariable("companyName")String companyName ) {
		int userId = buyOrSellTransaction.getUserId();
		System.out.println("purchaseStock userId:"+userId);
		boolean flag = true;
		try {
			UserController userController = new UserController();
			User user = userController.getUserData(userId);
			Transaction t = new Transaction();
			t.setAmount(buyOrSellTransaction.getEachSharePurchaseValue()*buyOrSellTransaction.getNumOfShares());;
			t.setTimeStamp(new Date(System.currentTimeMillis()));
			t.setTransactionType("Dr.");
			t.setUserId(buyOrSellTransaction.getUserId());
			user.getListOfCashTransactions().add(t);
			user.setCurrentCashBalance(user.getCurrentCashBalance()-(t.getAmount()));
			user.setNetTotalAssetValue(user.getNetTotalAssetValue()+t.getAmount());
			boolean cPresent = false;
			for(Stock cStock:user.getStocksHolded()) {
				if(companyName.equals(cStock.getCompanyName())) {
					cPresent = true;
				}
			}
			System.out.println("purchaseStock cPresent:"+cPresent);
			if(!cPresent) {
				TreeSet<Stock> stocksHolded = user.getStocksHolded();
				Stock s = new Stock(companyName,buyOrSellTransaction.getNumOfShares());
				stocksHolded.add(s);
				user.setStocksHolded(stocksHolded);
			}
			user.getStocksHolded().stream().filter(s->s.getCompanyName().equals(companyName)).forEach(s->s.setNumOfShares(s.getNumOfShares()+buyOrSellTransaction.getNumOfShares()));
			
		}catch (Exception e) {
			// TODO: handle exception
			flag = false;
			System.out.println("Exception:"+e.getMessage());
		}
		
		return flag;
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/sell/{companyName}")
	public boolean sellStock(@RequestBody BuyOrSellTransaction buyOrSellTransaction,@PathVariable("companyName")String companyName ) {
		int userId = buyOrSellTransaction.getUserId();
		System.out.println("sellStock userId:"+userId);
		boolean flag = true;
		try {
			UserController userController = new UserController();
			User user = userController.getUserData(userId);
			Transaction t = new Transaction();
			t.setAmount(buyOrSellTransaction.getEachSharePurchaseValue()*buyOrSellTransaction.getNumOfShares());;
			t.setTimeStamp(new Date(System.currentTimeMillis()));
			t.setTransactionType("Cr.");
			t.setUserId(buyOrSellTransaction.getUserId());
			user.getListOfCashTransactions().add(t);
			user.setCurrentCashBalance(user.getCurrentCashBalance()+(t.getAmount()));
			user.setNetTotalAssetValue(user.getNetTotalAssetValue()-t.getAmount());
			user.getStocksHolded().stream().filter(s->s.getCompanyName().equals(companyName)).forEach(s->s.setNumOfShares(s.getNumOfShares()-buyOrSellTransaction.getNumOfShares()));
			for(Stock cStock:user.getStocksHolded()) {
				if(companyName.equals(cStock.getCompanyName())) {
					if(cStock.getNumOfShares()==0) {
						user.getStocksHolded().remove(cStock);
					}
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			flag = false;
		}
		
		return flag;
		
	}

}
