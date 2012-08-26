/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.listener;

import org.tinysui.core.component.UiElement;

/**
 * Implements a common interface for user implemented action handlers
 * 
 * @author Thomas Cashman
 */
public interface ActionListener {
	/**
	 * Called when the action occurs
	 * @param source The {@link UiElement} which triggered the action
	 */
	public void onAction(UiElement source);
}
