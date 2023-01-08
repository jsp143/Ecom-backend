package com.pvrschcms.pvrcinemaschdulernew.controller;

import java.awt.AWTException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Test {
	private static volatile boolean wPressed = false;
	public static void main(String[] args) {
		try {
			auto();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void auto() throws InterruptedException {
		
		long endTime = System.currentTimeMillis() + 1000;

		for (long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(2); stop > System.nanoTime();) {

			try {

				Robot r = new Robot();
				r.mouseMove(600, 323); // move cursor over top URL bar

				r.keyPress(KeyEvent.VK_UP);
				Thread.sleep(4000);
				r.keyRelease(KeyEvent.VK_UP);
				Thread.sleep(6000);
				r.keyPress(KeyEvent.VK_DOWN);
				Thread.sleep(4000);
				r.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(4000);
				// r.keyPress(KeyEvent.VK_SPACE); //fire spacebar event
				//System.out.println("Executed");
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
					
					@Override
					public boolean dispatchKeyEvent(KeyEvent ke) {
						synchronized (Test.class) {
							switch (ke.getID()) {
							case KeyEvent.KEY_PRESSED:
								if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
									System.out.println("pressed");
									wPressed = true;
								}
								break;

							case KeyEvent.KEY_RELEASED:
								if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
									wPressed = true;
								}
								break;
							}
							return false;
						}
					}
				});

				if (wPressed)
					stop = System.nanoTime() - TimeUnit.SECONDS.toNanos(2);
				else {
					stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(2);
					continue;
				}

			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
