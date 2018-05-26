/*'########################################################
	' Objective            :     Verify Admin user's Login view--verify dropdowns avaiable for admin.
	' Test Case            :     testTC1_SignUp
	' Author               :     Niharika
	' Date Created         :     03-May-2018
*/	

package com.FABE.scripts;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.HomePageOR;
import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.objrepo.FaboryOR.SignUpOR;
import com.FABE.workflows.ApplicationUtil;

public class TC6_AddProductToCart extends ApplicationUtil {
	
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, "Smoke");
	}

	@Test
	public void testTC6_AddProductToCart() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String SheetName = "Smoke";
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				
				
			
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC6_AddProductToCart")) {
					child = extent.startTest("FABORY_Add to Cart", strDesc);
					iterationReport(iLoop - 1, strDesc +":         Start");
					
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					
					
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					
					//verify categories and sub-categories displayed under Products menu
					click(HomePageOR.ProductsMenu,"Products Menu");
					isWebElementPresent(HomePageOR.ProductsMenuFastenersCategory,"Fasteners category is displayed under Products menu");
					List<WebElement> ele=driver.findElements(HomePageOR.FastenersSubcategories);
					if(ele.size()>=1)
						SuccessReport("Fasteners", "Subcategories are displayed under Fasteners");
					else
						failureReport("Fasteners", "Subcategories are not displayed under Fasteners");
					
					isWebElementPresent(HomePageOR.ProductsMenuIndustrialsCategory,"Industrials category is displayed under Products menu");
					List<WebElement> ele1=driver.findElements(HomePageOR.IndustrialsSubcategories);
					if(ele1.size()>=1)
						SuccessReport("Industrials", "Subcategories are displayed under Industrials");
					else
						failureReport("Industrials", "Subcategories are not displayed under Industrials");
					
					//click on the home page
					driver.findElement(By.xpath("//*[@id='slider-1']/div/div/div")).click();
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
