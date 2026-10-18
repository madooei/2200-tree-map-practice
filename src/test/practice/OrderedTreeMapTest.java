package practice;

import list.List;
import map.Map;
import map.MapTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/** Runs the Map contract suite against OrderedTreeMap, then checks the ordered queries. */
public class OrderedTreeMapTest extends MapTest {

  @Override
  protected Map<String, Integer> createMap() {
    return new OrderedTreeMap<>();
  }

  @Test
  public void floorKeyOnAnEmptyMapIsNull() {
    assertNull(new OrderedTreeMap<Integer, String>().floorKey(5));
  }

  @Test
  public void ceilingKeyOnAnEmptyMapIsNull() {
    assertNull(new OrderedTreeMap<Integer, String>().ceilingKey(5));
  }

  @Test
  public void keysBetweenOnAnEmptyMapIsEmpty() {
    assertKeys(new OrderedTreeMap<Integer, String>().keysBetween(0, 100));
  }

  @Test
  public void floorKeyOfAMissingKeyIsTheNextKeyBelow() {
    assertEquals(50, example().floorKey(55));
  }

  @Test
  public void floorKeyOfAPresentKeyIsTheKeyItself() {
    assertEquals(40, example().floorKey(40));
  }

  @Test
  public void floorKeyBelowEveryKeyIsNull() {
    assertNull(example().floorKey(10));
  }

  @Test
  public void floorKeyAboveEveryKeyIsTheLargestKey() {
    assertEquals(90, example().floorKey(95));
  }

  @Test
  public void floorKeyCanBeFoundDeepOnTheRight() {
    assertEquals(75, example().floorKey(78));
  }

  @Test
  public void ceilingKeyOfAMissingKeyIsTheNextKeyAbove() {
    assertEquals(60, example().ceilingKey(55));
  }

  @Test
  public void ceilingKeyOfAPresentKeyIsTheKeyItself() {
    assertEquals(40, example().ceilingKey(40));
  }

  @Test
  public void ceilingKeyAboveEveryKeyIsNull() {
    assertNull(example().ceilingKey(95));
  }

  @Test
  public void ceilingKeyBelowEveryKeyIsTheSmallestKey() {
    assertEquals(20, example().ceilingKey(10));
  }

  @Test
  public void keysBetweenReturnsTheKeysInRangeInOrder() {
    assertKeys(example().keysBetween(55, 78), 60, 70, 75);
  }

  @Test
  public void keysBetweenIncludesBothEnds() {
    assertKeys(example().keysBetween(30, 60), 30, 40, 50, 60);
  }

  @Test
  public void keysBetweenWithNoKeyInRangeIsEmpty() {
    assertKeys(example().keysBetween(41, 49));
  }

  @Test
  public void keysBetweenCoveringEveryKeyReturnsThemAll() {
    assertKeys(example().keysBetween(0, 100), 20, 30, 40, 50, 60, 70, 75, 80, 90);
  }

  @Test
  public void keysBetweenWithEqualEndsReturnsThatKey() {
    assertKeys(example().keysBetween(75, 75), 75);
  }

  @Test
  public void keysBetweenWithLoAboveHiThrows() {
    try {
      example().keysBetween(60, 50);
      fail("expected IllegalArgumentException when lo is greater than hi");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void floorKeyOfNullThrows() {
    try {
      example().floorKey(null);
      fail("expected IllegalArgumentException for a null key");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void ceilingKeyOfNullThrows() {
    try {
      example().ceilingKey(null);
      fail("expected IllegalArgumentException for a null key");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void keysBetweenWithNullLoThrows() {
    try {
      example().keysBetween(null, 50);
      fail("expected IllegalArgumentException for a null lo");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void keysBetweenWithNullHiThrows() {
    try {
      example().keysBetween(50, null);
      fail("expected IllegalArgumentException for a null hi");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  // Builds the problem's example tree: 50, with 30 (20, 40) on the left and
  // 70 (60, 80 (75, 90)) on the right.
  private static OrderedTreeMap<Integer, String> example() {
    OrderedTreeMap<Integer, String> map = new OrderedTreeMap<>();
    int[] keys = {50, 30, 70, 20, 40, 60, 80, 75, 90};
    for (int key : keys) {
      map.put(key, "v" + key);
    }
    return map;
  }

  private static void assertKeys(List<Integer> actual, int... expected) {
    assertEquals(expected.length, actual.size());
    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], actual.get(i));
    }
  }
}
