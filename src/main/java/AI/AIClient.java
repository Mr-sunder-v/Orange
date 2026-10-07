package AI;

import java.io.IOException; 
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

import Framework.ConfigReader;

public class AIClient {
	
	private ConfigReader config;
	
	private final HttpClient httpclient;
	
	 private final String apiURL;
	 
	 private final int retryCount;
	 
	 private final int retryDelay;
	
	 
	
	
	public AIResponse askGemini(String prompt) throws IOException, InterruptedException {
		
		String body = buildRequestBody(prompt);
		
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(apiURL)).header("Content-Type", "application/json;charset=UTF-8").POST(BodyPublishers.ofString(body)).build();
		HttpResponse<String> response = httpclient.send(req, BodyHandlers.ofString());
		
//		System.out.println(response.statusCode());
//		System.out.println(response.body());
//		System.out.println(config.getProperty("geminiAPIKey"));
//		System.out.println(config.getProperty("geminiAPIKey").length());
		
		int statuscode = response.statusCode();
		
		if (statuscode==200)
		{
			return parseResponse(response);
			
		}
		
			
			switch(statuscode)
			{
			
			case 401:
			return new AIResponse("invalid API Key",statuscode, apiURL);
			
			
			case 429:
			return new AIResponse("Exceed Rate Limit",statuscode, apiURL);
			
			
			case 503:
//			return new AIResponse("model unavailable",status, apiURL);
				
				for(int attempt =1;attempt<=retryCount; attempt++)
				{
					response = httpclient.send(req, BodyHandlers.ofString());
					int status = response.statusCode();
					if (status==200)
					{
						return parseResponse(response);
						
					}
					if(attempt<retryCount)
					{
					Thread.sleep(retryDelay);
					}
					
					
				}
				return new AIResponse("Model not available after multiple attempts",statuscode, apiURL);
			
			
			default:
			return new AIResponse("Something went wrong",statuscode, apiURL);
			
			
			}
			

	}
	
	private AIResponse parseResponse(HttpResponse<String> response) {
		
		
		JSONObject root = new JSONObject(response.body());
		
		JSONArray candidates = root.getJSONArray("candidates");
		JSONObject contents = candidates.getJSONObject(0);
		JSONObject content = contents.getJSONObject("content");
		JSONArray parts = content.getJSONArray("parts");
		JSONObject text = parts.getJSONObject(0);
		String AiResponse = text.getString("text");
		int statuscode = response.statusCode();
//		String url = apiURL;
//		
//		AIResponse ai = new AIResponse(AiResponse, statuscode,url);
//		
//		return ai;
		return new AIResponse(
		        AiResponse,
		        statuscode,
		        apiURL);
	}

	public AIClient() throws IOException {
		config = new ConfigReader();
		httpclient = HttpClient.newHttpClient();
		retryCount = Integer.parseInt(config.getProperty("ai.retry.count"));
		retryDelay = Integer.parseInt(config.getProperty("ai.retry.delay"));
		
		String apiKey = System.getenv("GEMINIAPIKEY");

		if(apiKey == null || apiKey.isBlank()) {
		    apiKey = config.getProperty("geminiAPIKey");
		}
		
		apiURL= config.getProperty("url1")+"?key="+apiKey;
//		System.out.println(apiURL);
		
		

	}
		
		private String buildRequestBody(String prompt) {
			
			JSONObject rootobj = new JSONObject();
			JSONObject contentsobj = new JSONObject();
			JSONObject textobj = new JSONObject();
			
			JSONArray contentsarr = new JSONArray();
			JSONArray partsarr = new JSONArray();
			
			textobj.put("text",prompt);
			
			partsarr.put(textobj);
			
			contentsobj.put("parts",partsarr);
			
			contentsarr.put(contentsobj);
			rootobj.put("contents",contentsarr);
			
			return rootobj.toString();
		
		
		
		
	}
		
		
	
//	public static void main(String[] args) throws IOException {
//		ConfigReader config = new ConfigReader();
//		System.out.println(config.getProperty("url"));
//		System.out.println(config.getProperty("geminiAPIKey"));
//	}
}
