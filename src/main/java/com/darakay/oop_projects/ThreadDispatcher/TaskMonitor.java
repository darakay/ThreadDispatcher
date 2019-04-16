package com.darakay.oop_projects.ThreadDispatcher;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class TaskMonitor extends Task {
    private final ConcurrentHashMap<Long, TaskInfo> aliveTask = new ConcurrentHashMap<>();
    private volatile List<TaskInfo> lastCopiedTasks = new ArrayList<>();
    private volatile boolean changed = false;
    private volatile boolean stopped = false;

    TaskMonitor(String taskName) {

        super(taskName);
    }

    @Override
    protected Result doWork() {
        while (!this.stopped){
            if(this.changed){
                lastCopiedTasks = createCopy();
                changed = false;
            }
        }
        return Result.ok("");
    }

    private synchronized List<TaskInfo> createCopy(){
        ArrayList<TaskInfo> copy = new ArrayList<>();
        for(TaskInfo ti : aliveTask.values())
            copy.add((TaskInfo) ti.clone());
        return copy;
    }

    synchronized void stop(){
        stopped = true;
    }

    List<TaskInfo> getAliveTasks(){
        return lastCopiedTasks;
    }

    synchronized void taskWasAdded(long key, TaskInfo task){
        aliveTask.put(key, task);
        changed = true;
    }

    synchronized void removeTask(long taskId){
        aliveTask.remove(taskId);
        changed = true;
    }
}

