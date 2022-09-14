package model.service;

import java.util.Calendar;

import model.entities.Contract;

public class ContractService {
	
	public void processContract(Contract contract, Integer months){
		
		long t1 = contract.getDate().getTime();
		Calendar cal = new Calendar(t1);
	}

}
