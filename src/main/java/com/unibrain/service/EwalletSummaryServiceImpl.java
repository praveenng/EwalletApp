package com.unibrain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibrain.dao.EwalletSummaryDao;
import com.unibrain.entity.EwalletTransaction;
import com.unibrain.model.EwalletSummaryModel;

@Service
public class EwalletSummaryServiceImpl implements EwalletSummaryService{
	
	@Autowired
	EwalletSummaryDao ewalletSummaryDao;

	@Override
	public List<EwalletSummaryModel> getEwalletSummaryByUserId(Integer userId,Integer page,Integer limit) {
		
		List<EwalletTransaction> ewalletTransactionList = ewalletSummaryDao.getEwalletSummaryByUserId(userId,page,limit);
		EwalletSummaryModel model = null;
		List<EwalletSummaryModel> lModel = new ArrayList<>();
		
		for(EwalletTransaction et : ewalletTransactionList){
			model = new EwalletSummaryModel();
			LocalDateTime now = et.getPaymentInitiatedDate();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        String formatDateTime = now.format(formatter);
	        model.setDate(formatDateTime);
	        
	        StringBuilder narration = new StringBuilder();
	        
	        switch (et.getTransactionFlag()) {
			case 'D':
				narration.append("Deposit to ewallet - "+et.getEwalletId());
				break;
			case 'R':
				narration.append("Refund from - "+et.getSiteName()+"/"+et.getDepartmentName()+
						"/"+et.getTenderOrAuctionNumber()+"/"+et.getTenderOrAuctionItemName()+"/"+et.getPaymentTransactionMasterId());
				break;
			case 'W':
				narration.append("Withdraw from ewallet - "+et.getEwalletId());
				break;
			case 'B':
				narration.append("Payment to - "+et.getSiteName()+"/"+et.getDepartmentName()+
						"/"+et.getTenderOrAuctionNumber()+"/"+et.getTenderOrAuctionItemName()+"/"+et.getPaymentTransactionMasterId());
				
				break;

			default:
				break;
			}
	        
	        model.setNarration(narration.toString());
	        model.setPaymentReference(et.getPaymentTransactionReference());
	        if(et.getDebitCreditFlag() == 'D'){
	        model.setDebit(et.getPaymentInitiatedAmount().toString());
	        }else{
	        model.setCredit(et.getPaymentInitiatedAmount().toString());
	        }
	        model.setBalance(et.getCurrentBalance().toString());
	        lModel.add(model);
		}

		
		return lModel;
	}

	@Override
	public Long getTransactionCount(Integer userId) {
		return ewalletSummaryDao.getTransactionCount(userId);
	}

}
