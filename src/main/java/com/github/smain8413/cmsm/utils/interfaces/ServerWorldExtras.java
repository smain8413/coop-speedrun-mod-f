package com.github.smain8413.cmsm.utils.interfaces;

import java.util.ArrayList;
import java.util.List;
import com.github.smain8413.cmsm.utils.object_oriented.QueuedAction;


public interface ServerWorldExtras {
     List<QueuedAction> tickQueueActions = new ArrayList<>();


     void cmsm$runNextTick(QueuedAction action);
}
