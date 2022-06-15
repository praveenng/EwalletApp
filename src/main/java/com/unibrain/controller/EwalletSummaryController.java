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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unibrain.entity.User;
import com.unibrain.model.EwalletSummaryModel;
import com.unibrain.service.EwalletSummaryService;
import com.unibrain.service.UserService;


@RestController
@RequestMapping("ewalletSummary")
public class EwalletSummaryController extends BaseController{
	
	@Autowired
	EwalletSummaryService ewalletSummaryService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/getEwalletSummary")
	public ResponseEntity<Object> getEwalletSummary(HttpServletRequest request) {
		Map<String, Object> responseMap = new HashMap<>();
		
		
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("id");
		
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer limit = Integer.parseInt(request.getParameter("limit"));
		
		User user = userService.getUserById(userId);
		
		List<EwalletSummaryModel> lModel = ewalletSummaryService.getEwalletSummaryByUserId(userId, page, limit);
		
		Long count = ewalletSummaryService.getTransactionCount(userId);
		
		responseMap.put("user", user);
		responseMap.put("transactions", lModel);
		responseMap.put("count", count);
		
		return new ResponseEntity<Object>(responseMap, new HttpHeaders(), HttpStatus.OK);
	}

}
