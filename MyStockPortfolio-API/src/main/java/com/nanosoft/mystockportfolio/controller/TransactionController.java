package com.nanosoft.mystockportfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nanosoft.mystockportfolio.model.Transaction;
import com.nanosoft.mystockportfolio.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	
	@RequestMapping("/{userId}")
	public List<Transaction> getAllUserTransactions(@PathVariable("userId") int userId){
		//System.out.println("start getAllUserTransactions");
		return transactionService.getAllUserTransactions(userId);
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/{userId}")
	public void addTransaction(@RequestBody Transaction transaction, @PathVariable int userId) {
		//System.out.println("start addTransaction");
		transactionService.addTransaction(transaction);
		
	}

}
