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

/**
 * 
 * @author SeokheeLee 
 * Date : 2016-06-16
 * Subject : CJ Mall 
 * Name : TC_082
 * Scenario : 전시 > 로그인 > 잘못된 아이디 / 패스워드 입력
 * Assertion :  "아이디나 비밀번호가 틀립니다. 다시 한번 확인해 주세요." alert Text 체크
 * update : ScreenRecorder 기능 제거 (2016-06-16)
 */

public class Mobile_TC082 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;

	boolean setupSuccess = true;

	private String url = null;
	private String userId = null;
	private String passwd = null;
	private long waitTime = 50;
	private String videoPath = null;

	@Before
	public void setUp() throws Exception {	
		System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();

		url = sp.getProperty("URL");
		userId = sp.getProperty("ID");
		passwd = sp.getProperty("PWD");
		waitTime = Long.parseLong(sp.getProperty("WaitTime"));
		videoPath = sp.getProperty("VIDEO_LOC");

		System.out.println("url = " + url);
		System.out.println("userId 	= " + userId);
		System.out.println("passwd 	= " + passwd);
		System.out.println("waitTime=" + waitTime);
		System.out.println("videoPath = " + videoPath);

		try {			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps = DesiredCapabilities.android();
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "1a66dc8f"); // device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨.
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

			URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

			System.out.println("Start driver.");
			driver = new AndroidDriver<WebElement>(appiumUrl, caps);

		} catch (Exception e) {
			{
				try
				{
					Thread.sleep(5000);
					DesiredCapabilities caps = new DesiredCapabilities();
					caps = DesiredCapabilities. android ();
					
					
					//caps.setCapability(MobileCapabilityType.DEVICE_NAME , "LG-F160S-cdd1fa" );
					caps.setCapability(MobileCapabilityType.DEVICE_NAME , "LGF460S859d639d" ); //device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
					caps.setCapability(MobileCapabilityType.PLATFORM_NAME , MobilePlatform. ANDROID );
					caps.setCapability(MobileCapabilityType.BROWSER_NAME , "Chrome" );
					
					URL appiumUrl = new URL(    "http://127.0.0.1:4723/wd/hub" );
					
					System.out.println("Start driver.");
					driver = new AndroidDriver<WebElement>( appiumUrl , caps );
				
				
					/*
					driver = new FirefoxDriver();
				    baseUrl = "http://mw.cjmall.com";
				    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				    */
				}catch(Exception e1)
				{{
					try
					{
						Thread.sleep(5000);
						DesiredCapabilities caps = new DesiredCapabilities();
						caps = DesiredCapabilities. android ();
						
						
						//caps.setCapability(MobileCapabilityType.DEVICE_NAME , "LG-F160S-cdd1fa" );
						caps.setCapability(MobileCapabilityType.DEVICE_NAME , "LGF460S859d639d" ); //device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
						caps.setCapability(MobileCapabilityType.PLATFORM_NAME , MobilePlatform. ANDROID );
						caps.setCapability(MobileCapabilityType.BROWSER_NAME , "Chrome" );
						
						URL appiumUrl = new URL(    "http://127.0.0.1:4723/wd/hub" );
						
						System.out.println("Start driver.");
						driver = new AndroidDriver<WebElement>( appiumUrl , caps );
					
					
						/*
						driver = new FirefoxDriver();
					    baseUrl = "http://mw.cjmall.com";
					    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					    */
					}catch(Exception e2)
					{
						System.out.println("Session Creation failed.");
						e.printStackTrace();
						assertTrue(false);
						setupSuccess = false;
						return;	
						} 
					}
				}
				}
		}

		System.out.println("=====setUp end======");

	}

	@Test
	public void LoginAlertTextCheck() throws Exception {

		boolean isExist = false;
		WebDriverWait wait = null;

		System.out.println("=====TestCase Start======");

		if (!setupSuccess) {
			System.out.println("Setup failed. So this TestCase is stopped now.");
			assertTrue(true);
			return;
		}

		try {

			int window_num = 0;

			driver.get(url);

			String mainWindow = driver.getWindowHandle();
			System.out.println("main Windows =" + mainWindow);

			Set<String> handles = driver.getWindowHandles();
			window_num = handles.size();

			System.out.println("Windows Num =" + window_num);
			for (String handle : handles) {
				System.out.println("windows handles :" + handle);
				if (!handle.equals(mainWindow)) {
					driver.switchTo().window(handle);
					System.out.println("Switch Windows");
					break;
				}
			}

			System.out.println("Windows Num =" + window_num);

			// 팝업창이 뜨는데도 1로 나타는 경우가 있음. 왜 그럴까요?
			if (window_num > 1) {
				System.out.println("팝업창 뜸. 팝업창 Element는 일관성 없음.");

				Thread.sleep(3000);

				return;

			}

			// 팝업창이 존재하면... 팝업창이 떠도 위와 같이 1로 리턴하는 경우가 있음.
			isExist = existElement(driver, By.xpath(".//*[@id='ct']/div/div[1]/div/div/div/a[1]"), "팝업창");
			if (isExist) {
				element = driver.findElement(By.xpath(".//*[@id='ct']/div/div[1]/div/div/div/a[1]"));
				element.click();
			}
						
			// 좌측 카테고리 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='o1h_header_wrap']/dl/dt/a")));
		  	System.out.println("좌측 카테고리 기다림");
			
			 // 좌측 카테고리 클릭
			driver.findElement(By.xpath(".//*[@id='o1h_header_wrap']/dl/dt/a")).click();
		  	System.out.println("좌측 카테고리 클릭");
			
			// "로그인" Text 기다림.
		    wait = new WebDriverWait(driver, waitTime);
		  	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='wrapper_cate_left']/dl/dt/a")));
		  	System.out.println("'로그인' Text 기다림");

			 // 로그인 클릭
			driver.findElement(By.xpath(".//*[@id='wrapper_cate_left']/dl/dt/a")).click();
		  	System.out.println("'로그인' 클릭");
			
	  		//  로그인 화면 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("member_id")));
		  	System.out.println("로그인 화면 기다림");

			// 잘못된 아이디 / 패스워드 입력
			driver.findElement(By.xpath(".//*[@id='member_id']")).sendKeys("erurian1234");
	        driver.findElement(By.xpath(".//*[@id='pwd']")).sendKeys("password");
		  	System.out.println("잘못된 아이디 / 패스워드 입력");
			
			// 로그인 버튼 클릭
			element = driver.findElement(By.xpath(".//*[@id='loginForm']/input[4]"));
	  		element.click();
		  	System.out.println("로그인 버튼 클릭");
	  		Thread.sleep(3000);
	  		// Alert text 체크
			if ("아이디나 비밀번호가 틀립니다. 다시 한번 확인해 주세요.".equals(driver.switchTo().alert().getText())) {
				System.out.println("[TC_082] success");
				assertTrue(true);
				return;
			} else {
		    	System.out.println("[TC_082] failure : 'Alert Text' 불일치");
				assertTrue(false);
			}

	        
	        

		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			assertTrue(false);
		} 
		
		finally 
		{
			Thread.sleep(3000);

			System.out.println("=====TestCase end======");
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}

}
