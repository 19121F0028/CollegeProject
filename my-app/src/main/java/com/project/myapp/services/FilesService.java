package com.project.myapp.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FilesService {
  private final Path root = Paths.get("Images");
  private final Path root1 = Paths.get("pdfs");
 
  public void init() {
    try {
      Files.createDirectory(root);
      Files.createDirectory(root1);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }
  public void saveImages(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }
  public void savePdf(MultipartFile file) {
	    try {
	      Files.copy(file.getInputStream(), this.root1.resolve(file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
  public Resource loadImages(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }
  public Resource loadPdf(String filename) {
	    try {
	      Path file = root1.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());
	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
    FileSystemUtils.deleteRecursively(root1.toFile());
  }
  public Stream<Path> loadAllImages() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
  public Stream<Path> loadAllPdf() {
	    try {
	      return Files.walk(this.root1, 1).filter(path -> !path.equals(this.root1)).map(this.root1::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
}