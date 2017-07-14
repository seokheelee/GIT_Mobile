package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

public class M_009 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	// boolean setupSuccess = true;
	private String M_URL = null;

	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-06-19
	 * Subject : CJ Mall 운영  
	 * Name : M_009
	 * Scenario :  로그인 > 아이디 찾기 > 이름/생년월일 입력 > 확인
	 * Assertion :  '***** 입니다. Text 체크
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		//System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();
		M_URL = sp.getProperty("M_URL");
	
		try {
			Thread.sleep(5000);
			DesiredCapabilities caps = new DesiredCapabilities();
			caps = DesiredCapabilities.android();

			// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

			URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

			System.out.println("Start driver.");
			driver = new AndroidDriver<WebElement>(appiumUrl, caps);

		} catch (Exception e) {
			try {
				Thread.sleep(5000);
				DesiredCapabilities caps = new DesiredCapabilities();
				caps = DesiredCapabilities.android();

				// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
				caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
				caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
				caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

				URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

				System.out.println("Start driver.");
				driver = new AndroidDriver<WebElement>(appiumUrl, caps);

			} catch (Exception e1) {
				{
					try {
						Thread.sleep(5000);
						DesiredCapabilities caps = new DesiredCapabilities();
						caps = DesiredCapabilities.android();

						// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
						caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
						caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
						caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

						URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

						System.out.println("Start driver.");
						driver = new AndroidDriver<WebElement>(appiumUrl, caps);

					} catch (Exception e2) {
						System.out.println("Session Creation failed.");
						e.printStackTrace();
						assertTrue(false);
						return;
					}
				}
			}
		}
	}

	@Test
	public void m_009() throws Exception {
		
		driver.get(M_URL);
		
		boolean isExist = false;
		//팝업닫기
		isExist = existElement(driver, By.xpath("//*[@id='notToday']"), "오늘 하루 보지 않기");
		if (isExist) {
			driver.findElement(By.xpath("//*[@id='popup_spot']/div/div/div/div[2]/button")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//*[@id='header']/div[2]/a[1]")).click();
		//좌측카테고리 버튼 클릭
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='gnb']/div[1]/a")).click();
		System.out.println("좌측카테고리 버튼 클릭");
		//로그인 텍스트 클릭
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='findId']")).click();
		System.out.println("아이디 찾기 버튼 클릭");
		//아이디 찾기
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='oneName']")).sendKeys("조성주");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='oneBirth']")).sendKeys("19880701");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='simple_find']/span/label")).click();
		
		driver.findElement(By.xpath("//*[@id='mb_button']")).click();
		System.out.println("아이디/생년월일 입력 후 조회 클릭");
		
		Thread.sleep(7000);
		
		System.out.println((driver.findElement(By.xpath("//*[@id='txt_success']")).getText()));
		if ("고객님의 CJmall 아이디는\njsjjo**8입니다.".equals(driver.findElement(By.xpath("//*[@id='txt_success']")).getText())) {
           System.out.println("TC_9 PASS");
           Thread.sleep(3000);
           assertTrue(true);
           return;
        } else {
           System.out.println("TC_9 FAIL");
           assertTrue(false);
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
		// wait.ignoring(NoSuchElementException.class);

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
