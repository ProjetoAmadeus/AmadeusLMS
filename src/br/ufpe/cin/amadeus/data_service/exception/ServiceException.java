package br.ufpe.cin.amadeus.data_service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -7306645698396809047L;

	private Exception originalException;
	private String exceptionPoint;
	
	public ServiceException(Exception e, String text) {
		this.originalException = e;
		this.exceptionPoint = text;
	}

	public Exception getOriginalException() {
		return originalException;
	}

	public void setOriginalException(Exception originalException) {
		this.originalException = originalException;
	}

	public String getExceptionPoint() {
		return exceptionPoint;
	}

	public void setExceptionPoint(String exceptionPoint) {
		this.exceptionPoint = exceptionPoint;
	}
	
}
