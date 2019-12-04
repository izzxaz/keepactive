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
        String action;
        ThreadExecutor te = new ThreadExecutor();
        Scanner sc = new Scanner(System.in);
        lableStatus:
        while (true) {
            action = sc.nextLine();
            switch (action) {
                case "run":
                    if (te.running == false) te.execute(new ClickKeyBoard());
                    break;
                case "stop":
                    if (te.running == true) te.shutdown();
                    break;
                case "exit":
                    if (te.running == true) te.shutdown();
                    break lableStatus;
                default:
                    break;
            }
        }
    }
}
