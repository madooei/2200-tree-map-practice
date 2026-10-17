package list;

import java.util.Objects;

/**
 * An array-backed implementation of the List ADT.
 *
 * @param <T> the type of element stored in this list.
 */
public class ArrayList<T> implements List<T> {

  private T[] arr;
  private int size;

  // arr only ever holds T, so the cast is safe.
  @SuppressWarnings("unchecked")
  public ArrayList() {
    arr = (T[]) new Object[10];
    size = 0;
  }

  @Override
  public void add(T value) {
    if (size == arr.length) {
      grow();
    }
    arr[size] = value;
    size++;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return arr[index];
  }

  @Override
  public void set(int index, T value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    arr[index] = value;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean contains(T value) {
    return indexOf(value) != -1;
  }

  @Override
  public int indexOf(T value) {
    for (int i = 0; i < size; i++) {
      if (Objects.equals(arr[i], value)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean remove(T value) {
    int i = indexOf(value);
    if (i == -1) {
      return false;
    }
    removeAt(i);
    return true;
  }

  // Pre: index is a valid index.
  private void removeAt(int index) {
    for (int j = index; j < size - 1; j++) {
      arr[j] = arr[j + 1];
    }
    size--;
    arr[size] = null;
  }

  @SuppressWarnings("unchecked")
  private void grow() {
    // arr only ever holds T, so the cast is safe.
    T[] bigger = (T[]) new Object[arr.length * 2];
    for (int i = 0; i < size; i++) {
      bigger[i] = arr[i];
    }
    arr = bigger;
  }
}
