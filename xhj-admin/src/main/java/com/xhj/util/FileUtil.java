package com.xhj.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.xhj.util.LogFactory.Log;


public class FileUtil {

  private static final Log logger = LogFactory.getLog(FileUtil.class);

  public static String getFileExtension(String fileName) {
    String extensionName;
    String[] flds = fileName.split("\\.");
    if (flds.length >= 2) {
      extensionName = flds[flds.length - 1];
    } else {
      extensionName = "";
    }
    return extensionName.toLowerCase();
  }

  public static boolean writeFile(File dst, byte[] buffer) {
    if (buffer == null) {
      return false;
    }
    OutputStream out = null;
    try {
      out = new BufferedOutputStream(new FileOutputStream(dst));
      out.write(buffer);
    } catch (Exception e) {
      logger.error("caught exception when writting to file: " + e);
      return false;
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
          // ignore the exception
        }
      }
    }

    return true;
  }
}
