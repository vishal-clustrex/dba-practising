package com.home.springbatch.utils;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

public class Util {

	public static Resource getResource(String inputDataPath) {
		File file = null;
		try {
			file = ResourceUtils.getFile(inputDataPath);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return new FileSystemResource(file);
	}
	
}
