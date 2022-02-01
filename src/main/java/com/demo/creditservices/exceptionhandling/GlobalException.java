package com.demo.creditservices.exceptionhandling;

import java.io.Serializable;

public class GlobalException extends RuntimeException implements Serializable {
	    private static final long serialVersionUID = 1L;

	    public GlobalException(String msg) {
	        super(msg);
	    }
	 
}
