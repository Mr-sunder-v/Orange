package AI;

public class failureDetails {

	private String testName;
	private String pageName;
	private String currentUrl;
	private String locator;
	private String exceptionType;
	private String exceptionMessage;
	private String stackTrace;
	
	public failureDetails(String testName,String pageName,String currentUrl,String locator,String exceptionType,String exceptionMessage,String stackTrace) {
		// TODO Auto-generated method stub
		
		this.testName=testName;
		this.pageName=pageName;
		this.currentUrl=currentUrl;
		this.locator=locator;
		this.exceptionType=exceptionType;
		this.exceptionMessage=exceptionMessage;
		this.stackTrace=stackTrace;

	}
	
	
	public String getTestName() {
		return testName;
		
	}
public String getPageName() {
	return pageName;
		
	}
public String getcurrentUrl() {
	return currentUrl;
	
}
public String getLocator() {
	return locator;
	
}
public String getExceptionType() {
	return exceptionType;
	
}
public String getExceptionMessage() {
	return exceptionMessage;
	
}
public String getStackTrace() {
	return stackTrace;
	
}
}
