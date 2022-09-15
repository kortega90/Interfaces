package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}


	public void processContract(Contract contract, Integer months){
		Double basicQuota = contract.getTotalValue()/months;
		
		for (int i=1;i<=months;i++) {
			double updatedQuota =	basicQuota + onlinePaymentService.interest(basicQuota, i);
			double totalQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota );
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(contract.getDate());
			cal.add(Calendar.MONTH, i);
			Date date = cal.getTime();
			
			contract.addInstallment(new Installment(date, totalQuota));
		}
		
	}

}
