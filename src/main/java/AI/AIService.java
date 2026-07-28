package AI;

import java.io.IOException;

public class AIService {
	
	private AIClient client;

	
	public AIService() throws IOException {
		
		client = new AIClient();
		
	}
	
	public AIResponse failureAnalyzer(failureDetails details) throws IOException, InterruptedException {
		
		String prompt = PromptBuilder.buildPrompt(details);
		AIResponse ai= client.askGemini(prompt);
		
		
		return ai;
		
		
	}

}
