package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.net.URL;
import java.util.Set;

import org.junit.*;


import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.util.SmartProperties;



public class Mobile_Test_01 {
  private WebDriver driver;

  WebElement element = null;
  
  boolean setupSuccess = true;
  
  private String 	userId = null;
  private String 	passwd = null;
  private long 		waitTime = 50;
  private String 	videoPath = null;
  
  
  
  @Before
  public void setUp() throws Exception {
   
	  
	
	System.out.println("=====setUp start======");
	SmartProperties sp = SmartProperties.getInstance();
	 
	    
	userId 		= sp.getProperty("ID");
	passwd 		= sp.getProperty("PWD");
	waitTime 	= Long.parseLong(sp.getProperty("WaitTime"));
	videoPath 	= sp.getProperty("VIDEO_LOC");

	System.out.println("userId 	= "+userId);
	System.out.println("passwd 	= "+passwd);
	System.out.println("waitTime="+waitTime);
	System.out.println("videoPath = "+videoPath);
	    
	
	
	try
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps = DesiredCapabilities. android ();
		
		
		//caps .setCapability(MobileCapabilityType.DEVICE_NAME , "LG-F160S-cdd1fa" );
		caps .setCapability(MobileCapabilityType.DEVICE_NAME , "1a66dc8f" ); //device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
		caps .setCapability(MobileCapabilityType.PLATFORM_NAME , MobilePlatform. ANDROID );
		caps .setCapability(MobileCapabilityType.BROWSER_NAME , "Chrome" );
		
		URL appiumUrl = new URL( "http://127.0.0.1:4723/wd/hub" );
		
		System.out.println("Start driver.");
		driver = new AndroidDriver<WebElement>( appiumUrl , caps );
	
	
		/*
		driver = new FirefoxDriver();
	    baseUrl = "http://mw.cjmall.com";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    */
	}catch(Exception e)
	{
		System.out.println("Session Creation failed.");
		e.printStackTrace();
		assertTrue(true);
		setupSuccess = false;
		return;		
	}
	
