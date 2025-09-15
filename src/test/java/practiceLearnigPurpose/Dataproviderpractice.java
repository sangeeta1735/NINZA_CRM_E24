package practiceLearnigPurpose;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataproviderpractice 
{ 
	@DataProvider 
	public Object[][] loginDetails() 
		{ 
			Object[][] obj = new Object[3][2];  
			obj[0][0]="Pratiksha"; 
			obj[0][1]="Prati@123"; 
			obj[1][0]="Sangeeta"; 
			obj[1][1]="Sangu@123"; 
			obj[2][0]="Ankit"; 
			obj[2][1]="Anku@123"; 
			
			return obj; 
		} 
	
	@Test(dataProvider="loginDetails") 
	public void login(String un,String pw) 
		{ 
			System.out.println(un+"========"+pw); 
			
		}
	
	
	

}
