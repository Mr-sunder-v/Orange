package AI;

public class PromptBuilder {
	
	public static String buildPrompt(failureDetails details) {
		
		StringBuilder prompt = new StringBuilder();
		
		prompt.append("You are a Senior Software Development Engineer in Test (SDET) with expertise in Selenium, Java, TestNG, Maven, and web automation."
				+ "Analyze the following Selenium test failure and identify the root cause.\r\n"
				+ "Provide practical recommendations to fix the issue."
				+ "Provide the response in the following format:\r\n"
				+ "\r\n"
				+ "1. Root Cause\r\n"
				+ "2. Why it happened\r\n"
				+ "3. Recommended Fix\r\n"
				+ "4. Better Locator (if applicable)\r\n"
				+ "5. Best Practices to avoid this issue"
				+ "Failure Details:");
//		prompt.append("Testcase Name:")
//			  .append(details.getTestName())
//			  .append("\n");
//		prompt.append("Page Name")
//			  .append(details.getPageName())
//			  .append("\n");
//		prompt.append("URL")
//			  .append(details.getcurrentUrl())
//			  .append("\n");
//		prompt.append("Locator")
//		  .append(details.getLocator())
//		  .append("\n");
//		prompt.append("Exception Messsage")
//		  .append(details.getExceptionMessage())
//		  .append("\n");
//		prompt.append("Exception Type")
//		  .append(details.getExceptionType())
//		  .append("\n");
		
		appendField(prompt,"Testcase Name: ",details.getTestName());
		appendField(prompt,"Page Name: ",details.getPageName());
		appendField(prompt,"URL: ",details.getcurrentUrl());
		appendField(prompt,"Locator: ",details.getLocator());
		appendField(prompt,"Exception message: ",details.getExceptionMessage());
		appendField(prompt,"Exception Type: ",details.getExceptionType());
		appendField(prompt,"StackTrace: ",details.getStackTrace());
		
		
		
		
		return prompt.toString();
	}
	
	private static void appendField(StringBuilder prompt, String label,String value) {
		
		prompt.append(label)
		  .append(value)
		  .append("\n");
		
	}

}
