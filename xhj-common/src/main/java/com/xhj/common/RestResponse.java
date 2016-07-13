package com.xhj.common;

public class RestResponse {
  public static final String ERROR_STATUS = "error";
  public static final String SUCCESS_STATUS = "success";

  private String status = null;
  private Object data = null;
  private String errorMessage;

  /**
   * create a success response.
   * 
   * @param data data object, can be null if you just want to return status
   * @return
   */
  public static RestResponse successResponse(Object data) {
    RestResponse response = new RestResponse();
    response.setStatus(SUCCESS_STATUS);
    response.setData(data);
    return response;
  }

  /**
   * create a error response.
   * 
   * @param errorMsg , can be null if you just want to return status
   * @return
   */
  public static RestResponse errorResponse(String errorMsg) {
    RestResponse response = new RestResponse();
    response.setStatus(ERROR_STATUS);
    response.setErrorMessage(errorMsg);
    return response;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "RestResponseStatus [status=" + status + ", data=" + data + "]";
  }

  public boolean ifSuccess() {
    return this.status.equals(SUCCESS_STATUS);
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
