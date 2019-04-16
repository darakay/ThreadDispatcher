package com.darakay.oop_projects.ThreadDispatcher;

import java.util.function.Consumer;

public abstract class Task implements Runnable {
    private long threadId = -1;
    private final String taskName;
    private Consumer callback = (resultValue) -> {};

    public Task(String taskName){
        this.taskName = taskName;
    }

    @Override
    public final void run() {
        doWork().callback(callback);
        ThreadDispatcher.getInstance().taskWasCompleted(threadId);
        threadId = -1;
    }

    public final <T> Task withCallback(Consumer<T> callback){
        this.callback = callback;
        return this;
    }

    TaskInfo createTaskInfo(){
        return new TaskInfo(taskName);
    }

    void setThreadId(long threadId){
        this.threadId = threadId;
    }

    public String getTaskName(){
        return taskName;
    }

    public abstract Result doWork();

    @Override
    public String toString() {
        return createTaskInfo().toString();
    }
}