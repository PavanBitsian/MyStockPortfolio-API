package com.nanosoft.mystockportfolio.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nanosoft.mystockportfolio.controller.UserController;
import com.nanosoft.mystockportfolio.model.Transaction;
import com.nanosoft.mystockportfolio.model.User;

@Service
public class TransactionService {
	List<Transaction> allTransactions ;
	Map<Integer,List<Transaction>> userTransactions;
	
	public List<Transaction> getAllUserTransactions(int userId){
		//return allTransactions;
		System.out.println("getAllUserTransactions "+UserController.userData.get(userId).getUserName());
		return UserController.userData.get(userId).getListOfCashTransactions();
	}
	
	
	public void addTransaction(Transaction transaction) {
		System.out.println("start addTransaction");
		if(UserController.userData == null) {
			userTransactions = new HashMap<Integer,List<Transaction>>();
			if(userTransactions.get(transaction.getUserId())==null) {
				allTransactions = new ArrayList<Transaction>();
				allTransactions.add(transaction);
				System.out.println(transaction.getUserId());
			}else {
				allTransactions.add(transaction);
			}
		}else {
			User u = UserController.userData.get(transaction.getUserId());
			List<Transaction> userTrans = u.getListOfCashTransactions();
			userTrans.add(transaction);
			u.setListOfCashTransactions(userTrans);
			System.out.println("addTransaction "+transaction.getUserId());
			System.out.println("addTransaction "+UserController.userData.get(transaction.getUserId()).getListOfCashTransactions().size());;
			//userTransactions.get(transaction.getUserId()).add(transaction);
		}
		System.out.println("end addTransaction");
		
	}

}
