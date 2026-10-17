package list;

/**
 * A List is an ordered, indexed collection of elements. Each element keeps the
 * position it is given: the element at index 0 comes before the one at index 1,
 * and so on. Valid indices run from 0 to size() - 1.
 *
 * @param <T> the type of element stored in this list.
 */
public interface List<T> {

  /**
   * Append an element to the end of this list.
   *
   * @param element the value to add.
   */
  void add(T element);

  /**
   * Remove the first occurrence of a value, shifting any later elements one
   * position earlier to close the gap.
   *
   * @param element the value to remove.
   * @return true if a value was removed, false if it was not found.
   */
  boolean remove(T element);

  /**
   * Replace the element at the given index with a new value.
   *
   * @param index the position to write to.
   * @param element the value to store there.
   * @throws IndexOutOfBoundsException if index < 0 or index >= size().
   */
  void set(int index, T element);

  /**
   * Return the element stored at the given index.
   *
   * @param index the position to read from.
   * @return the element at that position.
   * @throws IndexOutOfBoundsException if index < 0 or index >= size().
   */
  T get(int index);

  /**
   * Report whether a value is present in this list.
   *
   * @param element the value to look for.
   * @return true if the value is in the list, false otherwise.
   */
  boolean contains(T element);

  /**
   * Find the position of the first occurrence of a value.
   *
   * @param element the value to look for.
   * @return the index of the first occurrence, or -1 if it is not present.
   */
  int indexOf(T element);

  /**
   * Report how many elements are in this list.
   *
   * @return the number of elements; never negative.
   */
  int size();
}
