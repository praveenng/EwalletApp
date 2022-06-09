package com.unibrain.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unibrain.validator.ValidatorPatternUtil;

@Service
public class FileActivityServiceImpl implements FileActivityService {

	@Autowired
	protected Environment env;

	@Override
	public String validateAndSaveBankFile(MultipartFile bankFile, String bankFilePath) {
		String errorMsg = null;

		ValidatorPatternUtil validatorPatternUtil = new ValidatorPatternUtil();

		Pattern textFieldTwoPattern = validatorPatternUtil.getTextFieldTwoPattern();
		String bankFileName = bankFile.getOriginalFilename();

		if (bankFileName != null && !bankFileName.isEmpty()) {

			String fileExtension = FilenameUtils.getExtension(bankFileName);

			if (fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpeg")
					|| fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("pdf")) {

				if (!(textFieldTwoPattern.matcher(bankFileName).matches())) {
					errorMsg = " File name is invalid. Please try to rename it and upload.";
				}
			} else {
				errorMsg = "Invalid file type.";
			}
		} else {
			errorMsg = "File name must be valid.";
		}
		
		if (errorMsg == null) {
			File file = new File(bankFilePath + bankFileName);
			Path path = Paths.get(bankFilePath);
			Integer maxSizeOfFileInBytes = 2097152; // 2 MB
			try {
				// If path exists clears the folder
				if (Files.exists(path)) {
					Arrays.stream(new File(bankFilePath).listFiles()).forEach(File::delete);
				} else {
					Files.createDirectories(path);
				}

				byte[] bytes = bankFile.getBytes();
				FileOutputStream out = new FileOutputStream(file);
				BufferedOutputStream stream = new BufferedOutputStream(out);
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = "Something went wrong!";
				return errorMsg;
			}

			if ((file.length() > 0) && (file.isFile())) {

				if (file.length() > maxSizeOfFileInBytes.intValue()) {
					if (file.exists()) {
						file.delete();
					}
					errorMsg = "Please upload file less than 2 MB";
				}
			} else {
				if (file.exists()) {
					file.delete();
				}
				errorMsg = "Please upload valid file.";
			}
		}

		return errorMsg;
	}

	@Override
	public String getUserFilePath(String ewalletId, String module) {

		String filePath = null;
		String baseFilePath = env.getProperty("ewallet.base.file.path");

		switch (module) {
		case "BankFile":
			filePath = baseFilePath + "Bank File/" + ewalletId + "/";
			break;

		default:
			break;
		}

		return filePath;
	}
}
