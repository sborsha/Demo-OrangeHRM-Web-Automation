package utils;

import config.UsersModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {
    public static void doScroll(WebDriver driver, int firstPositn, int lastPositn){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript ("window.scrollBy("+firstPositn+","+lastPositn+")");
    }
    public static void saveUsers(UsersModel usersModel) throws IOException, ParseException {
        String fileLocation="./src/test/resources/users.json";
        JSONParser jsonParser=new JSONParser ();
        JSONArray jsonArray= (JSONArray) jsonParser.parse (new FileReader (fileLocation));

        JSONObject userObj=new JSONObject ();
        userObj.put ("firstName", usersModel.getFirstname ());
        userObj.put("lastName", usersModel.getLastname ());
        userObj.put ("empID", usersModel.getEmpID ());
        userObj.put ("username", usersModel.getUsername ());
        userObj.put ("password", usersModel.getPassword ());

        jsonArray.add(userObj);

        FileWriter writer=new FileWriter (fileLocation);
        writer.write (jsonArray.toJSONString ());
        writer.flush ();
        writer.close ();
    }
    public static String generateRandomPass(){
        String upperCaseLtr="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLtr="abcdefghijklmnopqrstuvwxyz";
        String digits="0123456789";
        String symbols="!@#$%^&*()_-=+<>,./?{}[]";

        String allcharacters = upperCaseLtr+lowerCaseLtr+digits+symbols;

        StringBuilder password=new StringBuilder();
        password.append (getRandomchar (upperCaseLtr));
        password.append (getRandomchar (lowerCaseLtr));
        password.append (getRandomchar (digits));
        password.append (getRandomchar (symbols));

        int length = 10-password.length ();
        for (int i = 0;i<length;i++){
            password.append (getRandomchar (allcharacters));
        }
        List<Character> chars = password.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Collections.shuffle(chars);
        return chars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
    public static char getRandomchar(String characters){
        int randomInd = (int)(Math.random ()*characters.length());
        return characters.charAt (randomInd);
    }
    public static JSONObject getUsers() throws IOException, ParseException {
        String fileLocation="./src/test/resources/users.json";
        JSONParser jsonParser=new JSONParser ();
        JSONArray jsonArray= (JSONArray) jsonParser.parse (new FileReader (fileLocation));
        JSONObject jsonObject = (JSONObject) jsonArray.get (jsonArray.size ()-1);
        return jsonObject;
    }

    public static void waitForElement(WebDriver driver, WebElement element){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
