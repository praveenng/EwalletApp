package com.unibrain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unibrain.entity.EwalletTransaction;
import com.unibrain.entity.User;
import com.unibrain.service.EwalletSummaryService;
import com.unibrain.service.UserService;


@RestController
@RequestMapping("ewalletSummary")
public class EwalletSummaryController extends BaseController{
	
	@Autowired
	EwalletSummaryService ewalletSummaryService;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/getEwalletSummary")
	public ResponseEntity<Object> getEwalletSummary(HttpServletRequest request) {
		Map<String, Object> responseMap = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("id");
		
		List<EwalletTransaction> ewalletTransactionList = ewalletSummaryService.getEwalletSummaryByUserId(userId);
		User user = userService.getUserById(userId);
		
		responseMap.put("user", user);
		responseMap.put("transactions", ewalletTransactionList);
		
		return new ResponseEntity<Object>(responseMap, new HttpHeaders(), HttpStatus.OK);
	}

}
