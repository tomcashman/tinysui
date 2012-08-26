/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An ArrayList that can be accessed concurrently
 * 
 * @author Thomas Cashman
 * 
 * @param <T>
 *            Type of items the list stores
 */
public class ConcurrentArrayList<T extends Comparable<T>> {
	private ArrayList<T> m_list;
	private Lock m_lock;

	/**
	 * Constructor
	 */
	public ConcurrentArrayList() {
		m_list = new ArrayList<T>();
		m_lock = new ReentrantLock();
	}

	/**
	 * Returns item from list
	 * 
	 * @param index
	 *            Index of item in the list
	 * @return Item of type T
	 */
	public T get(int index) {
		T result = null;
		m_lock.lock();
		result = m_list.get(index);
		m_lock.unlock();
		return result;
	}

	/**
	 * Adds an item to the list
	 * 
	 * @param item
	 */
	public void add(T item) {
		m_lock.lock();
		m_list.add(item);
		m_lock.unlock();
	}

	/**
	 * Removes an item from the list
	 * 
	 * @param item
	 * @return
	 */
	public boolean remove(T item) {
		boolean result = false;
		m_lock.lock();
		result = m_list.remove(item);
		m_lock.unlock();
		return result;
	}

	/**
	 * Removes an item from the list
	 * 
	 * @param index The item index
	 * @return The removed item
	 */
	public T removeAt(int index) {
		T result = null;
		m_lock.lock();
		result = m_list.remove(index);
		m_lock.unlock();
		return result;
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return Integere size of list
	 */
	public int size() {
		int result = 0;
		m_lock.lock();
		result = m_list.size();
		m_lock.unlock();
		return result;
	}

	/**
	 * Sorts the list using a comparator
	 * 
	 * @param c
	 */
	public void sort(Comparator<T> c) {
		m_lock.lock();
		Collections.sort(m_list, c);
		m_lock.unlock();
	}

	/**
	 * Sorts the list using the default comparer
	 */
	public void sort() {
		m_lock.lock();
		Collections.sort(m_list);
		m_lock.unlock();
	}

	/**
	 * Locks the list to the current thread
	 */
	public void lock() {
		m_lock.lock();
	}

	/**
	 * Unlocks the list from the current thread
	 */
	public void unlock() {
		m_lock.unlock();
	}
}
