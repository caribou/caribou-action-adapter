package com.instrument.triface.util;

import java.io.File;

/**
 * Helper class for dealing with Files.
 * 
 * @author feigner
 *
 */
public class FileUtils {
	
	/**
	 * Get the file path
	 * 
	 * @param file
	 * @return String the path to the file
	 */
	public static String getPath(File file)
	{
		int sep = file.getPath().lastIndexOf(File.separator);
		return file.getPath().substring(0, sep);
	}
	
	/**
	 * Get the file from the path, sand the file extension.
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileNameSansExtension(File file)
	{
		String filename = file.getName();
		if(filename.lastIndexOf('.') > 0)
		{
			return filename.substring(0,filename.lastIndexOf('.'));
		}
		else return filename;
	}
	
	/**
	 * Get the file extension.
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileExtension(File file)
	{
		String filename = file.getName();
		return filename.substring(filename.lastIndexOf('.')).toLowerCase();
	}

}
