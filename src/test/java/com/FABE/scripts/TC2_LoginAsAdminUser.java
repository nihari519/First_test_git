/*'########################################################
	' Objective            :     Verify Admin user's Login view.
	' Test Case            :     testTC1_SignUp
	' Author               :     Niharika
	' Date Created         :     03-May-2018
*/	

package com.FABE.scripts;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.workflows.ApplicationUtil;

public class TC2_LoginAsAdminUser extends ApplicationUtil {
	
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, "Smoke");
	}

	@Test
	public void testTC2_LoginAsAdminUser() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String SheetName = "Smoke";
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
					
			
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC2_LoginAsAdminUser")) {
					child = extent.startTest("FABORY_Login", strDesc);
					iterationReport(iLoop - 1, strDesc+ ":   Start");
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					
					
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					if(driver.findElements(LoginOR.AdminMenuDropdown).size()!=0)
						SuccessReport("Admin Menu", "is displayed Successful");
					else
						failureReport("Admin Menu","is not displayed");
					
					//verify Welcome menu options
					String ele=driver.findElement(By.xpath("//*[@id='navbar-secondary']/descendant::a[@data-toggle='dropdown'][2]")).getText();
					if(ele.contains("Wel"))
						SuccessReport("Welcome Menu", "is displayed Successful");
					else
						failureReport("Welcome Menu","is not displayed");
					
					logoutWebShop();
				    parent.appendChild(child);
					// This will mark end of the one row in data sheet
					iterationReport(iLoop - 1, "Completed");
                    
				}

			} catch (Exception e) {
				catchBlock(e);
			}

		}
	}

	

	

}
