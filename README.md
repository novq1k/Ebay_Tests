Run from command line: mvn clean test -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src\test\resources\testng-smoke.xml -Denvironment=qa

-Dbrowser=browser name(required)

-Dsurefire.suiteXmlFiles=path to xml file (optional)
(testng-all.xml suite will be run in case parameter is empty)

-Denvironment=name of properties file for environment parameters (required)