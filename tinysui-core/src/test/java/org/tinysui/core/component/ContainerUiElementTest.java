/**
 * Copyright 2012 TinySUI
 * License: https://github.com/thebattlebard/tinysui/blob/master/license.txt
 */
package org.tinysui.core.component;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.tinysui.core.listener.KeyListener;

/**
 * Implements unit tests for {@link ContainerUiElement}
 * 
 * @author Thomas Cashman
 */
public class ContainerUiElementTest {
	private ContainerUiElement element;
	private UiElement child;
	private Random random;
	
	@Before
	public void setup() {
		random = new Random();
		
		element = new ContainerUiElement() {
			@Override
			public void update(int delta) {}

			@Override
			public void render() {}

			@Override
			public void mouseHover(int x, int y) {}

			@Override
			public Draggable mousePressed(int button, int x, int y) {
				return null;
			}

			@Override
			public KeyListener mouseReleased(int button, int x, int y) {
				return null;
			}
		};
		child = new UiElement() {
			@Override
			public void update(int delta) {}

			@Override
			public void render() {}

			@Override
			public void mouseHover(int x, int y) {}

			@Override
			public Draggable mousePressed(int button, int x, int y) {
				return null;
			}

			@Override
			public KeyListener mouseReleased(int button, int x, int y) {
				return null;
			}
		};
		child.setX(random.nextInt());
		child.setY(random.nextInt());
	}
	
	@Test
	public void testChildRenderXMovesWithX() {
		int x = random.nextInt();
		element.setX(x);
		
		int childX = random.nextInt();
		child.setX(childX);
		
		element.addChild(child);
		Assert.assertEquals(x, element.getX());
		Assert.assertEquals(element.getX() + child.getX(), child.getRenderX());
		
		x = random.nextInt();
		element.setX(x);
		Assert.assertEquals(x, element.getX());
		Assert.assertEquals(element.getX() + child.getX(), child.getRenderX());
	}
	
	@Test
	public void testChildRenderYMovesWithY() {
		int y = random.nextInt();
		element.setY(y);
		
		int childY = random.nextInt();
		child.setY(childY);
		
		element.addChild(child);
		Assert.assertEquals(y, element.getY());
		Assert.assertEquals(element.getY() + child.getY(), child.getRenderY());
		
		y = random.nextInt();
		element.setY(y);
		Assert.assertEquals(y, element.getY());
		Assert.assertEquals(element.getY() + child.getY(), child.getRenderY());
	}
	
	@Test
	public void testAddChild() {
		Assert.assertEquals(0, element.getChildCount());
		element.addChild(child);
		Assert.assertEquals(1, element.getChildCount());
		Assert.assertTrue(child.getParent().compareTo(element) == 0);
		Assert.assertTrue(child.compareTo(element.getChild(0)) == 0);
		element.removeChild(0);
		Assert.assertEquals(0, element.getChildCount());
	}
}
