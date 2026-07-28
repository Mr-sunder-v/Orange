package AI;

import java.io.IOException;

public class testing_AI {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		AIClient ai = new AIClient();
		
		
		 AIResponse response = ai.askGemini("Hi How are you");
		 System.out.println(response.getStatusCode());
		 System.out.println(response.getResponse());
		
		
		

	}

}
