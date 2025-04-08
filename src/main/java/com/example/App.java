package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;


public class App {
    public static void main(String[] args) throws Exception {
        // Create a temporary directory for the user data
        Path profileDir = Files.createTempDirectory("chrome-ud-"+System.getenv("BUILD_NUMBER"));

        // Set up ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--user-data-dir=" + profileDir.toAbsolutePath());
	options.addArguments("--disable-dev-shm-usage");
        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        // Perform your Selenium operations
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Close the browser
        driver.quit();

        // Clean up the temporary directory
        deleteDirectory(tempUserDataDir.toFile());
    }

    // Method to delete the temporary directory
    public static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }
}

