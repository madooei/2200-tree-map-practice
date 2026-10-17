package practice;

import java.lang.reflect.Field;
import map.Map;
import map.MapTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Runs the Map contract suite against RankedTreeMap, then checks select, rank,
 * and the cached subtree sizes.
 */
public class RankedTreeMapTest extends MapTest {

  @Override
  protected Map<String, Integer> createMap() {
    return new RankedTreeMap<>();
  }

  @Test
  public void selectFindsEveryPosition() {
    RankedTreeMap<Integer, String> map = example();
    int[] sorted = {20, 30, 40, 50, 60, 70, 75, 80, 90};
    for (int k = 1; k <= sorted.length; k++) {
      assertEquals(sorted[k - 1], map.select(k), "k = " + k);
    }
  }

  @Test
  public void selectBelowOneThrows() {
    try {
      example().select(0);
      fail("expected IllegalArgumentException for k = 0");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void selectAboveSizeThrows() {
    try {
      example().select(10);
      fail("expected IllegalArgumentException for k greater than size()");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void rankOfAPresentKeyCountsTheKeysBelowIt() {
    assertEquals(3, example().rank(50));
  }

  @Test
  public void rankOfAMissingKeyCountsTheKeysBelowIt() {
    assertEquals(6, example().rank(72));
  }

  @Test
  public void rankBelowEveryKeyIsZero() {
    assertEquals(0, example().rank(5));
  }

  @Test
  public void rankAboveEveryKeyIsTheSize() {
    assertEquals(9, example().rank(99));
  }

  @Test
  public void rankOfTheKthKeyIsKMinusOne() {
    RankedTreeMap<Integer, String> map = example();
    int[] ranks = new int[map.size()];
    for (int k = 1; k <= map.size(); k++) {
      ranks[k - 1] = map.rank(map.select(k));
    }
    assertArrayEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8}, ranks);
  }

  @Test
  public void overwritingAKeyKeepsTheSize() {
    RankedTreeMap<Integer, String> map = example();
    map.put(40, "new");
    assertEquals(9, map.size());
    assertSizesCorrect(readField(map, "root"));
  }

  @Test
  public void cachedSizesStayCorrectThroughManyPuts() {
    RankedTreeMap<Integer, String> map = scrambledMap();
    assertSizesCorrect(readField(map, "root"));
  }

  @Test
  public void cachedSizesStayCorrectThroughManyRemoves() {
    RankedTreeMap<Integer, String> map = scrambledMap();
    for (int key = 0; key < 101; key += 3) {
      map.remove(key);
    }
    assertSizesCorrect(readField(map, "root"));
  }

  @Test
  public void sizeCountsTheKeysAfterManyRemoves() {
    RankedTreeMap<Integer, String> map = scrambledMap();
    for (int key = 0; key < 101; key += 3) {
      map.remove(key);
    }
    assertEquals(101 - 34, map.size());
  }

  // Puts the keys 0 to 100 in a scrambled order, with some keys put twice.
  private static RankedTreeMap<Integer, String> scrambledMap() {
    RankedTreeMap<Integer, String> map = new RankedTreeMap<>();
    for (int i = 0; i < 200; i++) {
      int key = (i * 37) % 101;
      map.put(key, "v" + key);
    }
    return map;
  }

  // Builds the example tree: 50, with 30 (20, 40) on the left and
  // 70 (60, 80 (75, 90)) on the right.
  private static RankedTreeMap<Integer, String> example() {
    RankedTreeMap<Integer, String> map = new RankedTreeMap<>();
    int[] keys = {50, 30, 70, 20, 40, 60, 80, 75, 90};
    for (int key : keys) {
      map.put(key, "v" + key);
    }
    return map;
  }

  // Reads a private field of an object by reflection.
  private static Object readField(Object object, String name) {
    try {
      Field field = object.getClass().getDeclaredField(name);
      field.setAccessible(true);
      return field.get(object);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  // Checks that every node in the subtree rooted at node caches its correct
  // size, and returns that size.
  private static int assertSizesCorrect(Object node) {
    if (node == null) {
      return 0;
    }
    int size = 1 + assertSizesCorrect(readField(node, "left"))
        + assertSizesCorrect(readField(node, "right"));
    assertEquals(size, (int) (Integer) readField(node, "size"), "stale size at " + readField(node, "key"));
    return size;
  }
}
