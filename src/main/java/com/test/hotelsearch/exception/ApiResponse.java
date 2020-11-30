package com.test.hotelsearch.exception;

public class ApiResponse {

	private int status;
	private String message;
	private String result;

	public ApiResponse(int status, String message, String result){
	    this.status = status;
	    this.message = message;
	    this.result = result;
    }

    public ApiResponse(int status, String result){
        this.status = status;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
	public String toString() {
		return "ApiResponse [statusCode=" + status + ", message=" + message +"]";
	}


}
