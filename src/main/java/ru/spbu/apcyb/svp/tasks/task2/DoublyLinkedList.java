package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**  Класс двусвязного линейного списка с имплементированным интерфейсом java.util.List*/
public class DoublyLinkedList<E> implements List<E> {

  private int size = 0;
  private Node head; // голова списка (является 1ым элементом, еслм список непуст)
  private Node tail; // хвост списка (является последним элементом, еслм список непуст)

  /* Узел списка */
  private class Node {
    E value; // значение лежащее в узле
    Node next; // ссылка на следующий элемент
    Node prev; // ссылка на предыдущий элемент

    Node(E data, Node next, Node prev) {
      this.value = data;
      this.next = next;
      this.prev = prev;
    }
  }


  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(Object o) {
    if (size != 0) {
      Node it = head;
      for (int i = 0; i < size; ++i) {
        if (it.value.equals(o)) {
          return true;
        }
        it = it.next;
      }
    }
    return false;
  }

  @Override
  public boolean add(E e) {
    if (size == 0) {
      head = new Node(e, null, null);
      tail = head;
    } else {
      Node newNode = new Node(e, null, tail);
      tail.next = newNode;
      tail = newNode;
    }

    ++size;
    return true;
  }

  @Override
  public void add(int index, E element) {
    indexCheck(index, size);

    if (size == 0) {
      this.add(element);
    } else {
      if (index == 0) {
        Node newNode = new Node(element, head, null);
        head.prev = newNode;
        head = newNode;
      } else if (index == size) {
        Node newNode = new Node(element, null, tail);
        tail.next = newNode;
        tail = newNode;
      } else {
        Node it = head;
        for (int i = 0; i < index; ++i) {
          it = it.next;
        }
        Node newNode = new Node(element, it, it.prev);
        it.prev.next = newNode;
        it.prev = newNode;
      }
      ++size;
    }
  }


  @Override
  public E get(int index) {
    indexCheck(index, size - 1);

    Node it = head;
    for (int i = 0; i < index; ++i) {
      it = it.next;
    }
    return it.value;
  }

  @Override
  public E remove(int index) {
    indexCheck(index, size - 1);

    Node it;
    if (size == 1) {
      it = head;
      head = null;
      tail = null;

    } else if (index == 0) {
      it = head;
      head = head.next;
      head.prev = null;
      it.next = null;

    } else if (index == size - 1) {
      it = tail;
      tail = tail.prev;
      tail.next = null;
      it.prev = null;

    } else {
      it = head;
      for (int i = 0; i < index; ++i) {
        it = it.next;
      }
      it.prev.next = it.next;
      it.next.prev = it.prev;
    }

    --size;
    return it.value;
  }


  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  /** Проверяет, находится ли число index между 0 и maxValue.*/
  public void indexCheck(int index, int maxValue) throws IndexOutOfBoundsException {
    if (index < 0 || index > maxValue) {
      throw new IndexOutOfBoundsException(
          "Указан индекс, выходящий за пределы допустимых значений");
    }
  }


  @Override
  public Iterator<E> iterator() {
    throw new UnsupportedOperationException("Метод iterator не переопределен");
  }

  @Override
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод toArray не переопределен");
  }

  @Override
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод toArray не переопределен");
  }

  @Override
  public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод containsAll не переопределен");
  }

  @Override
  public boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод addAll не переопределен");
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод addAll не переопределен");
  }

  @Override
  public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод removeAll не переопределен");
  }

  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод retainAll не переопределен");
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод clear не переопределен");
  }

  @Override
  public E set(int index, E element) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод set не переопределен");
  }

  @Override
  public int indexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод indexOf не переопределен");
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод lastIndexOf не переопределен");
  }

  @Override
  public ListIterator<E> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод listIterator не переопределен");
  }

  @Override
  public ListIterator<E> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод listIterator не переопределен");
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Метод subList не переопределен");
  }

}
