package com.darakay.oop_projects.ThreadDispatcher;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreadDispatcher implements Closeable {
    private volatile static ThreadDispatcher instance;
    private final TaskMonitor taskMonitor;


    private ThreadDispatcher(){
        this.taskMonitor = new TaskMonitor("Task Monitor");
        addTask(taskMonitor);
    }

    public  static ThreadDispatcher getInstance() {
        if(instance == null)
            synchronized (ThreadDispatcher.class){
                if(instance == null)
                    instance = new ThreadDispatcher();
            }
        return instance;
    }

    public synchronized void addTask(Task task){
        TaskInfo info = task.createTaskInfo();
        Thread thread = new Thread(task);
        long id = thread.getId();
        task.setThreadId(id);
        thread.start();
        taskMonitor.taskWasAdded(thread.getId(),info);
    }

    public synchronized void close(){
        taskMonitor.stop();
    }

    public List<TaskInfo> getExecutedTasks()
    {
        return taskMonitor.getAliveTasks();
    }

    protected void taskWasCompleted(long taskId){
        taskMonitor.removeTask(taskId);
    }

}

