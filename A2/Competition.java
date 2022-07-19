/* THIS ASSIGNMENT WAS COMPLETED BY:
   Name: Shervyn Singh
   Student Number: 1236509
   Username: shervyns
 */

import java.util.ArrayList;

public class Competition {

  private String name; //competition name

  private int id; //competition identifier

  // TODO can be boolean bc only 2 choices
  private String isActive = ""; // competition status (active: "yes")

  // records list of all entries
  private ArrayList<Entry> listOfEntries = new ArrayList<Entry>();

  // records the winning entry numbers (as an Entry object)
  private Entry luckyWinner;

  // records list of winning entries
  private ArrayList<Entry> winningEntries = new ArrayList<Entry>();

  // These variables were put in the Competitions class in case future competitions have a
  // different set of criteria for the MemberID, BillID, BillAmount, MaxEntry number
  private final int MEMBER_ID_MAX_DIGITS = 6;
  private final int BILL_ID_MAX_DIGITS = 6;
  private final double MINIMUM_BILL_AMOUNT = 50.0;
  private final int ENTRY_MAX_DIGITS = 7;


  /**
   * Checks to see whether the user-input memberID is a valid String
   * @param memberID
   * @return true if memberID is valid, false otherwise
   */
  public boolean validMemberID(String memberID) {
    if ((memberID.length() == MEMBER_ID_MAX_DIGITS) && (memberID.matches("[0-9]+"))) {
      return true;
    } else {
      System.out.println("Invalid member id! It must be a 6-digit number. Please try again.");
      return false;
    }
  }


  /**
   * Checks to see whether the user-input billID is a valid String
   * @param billID
   * @return true if billID is valid, false otherwise
   */
  public boolean validBillID(String billID) {
    if ((billID.length() == BILL_ID_MAX_DIGITS) && (billID.matches("[0-9]+"))) {
      return true;
    } else {
      System.out.println("Invalid bill id! It must be a 6-digit number. Please try again.");
      return false;
    }
  }


  /**
   * Calculates the number of entries that a member is eligible to receive (based on receipt
   * amount)
   * @param billTotal
   * @return numEntries == number of entries the member can enter into the competition
   */
  public int numberOfEntries(Double billTotal) {
    double eligibleEntries = billTotal / MINIMUM_BILL_AMOUNT;
    int numEntries = (int) eligibleEntries;
    if (numEntries > 0) {
      System.out.println("This bill is eligible for " + numEntries
          + " entries. How many manual entries did the customer fill up?: ");
    } else {
      System.out.println("This bill is not eligible for an entry. The total amount is smaller "
          + "than $" + MINIMUM_BILL_AMOUNT);
    }
    return numEntries;
  }


  /**
   * Checks whether the manually input entry meets certain criteria and prints an error if not
   * @param entryString
   * @return an array of type integer (i.e. returns the 7-digit entry) if all checks are passed,
   * else returns null (no entry)
   */
  public int[] validManualEntries(String entryString) {
    String[] arrOfEntryString = entryString.split(" ");

    if (arrOfEntryString.length < ENTRY_MAX_DIGITS) {
      System.out.println("Invalid input! Fewer than 7 numbers are provided. Please try again!");
      return null;

    } else if (arrOfEntryString.length > ENTRY_MAX_DIGITS) {
      System.out.println("Invalid input! More than 7 numbers are provided. Please try again!");
      return null;

    } else if (arrOfEntryString.length == ENTRY_MAX_DIGITS) {
      if (containsDuplicates(arrOfEntryString) == true) {
        System.out.println("Invalid input! All numbers must be different!");
      } else if (containsDuplicates(arrOfEntryString) == false) {
        int[] arrOfEntryInt = new int[ENTRY_MAX_DIGITS];
        for (int b = 0; b < arrOfEntryString.length; b++) {
          arrOfEntryInt[b] = Integer.parseInt(arrOfEntryString[b]);
        }
        return arrOfEntryInt;
      }
    }
    return null;
  }


  /**
   * Checks whether an entry (converted from array of type int to a String sentence) contains any
   * duplicate values
   * @param aString
   * @return true if there are duplicates, false if no duplicates
   */
  private boolean containsDuplicates(String[] aString) {
    for (int i = 0; i < aString.length; i++) {
      for (int j = i + 1; j < aString.length; j++) {
        if (aString[i].equals(aString[j])) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * Used to format printing of the Competition specific report Shows the ID, Name, active status of
   * the competition, total number of entries, total number of winning entries and total awarded
   * prizes for a specific competition
   */
  public void report() {
    System.out.println("");
    System.out.println(
        "Competition ID: " + this.getId() + ", name: " + this.getName() + ", active: " + this
            .getIsActive());
    System.out.println("Number of entries: " + this.listOfEntries.size());
    if (this.listOfEntries.size() != 0 && this.winningEntries.size() != 0) {
      System.out.println("Number of winning entries: " + this.winningEntries.size());

      int totalPrize = 0;
      for (Entry winningEntry : this.winningEntries) {
        totalPrize += winningEntry.getPrizePoints();
      }
      System.out.println("Total awarded prizes: " + totalPrize);
    }
    return;
  }

  public void addEntries(Entry entry) {
    listOfEntries.add(entry);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIsActive() {
    return isActive;
  }

  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }

  public int getNumberOfEntries() {
    return this.listOfEntries.size();
  }

  public Entry getEntry(Integer index) {
    return this.listOfEntries.get(index);
  }

  public Entry getLuckyWinner() {
    return this.luckyWinner;
  }

  public void setLuckyWinner(Entry luckyWinner) {
    this.luckyWinner = luckyWinner;
  }

  public ArrayList<Entry> getWinningEntries() {
    return this.winningEntries;
  }

  public void setWinningEntries(ArrayList<Entry> winningEntries) {
    this.winningEntries = winningEntries;
  }
}