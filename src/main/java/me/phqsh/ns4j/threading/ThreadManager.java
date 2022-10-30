package me.phqsh.ns4j.threading;

import java.util.concurrent.Executors;

public class ThreadManager {
    public static void executeOffThread(Runnable r){
        Thread t = new Thread(r);
        t.start();
    }
}