/**
 * com.wolong.common.util.Logger.java created by Xueliang(liang.xue@shenma-inc.com) on 2015年6月24日
 * 上午11:46:05
 */
package com.xhj.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.xhj.common.ThreadContext;



/**
 * 
 * created by Xueliang on 2015年6月24日 上午11:46:05
 */
public final class LogFactory {
  public static Log getLog(Class clazz) {
    return new Log(clazz);
  }

  public static class Log {
    private Logger logger;

    private String getTarget() {
      Long targetId;
      try {
        targetId = ThreadContext.get("targetId");
      } catch (Exception e) {
        targetId = null;
      }
      return targetId == null ? "" : String.valueOf(targetId);
    }

    private String getOperator() {
      String operatorName;
      try {
        operatorName = ThreadContext.get("operatorName");
      } catch (Exception e) {
        operatorName = null;
      }

      return StringUtils.isBlank(operatorName) ? "" : operatorName;
    }

    public Log(Class clazz) {
      logger = Logger.getLogger(clazz);
    }

    public void debug(Object message) {
      String fullMessage = getFullMessage(message);
      logger.debug(fullMessage);
    }

    public void debug(Object message, Throwable t) {
      String fullMessage = getFullMessage(message);
      logger.debug(fullMessage, t);
    }

    public void warn(Object message) {
      String fullMessage = getFullMessage(message);
      logger.warn(fullMessage);
    }

    public void warn(Object message, Throwable t) {
      String fullMessage = getFullMessage(message);
      logger.warn(fullMessage, t);
    }

    public void error(Object message) {
      String fullMessage = getFullMessage(message);
      logger.error(fullMessage);
    }

    public void error(Object message, Throwable t) {
      String fullMessage = getFullMessage(message);
      logger.error(fullMessage, t);
    }

    public void fatal(Object message) {
      String fullMessage = getFullMessage(message);
      logger.fatal(fullMessage);
    }

    public void fatal(Object message, Throwable t) {
      String fullMessage = getFullMessage(message);
      logger.fatal(fullMessage, t);
    }

    public void info(Object message) {
      String fullMessage = getFullMessage(message);
      logger.info(fullMessage);
    }

    public void info(Object message, Throwable t) {
      String fullMessage = getFullMessage(message);
      logger.info(fullMessage, t);
    }

    /**
     * @param message
     * @return created by Xueliang on 2015年6月24日 下午2:56:05
     */
    private String getFullMessage(Object message) {
      String targetId = getTarget();
      String operatorName = getOperator();
      String fullMessage = "[" + operatorName + "->" + targetId + "]" + String.valueOf(message);
      return fullMessage;
    }
  }

}
