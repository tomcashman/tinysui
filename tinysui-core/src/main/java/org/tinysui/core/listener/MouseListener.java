/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.listener;

import org.tinysui.core.component.Draggable;

/**
 * A common interface to objects that listen for mouse actions
 * 
 * @author Thomas Cashman
 */
public interface MouseListener {
	/**
	 * Called by the library when the mouse is hovering on screen
	 * 
	 * @param x
	 *            The on-screen X coordinate
	 * @param y
	 *            The on-screen Y coordiante
	 */
	public void mouseHover(int x, int y);

	/**
	 * Called by the library when a mouse button is pressed down
	 * 
	 * @param button
	 *            The mouse button which was pressed down
	 * @param x
	 *            The on-screen X coordinate of the mouse
	 * @param y
	 *            The on-screen Y coordinate of the mouse
	 * @return An instance of {@link Draggable} that is now being dragged or
	 *         null if none
	 */
	public Draggable mousePressed(int button, int x, int y);

	/**
	 * Called by the library when a mouse button is released
	 * 
	 * @param button
	 *            The mouse button which was pressed down
	 * @param x
	 *            The on-screen X coordinate of the mouse
	 * @param y
	 *            The on-screen Y coordinate of the mouse
	 * @return An instance of {@link KeyListener} which took focus or null if
	 *         none
	 */
	public KeyListener mouseReleased(int button, int x, int y);

	/**
	 * Sets the implementation of {@link ActionListener} to call when the
	 * element is clicked
	 * 
	 * @param listener
	 *            The implementation of {@link ActionListener} to be called
	 */
	public void onClick(ActionListener listener);

	/**
	 * Sets the implementation of {@link ActionListener} to call when the
	 * element is hovered over
	 * 
	 * @param listener
	 *            The implementation of {@link ActionListener} to be called
	 */
	public void onHover(ActionListener listener);
}
