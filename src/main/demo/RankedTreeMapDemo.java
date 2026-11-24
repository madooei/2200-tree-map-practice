package demo;

import java.util.Arrays;
import practice.RankedTreeMap;

/** A demo of RankedTreeMap on the tree the notes trace. */
public final class RankedTreeMapDemo {

  private RankedTreeMapDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    // This order builds the notes' tree: 50 at the root, 30 and 70 below it.
    int[] keys = {50, 30, 70, 20, 40, 60, 80, 75, 90};
    System.out.println("keys added: " + Arrays.toString(keys));
    RankedTreeMap<Integer, String> map = new RankedTreeMap<>();
    for (int key : keys) {
      map.put(key, "v" + key);
    }

    // In order, the keys are 20 30 40 50 60 70 75 80 90.
    // Expect 20 for the 1st smallest and 70 for the 6th.
    System.out.println("select(1): " + map.select(1));
    System.out.println("select(6): " + map.select(6));

    // Three keys are smaller than 50. Expect 3.
    System.out.println("rank(50): " + map.rank(50));

    // 72 is not stored, and six keys are smaller than it. Expect 6.
    System.out.println("rank(72): " + map.rank(72));
  }
}
