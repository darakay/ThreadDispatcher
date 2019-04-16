package com.darakay.oop_projects.ThreadDispatcher;

class TaskInfo implements Cloneable{
    private String taskName;

    TaskInfo(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return "Task name: " + taskName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TaskInfo){
            return taskName == ((TaskInfo) obj).taskName;
        }
        return false;
    }

    @Override
    protected Object clone() {
        return new TaskInfo(taskName);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
