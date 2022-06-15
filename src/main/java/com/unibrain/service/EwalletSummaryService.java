package com.unibrain.service;

import java.util.List;

import com.unibrain.entity.EwalletTransaction;
import com.unibrain.model.EwalletSummaryModel;

public interface EwalletSummaryService {

	public List<EwalletSummaryModel> getEwalletSummaryByUserId(Integer userId,Integer page,Integer limit);
	public Long getTransactionCount(Integer userId);
}
