package com.project.KeywordEngine;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;

import static com.project.Base.OpenBrowser.openBrowser;

public class KeywordEngine {

    String sheetPath = "KeywordsData/Keywords.xlsx";
    WebDriver driver;
    public void startEngine(String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(sheetPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i=1;i<rowCount;i++)
        {
            XSSFRow row = sheet.getRow(i);

            String locatorColValue = row.getCell(1).toString().trim(); //"id=login-username"
            String locatorName = "";
            String locatorValue = "";

            if (!locatorColValue.equalsIgnoreCase("NA")) // "id=login-username" split(=)-> {"id","login-username"}
            {
                locatorName = locatorColValue.split("=")[0];
                locatorValue = locatorColValue.split("=")[1];
            }

            String action = row.getCell(2).toString().trim();
            String value = row.getCell(3).toString().trim();

            switch (action) // keywords
            {
                case "open browser" : driver = openBrowser(value);
                    break;
                case "enter url" : driver.get(value);
                    break;
                case "quit" : driver.close();
                    break;
                case "assertWithUrl" :
                    Assert.assertEquals(driver.getCurrentUrl(),
                            value,"wrong page");
                    break;
                case "assertWithPageTitle" :
                    Assert.assertEquals(driver.getTitle(),
                            value,"wrong page");
                    break;
            }

            switch (locatorName)
            {
                case "id" : if(action.equalsIgnoreCase("click"))
                                driver.findElement(By.id(locatorValue)).click();
                            else if(action.equalsIgnoreCase("sendkeys"))
                                driver.findElement(By.id(locatorValue)).sendKeys(value);
                            break;

                case "name" :   if(action.equalsIgnoreCase("click"))
                                    driver.findElement(By.name(locatorValue)).click();
                                else if(action.equalsIgnoreCase("sendkeys"))
                                    driver.findElement(By.name(locatorValue)).sendKeys(value);
                                break;

                case "linkText" :
                    driver.findElement(By.linkText(locatorValue)).click();
                    break;
            }


        }

    }
}
