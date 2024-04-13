import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenAvito {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void Screen_1_Test() throws Exception {
        driver.navigate().to("https://www.avito.ru/avito-care/eco-impact");

        // Находим элемент на странице для скриншота
        WebElement tableElement = driver.findElement(By.className("desktop-impact-items-F7T6E"));
        Thread.sleep(3000L); //ждем

        // Используем JavascriptExecutor для прокрутки к нужному элементу
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", tableElement);

        // Форм. дату для имени файла
        Date dateNow =new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String fileName = format.format(dateNow) + ".png";

        // Создание скриншота
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // В папку
        BufferedImage ing = ImageIO.read(screenshot);
        ImageIO.write(ing, "png", new File("output/Screen_1_Test " + fileName));
    }
}

