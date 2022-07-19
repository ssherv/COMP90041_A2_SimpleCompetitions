/* THIS ASSIGNMENT WAS COMPLETED BY:
   Name: Shervyn Singh
   Student Number: 1236509
   Username: shervyns
 */

public class Entry {

  private int entryId; // entry identifier

  private int[] numbers; // individual entry numbers

  private String memberID; // records the member ID of each entrant

  private int prizePoints = 0; // records the points associated with a winning entry



  public int getEntryId() {
    return entryId;
  }

  public void setEntryId(int entryId) {
    this.entryId = entryId;
  }

  public int[] getNumbers() {
    return numbers;
  }

  public void setNumbers(int[] numbers) {
    this.numbers = numbers;
  }

  public String getMemberID() {
    return memberID;
  }

  public void setMemberID(String memberID) {
    this.memberID = memberID;
  }

  public int getPrizePoints() {
    return this.prizePoints;
  }

  public void setPrizePoints(int prize) {
    this.prizePoints = prize;
  }


  /**
   * Used to format the printing of Entry ID when listing entries made, and when creating the
   * competition report
   */
  public void printEntryId() {
    System.out.printf("Entry ID: %-6d", this.getEntryId());
  }


  /**
   * Used to format the printing of the manually generated entries Overloaded from AutoEntry class
   */
  public void printEntryNumbers() {
    int[] numList = getNumbers();
    System.out.printf("Numbers:");
    for (int i = 0; i < numList.length; i++) {
      System.out.printf("%3d", numList[i]);
    }
    System.out.println("");
  }


  /**
   * Used to format the printing of the competition report, showing MemberID, corresponding winning
   * entry, and the prize that they won
   */
  public void printEntryReport() {
    System.out.printf("Member ID: " + this.getMemberID() + ", ");
    this.printEntryId();
    System.out.printf(", Prize: %-5d, ", this.prizePoints);
    this.printEntryNumbers();
  }
}