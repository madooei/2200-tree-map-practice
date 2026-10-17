package practice;

import map.Map;

/**
 * A RankedMap is a Map whose keys have a natural order, and which can find a
 * key by its position in that order.
 *
 * @param <K> the type of keys in this map.
 * @param <V> the type of values in this map.
 */
public interface RankedMap<K extends Comparable<K>, V> extends Map<K, V> {

  /**
   * Returns the k-th smallest key in this map, counting from 1.
   *
   * @param k the position of the key in ascending order, from 1 to size().
   * @return the k-th smallest key.
   * @throws IllegalArgumentException if k is less than 1 or greater than size().
   */
  K select(int k);

  /**
   * Returns the number of keys in this map that are smaller than the given
   * key. The key does not have to be in the map.
   *
   * @param key the key to rank.
   * @return the number of keys smaller than key.
   * @throws IllegalArgumentException if the key is null.
   */
  int rank(K key);
}
