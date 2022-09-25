package me.phqsh.ns4j.threading;

import java.util.concurrent.Executors;

public class ThreadManager {
    public static void executeOffThread(Runnable r){
        Executors.newSingleThreadExecutor().execute(r);
    }
}