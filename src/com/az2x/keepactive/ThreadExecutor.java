package com.az2x.keepactive;

/**
 * 通用线程执行器。
 * 为了防止传入的线程发生异常无法结束，使其成为守护线程，并由线程执行器提供的管理线程管理。
 */
public class ThreadExecutor {
    private boolean finished = false;
    public boolean running = false;
    private Thread executeService;

    public void execute(Runnable runnable) {
        finished = false;
        running = true;
        System.out.println(">>>管理线程已启动");
        executeService = new Thread() {
            @Override
            public void run() {
                System.out.println(">>>执行线程已启动");
                Thread runner = new Thread(runnable);
                //设置runner为executeService的守护线程
                runner.setDaemon(true);
                runner.start();
                //保证runner执行完毕再设置finished为true
                try {
                    runner.join();
                } catch (InterruptedException e) {
                    runner.interrupt();
                    System.out.println(">>>管理线程结束");
                } finally {
                    running = false;
                }
                finished = true;
            }
        };
        executeService.start();
    }

    /**
     * 当millions时间之后还未执行完毕，则打断被守护线程，强迫守护线程终止。
     *
     * @param millions
     */
    public void shutdown(long millions) {
        long current = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - current > millions) {
                executeService.interrupt();
            }
            //让执行线程休眠
            try {
                executeService.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 立刻打断主线程，强迫守护线程终止
     */
    public void shutdown() {
        while (!finished) {
            executeService.interrupt();
            try {
                executeService.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
