package samplepac;

import org.testng.annotations.Test;

public class contactTest {

	@Test
	public void sampleTest() {
		String URL = System.getProperty("url");
		String BroWser = System.getProperty("browser");
		String UN = System.getProperty("username");
		String Pwd = System.getProperty("password");
		System.out.println("execue--ContactTest");
		
		System.out.println(URL);		
		System.out.println(BroWser);
		System.out.println(UN);
		System.out.println(Pwd);
	}
	
	@Test
	public void modifyTest() {
		System.out.println("execue--modifyTest");
	}
}
