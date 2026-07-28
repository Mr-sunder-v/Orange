package AI;


public class AIResponse {
	
	


	private int statuscode;
	private String AiResponse;
	
	
	public AIResponse(String aIResponse2, int statuscode2) {
		// TODO Auto-generated constructor stub
		
		this.statuscode = statuscode2;
		this.AiResponse = aIResponse2;
	}




	public int getStatusCode() {
		return statuscode;
		
	}
	
	public String getResponse() {
		return AiResponse;

}
}
