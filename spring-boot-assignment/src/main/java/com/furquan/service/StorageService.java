package com.furquan.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.furquan.entity.FileEntity;
import com.furquan.exception.StorageException;
import com.furquan.repository.FileRepository;

/**
 * @author furquan
 *
 */
@Service
public class StorageService {

	@Value("${upload.path}")
	private String path;

	@Autowired
	FileRepository repository;

	public void uploadFile(MultipartFile file) {

		if (file.isEmpty()) {

			throw new StorageException("Failed to store empty file");
		}

		try {
			String fileName = file.getOriginalFilename();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Calendar cal = Calendar.getInstance();
			fileName = dateFormat.format(cal.getTime()) + "_" + fileName;
			Date date = new Date();
			InputStream is = file.getInputStream();
			Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
			FileInputStream fis = new FileInputStream(path + fileName);
			List<String> allLines = Files.readAllLines(Paths.get(path + fileName));
			List<FileEntity> entityList = new ArrayList<FileEntity>();
			/* Start: Validating File content */
			for (String string : allLines) {
				String[] dataList = string.split(",");
				if (dataList.length != 3) {
					String msg = String.format("Failed to process file as it contains more than three line %f",
							file.getName());
					throw new StorageException(msg);
				}

				else {
					try {
						String Num = dataList[0].replaceFirst("^0+(?!$)", "");
						Long noOfCoin = Long.parseLong(dataList[1]);
						if (Num.length() != 10) {
							String msg = String.format("Failed to proecess file as it contains invalid number%f",
									file.getName());
							throw new StorageException(msg);
						} else if (noOfCoin < 0) {
							String msg = String.format("Failed to proecess file as it contains invlaid coin number%f",
									file.getName());
							throw new StorageException(msg);
						}
						/* End: Validating File content */

						else {
							FileEntity entity = new FileEntity();
							Long userId = Long.parseLong(Num);

							String userName = dataList[2];
							entity.setUserId(userId);
							entity.setName(userName);
							entity.setAmount(Long.parseLong(dataList[1]));
							entity.setFileName(fileName);
							// repository.save(entity);
							entityList.add(entity);

						}
					} catch (NumberFormatException e) {
						String msg = String.format("failed to convert string to number%f", file.getName());
						throw new StorageException(msg);
					}
				}

			}
			// save data to database if all data is correct
			repository.saveAll(entityList);
		}

		catch (IOException e) {

			String msg = String.format("Failed to store file %f", file.getName());

			throw new StorageException(msg, e);

		}

	}
}