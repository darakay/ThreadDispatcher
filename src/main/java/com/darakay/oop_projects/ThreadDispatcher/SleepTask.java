package com.darakay.oop_projects.ThreadDispatcher;

public class SleepTask extends Task {
    private int seconds;

    public SleepTask(String name, int seconds)
    {
        super(name);
        this.seconds = seconds;
    }

    @Override
    public Result doWork() {
        try {
            Thread.sleep(seconds * 1000);
            return Result.ok("It's ok!");
        } catch (InterruptedException e) {
            return Result.fail("Fail");
        }
    }
}