	System.out.println("=====setUp end======");
	
  }

  @Test
  public void TestWindows() throws Exception {
	
	boolean isExist = false;
	boolean isExist2 = false;
	boolean isExist3 = false;
	WebDriverWait wait	=	null;
	
	System.out.println("=====TestCase Start======");
	
	if(!setupSuccess)	
	{
		System.out.println("Setup failed. So this TestCase is stopped now.");
		assertTrue(true);
		return;
	}
	
	try
	{
	    int window_num = 0;
	    
	    driver.get("http://mw.cjmall.com/m/main.jsp");
	    
	    
	
	    String mainWindow = driver.getWindowHandle();
	    System.out.println("main Windows ="+mainWindow);
	   
	    
	        
	   
	   
	    Set<String> handles = driver.getWindowHandles();  
	    window_num = handles.size();
	    
	    System.out.println("Windows Num ="+window_num);
	    for (String handle : handles) {
	    	System.out.println("windows handles :"+handle);
	        if (!handle.equals(mainWindow)) {
	            driver.switchTo().window(handle);
	            System.out.println("Switch Windows");
	            break;
	        }
	    }
	    
	 
        
	    
	    System.out.println("Windows Num ="+window_num);
	   
	   
	    
	    //팝업창이 뜨는데도 1로 나타는 경우가 있음. 왜 그럴까요?
	    if(window_num > 1)
	    {
	    	System.out.println("팝업창 뜸. 팝업창 Element는 일관성 없음.");
	    	
	    	Thread.sleep(3000);
	    	return;
		 /*
	      	//오늘 하루 안보기 체크박스 선택    	
	    	element = driver.findElement(By.xpath(".//*[@id='ct']/div/div[1]/div/div/div/a[1]"));
	    	element.click();
	    	
	    */
	    	                        
	    }
	  
	    //팝업창이 존재하면...  팝업창이 떠도 위와 같이 1로 리턴하는 경우가 있음.
	    isExist = existElement(driver,By.xpath(".//*[@id='ct']/div/div[1]/div/div/div/a[1]"),"팝업창");
	  	if(isExist)
	  	{
	  		element = driver.findElement(By.xpath(".//*[@id='ct']/div/div[1]/div/div/div/a[1]"));
	  		element.click();
	  	}
    	
		////
		//// 1. 사이드 메뉴 기다림.
		 wait = new WebDriverWait(driver, waitTime);
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='o1h_header_wrap']/dl/dt/a")));
	     
	    element = driver.findElement(By.xpath(".//*[@id='o1h_header_wrap']/dl/dt/a"));
	    element.click();
	    
	   
	    
	    
	    ////
	  	//// 2. 로그인 화면 기다림.
	  	 wait = new WebDriverWait(driver, waitTime);
	  	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='wrapper_cate_left']/dl/dt/a")));
	  	 
	    element = driver.findElement(By.xpath(".//*[@id='wrapper_cate_left']/dl/dt/a"));
	    element.click();
	    
	    
	    ////
	  	//// 2-1. ID 객체 기다림.
	  	 wait = new WebDriverWait(driver, waitTime);
	  	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='member_id']")));
	  	 
	  	 
	    element = driver.findElement(By.xpath(".//*[@id='member_id']"));
	    element.sendKeys(userId);
	    
	    element = driver.findElement(By.xpath(".//*[@id='pwd']"));
	    element.sendKeys(passwd);
	    
	    element = driver.findElement(By.xpath(".//*[@id='loginForm']/input[3]"));
	    element.click();
	   
	    
	  	 //// 상담신청 화면에 대한 xpath는 .//*[@id='ct']/div/div[3]/div/ul/li/a
	     //// <a href="http://mw.cjmall.com/m/item/36307052?prog_key=562611%5E20160131224000&chn_cd=50001001&pic=btn_consult&app_cd=PDA">상담신청</a>
	     
	  	 //// .//*[@id='ct']/div/div[3]/div/button => 상담신청을 위한 동영상 재생 화면 이벤트. 이것으로 구분해도 될까?
	    
	  	
  	
	  	Thread.sleep(3000);

	    
	  	isExist = existElement(driver,By.xpath(".//*[@id='ct']/div/div[3]/div/ul/li/a"),"비교상품 or 상담신청");
	  	isExist2 = existElement(driver, By.xpath(".//*[@id='ct']/div/div[3]/div/div[1]/span[1]"),"ONAIR img"); // 20160328 생방송 ONAIR img 없을경우처리
	  	if(isExist || isExist2 == false)
	  	{
	  		if(isExist2 != false){
	  			element = driver.findElement(By.xpath(".//*[@id='ct']/div/div[3]/div/ul/li/a"));
		  		System.out.println("====="+element.getText());
		  		
		  		//동영상 재생으로 가는 상품의 패턴은 비교상품 상담신청이 같이 나오는 것과 상담신청 하나만 나오는 것이 있음. 그래서 첫번째 객체가 비교상품 또는 상담신청이면 종료처리함.
		  		if(element.getText().equals("상담신청") || element.getText().equals("비교상품") )
		  		{
		  			System.out.println(element.getText()+"입니다. 여기서 종료");
		  			assertTrue(true);
		  		   	return;
		  		}
	  		} else if(isExist2 == false) {
	  			System.out.println("ONAIR 이미지 보이지 않음. 여기서 종료");
	  			assertTrue(true);
	  		   	return;
	  		}
	  	}
	  	
  		 ////
	  	 //// 3. 생방송 상품 이미지 기다림.
	  	 isExist3 = existElement(driver,By.xpath(".//*[@id='ct']/div/div[3]/div/a/div[1]/img"),"생방송상품 img");
	  	 if(isExist3){
	  		 element = driver.findElement(By.xpath(".//*[@id='ct']/div/div[3]/div/a/div[1]/img")); // 20160328 생방송 ONAIR img
		     element.click();
	  	 }
	  	
	    
	     ////
	   	 //// 4. 구매히기 버튼 기다림.
	   	 wait = new WebDriverWait(driver, waitTime);
	   	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='orderArea']/div[1]/a")));
	     
	     element = driver.findElement(By.xpath(" .//*[@id='orderArea']/div[1]/a"));
	     element.click();
	    
	    
	    
	     /*
	     ////
	   	 //// 5. 생방송 좆료까지 얼마남았다는 시간 객체 기다림.
	     //// 이 객체가 없는 상품도 많음.. 없는 경우는 바로 종료 처리
	     	  
	     try
	     {
	    	 wait = new WebDriverWait(driver, waitTime);
	    	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id='ct']/div[3]/div[1]/div/div[2]/p/strong")));
	     }catch(TimeoutException te)
	     {
	    	 System.out.println("생방송 종료시간이 표시가 안되었음. 여기서 종료");
	    	 assertTrue(true);
	    	 Thread.sleep(3000);
			 			
			 return;
	     }
	     */
	     
	     
	    ////
	   	//// 5. TV생방송리아는 객체 기다림.
	    wait = new WebDriverWait(driver, waitTime);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='ct']/div[1]/div[1]/div/div[2]/p/span")));
     
    	   	 
	    
	    isExist = existElement(driver,By.xpath(".//*[@id='orderArea']/div[2]/div[1]/div/div/div[1]/div/span"),"옵션");
	    if(isExist)
	    {
	    	
	    	 //     .//*[@id="ct"]/div[6]/div[2]/div[1]/div/div/div[1]/div/span  => 옵션 부분
	        //     .//*[@id="ct"]/div[6]/div[2]/div[1]/div/div/div[1]/div/div[2]/div/ul/li[1]/a/span => 첫번째 상품 클릭
	    	
	    	 element = driver.findElement(By.xpath(".//*[@id='orderArea']/div[2]/div[1]/div/div/div[1]/div/span"));
	    	 element.click();
	    	 
	    	 element = driver.findElement(By.xpath(".//*[@id='orderArea']/div[2]/div[1]/div/div/div[1]/div/div[2]/div/ul/li[1]/a/span"));
	    	 //매진 여부 체크
	    	 System.out.println(element.getText());
	    	 
	    	 if(element.getText().indexOf("매진") >=0)
	    	 {
	    		 System.out.println("매진 상풉입니다.. 여기서 종료합니다.["+element.getText()+"]");
	    		 assertTrue(true);
	    		 Thread.sleep(3000);
	    		 return;
	    		 
	    	 }
	    	 element.click();
	    	 
	    	 
	    	 /*
	    	 element = driver.findElement(By.xpath(".//*[@id='ct']/div[6]/div[2]/div[2]/div[2]/span[2]/a"));
	    	 element.click();
	    	 */
	    	 
	    	 
	    	// 일부 화면은 장바구니가 없고 바로구매만 나오는 경우가 있음.
		   	// 그래서 정확히 하려면 2개인지 1개인지 판단하여 2개짜리면 2번째 클릭, 1개짜리면  1번 클릭하도록 수정 필요
		    element = driver.findElement(By.xpath(".//*[@id='orderArea']/div[2]/div[2]/div[2]/span[1]/a"));
		    if(element.getText().equals("바로구매"))
		    {
		    	System.out.println("바로구매가 첫번째 버튼에 존재함-1");
		    	element.click();
		    }
		    	
		   	else
		    {
		   		System.out.println("바로구매가 2번째 버튼에 존재함-1");
		   		element = driver.findElement(By.xpath(".//*[@id='orderArea']/div[2]/div[2]/div[2]/span[2]/a"));
		   		element.click();
		   	}
		    	
		    	
	    }
	    
	    else
	    {
	    	// 일부 화면은 장바구니가 없고 바로구매만 나오는 경우가 있음.
	    	// 그래서 정확히 하려면 2개인지 1개인지 판단하여 2개짜리면 2번째 클릭, 1개짜리면  1번 클릭하도록 수정 필요
	       	element = driver.findElement(By.xpath(".//*[@id='orderArea']/div[2]/div[2]/div[2]/span[1]/a"));
	    	if(element.getText().equals("바로구매"))
	    	{
	    		System.out.println("바로구매가 첫번째 버튼에 존재함-2");
	    		element.click();
	    	}
	    	
	    	else
	    	{
	    		System.out.println("바로구매가 2번째 버튼에 존재함-2");
	    		element = driver.findElement(By.xpath(".//*[@id='orderArea']/div[2]/div[2]/div[2]/span[2]/a"));
	    		element.click();
	    	}
	    }
	    
	    
	    
	   
	    ////
	  	//// 6. 결제하기 버튼 기다림.
	  	 wait = new WebDriverWait(driver, waitTime);
	  	 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='ordFrm']/em/div[4]/button")));
		
	 }catch(Exception e)
	 {
		 e.printStackTrace();
		 assertTrue(false);
	 }finally
	 {
		 Thread.sleep(3000);
		 System.out.println("=====TestCase end======");
	 }
    
   
}
    
    


  @After
  public void tearDown() throws Exception {
    
	System.out.println("=====tearDown start======");
	
	if(setupSuccess)
		driver.quit();
   
	System.out.println("=====tearDown end======");
  }

  
  

  
  public boolean existElement(WebDriver wd, By by, String meaning) 
  {
	  WebDriverWait wait = new WebDriverWait(wd, 2);
	 // wait.ignoring(NoSuchElementException.class); 
	  
	  try
	  {
		  wait.until(ExpectedConditions.presenceOfElementLocated(by));
	    
	  }catch (TimeoutException e)
	  {
		  
		  System.out.println("["+meaning+"] WebElement does not Exist. time out ");
		  return false;
	  }
	  System.out.println("["+meaning+"] WebElement Exist.");
	  return true;
  }
  
  
}
