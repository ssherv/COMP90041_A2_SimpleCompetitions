/* THIS ASSIGNMENT WAS COMPLETED BY:
   Name: Shervyn Singh
   Student Number: 1236509
   Username: shervyns
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SimpleCompetitions {

  // Scanner available to all classes
  public static Scanner keyboard = new Scanner(System.in);

  // Records the Program Mode (Normal, Testing)
  //TODO  Can use boolean if only 2 options (if test mode --> then true, else false)
  private String programMode = "";

  // Initialise ArrayList of Competitions -- records all competitions made
  private ArrayList<Competition> listOfCompetitions = new ArrayList<Competition>();

  // Global record of entry ID -- increases by 1 every time a new entry is entered
  //TODO can use static variable (only 3 or 4 static TOTAL)
  private int entryId = 1;

  // Records the total points won by an entry based on matching index
  //   e.g  if an entry matches 2 numbers, it will received the prize at prizePoints[2] == 50
  //   e.g. if an entry matches 7 numbers, it will received the prize at prizePoints[7] == 50000
  //TODO can use constant values for each prize
  private Integer[] prizePoints = {0, 0, 50, 100, 500, 1000, 5000, 50000};


  /**
   * Initialises program in 'Normal' or 'Testing' mode after verifying user input
   */
  private void initialiseMode() {
    while (true) {
      System.out.println("Which mode would you like to run? "
          + "(Type T for Testing, and N for Normal mode):");

      char modeChoice = keyboard.next().toUpperCase().charAt(0);

      if (modeChoice == 'N') {
        programMode = "Normal";
        break;
      } else if (modeChoice == 'T') {
        programMode = "Testing";
        break;
      } else {
        System.out.println("Invalid mode! Please choose again.");
      }
    }
  }


  /**
   * Checks the ArrayList of competitions for any Competition objects that are currently active
   *
   * @return true if there is currently an active competition, otherwise false
   */
  private boolean checkStatus() {
    for (Competition status : listOfCompetitions) {
      if (status.getIsActive().equalsIgnoreCase("yes")) {
        return true;
      }
    }
    return false;
  }


  /**
   * Add a new competition to the program -- user input determines the name (ID is auto-generated)
   *
   * @return Instantiate a new Competition object with variables initialised to user input
   */
  private Competition addNewCompetition() {
    Competition newCompetition = new Competition();

    System.out.println("Competition name: ");
    String competitionName = keyboard.nextLine();
    newCompetition.setName(competitionName); // Sets the name of the competition

    newCompetition.setIsActive("yes"); // Sets isActive to "yes" to block new competitions
    listOfCompetitions.add(newCompetition); // Adds the newly created competition to the Array List
    newCompetition.setId(listOfCompetitions.size()); // Sets the ID of the competition
    System.out.println("A new competition has been created!");
    System.out.println("Competition ID: " + newCompetition.getId()
        + ", Competition Name: " + newCompetition.getName());
    return newCompetition;
  }


  /**
   * Check whether an integer array contains a certain value
   *
   * @param arr   array of type integer
   * @param value key-value -- method is checking for this integer
   * @return true if the array contains the key value, otherwise return false
   */
  private boolean contains(int[] arr, int value) {
    for (int i : arr) {
      if (i == value) {
        return true;
      }
    }
    return false;
  }


  /**
   * Prints the report for the entire program
   */
  public void report() {
    System.out.println("----SUMMARY REPORT----");

    int completedCompetitions = 0;
    int activeCompetitions = 0;

    for (Competition comp : this.listOfCompetitions) {
      if (comp.getIsActive().equals("yes")) {
        activeCompetitions += 1;
      } else {
        completedCompetitions += 1;
      }
    }
    System.out.println("+Number of completed competitions: " + completedCompetitions);
    System.out.println("+Number of active competitions: " + activeCompetitions);

    for (Competition comp : this.listOfCompetitions) {
      comp.report();
    }
  }


  /**
   * Main program loop -- shows choices and includes switch statement to handle logic
   * Separated into different method in order to aid encapsulation requirement
    */
  public void programLoop() {
    while (true) {
      System.out.println("Please select an option. Type 5 to exit.");
      System.out.println("1. Create a new competition");
      System.out.println("2. Add new entries");
      System.out.println("3. Draw winners");
      System.out.println("4. Get a summary report");
      System.out.println("5. Exit");

      int userChoice = keyboard.nextInt();

      switch (userChoice) {
        case 1:
          /*First checks if there is an active competition in listOfCompetitions, if there is an
          active competition, block user from creating a new competition
           */
          if (checkStatus()) {
            System.out.println("There is an active competition. SimpleCompetitions does not "
                + "support concurrent competitions!");
            // TODO can remove else-if, and just make it else
          } else if (!checkStatus()) {
            keyboard.nextLine();
            addNewCompetition();
          }
          break;

        //TODO should put all entry generation code in Entry class
        case 2:
          /*First checks if there is an active competition in listOfCompetitions, if there
          is no active competition, user is prevented from adding entries.
           */
          char addEntries = 'Y';
          if (!checkStatus()) {
            System.out.println("There is no active competition. Please create one!");
            break;
          }
          while (addEntries == 'Y') {
            String memberID = "";

            // get the last competition added (which will always be the active one)
            int lengthOfComps = listOfCompetitions.size();
            Competition currentCompetition = listOfCompetitions.get(lengthOfComps - 1);
            int entryIndex = currentCompetition.getNumberOfEntries();

            int idCheck = 0;
            while (idCheck == 0) {
              System.out.println("Member ID: ");
              memberID = keyboard.next();
              if (currentCompetition.validMemberID(memberID) == true) {
                idCheck = 1;
              }
            }

            int billCheck = 0;
            while (billCheck == 0) {
              System.out.println("Bill ID: ");
              String billID = keyboard.next();
              if (currentCompetition.validBillID(billID) == true) {
                billCheck = 1;
              }
            }

            System.out.println("Total amount: ");
            Double billTotal = keyboard.nextDouble();
            int numberOfEntries = currentCompetition.numberOfEntries(billTotal);

            if (numberOfEntries > 0) {
              int numManualEntries = keyboard.nextInt();
              keyboard.nextLine();
              int numAutomaticEntries = numberOfEntries - numManualEntries;
              while (numManualEntries > 0) {
                System.out.println("Please enter 7 different numbers (from the range 1 to 35) "
                    + "separated by whitespace.");
                String entryString = keyboard.nextLine();
                int[] entryInt = currentCompetition.validManualEntries(entryString);

                if (entryInt != null) {
                  Arrays.sort(entryInt);
                  Entry entry = new Entry();
                  entry.setMemberID(memberID);
                  entry.setEntryId(entryId);
                  entry.setNumbers(entryInt);
                  currentCompetition.addEntries(entry);
                  this.entryId += 1;
                  numManualEntries -= 1;
                }
              }

              // Automatically generate entries
              while (numAutomaticEntries > 0) {

                int seedValue = currentCompetition.getNumberOfEntries();
                AutoEntry entry = new AutoEntry();
                entry.setMemberID(memberID);
                entry.setEntryId(entryId);

                // Check which mode the program is in (to determine seed value)
                // If normal mode use a random integer
                if (programMode.equals("Normal")) {
                  seedValue = (int) (Math.random() * (1001));
                }

                entry.setNumbers(entry.createNumbers(seedValue));
                currentCompetition.addEntries(entry);
                this.entryId += 1;
                numAutomaticEntries -= 1;
              }

              // Print the entries
              System.out.println("The following entries have been added:");
              for (int i = entryIndex; i < currentCompetition.getNumberOfEntries(); i++) {
                Entry entry = currentCompetition.getEntry(i);
                entry.printEntryId();
                System.out.printf(" ");
                entry.printEntryNumbers();
              }
            }

            // Ask user if they want to add more entries
            System.out.println("Add more entries (Y/N)?");
            addEntries = keyboard.next().toUpperCase().charAt(0);
            while (addEntries != 'N' && addEntries != 'Y') {
              System.out.println("Unsupported option. Please try again!");
              System.out.println("Add more entries (Y/N)?");
              addEntries = keyboard.next().toUpperCase().charAt(0);
            }
          }
          break;

        case 3:
          /*First checks if there is an active competition in listOfCompetitions, if there
          is no active competition, user is prevented from drawing winners.
           */
          if (!checkStatus()) {
            System.out.println("There is no active competition. Please create one!");
            break;
          }
          // get the last competition added (which will always be the active one)
          int lengthOfComps = listOfCompetitions.size();
          Competition currentCompetition = listOfCompetitions.get(lengthOfComps - 1);

          // If there is an active competition, check if there are any entries yet.
          if (currentCompetition.getNumberOfEntries() == 0) {
            System.out.println("The current competition has no entries yet!");
            break;
          }

          ArrayList<String> winningMemberIds = new ArrayList<String>();
          ArrayList<Entry> winningEntries = currentCompetition.getWinningEntries();
          System.out.println("Lucky entry for Competition ID: " + currentCompetition.getId()
              + ", Competition Name: " + currentCompetition.getName());
          // retrieve winning numbers in TEST MODE
          int[] winnerNumbers = new AutoEntry().createNumbers(currentCompetition.getId());
          if (programMode.equals("Normal")) {
            winnerNumbers = new AutoEntry().createNumbers((int) (Math.random() * (1001)));
          }

          // Entry for winning numbers
          Entry luckyWinner = new AutoEntry();
          luckyWinner.setEntryId(entryId);
          luckyWinner.setNumbers(winnerNumbers);
          luckyWinner.printEntryNumbers();

          // Store winner in current competition
          currentCompetition.setLuckyWinner(luckyWinner);

          // Iterate through every entry
          for (int i = 0; i < currentCompetition.getNumberOfEntries(); i++) {
            // Current iteration entry number
            Entry currentEntry = currentCompetition.getEntry(i);
            // Gets entry numbers
            int[] entryNumbers = currentEntry.getNumbers();
            // Get MemberId for a particular entry
            String memberId = currentEntry.getMemberID();
            // Count the number of digits that match between entry and winner
            int numberOfMatches = 0;
            // For every number in winning numbers (luckyWinner)
            for (int j : winnerNumbers) {
              // Check if entryNumbers contains a winning number
              if (contains(entryNumbers, j)) {
                // increment numberOfMatches by one
                numberOfMatches += 1;
              }
            }

            // return what prize they get
            int prize = prizePoints[numberOfMatches];
            if (prize > 0) {
              int indexOfMember = winningMemberIds.indexOf(memberId);
              currentEntry.setPrizePoints(prize);
              if (indexOfMember == -1) {
                winningMemberIds.add(memberId);
                winningEntries.add(currentEntry);
              } else {
                Entry previousPrizeEntry = winningEntries.get(indexOfMember);
                int previousPrize = previousPrizeEntry.getPrizePoints();
                if (previousPrize < prize) {
                  winningEntries.set(indexOfMember, currentEntry);
                }
              }
            }
          }

          // Print the winning entries
          System.out.println("Winning entries:");
          currentCompetition.setWinningEntries(winningEntries);
          for (Entry winningEntry : winningEntries) {
            winningEntry.printEntryReport();
          }
          currentCompetition.setIsActive("no");
          entryId += 1;
          break;

        case 4:
          /* First checks if there is an active competition in listOfCompetitions, if there there
          is no active competition, user is prevented from creating a report.
           */
          if (listOfCompetitions.size() == 0) {
            System.out.println("No competition has been created yet!");
            break;
          }
          this.report();
          break;

        case 5:
          System.out.println("Goodbye!");
          System.exit(0);
          break;

        default:
          System.out.println("Unsupported option. Please try again!");
      }
    }
  }


  /**
   * Main program that uses the main SimpleCompetitions class
   *
   * @param args main program arguments
   */
  public static void main(String[] args) {
    SimpleCompetitions sc = new SimpleCompetitions(); // Initialise SimpleCompetitions object

    System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");
    sc.initialiseMode();
    sc.programLoop();

    keyboard.close();
  }
}