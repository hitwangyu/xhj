package com.xhj.common;

import java.util.HashMap;
import java.util.Map;

public class ThreadContext {
  private static final ThreadLocal<Map<String, Object>> CTX = new ThreadLocal<>();

  public static void ensureInited() {
    if (CTX.get() == null)
      init();
  }

  public static final void clean() {
    CTX.remove();
  }

  public static final boolean init() {
    if (CTX.get() != null) {
      return false;
    }

    Map<String, Object> currentThreadCtx = new HashMap<>();
    CTX.set(currentThreadCtx);
    return true;
  }

  public static final <V> void put(String key, V value) {
    try {
      ((Map<String, Object>) CTX.get()).put(key, value);
    } catch (NullPointerException e) {
      System.out
          .println("调用ThreadContext时，必须要先进行ThreadContext的init，线程退出前再进行clean，避免被其他线程使用到本线程的数据，发生线程安全问题。");
      throw e;
    }
  }

  public static final <V> V get(String key) {
    Map<String, Object> map = (Map<String, Object>) CTX.get();
    return (V) ((map == null) ? null : ((Map<String, Object>) CTX.get()).get(key));
  }

}
