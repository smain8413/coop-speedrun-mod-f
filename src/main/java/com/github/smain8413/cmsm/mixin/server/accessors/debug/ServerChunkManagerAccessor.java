package com.github.smain8413.cmsm.mixin.server.accessors.debug;

import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerChunkManager.class)
public interface ServerChunkManagerAccessor {
//    @Accessor("threadedAnvilChunkStorage")
//    @Mutable
//    void cmsm$setThreadedAnvilChunkStorage(ThreadedAnvilChunkStorage storage);

    @Accessor("ticketManager")
    ChunkTicketManager getTicketManager();

    @Accessor("chunkCache")
    @Mutable
    Chunk[] getChunkCache();

    @Accessor("chunkGenerator")
    @Mutable
    void cmsm$setChunkGenerator(ChunkGenerator generator);

    @Accessor("chunkGenerator")
    ChunkGenerator cmsm$getChunkGenerator();
}
