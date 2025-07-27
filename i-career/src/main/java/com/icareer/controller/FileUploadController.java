package com.icareer.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icareer.service.FileUploadService;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
	
	@Autowired
	FileUploadService fileUploadService;

	@PostMapping("/uploadZip")
	public ResponseEntity<String> handleZipUpload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty() || !file.getOriginalFilename().endsWith(".zip") || file.getContentType() == null || !file.getContentType().equals("application/zip")) {
			return ResponseEntity.badRequest().body("Please upload a valid ZIP file.");
		}

		try {			
			return ResponseEntity.ok(fileUploadService.handleZipUpload(file));
//			String correlatedId = UUID.randomUUID().toString();
//			fileUploadService.YouTubeActivityExtractor(file, correlatedId);
//			return ResponseEntity.ok(correlatedId + " - ZIP file uploaded successfully: " + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed.");
		}
	}	
}