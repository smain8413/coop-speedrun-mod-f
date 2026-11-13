package com.github.smain8413.cmsm.mixin.server.accessors.debug;

import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ThreadedAnvilChunkStorage.class)
public interface ThreadedAnvilChunkStorageAccessor {
    @Accessor("worldGenerationProgressListener")
    @Mutable
    WorldGenerationProgressListener cmsm$setWorldGenerationProgressListener();

    @Invoker("save")
    void cmsm$save(boolean flush);
}
