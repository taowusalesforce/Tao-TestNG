package TestSample;

import org.testng.annotations.*;


public class ApplicationTest {

 @BeforeClass
 public void setUp() {
   // code that will be invoked when this test is instantiated
   System.out.println("beforeclass setup");
 }
 
 @Test(groups = { "fast" })
 public void aFastTest() {
   System.out.println("Fast test");
 }
 
 @Test(groups = { "slow" })
 public void aSlowTest() {
    System.out.println("Slow test");
 }

    @Test(groups = {"fast"})
    public void testMainMethod() {
        System.out.println("everything is OK");
    }
}
