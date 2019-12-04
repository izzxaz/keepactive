package com.az2x.keepactive;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class ClickKeyBoard implements Runnable {
    private long sleepTime = 30L;

    public ClickKeyBoard() {
    }

    public ClickKeyBoard(long seconds) {
        this.sleepTime = seconds;
    }

    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            while (true) {
                robot.keyPress(KeyEvent.VK_CAPS_LOCK);
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
                System.out.println("已按下CapsLock");
                //每分钟按一次
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(">>>执行线程结束");
        }
    }
}
