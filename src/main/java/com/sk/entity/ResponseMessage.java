package com.sk.entity;

import com.sk.myEnum.ResponseStatus;

public class ResponseMessage<E> {
	 private ResponseStatus status;
	 private E result = null;
	
	public ResponseMessage(ResponseStatus status, E result) {
		this.status = status;
		this.result = result;
	}
	
	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public E getResult() {
		return result;
	}
	public void setResult(E result) {
		this.result = result;
	}
	 
	 

}
