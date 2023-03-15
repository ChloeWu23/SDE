package ic.doc;

import java.util.*;

public class RecentlyUsedList<E> {
  private List<E> items;

  public RecentlyUsedList() {
    items = new ArrayList<E>();
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public void add(E newItem) {
    if (items.contains(newItem)) {
      items.remove(newItem);
    }
    items.add(0, newItem);
  }

  public E retrive(int i) {
    return items.get(i);
  }

  public int size() {
    return items.size();
  }
}
