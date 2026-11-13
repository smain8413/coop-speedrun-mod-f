package com.github.smain8413.cmsm.mixin.server.accessors.debug;


import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.level.storage.SessionLock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LevelStorage.Session.class)
public interface SessionAccessor {

    @Accessor("lock")
    @Mutable
    void SetSessionLock(SessionLock lock);

}
