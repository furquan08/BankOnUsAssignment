package com.furquan.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.furquan.exception.StorageException;
import com.furquan.service.StorageService;

/**
 * @author furquan
 *
 */
@Controller
@RequestMapping("/file")
public class FilesController {

	@Autowired
	private StorageService storageService;
	

	@RequestMapping(value = "/fileUplaod", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		return "file-upload";
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public String upload(@RequestParam MultipartFile file) {

		String view = "upload-success";
		try {
			/*Service call to upload the file*/
			storageService.uploadFile(file);
		} catch (Exception e) {
			view = "upload-failure";
		}

		return  view;
	}

	@ExceptionHandler(StorageException.class)
	public String handleStorageFileNotFound(StorageException e) {

		return "redirect:/upload-failure";
	}
}