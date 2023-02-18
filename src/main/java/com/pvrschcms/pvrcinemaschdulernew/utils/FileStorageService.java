package com.pvrschcms.pvrcinemaschdulernew.utils;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pvrschcms.pvrcinemaschdulernew.config.FileStorageProperties;
import com.pvrschcms.pvrcinemaschdulernew.utils.constant.Utility;
import com.pvrschcms.pvrcinemaschdulernew.utils.exception.FileStorageException;
import com.pvrschcms.pvrcinemaschdulernew.utils.exception.MyFileNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
	private final Path fileStorageLocation;
	@Autowired
	FileStorageProperties fileStorageProperties;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    public String storeFile(MultipartFile file,String prefix) {
        // Normalize file name
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = "";
        String uploadDir = "";
        String extension = "";
        try {
        	int index = StringUtils.cleanPath(file.getOriginalFilename()).lastIndexOf('.');
            if(index > 0) {
              extension = StringUtils.cleanPath(file.getOriginalFilename()).substring(index + 1);
              System.out.println("File extension is " + extension);
            }
            //fileName = "MP"+ Utility.getCurrentTimewithoutspace()+StringUtils.cleanPath(file.getOriginalFilename());
        	fileName = prefix+Utility.getCurrentTimewithoutspace()+UUID.randomUUID().toString();
        	fileName = Utility.hifenRemove(fileName)+"."+extension;
        	// Check if the file's name contains invalid characters
            System.out.println(" fileName :: "+fileName);
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            uploadDir = this.fileStorageLocation.toString();
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            

        } catch (IOException ex) {
            ex.printStackTrace();
            //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileName+"|"+uploadDir;
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
