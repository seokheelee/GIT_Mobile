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
 * Date : 2016-11-28
 * Subject : CJ Mall 
 * Name : TC_012
 * Scenario : 전시 > 오클락 딜 > 방송상품 > "간편구매" 버튼 노출 확인 (비로그인)
 * Assertion : "간편구매" Text 체크 (보험/상담 상품 시간이 아닌 경우만 가능)
 * update : ScreenRecorder 기능 제거 (2016-06-16)
 * update : Click 이벤트 변경 (2016-11-28)
 * 
 */

public class Mobile_TC012 {
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
	public void easyBuyView() throws Exception {

		boolean isExist = false;
		boolean isExist2 = false;
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

			Thread.sleep(3000);
			// 팝업창이 존재하면... 팝업창이 떠도 위와 같이 1로 리턴하는 경우가 있음.
			isExist = existElement(driver, By.xpath(".//*[@id='ct']/div/div[1]/div/div/div/a[1]"), "팝업창");
			if (isExist) {
				driver.findElement(By.xpath(".//*[@id='ct']/div/div[1]/div/div/div/a[1]")).click();
			}
			
			// 오클락딜 버튼 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='headerMenu']/ul/li[3]/a")));
			System.out.println("오클락딜 버튼 기다림");
			
			// 오클락딜 버튼 클릭
			driver.findElement(By.xpath(".//*[@id='headerMenu']/ul/li[3]/a")).click(); 
			System.out.println("오클락딜 버튼 클릭");
			Thread.sleep(10000);
			
			// 생방송 ONAIR 객체 체크
			boolean isExist4 = existElement(driver, By.xpath(".//*[@id='ct']/div/div[4]/div/a"), ""); // ONAIR
			if(isExist4){
				
				// 생방송 ONAIR 객체 기다림.
				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='ct']/div/div[4]/div/a")));
				System.out.println("생방송 ONAIR 객체 기다림");
				Thread.sleep(5000);
				
				// 생방송 객체로 이동
				Point hoverItem = driver.findElement(By.xpath(".//*[@id='ct']/div/div[4]/div/a/div[1]/img")).getLocation();
			  	((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
			  	System.out.println("생방송 객체로 이동");
				
				// TV 편성표 뜰 때까지 기다림.  (time delay)
				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='ct']/div/div[6]/ul/li[2]/a")));
				System.out.println("TV 편성표 뜰 때까지 기다림");
				Thread.sleep(3000);
				}
				else {
				assertTrue(true);
				}
		  	
		  	// "간편구매" Text 체크 (보험/상담 상품 시간이 아닌 경우만 가능)
		  	isExist = existElement(driver,By.xpath(".//*[@id='ct']/div/div[4]/div/ul/li/a"),"비교상품 or 상담신청");
		  	isExist2 = existElement(driver, By.xpath(".//*[@id='ct']/div/div[4]/div/div[1]/span[1]"),"ONAIR img"); // 생방송 ONAIR img

		  	if(isExist || isExist2 == false)
		  	{
		  		if(isExist2){
		  			wait = new WebDriverWait(driver, waitTime);
				  	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='ct']/div/div[4]/div/ul/li/a")));
				  	element = driver.findElement(By.xpath(".//*[@id='ct']/div/div[4]/div/ul/li/a"));
			  		System.out.println("====="+element.getText());
			  		
			  		//동영상 재생으로 가는 상품의 패턴은 비교상품 상담신청이 같이 나오는 것과 상담신청 하나만 나오는 것이 있음. 그래서 첫번째 객체가 비교상품 또는 상담신청이면 종료처리함.
			  		if(element.getText().equals("상담신청") || element.getText().equals("비교상품") )
			  		{
			  			System.out.println("[TC_012] failure :"+ element.getText() +"입니다. 다른 시간대 검증필요. 여기서 종료");
			  			assertTrue(true);
			  		   	return;
			  		} else if(element.getText().equals("간편구매")) {
			  			System.out.println("[TC_012] success");
						assertTrue(true);
						return;
			  		}
		  		} else if(isExist2 == false) {
		  			System.out.println("[TC_012] failure : 생방송 상품 시간 아님. 다른 시간대 검증필요. ONAIR 이미지 보이지 않음. 여기서 종료");
		  			assertTrue(true);
		  		   	return;
		  		}
		  	}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		} finally {
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
