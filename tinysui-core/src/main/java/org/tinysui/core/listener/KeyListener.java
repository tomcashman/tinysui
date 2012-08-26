/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.listener;

/**
 * Implements a common interface to objects that can accept keyboard input
 * 
 * @author Thomas Cashman
 */
public interface KeyListener {
	/**
	 * Called by the library when a key is pressed down
	 * @param key The key pressed down
	 * @param c The character input associated with the key
	 */
	public void keyPressed(int key, char c);
	
	/**
	 * Called by the library when a key is released
	 * @param key The key that was released
	 * @param c The character input associated with the key
	 */
	public void keyReleased(int key, char c);
}
