package com.xhj.controller.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.xhj.common.RestResponse;
import com.xhj.constant.FileUtilType;
import com.xhj.constant.IniConstant;
import com.xhj.controller.base.BaseController;
import com.xhj.localCache.IniCache;
import com.xhj.util.FileUtil;
import com.xhj.util.LogFactory;
import com.xhj.util.LogFactory.Log;

@Controller
public class FileUploadController extends BaseController {

  private static final Log logger = LogFactory.getLog(FileUploadController.class);

  private static final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

  @RequestMapping(value = {"/fs/upload"}, method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public RestResponse uploadService(@RequestParam(value = "file") MultipartFile file,
      @RequestParam(value = "fileName", required = false) String fileName, @RequestParam(
          value = "fileType") String fileType) {

    FileUtilType utilType = FileUtilType.fromString(fileType);
    if (utilType == null) {
      return RestResponse.errorResponse("文件类型不支持");
    }
    String realFileName = file.getOriginalFilename();
    String path = buildFilePathUrl(utilType, realFileName);
    String rootDir = IniCache.getIniValue(IniConstant.UPLOAD_FILE_BASE_DIR, "f:/files");
    String fullPath = rootDir + File.separator + path;
    syncUploadFile(utilType, file, fullPath);
    String downloadUrl =
        IniCache.getIniValue(IniConstant.DOWNLOAD_FILE_BASE_URL, "http://localhost/download");
    return RestResponse.successResponse(downloadUrl + File.separator + path);
  }

  private String buildFilePathUrl(FileUtilType fileType, String realFileName) {
    String extensionName = FileUtil.getFileExtension(realFileName);
    return fileType.name().toLowerCase() + File.separator + extensionName + File.separator
        + formater.format(new Date()) + "_" + realFileName;
  }

  private boolean syncUploadFile(FileUtilType fileType, MultipartFile file, String path) {
    File storeFile = new File(path);
    try {
      storeFile.getParentFile().mkdirs();
      if (!storeFile.createNewFile()) {
        logger.info("the temp file " + path + " exists, delete it");
        storeFile.delete();
        return false;
      }
      if (!writeFile(file, storeFile)) {
        logger.error("write file fail.");
        return false;
      }
    } catch (IOException e) {
      logger.error("caught exception when creating the new tmp file " + path, e);
      return false;
    }
    return true;
  }

  public boolean writeFile(MultipartFile file, File writeToFile) {
    byte buffer[] = getBuffer(file);
    if (buffer != null) {
      if (!FileUtil.writeFile(writeToFile, buffer)) {
        logger.error("write file failed!." + writeToFile);
        buffer = null;
        return false;
      }
      buffer = null;
      return true;
    } else {
      logger.error("writeFile#getBuffer null failed.file=" + file.getName());
      return false;
    }
  }

  public byte[] getBuffer(MultipartFile file) {
    Long fileSize = file.getSize();
    byte[] buffer = new byte[fileSize.intValue()];
    try {
      InputStream stream = null;
      try {
        stream = new BufferedInputStream(file.getInputStream());
        stream.read(buffer);
      } finally {
        if (stream != null) {
          stream.close();
        }
      }
    } catch (Exception e) {
      logger.error(e);
      return null;
    }
    return buffer;
  }
}
