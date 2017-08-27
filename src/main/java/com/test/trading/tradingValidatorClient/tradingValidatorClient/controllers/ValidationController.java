package com.test.trading.tradingValidatorClient.tradingValidatorClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.test.trading.tradingValidatorClient.tradingValidatorClient.model.TransactionRespone;
import com.test.trading.tradingValidatorClient.tradingValidatorClient.model.Transactions;

@Controller
public class ValidationController {
	
	
	@RequestMapping({"/", "/homePage"})
	public String intitialize(Model model) {
		
		model.addAttribute("transactions" , new Transactions() );
		
		model.addAttribute("transactionsResponse" , new TransactionRespone() );
		
		return "HomePage";		
	}
	

	@RequestMapping(value = "/homePage", params = { "sendTransactions" })
	public String sendValidationData(@ModelAttribute Transactions transactions, Model model) {
				
		final String url = "http://localhost:8080/checkTransactions";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(url, transactions.getContent(), String.class);
		
		TransactionRespone transactionRespone = new TransactionRespone();
		transactionRespone.setRespone(result);
		
		model.addAttribute("transactionsResponse" , transactionRespone );
		
		return "HomePage";
	}

}
