package com.unibrain.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileActivityService {
	
	public String validateAndSaveBankFile(MultipartFile bankFile, String bankFilePath);
	
	public String getUserFilePath(String ewalletId, String module);
}
