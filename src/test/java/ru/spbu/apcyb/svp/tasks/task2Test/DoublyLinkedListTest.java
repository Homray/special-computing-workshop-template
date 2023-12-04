package ru.spbu.apcyb.svp.tasks.task2Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task2.DoublyLinkedList;

class DoublyLinkedListTest {

  private DoublyLinkedList<Integer> emptyList;
  private DoublyLinkedList<Integer> notEmptyList;

  @BeforeEach
  public void setUp() {
    emptyList = new DoublyLinkedList<>();
    notEmptyList = new DoublyLinkedList<>();
    notEmptyList.add(1);
    notEmptyList.add(2);
    notEmptyList.add(3);
    notEmptyList.add(4);
  }


  @Test
  void testThatNewListIsEmpty() {
    assertEquals(0, emptyList.size());
  }

  @Test
  void addTest_emptyList() {
    DoublyLinkedList<Object> list = new DoublyLinkedList<>();
    assertTrue(list.add(1));
  }

  @Test
  void addTest_notEmptyList() {
    DoublyLinkedList<Object> list = new DoublyLinkedList<>();
    list.add(1);
    assertTrue(list.add(2));
  }

  @Test
  void isEmptyTest_empty() {
    assertTrue(emptyList.isEmpty());
  }

  @Test
  void isEmptyTest_notEmpty() {
    assertFalse(notEmptyList.isEmpty());
  }

  @Test
  void containsTest_emptyList() {
    assertFalse(emptyList.contains(0));
  }

  @Test
  void containsTest_notContains() {
    assertFalse(notEmptyList.contains(0));
  }

  @Test
  void containsTest_contains() {
    assertTrue(notEmptyList.contains(2));
  }

  @Test
  void getTest_emptyList() {
    assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
  }

  @Test
  void getTest() {
    assertEquals(2, notEmptyList.get(1));
  }

  @Test
  void getTest_IndexOutOfBoundsException() {
    assertThrows(IndexOutOfBoundsException.class, () -> notEmptyList.get(-3));
    assertThrows(IndexOutOfBoundsException.class, () -> notEmptyList.get(99));
  }

  @Test
  void addByIndexTest() {
    emptyList.add(0, 4);
    assertEquals(1, emptyList.size());

    notEmptyList.add(0, 0);
    assertEquals(5, notEmptyList.size());

    notEmptyList.add(5, 0);
    assertEquals(6, notEmptyList.size());

    notEmptyList.add(4, 0);
    assertEquals(7, notEmptyList.size());
  }

  @Test
  void addByIndexTest_IndexOutOfBoundsException() {
    assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(-7, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> emptyList.add(7, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> notEmptyList.add(-7, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> notEmptyList.add(7, 0));
  }

  @Test
  void removeTest() {
    assertEquals(3, notEmptyList.remove(2));
    assertEquals(3, notEmptyList.size());

    assertEquals(1, notEmptyList.remove(0));
    assertEquals(2, notEmptyList.size());

    assertEquals(4, notEmptyList.remove(1));
    assertEquals(1, notEmptyList.size());

    assertEquals(2, notEmptyList.remove(0));
    assertEquals(0, notEmptyList.size());
  }

  @Test
  void removeTest_IndexOutOfBoundsException() {
    assertThrows(IndexOutOfBoundsException.class, () -> emptyList.remove(0));
    assertThrows(IndexOutOfBoundsException.class, () -> notEmptyList.remove(-7));
    assertThrows(IndexOutOfBoundsException.class, () -> notEmptyList.remove(10));

  }


  @Test
  void iteratorTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.iterator());
  }

  @Test
  void toArrayTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.toArray());
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.toArray(new Object[0]));
  }

  @Test
  void removeByObjectTest_UnsupportedOperationException() {
    DoublyLinkedList<Object> list = new DoublyLinkedList<>();
    assertThrows(UnsupportedOperationException.class, () -> list.remove("asd"));
  }

  @Test
  void containsAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.containsAll(s));
  }

  @Test
  void addAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.addAll(s));
  }

  @Test
  void addAllOnIndexTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.addAll(0, s));
  }

  @Test
  void removeAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.removeAll(s));
  }

  @Test
  void retainAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.retainAll(s));
  }

  @Test
  void clearTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.clear());
  }

  @Test
  void setAllTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.set(0, 5));
  }

  @Test
  void indexOfTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.indexOf(1));
  }

  @Test
  void lastIndexOfTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.lastIndexOf(1));
  }

  @Test
  void listIteratorTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.listIterator());
  }

  @Test
  void listIteratorOnIndexTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.listIterator(6));
  }

  @Test
  void subListTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> notEmptyList.subList(1, 9));
  }

}
