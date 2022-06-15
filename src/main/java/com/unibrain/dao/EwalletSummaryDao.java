package com.unibrain.dao;

import java.util.List;

import com.unibrain.entity.EwalletTransaction;

public interface EwalletSummaryDao {
	
	public List<EwalletTransaction> getEwalletSummaryByUserId(Integer userId,Integer page,Integer limit);
	public Long getTransactionCount(Integer userId);

}
