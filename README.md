# COMP90041_A2_SimpleCompetitions
A console-based program named SimpleCompetitions that helps companies (e.g., retailers) create competitions to boost their sales.
<br>
<h4> Completed in May 2021 (Semester 1) </h4>
<hr>

<b>Functionality:</b>
A retailer can use this software to create a lucky draw game. In the game, a customer is given entries equivalent to the size of its purchase after their single-purchase final balance, after discounts, reach a specifc amount, say $50. Once the competition time ends, the retailer draws winners and the winning customers will get points added to their membership account; customers can use these points for future purchases if they wish. 

<b>Topics of focus:</b>
Class design and implementation, control flow structures, basic I/O, arrays, inheritance and polymorphism
<hr>

See 'A2_Specification' for the competition policy and application walk-through including intended inputs and expected outputs. This application supports two different modes: (1) the normal mode (release mode) and (2) the testing mode (designed to test the program in a deterministic manner).

To run this console application in NORMAL MODE:
<ol>
  <li> Download the Java code files in the "A2" folder </li>
  <li> Open a console and navigate to the project directoty (where the .java classes reside) </li>
  <li> Run and complile the program with: <code> javac *.java </code> </li>
  <li> Run the command: <code> java SimpleCompetitions </code> </li>
</ol>

To run this console application in TESTING MODE:
<ol>
  <li> Download the Java code files in the "A2" folder </li>
  <li> Download the test files (including input and output) in the "A2-test-cases" folder </li>
  <li> Place the text input data files in the same directory as your project directory (where the .java classes reside) </li>
  <li> Open a console and navigate to the project directory </li>
  <li> Run and complile the program with: <code> javac *.java </code> </li>
  <li> Run the command: <code> java SimpleCompetitions < input1.txt > my-output1.txt </code> <br> 
  (This will run SimpleCompetitions using contents in "input1.txt" as input and write the output to "my-output1.txt". </li>
  <li> Inpect the file "my-output1.txt" with the provided output files (e.g.output1.txt) to test that the program is working as intended </li>
  <li> Repeat steps 6 and 7 to test all given input and output pairs </li>
</ol>
