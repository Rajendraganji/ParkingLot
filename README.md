                                    Parking Lot Execution Guide 

Note :
1)	Historical data is not considered (As per FAQ’s). If the vehicle leaves the parking lot then it gets deleted from the data we hold.
2)	The technologies I used are Java , spring boot( embedded h2 database).

Steps :
1)	Download and install JDK 1.8 or later. The following link has all the details/steps to install and configure this. (https://www.guru99.com/install-java.html) 
i) Setting the JAVA_HOME Variable in Windows . The following link has all the details ( https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html )
2)	Download and install maven latest version. The following link has all the details/steps to install and configure this. (https://www.javatpoint.com/how-to-install-maven )  
3)	Clone the code from here. ( https://github.com/Rajendraganji/ParkingLot.git ) and I’m also sharing attachment of project zip file. 
4)	You can also import the code straight into your IDE’s like Eclipse, Intellij IDEA etc.
5)	Open the terminal either in your IDE or command prompt terminal.
6)	Go to inside project location and type the below command . (I’m taking test input file location from command line arguments. Output will be printed in terminal itself.)

      To build and run  : commands
      
      ***         mvn clean install -DskipTests         ***
      
      ***   mvn spring-boot:run -Dspring-boot.run.arguments=--inputfile.path="input test file path"  ***
      
      
Example : 
 (  C:\practise_projects\Parkinglot\Parkinglot>mvn clean install -DskipTests ) -----> for building the project
 
( C:\practise_projects\Parkinglot\Parkinglot>mvn spring-boot:run -Dspring-boot.run.arguments=--inputfile.path="C:\Users\ganjir\Desktop\input.txt"  )   ---> For running project


7) once you run this , you will get a output for the inputs you provided in file( input.txt ).

8) To test the code with another text file , clicl CTRL+C and then click "y". Now you can use the same 2nd command provided in step 6 (mvn clean install -DskipTests is not needed)
   
   with new file location which you want to test.

