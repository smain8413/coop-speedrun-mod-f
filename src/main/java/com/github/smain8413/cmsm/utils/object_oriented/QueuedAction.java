package com.github.smain8413.cmsm.utils.object_oriented;



public final class QueuedAction {
    private final Runnable action;
    private int ticksTillRun;
    public QueuedAction(Runnable action, int ticksTillRun) {
        this.action = action;
        this.ticksTillRun = Math.abs(ticksTillRun);
    }
    public void ticked() {
        if (--ticksTillRun<=0)
            action.run();
    }
    public int getTicksTillRun(){return ticksTillRun;}

}

