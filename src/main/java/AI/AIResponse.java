package AI;


public class AIResponse {
	
	


	private int statuscode;
	private String AiResponse;
	private String url;
	
	
	public AIResponse(String aIResponse2, int statuscode2, String url) {
		// TODO Auto-generated constructor stub
		
		this.statuscode = statuscode2;
		this.AiResponse = aIResponse2;
		this.url=url;
	}




	public int getStatusCode() {
		return statuscode;
		
	}
	
	public String getResponse() {
		return AiResponse;

}
	public String geturl() {
		return url;

}
}
