package TestSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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

		DefaultHttpClient httpClient = new DefaultHttpClient();
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

		httpClient.getConnectionManager().shutdown();

	  } catch (ClientProtocolException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();
	  } 
 }
 
 @Test(groups = { "smoke" })
 public void postTest() {
    System.out.println("Slow test");
 }

    @Test(groups = {"fast"})
    public void testMainMethod() {
        System.out.println("everything is OK");
    }
}
