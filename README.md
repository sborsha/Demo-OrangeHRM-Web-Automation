# Demo OrangeHRM Web Automation
This is simple project of web automation for OrangeHRM website. Here I have used TestNG as a test framework and generate random users with faker library. Also used generate randomly strong password with unique symbols. And here I automated user login and creation and an employee search, Information Update of system.

## OverView 
1.  Login as a admin to https://opensource-demo.orangehrmlive.com/
2.  Go to PIM menu and create a new employee. Save the employee firstname, lastname, employeeid, username and password into JSONArray file. Generate random password which meets following criteria:
For a strong password, please use a hard to guess combination of text with upper and lower case characters, symbols and numbers. Assert if employee is created successfully.
3. Now go to the dashboard again and search by the employee id to check if the employee is found
4. Now go to the Directory menu and search by employee name and check if the employee is found and logout the session
5. Now login with the newly created employee credentials
6. Assert your full name is showing besides the profile icon.
7. Go to my info
8. Scroll down and select Gender and Blood Type as O+ and save it. Then logout the user.

## Required Tools & Tech
  - Installed Java Development Kit (JDK)
  - IntelliJ Idea or any community (JAVA IDE)
  - Selenium with TestNG
  - Allure
  - Gradle

## How to Run this Project
- Clone this project repo

      https://github.com/sborsha/Demo-OrangeHRM-Web-Automation.git
- Open this file with IntelliJ Idea or any community (JAVA IDE)
- Run the command in command line

      gradle clean test
- To generate report with the command

      allure generate allure-results --clean -output
      allure serve allure-results

## Automation Video
  https://github.com/sborsha/Demo-OrangeHRM-Web-Automation/assets/97577812/f5c08230-03e6-4c74-88e7-5b80088a4d70


##  Allure Report
  ![WebAutomationOrangeHRM-Allure](https://github.com/sborsha/Demo-OrangeHRM-Web-Automation/assets/97577812/49bae88e-0b43-4055-814f-91b905b49600)
  ![allure-behavior](https://github.com/sborsha/Demo-OrangeHRM-Web-Automation/assets/97577812/ca719df0-325b-40a9-8874-146a9d2e8c40)


## Test Case
  https://docs.google.com/spreadsheets/d/1u0-1O60tUZG-xMvbuNUo5JorDMCiZ_iN/edit?usp=sharing&ouid=117539685062847307961&rtpof=true&sd=true

