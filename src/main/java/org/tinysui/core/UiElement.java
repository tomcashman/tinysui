/**
 * Copyright 2012 TinySUI
 * See http://www.tinysui.org for the latest license
 */
package org.tinysui.core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.tinysui.core.util.ConcurrentArrayList;

/**
 * Represents a UI element
 * 
 * @author Thomas Cashman
 * @author Keith Cummins
 */
public abstract class UiElement implements Comparable<UiElement> {
	protected volatile int m_uid = 0;
	protected volatile float m_x = 0;
	protected volatile float m_y = 0;
	protected volatile float m_width = 0;
	protected volatile float m_height = 0;
	private volatile float m_maxX = 0;
	private volatile float m_maxY = 0;
	protected volatile float m_renderX = 0;
	protected volatile float m_renderY = 0;
	protected volatile float m_renderMaxX = 0;
	protected volatile float m_renderMaxY = 0;

	private volatile float m_boundX = 0;
	private volatile float m_boundY = 0;
	private volatile float m_boundMaxX = 0;
	private volatile float m_boundMaxY = 0;

	protected volatile boolean m_hasFocus = false;
	protected volatile boolean m_isDraggable;
	protected volatile boolean m_isVisible = true;
	protected ConcurrentArrayList<UiElement> m_children;

	protected Font m_font;

	private static int UID_COUNTER = 0;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            X coordinate (relative to parent)
	 * @param y
	 *            Y coordinate (relative to parent)
	 * @param width
	 *            Element width
	 * @param height
	 *            Element height
	 */
	public UiElement(float x, float y, float width, float height) {
		m_uid = UID_COUNTER;
		if (UID_COUNTER == Integer.MAX_VALUE) {
			UID_COUNTER = Integer.MIN_VALUE;
		} else {
			UID_COUNTER++;
		}

		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		m_maxX = m_x + m_width;
		m_maxY = m_y + m_height;
		m_renderX = m_x;
		m_renderY = m_y;
		m_renderMaxX = m_maxX;
		m_renderMaxY = m_maxY;

		m_boundX = 0;
		m_boundY = 0;
		m_boundMaxX = 1920;
		m_boundMaxY = 1080;

		m_isDraggable = false;
		m_children = new ConcurrentArrayList<UiElement>();
	}

	/**
	 * Updates the element
	 * 
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		if (m_children != null) {
			for (int i = 0; i < m_children.size(); i++) {
				UiElement element = m_children.get(i);
				element.update(gc, delta);
			}
		}
	}

	/**
	 * Updates the element
	 * 
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, StateBasedGame game, int delta) {
		if (m_children != null) {
			for (int i = 0; i < m_children.size(); i++) {
				UiElement element = m_children.get(i);
				if (element.getFont() == null)
					element.setFont(m_font);

				element.update(gc, game, delta);
			}
		}
	}

	/**
	 * Renders the UI element
	 * 
	 * @param gc
	 *            The game container
	 * @param g
	 *            Graphics
	 */
	public void render(GameContainer gc, Graphics g) {
		if (m_isVisible) {
			Color tempColor = g.getColor();
			/* Render all children of this element */
			if (m_children != null) {
				for (int i = 0; i < m_children.size(); i++) {
					UiElement element = m_children.get(i);
					element.render(gc, g);
				}
			}
			g.setColor(tempColor);
		}
	}

