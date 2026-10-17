package practice;

import list.List;
import map.Map;

/**
 * An OrderedMap is a Map whose keys have a natural order, and which answers
 * questions about that order.
 *
 * @param <K> the type of keys in this map.
 * @param <V> the type of values in this map.
 */
public interface OrderedMap<K extends Comparable<K>, V> extends Map<K, V> {

  /**
   * Returns the largest key in this map that is less than or equal to the
   * given key, or null if there is no such key.
   *
   * @param key the key to look below.
   * @return the largest key at or below key, or null if there is none.
   * @throws IllegalArgumentException if the key is null.
   */
  K floorKey(K key);

  /**
   * Returns the smallest key in this map that is greater than or equal to the
   * given key, or null if there is no such key.
   *
   * @param key the key to look above.
   * @return the smallest key at or above key, or null if there is none.
   * @throws IllegalArgumentException if the key is null.
   */
  K ceilingKey(K key);

  /**
   * Returns every key in this map from lo to hi, both included, in ascending
   * order. The list is empty if no key falls in the range.
   *
   * @param lo the smallest key the range includes.
   * @param hi the largest key the range includes.
   * @return the keys from lo to hi, in ascending order.
   * @throws IllegalArgumentException if lo or hi is null, or lo is greater than hi.
   */
  List<K> keysBetween(K lo, K hi);
}
