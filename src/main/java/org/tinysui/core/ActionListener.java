/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core;

/**
 * An abstract class for action handling
 * 
 * @author Thomas Cashman
 * @author Keith Cummins
 */
public abstract class ActionListener {
	/**
	 * Handles an event from a UI source
	 * 
	 * @param source
	 */
	public abstract void handleEvent(UiElement source);
}
