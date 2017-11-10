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
import org.apache.http.util.*;
import org.apache.commons.io.*;
import org.json.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class HttpRequestTest {

CloseableHttpClient httpClient;
HttpPost postRequest;

 @BeforeClass
 public void setUp() {
   // code that will be invoked when this test is instantiated
   System.out.println("beforeclass setup");
   httpClient = HttpClientBuilder.create().build();
   postRequest = new HttpPost(
			"http://inca-test.herokuapp.com/graphql");
   postRequest.addHeader("Content-Type", "application/json");
   postRequest.setHeader("Accept", "*/*");
 }
 
 @BeforeMethod
 public void prepare() {
     //code that will be invoked when each test method is called
     System.out.println("beforemethod start");
 }

@AfterClass
public void teardown() {
    //code that will be invoked when the test is completed
    System.out.println("afterclass teardown");
    try {
        httpClient.close();
    } catch (IOException e) {
		e.printStackTrace();
	  }
    
}


/*
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
 */

 @Test(groups = { "smoke" })
 public void createRoleTest() {
    System.out.println("---create Role test");

    try {

        StringEntity input = new StringEntity("{\"query\": \"mutation{createRole(role: {name: \\\"NEXT\\\"   description: \\\"BASICEMPLOYEE\\\"}) {name}}\"}");
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);

        String responsejson = EntityUtils.toString(response.getEntity());
		
        if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}

        System.out.println("response json string: " + responsejson);
        JSONObject jo = new JSONObject(responsejson);


        JSONObject ja = (JSONObject) jo.get("data");
        JSONObject jrole = (JSONObject) ja.get("createRole");
        String rolename = (String) jrole.get("name");

        System.out.println("role name: " + rolename);
        
	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  } catch (Exception e) {
          e.printStackTrace();
      }
      finally
      {
        
      }

 }


@Test(groups = { "smoke" })
public void createProcessorTest() {
    System.out.println("---create Processor test");

    try {
		
        StringEntity input = new StringEntity("{\"query\":\"mutation {createProcessor(activeFlag: \\\"true\\\", orgId: 1000, stripeConnectId: \\\"TEST00\\\") {\\n    activeFlag\\n    orgId\\n    stripeConnectId}}\",\"variables\":\"\"}");
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);

        String responsejson = EntityUtils.toString(response.getEntity());
		
        if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}


        System.out.println("response json string: " + responsejson);
        JSONObject jo = new JSONObject(responsejson);

        // getting stripeConnectId
        JSONObject ja = (JSONObject) jo.get("data");
        JSONObject jprocessor = (JSONObject) ja.get("createProcessor");
        String stripeconnectID = (String) jprocessor.get("stripeConnectId");

        System.out.println("stripeConnectId name: " + stripeconnectID);
        if(!stripeconnectID.equals("TEST00"))
        {
            System.out.println("stringconnectID is not expected: " + stripeconnectID);
            throw new Exception("stringconnectID is not expected.");
        }
        

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  } catch (Exception e) {
          e.printStackTrace();
      }
      finally
      {
        
      }

 }




@Test(groups = { "smoke" })
public void createWrokPlacePartnerTest() {
    System.out.println("---create WrokPlace Partner test");

    try {

        StringEntity input = new StringEntity("{\"query\": \"mutation {createWorkplacePartner(orgId: 1000, processorId: 1000, division: \\\"WESTCOAST\\\", reseller: 1000) {\\n    workplacePartnerId}}\",\"variables\":\"\"}");
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);

        String responsejson = EntityUtils.toString(response.getEntity());
		
        if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}


        System.out.println("response json string: " + responsejson);
        JSONObject jo = new JSONObject(responsejson);

        // getting stripeConnectId
        JSONObject ja = (JSONObject) jo.get("data");
        JSONObject jworkplace = (JSONObject) ja.get("createWorkplacePartner");
        int workplaceID01 = (int) jworkplace.get("workplacePartnerId");

        System.out.println("workplacePartnerId name: " + workplaceID01);
        if(workplaceID01 <= 0)
        {
            System.out.println("first workplacePartnerId is not expected: " + workplaceID01);
            throw new Exception("first workplacePartnerId is not expected.");
        }
        
        //rerun the same query, and the return value should be increased
        response = httpClient.execute(postRequest);
        responsejson = EntityUtils.toString(response.getEntity());
		
        if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}


        System.out.println("second response json string: " + responsejson);
        jo = new JSONObject(responsejson);

        // getting stripeConnectId
        ja = (JSONObject) jo.get("data");
        jworkplace = (JSONObject) ja.get("createWorkplacePartner");
        int workplaceID02 = (int) jworkplace.get("workplacePartnerId");

        System.out.println("workplacePartnerId name: " + workplaceID02);
        assertEquals(workplaceID02, workplaceID01 + 1, "second workplacePartnerId is not expected: ");

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  } catch (Exception e) {
          e.printStackTrace();
      }
      finally
      {
        
      }

 }


}
