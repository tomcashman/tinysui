/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core.container;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.UiElement;

/**
 * Represents the root of the UI tree
 * 
 * @author Thomas Cashman
 * @author Keith Cummins
 */
public class RootUiElement extends UiElement {
	private UiElement m_focusElement, m_dragElement;

	/**
	 * Constructor
	 * 
	 * @param width
	 *            Screen width
	 * @param height
	 *            Screen height
	 */
	public RootUiElement(GameContainer container) {
		super(0, 0, container.getWidth(), container.getHeight());
		m_focusElement = null;
		m_dragElement = null;
		m_font = container.getDefaultFont();
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if (m_children != null) {
			for (int i = 0; i < m_children.size(); i++) {
				UiElement element = m_children.get(i);
				element.updateRenderPosition(this);
				element.update(gc, delta);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		if (m_children != null) {
			for (int i = 0; i < m_children.size(); i++) {
				UiElement element = m_children.get(i);
				element.updateRenderPosition(this);
				element.update(gc, game, delta);
			}
		}
	}

	@Override
	public UiElement grabsFocus(float mouseX, float mouseY) {
		if (m_focusElement != null) {
			m_focusElement.setFocus(false);
		}
		m_focusElement = null;

		if (m_children != null) {
			for (int i = 0; i < m_children.size(); i++) {
				UiElement focus = m_children.get(i).grabsFocus(mouseX, mouseY);
				if (focus != null) {
					m_focusElement = focus;
					return focus;
				}
			}
		}
		return null;
	}

	@Override
	public void handleInput(int key, char c) {
		if (m_focusElement != null) {
			m_focusElement.handleInput(key, c);
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if (m_dragElement != null) {
			m_dragElement.setX(m_dragElement.getX() + (newx - oldx));
			m_dragElement.setY(m_dragElement.getY() + (newy - oldy));
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (button == Input.MOUSE_LEFT_BUTTON) {
			UiElement element = this.grabsFocus(x, y);
			if (element != null && element.isDraggable()) {
				m_dragElement = element;
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (m_focusElement != null) {
			m_focusElement.setHasFocus(false);
		}
		if (m_dragElement != null) {
			m_dragElement.setHasFocus(false);
			m_dragElement = null;
		}
		if (button == Input.MOUSE_LEFT_BUTTON) {
			m_focusElement = this.grabsFocus(x, y);
		}
	}

	@Override
	public void mouseWheelMoved(int change) {
	}

	/**
	 * Returns true if the UI has focus
	 */
	public boolean hasFocus() {
		return m_focusElement != null || m_dragElement != null;
	}

	/**
	 * Returns the element currently handling keyboard input
	 * 
	 * @return the focusElement
	 */
	public UiElement getFocusElement() {
		return m_focusElement;
	}

	/**
	 * Returns the element currently being dragged
	 * 
	 * @return the dragElement
	 */
	public UiElement getDragElement() {
		return m_dragElement;
	}
}
