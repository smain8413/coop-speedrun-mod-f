package com.github.smain8413.cmsm.mixin.server.accessors.debug;

import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerWorld.class)
public interface ServerWorldAccessor {
    @Accessor("serverChunkManager")
    ServerChunkManager cmsm$getServerChunkManager();

}
