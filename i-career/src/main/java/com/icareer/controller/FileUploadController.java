package com.icareer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icareer.exception.ErrorMessage;
import com.icareer.exception.InstaCareerException;
import com.icareer.service.FileUploadService;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
	
	@Autowired
	FileUploadService fileUploadService;

	@PostMapping("/uploadZip")
	public ResponseEntity<String> handleZipUpload(@RequestParam("file") MultipartFile file) throws InstaCareerException {
//		if (file.isEmpty() || !file.getOriginalFilename().endsWith(".zip") || file.getContentType() == null || !file.getContentType().equals("application/zip")) {
//			return ResponseEntity.badRequest().body("Please upload a valid ZIP file.");
//		}
		
        if (file.isEmpty()) {
            ErrorMessage errorDetail = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Uploaded file is null or empty");
            throw new InstaCareerException(errorDetail);
//            return ResponseEntity.badRequest().body("Uploaded file is empty.");
        }

        if (file.getOriginalFilename() == null || !file.getOriginalFilename().endsWith(".zip")) {
            return ResponseEntity.badRequest().body("Uploaded file must have a .zip extension.");
        }

        if (file.getContentType() == null) {
            return ResponseEntity.badRequest().body("Content type is missing.");
        }

        if (!file.getContentType().equals("application/zip") &&
            !file.getContentType().equals("application/x-zip-compressed")) {
            return ResponseEntity.badRequest().body("Invalid content type. Expected application/zip.");
        }

		try {			
			return ResponseEntity.ok(fileUploadService.handleZipUpload(file));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed.");
		}
	}	
}