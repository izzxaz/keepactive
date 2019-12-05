package com.az2x.keepactive;

import java.util.Scanner;

/**
 * 程序入口
 * 输入run执行
 * 输入stop停止
 * 输入exit退出程序
 */
public class Main {
    public static void main(String[] args) {
        long sleepTime = 30L;
        if (args != null && args.length >= 1) sleepTime = new Long(args[0]);
        String action;
        ThreadExecutor te = new ThreadExecutor();
        Scanner sc = new Scanner(System.in);
        lableStatus:
        while (true) {
            action = sc.nextLine().toLowerCase();
            switch (action) {
                case "run":
                    if (te.isRunning() == false) te.execute(new ClickKeyBoard(sleepTime));
                    break;
                case "stop":
                    if (te.isRunning() == true) te.shutdown();
                    break;
                case "exit":
                    if (te.isRunning() == true) te.shutdown();
                    break lableStatus;
                default:
                    break;
            }
        }
    }
}
