AppDirect Automation Project: 

The AppDirect Test Automation Project is designed based on the Page Object Model (Page Factory) framework design pattern. The Framework is kept dry as possible and modularized considering the scalable aspects. This readme document will give a brief description about the whole structure and help the team/users to gain better insights. 
 
This Project is Categorized as two main parts,
1. Automation Test Framework - Core  (atf-core)
2. Automation Test Framework - Tests (atf-tests)

ATF-CORE: 
This is considered to be the framework of the application and has all the reusable components which is modularized as different packages. The Page Objects also placed within this atf-core. Various other helpers such as Constants, PageBase, TestBase, Reporters, DataProviders etc are placed within the atf-core. Moreover PlaceHolders are created considering the scope of the future extension of the project to the other areas of the application. 
1. Common
2. PageFactory
3. Constants
4. Utility

ATF-TESTS: 
This contains the actual test cases for the AppDirect Application

OTHERS: 
Configuration: Contains settings and properties of the test configuration. 
Test-Output: Contains Test Results in the form of HTML Reports
POM.XML: Maven Configuration
TestNG.XML: TestNG Configuration
Resources: Has the Test Resources or the TestData.CSV files. 


TOOLS USED: 
1.Java (Version 1.8.x)
Selenium 
TestNG (Version 6.7)
Maven 
Extent Reports
Eclipse IDE

HOW TO RUN:  
With Maven surefire & TestNG 
Run the Batch File
Run with Eclipse IDE
Run with Jenkins CI
