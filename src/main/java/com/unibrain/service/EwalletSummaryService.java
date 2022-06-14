package com.unibrain.service;

import java.util.List;

import com.unibrain.entity.EwalletTransaction;

public interface EwalletSummaryService {

	public List<EwalletTransaction> getEwalletSummaryByUserId(Integer userId);
}
