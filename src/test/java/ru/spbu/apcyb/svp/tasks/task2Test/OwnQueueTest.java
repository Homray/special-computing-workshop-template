package ru.spbu.apcyb.svp.tasks.task2Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spbu.apcyb.svp.tasks.task2.OwnQueue;

class OwnQueueTest {

  private OwnQueue<Integer> queue;

  @BeforeEach
  public void setUp() {
    queue = new OwnQueue<>();
  }


  @Test
  void testThatNewQueueIsEmpty() {
    assertEquals(0, new OwnQueue<>().size());
  }
  @Test
  void addTest() {
    OwnQueue<Integer> queue = new OwnQueue<>();

    assertTrue(queue.add(0));
    assertEquals(1, queue.size());

    assertTrue(queue.add(1));
    assertEquals(2, queue.size());
  }

  @Test
  void isEmptyTest_empty() {
    assertTrue(queue.isEmpty());
  }

  @Test
  void isEmptyTest_notEmpty() {
    queue.add(0);
    assertFalse(queue.isEmpty());
  }

  @Test
  void elementTest_empty() {
    assertThrows(NoSuchElementException.class, () -> queue.element());
  }

  @Test
  void elementTest_notEmpty() {
    queue.add(0);
    assertEquals(0, queue.element());

    queue.add(1);
    assertEquals(0, queue.element());
  }




  @Test
  void iteratorTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.iterator());
  }

  @Test
  void toArrayTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.toArray());
    assertThrows(UnsupportedOperationException.class, () -> queue.toArray(new Object[0]));
  }

  @Test
  void removeByObjectTest_UnsupportedOperationException() {
    OwnQueue<Object> queue1 = new OwnQueue<>();
    assertThrows(UnsupportedOperationException.class, () -> queue1.remove("asd"));
  }

  @Test
  void containsAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> queue.containsAll(s));
  }

  @Test
  void addAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> queue.addAll(s));
  }

  @Test
  void removeAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> queue.removeAll(s));
  }

  @Test
  void retainAllTest_UnsupportedOperationException() {
    List<Integer> s = List.of(1, 2);
    assertThrows(UnsupportedOperationException.class, () -> queue.retainAll(s));
  }

  @Test
  void clearTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.clear());
  }

  @Test
  void containsTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.contains(0));
  }

  @Test
  void offerTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.offer(0));
  }

  @Test
  void removeTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.remove());
  }

  @Test
  void pollTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.poll());
  }

  @Test
  void peekTest_UnsupportedOperationException() {
    assertThrows(UnsupportedOperationException.class, () -> queue.peek());
  }

}