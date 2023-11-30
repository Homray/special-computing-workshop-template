package ru.spbu.apcyb.svp.tasks.task2;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/** Клссс очереди с имплементированным интерфейсом java.util.Queue*/
public class OwnQueue<E> implements Queue<E> {
  private final DoublyLinkedList<E> queue;

  public OwnQueue() {
    queue = new DoublyLinkedList<>();
  }


  @Override
  public int size() {
    return queue.size();
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  @Override
  public boolean add(E o) {
    return queue.add(o);
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public E remove() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public E element() {
    if (queue.isEmpty()) {
      throw new NoSuchElementException();
    }
    return queue.get(0);
  }



  @Override
  public boolean contains(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<E> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean offer(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }


  @Override
  public E poll() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }


  @Override
  public E peek() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}