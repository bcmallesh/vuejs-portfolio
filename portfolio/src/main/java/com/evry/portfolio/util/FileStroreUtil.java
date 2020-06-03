package com.evry.portfolio.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.core.env.Environment;



public class FileStroreUtil {

	public String storeFile(ServletContext context,byte[] imageBytes, String imageName ,Environment env1) {
		String webappPath =context.getRealPath("/"); 
		String filePath="";
		String path = webappPath+env1.getProperty("images.path");

		  File folder = new File(path);
		File subfolder = new File(webappPath+env1.getProperty("images.path.projectinfo"));
		  System.out.println("Create Folder - "+folder.getPath());
		  //Make the Folder
		  if(!folder.isDirectory()){
		  if(folder.mkdir()){
			  System.out.println("Create Folder Done - "+folder.getPath());

		  if(subfolder.mkdir())

			  System.out.println("Create Sub - Folder Done - "+subfolder.getPath());
		  }

		  }

		  if(!("").equals(imageName)){

		  File storeFile = new File(subfolder.getPath(),imageName);

		  System.out.println("Store File Format - "+storeFile.getName()+" Path - "+storeFile.getPath());
		  if(!storeFile.exists()){
		  try {

			  System.out.println("File Stream Store File Format - "+storeFile.getName()+"- Path - "+storeFile.getPath());
		  FileOutputStream fos = new FileOutputStream(storeFile);

		  fos.write(imageBytes);

		  fos.flush();

		  fos.close();

		  } catch (FileNotFoundException e) {

		  // TODO Auto-generated catch block

		  e.printStackTrace();

		  } catch (IOException e) {
		  // TODO Auto-generated catch block
		 

		  e.printStackTrace();
		  }

		  }

		  filePath=storeFile.getPath();
		 

		  }else{
			  filePath= "Photo Failed to load";

		  }
		 return filePath;
	}
}
