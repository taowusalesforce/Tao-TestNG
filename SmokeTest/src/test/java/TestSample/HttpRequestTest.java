package TestSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.*;

public class HttpRequestTest {

 @BeforeClass
 public void setUp() {
   // code that will be invoked when this test is instantiated
   System.out.println("beforeclass setup");
 }
 
 @BeforeMethod
 public void prepare() {
     //code that will be invoked when each test method is called
     System.out.println("beforemethod start");
 }

 @Test(groups = { "smoke" })
 public void getTest() {

   System.out.println("Fast test");

   try {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(
			"https://tao-macroservice.herokuapp.com/people");
		//getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		httpClient.close();

	  } catch (ClientProtocolException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();
	  } 
 }
 
 @Test(groups = { "smoke" })
 public void postTest() {
    System.out.println("post test");

    try {
		//DefaultHttpClient is old class, and is replaced by CloseableHttpClient 
        //import org.apache.http.impl.client.DefaultHttpClient;
        //DefaultHttpClient httpClient = new DefaultHttpClient(); 
        //httpClient.getConnectionManager().shutdown();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(
			"http://tao-graphql.herokuapp.com/graphql");


        postRequest.addHeader("Content-Type", "application/json");
        postRequest.setHeader("accept", "application/json");

		StringEntity input = new StringEntity("{\"query\":\"query{ person(id: \\\"2\\\") {first_name, last_name, id} }\"}");
		//input.setContentType("application/json");
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		httpClient.close();
        
	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

 }

 @Test(groups = { "smoke" })
 public void createRoleTest() {
    System.out.println("create Role test");

    try {
		
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(
			"http://inca-test.herokuapp.com/graphql");


        postRequest.addHeader("Content-Type", "application/json");
        postRequest.setHeader("Accept", "*/*");

        StringEntity input = new StringEntity("{\"query\": \"mutation{createRole(role: {name: \\\"NEXT\\\"   description: \\\"BASICEMPLOYEE\\\"}) {name}}\"}");
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		httpClient.close();
        
	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

 }

}
