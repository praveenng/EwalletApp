package com.unibrain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibrain.dao.EwalletSummaryDao;
import com.unibrain.entity.EwalletTransaction;

@Service
public class EwalletSummaryServiceImpl implements EwalletSummaryService{
	
	@Autowired
	EwalletSummaryDao ewalletSummaryDao;

	@Override
	public List<EwalletTransaction> getEwalletSummaryByUserId(Integer userId) {
		return ewalletSummaryDao.getEwalletSummaryByUserId(userId);
	}

}
