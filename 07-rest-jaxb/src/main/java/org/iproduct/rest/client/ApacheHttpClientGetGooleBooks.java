package org.iproduct.rest.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class ApacheHttpClientGetGooleBooks {

	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
					"https://www.googleapis.com/books/v1/volumes?q=Java+Web");
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					(response.getEntity().getContent())));

//			String output;
//			System.out.println("Output from Server .... \n");
//			br.mark(Integer.MAX_VALUE);
//			while ((output = br.readLine()) != null) {
//
//				System.out.println(output);
//			}
//			System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
			
			//JsonPath demo
			DocumentContext jsonContext = JsonPath.parse(response.getEntity().getContent());
			String jsonpathBookTitlePath = "$.items[*].volumeInfo.industryIdentifiers";
			List<Map<String, Object>>  volumes = jsonContext.read(jsonpathBookTitlePath);
			System.out.println(volumes);
			String jsonpathBookYearPath = "$.items[*].volumeInfo.publishedDate";
			List<String> bookYears = jsonContext.read(jsonpathBookYearPath);
			System.out.println(bookYears);

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}