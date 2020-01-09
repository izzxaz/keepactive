package com.az2x.keepactive;

import java.util.concurrent.TimeUnit;

/**
 * 通用线程执行器。
 */
public class ThreadExecutor {
    private Thread executeService;

    public void execute(Runnable runnable) {
        executeService = new Thread(runnable);
        executeService.setDaemon(true);
        executeService.start();
    }

    public boolean isRunning() {
        if (executeService == null) return false;
        if (executeService.isAlive()) return true;
        return false;
    }

    /**
     * 当millions时间之后还未执行完毕，则打断。
     *
     * @param millions
     */
    public void shutdown(long millions) {
        long current = System.currentTimeMillis();
        while (isRunning()) {
            if (System.currentTimeMillis() - current > millions) {
                executeService.interrupt();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 立刻打断线程
     */
    public void shutdown() {
        while (isRunning()) {
            executeService.interrupt();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
