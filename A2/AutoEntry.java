/* THIS ASSIGNMENT WAS COMPLETED BY:
   Name: Shervyn Singh
   Student Number: 1236509
   Username: shervyns
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AutoEntry extends Entry {


  /**
   * Creates an array of integers based on a particular seed value The same array will always be
   * created with a particular seed value
   *
   * @param seed integer value -- can be random or predetermined
   * @return integer array with 7 unique values
   */
  public int[] createNumbers(int seed) {
    ArrayList<Integer> validList = new ArrayList<Integer>();
    int[] tempNumbers = new int[7];
    for (int i = 1; i <= 35; i++) {
      validList.add(i);
    }
    Collections.shuffle(validList, new Random(seed));
    for (int i = 0; i < 7; i++) {
      tempNumbers[i] = validList.get(i);
    }
    Arrays.sort(tempNumbers);
    return tempNumbers;
  }


  /**
   * Used to format the printing of the automatically generated entries Overloaded from Entry class
   */
  public void printEntryNumbers() {
    int[] numList = getNumbers();
    System.out.printf("Numbers:");
    for (int i = 0; i < numList.length; i++) {
      System.out.printf("%3d", numList[i]);
    }
    System.out.println(" [Auto]");
  }
}