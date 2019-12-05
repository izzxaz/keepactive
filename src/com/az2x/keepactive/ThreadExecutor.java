package com.az2x.keepactive;

import java.util.concurrent.TimeUnit;

/**
 * 通用线程执行器。
 * 为了防止传入的线程发生异常无法结束，使其成为守护线程，并由线程执行器提供的管理线程管理。
 */
public class ThreadExecutor {
    private Thread executeService;

    public void execute(Runnable runnable) {
        executeService = new Thread(runnable);
        executeService.setDaemon(true);
        executeService.start();
    }

    public boolean isRunning() {
        return executeService.isAlive();
    }

    /**
     * 当millions时间之后还未执行完毕，则打断被守护线程，强迫守护线程终止。
     *
     * @param millions
     */
    public void shutdown(long millions) {
        long current = System.currentTimeMillis();
        while (!isRunning()) {
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
     * 立刻打断主线程，强迫守护线程终止
     */
    public void shutdown() {
        while (!isRunning()) {
            executeService.interrupt();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