	/**
	 * Renders the UI element
	 * 
	 * @param gc
	 *            The game container
	 * @param game
	 *            The game itself
	 * @param g
	 *            Graphics
	 */
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		if (m_isVisible) {
			Color tempColor = g.getColor();
			/* Render all children of this element */
			if (m_children != null) {
				for (int i = 0; i < m_children.size(); i++) {
					UiElement element = m_children.get(i);
					element.render(gc, game, g);
				}
			}
			g.setColor(tempColor);
		}
	}

	/**
	 * Handles keyboard input Note: Can be overridden by child classes
	 * 
	 * @param key
	 *            Key ID which was pressed
	 * @param c
	 *            Character which was pressed
	 */
	public void handleInput(int key, char c) {
	}

	/**
	 * Handles mouse movement
	 * 
	 * @param oldx
	 * @param oldy
	 * @param newx
	 * @param newy
	 */
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	/**
	 * Handles mouse button presses
	 * 
	 * @param button
	 * @param x
	 * @param y
	 */
	public void mousePressed(int button, int x, int y) {
	}

	/**
	 * Handles mouse button releases
	 * 
	 * @param button
	 * @param x
	 * @param y
	 */
	public void mouseReleased(int button, int x, int y) {
	}

	/**
	 * Handles mouse scroll wheel
	 * 
	 * @param change
	 */
	public void mouseWheelMoved(int change) {
	}

	/**
	 * Returns the UI element which has grabbed focus on mouse button release
	 * Can be this element or a child element
	 * 
	 * @param mouseX
	 *            Mouse X coordinate
	 * @param mouseY
	 *            Mouse Y coordinate
	 * @return Null if no element has grabbed focus
	 */
	public abstract UiElement grabsFocus(float mouseX, float mouseY);

	/**
	 * @param hasFocus
	 *            the hasFocus to set
	 */
	public void setHasFocus(boolean hasFocus) {
		m_hasFocus = hasFocus;
	}

	/**
	 * Adds a child element
	 * 
	 * @param element
	 */
	public void addChild(UiElement element) {
		if (element.getFont() == null)
			element.setFont(m_font);

		if (m_children == null)
			m_children = new ConcurrentArrayList<UiElement>();
		m_children.add(element);

		/* Update render coordinates */
		element.updateRenderPosition(this);
	}

	/**
	 * Removes a child element
	 * 
	 * @param element
	 */
	public void removeChild(UiElement element) {
		if (m_children != null) {
			m_children.remove(element);
		}
	}

	/**
	 * Returns the children of the UI element
	 * 
	 * @return
	 */
	public ConcurrentArrayList<UiElement> getChildren() {
		return m_children;
	}

	/**
	 * Returns true if the element contains a given screen coordinate
	 * 
	 * @param xp
	 *            Screen X coordinate
	 * @param yp
	 *            Screen Y coordinate
	 * @return True if coordinate is inside of the element
	 */
	public boolean contains(float xp, float yp) {
		if (xp < m_renderX)
			return false;
		if (yp < m_renderY)
			return false;
		if (xp > m_renderMaxX)
			return false;
		if (yp > m_renderMaxY)
			return false;
		return true;
	}

	/**
	 * Updates the render position of the element to be relative to its parent
	 * 
	 * @param parent
	 */
	public void updateRenderPosition(UiElement parent) {
		if (parent != null) {
			m_renderX = parent.getRenderX() + m_x;
			m_renderY = parent.getRenderY() + m_y;
			m_renderMaxX = parent.getRenderX() + m_maxX;
			m_renderMaxY = parent.getRenderY() + m_maxY;

			for (int i = 0; i < m_children.size(); i++) {
				m_children.get(i).updateRenderPosition(this);
			}
		}
	}

	/**
	 * Update the render position of the element to be relative to coordinates
	 * 
	 * @param parentX
	 *            Parent render X coordinate
	 * @param parentY
	 *            Parent render Y coordinate
	 */
	public void updateRenderPosition(float parentX, float parentY) {
		m_renderX = parentX + m_x;
		m_renderY = parentY + m_y;
		m_renderMaxX = parentX + m_maxX;
		m_renderMaxY = parentY + m_maxY;

		for (int i = 0; i < m_children.size(); i++) {
			m_children.get(i).updateRenderPosition(this);
		}
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return m_x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(float x) {
		if (x >= m_boundX && x + m_width <= m_boundMaxX) {
			m_x = x;
			m_maxX = m_x + m_width;
		}
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return m_y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(float y) {
		if (y >= m_boundY && y + m_height <= m_boundMaxY) {
			m_y = y;
			m_maxY = m_y + m_height;
		}
	}

	/**
	 * @return the renderX
	 */
	public float getRenderX() {
		return m_renderX;
	}

	/**
	 * @param renderX
	 *            the renderX to set
	 */
	public void setRenderX(float renderX) {
		m_renderX = renderX;
	}

	/**
	 * @return the renderY
	 */
	public float getRenderY() {
		return m_renderY;
	}

	/**
	 * @param renderY
	 *            the renderY to set
	 */
	public void setRenderY(float renderY) {
		m_renderY = renderY;
	}

	/**
	 * @return the boundX
	 */
	public float getBoundX() {
		return m_boundX;
	}

	/**
	 * @param boundX
	 *            the boundX to set
	 */
	public void setBoundX(float boundX) {
		m_boundX = boundX;
	}

	/**
	 * @return the boundY
	 */
	public float getBoundY() {
		return m_boundY;
	}

	/**
	 * @param boundY
	 *            the boundY to set
	 */
	public void setBoundY(float boundY) {
		m_boundY = boundY;
	}

	/**
	 * @return the boundMaxX
	 */
	public float getBoundMaxX() {
		return m_boundMaxX;
	}

	/**
	 * @param boundMaxX
	 *            the boundMaxX to set
	 */
	public void setBoundMaxX(float boundMaxX) {
		m_boundMaxX = boundMaxX;
	}

	/**
	 * @return the boundMaxY
	 */
	public float getBoundMaxY() {
		return m_boundMaxY;
	}

	/**
	 * @param boundMaxY
	 *            the boundMaxY to set
	 */
	public void setBoundMaxY(float boundMaxY) {
		m_boundMaxY = boundMaxY;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return m_width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(float width) {
		m_width = width;
		m_maxX = m_x + m_width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return m_height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(float height) {
		m_height = height;
		m_maxY = m_y + m_height;
	}

	/**
	 * @return the isDraggable
	 */
	public boolean isDraggable() {
		return m_isDraggable;
	}

	/**
	 * @param isDraggable
	 *            the isDraggable to set
	 */
	public void setDraggable(boolean isDraggable) {
		m_isDraggable = isDraggable;
	}

	/**
	 * @return the hasFocus
	 */
	public boolean hasFocus() {
		return m_hasFocus;
	}

	/**
	 * @param hasFocus
	 *            the hasFocus to set
	 */
	public void setFocus(boolean hasFocus) {
		m_hasFocus = hasFocus;
	}

	/**
	 * Returns the font for the UI element
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return m_font;
	}

	/**
	 * Sets the font of the UI element
	 * 
	 * @param font
	 *            the font to set
	 */
	public void setFont(Font font) {
		m_font = font;

		/* Ensure children have a font */
		if (m_font != null && this.getChildren() != null) {
			for (int i = 0; i < this.getChildren().size(); i++) {
				if (this.getChildren().get(i).getFont() == null) {
					this.getChildren().get(i).setFont(m_font);
				}
			}
		}
	}

	/**
	 * True if the element is visible
	 * 
	 * @return the isVisible
	 */
	public boolean isVisible() {
		return m_isVisible;
	}

	/**
	 * Sets if the element is visible
	 * 
	 * @param isVisible
	 *            the isVisible to set
	 */
	public void setVisible(boolean isVisible) {
		m_isVisible = isVisible;
	}

	/**
	 * Returns the globally unique ID for this element
	 * 
	 * @return
	 */
	public int getUid() {
		return m_uid;
	}

	/**
	 * Implements comparable interface
	 */
	public int compareTo(UiElement element) {
		if (this.getUid() < element.getUid())
			return -1;
		else if (this.getUid() > element.getUid())
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "UiElement "
				+ m_uid
				+ " ["
				+ getClass().getName().substring(
						getClass().getName().lastIndexOf(".")) + "]";
	}
}