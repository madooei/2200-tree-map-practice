package demo;

import java.util.Arrays;
import list.List;
import practice.OrderedTreeMap;

/** A demo of OrderedTreeMap on the tree the notes trace. */
public final class OrderedTreeMapDemo {

  private OrderedTreeMapDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    // This order builds the notes' tree: 50 at the root, 30 and 70 below it.
    int[] keys = {50, 30, 70, 20, 40, 60, 80, 75, 90};
    System.out.println("keys added: " + Arrays.toString(keys));
    OrderedTreeMap<Integer, String> map = new OrderedTreeMap<>();
    for (int key : keys) {
      map.put(key, "v" + key);
    }

    // 55 is not stored. Expect a floor of 50 and a ceiling of 60.
    System.out.println("floorKey(55): " + map.floorKey(55));
    System.out.println("ceilingKey(55): " + map.ceilingKey(55));

    // 40 is stored, so it is its own floor and ceiling. Expect 40 and 40.
    System.out.println("floorKey(40): " + map.floorKey(40));
    System.out.println("ceilingKey(40): " + map.ceilingKey(40));

    // Expect 60 70 75.
    List<Integer> inRange = map.keysBetween(55, 78);
    System.out.print("keysBetween(55, 78):");
    for (int i = 0; i < inRange.size(); i++) {
      System.out.print(" " + inRange.get(i));
    }
    System.out.println();
  }
}
