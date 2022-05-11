package com.project.myapp.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.project.myapp.models.FileInfo;
import com.project.myapp.services.FilesService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/files")
public class FilesController {
  @Autowired
  FilesService storageService;
  @PostMapping("/uploadImages")
  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.saveImages(file);
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return new ResponseEntity<String>(message,HttpStatus.OK);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return new ResponseEntity<String>(message,HttpStatus.EXPECTATION_FAILED);
    }
  }
  @GetMapping("/getAllImages")
  public ResponseEntity<?> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAllImages().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
      return new FileInfo(filename, url);
    }).collect(Collectors.toList());
    return new ResponseEntity<List<FileInfo>>(fileInfos,HttpStatus.OK);
  }
  @GetMapping("/getPhotos")
  public ResponseEntity<?> getList() {
    List<FileInfo> fileInfos = storageService.loadAllImages().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
      return new FileInfo(url);
    }).collect(Collectors.toList());
    return new ResponseEntity<List<FileInfo>>(fileInfos,HttpStatus.OK);
  }
  @GetMapping("/Images/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.loadImages(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
  @PostMapping("/uploadPdf")
  public ResponseEntity<?> uploadPdfFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.savePdf(file);
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return new ResponseEntity<String>(message,HttpStatus.OK);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return new ResponseEntity<String>(message,HttpStatus.EXPECTATION_FAILED);
    }
  }
  @GetMapping("/getAllPdf")
  public ResponseEntity<?> getListPdfFiles() {
    List<FileInfo> fileInfos = storageService.loadAllPdf().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getPdfFile", path.getFileName().toString()).build().toString();
      System.out.println(url);
      return new FileInfo(filename, url);
    }).collect(Collectors.toList());
    return new ResponseEntity<List<FileInfo>>(fileInfos,HttpStatus.OK);
  }
  @GetMapping("/getPdfFiles")
  public ResponseEntity<?> getPdfList() {
    List<FileInfo> fileInfos = storageService.loadAllPdf().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
      return new FileInfo(url);
    }).collect(Collectors.toList());
    return new ResponseEntity<List<FileInfo>>(fileInfos,HttpStatus.OK);
  }
  @GetMapping("/pdf/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getPdfFile(@PathVariable String filename) {
    Resource file = storageService.loadPdf(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}