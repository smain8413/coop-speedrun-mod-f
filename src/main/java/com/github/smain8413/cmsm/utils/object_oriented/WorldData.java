package com.github.smain8413.cmsm.utils.object_oriented;

import com.github.smain8413.cmsm.mixin.server.accessors.GeneratorOptionsAccessor;
import com.github.smain8413.cmsm.mixin.server.accessors.ServerAccessor;
import com.github.smain8413.cmsm.mixin.server.accessors.debug.ServerChunkManagerAccessor;
import com.github.smain8413.cmsm.mixin.server.accessors.debug.ServerWorldAccessor;
import com.github.smain8413.cmsm.mixin.server.accessors.debug.SessionAccessor;
import com.github.smain8413.cmsm.mixin.server.accessors.debug.ThreadedAnvilChunkStorageAccessor;
import com.github.smain8413.cmsm.utils.interfaces.ServerWorldExtras;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.level.storage.LevelStorage;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Path;

import static com.github.smain8413.cmsm.Cmsm.LOGGER;

public final class WorldData {
    public final ServerWorld serverWorld;
    public final MinecraftServer server;
    public final ServerAccessor serverAccessor;
    public final ServerWorldAccessor serverWorldAccessor;
    public final LevelStorage.Session session;
    public final SessionAccessor sessionAccessor;
    public final ServerChunkManager serverChunkManager;
    public final ThreadedAnvilChunkStorage threadedAnvilChunkStorage;
    public final ServerWorldExtras serverWorldExtra;
    public final GeneratorOptionsAccessor generatorOptionsAccessor;
    public final RegistryKey<World> worldRegistryKey;
    public final ServerChunkManagerAccessor serverChunkManagerAccessor;
    public final ThreadedAnvilChunkStorageAccessor threadedAnvilChunkStorageAccessor;
    public final Path rootDir;
    public final Path dataDir;
    public final File worldDir;

    public WorldData(ServerWorld serverWorld) {
        this.serverWorld = serverWorld;
        this.serverWorldAccessor = (ServerWorldAccessor) serverWorld;
        this.server = serverWorld.getServer();
        this.serverAccessor = (ServerAccessor) server;
        this.session = serverAccessor.cmsm$getSession();
        this.sessionAccessor = (SessionAccessor) session;
        this.serverChunkManager = serverWorldAccessor.cmsm$getServerChunkManager();
        this.threadedAnvilChunkStorage = serverChunkManager.threadedAnvilChunkStorage;
        this.serverWorldExtra = (ServerWorldExtras) serverWorld;
        this.generatorOptionsAccessor = (GeneratorOptionsAccessor) serverWorld.getServer().getSaveProperties().getGeneratorOptions();
        this.worldRegistryKey = serverWorld.getRegistryKey();
        this.serverChunkManagerAccessor = (ServerChunkManagerAccessor) serverChunkManager;
        threadedAnvilChunkStorageAccessor = (ThreadedAnvilChunkStorageAccessor) threadedAnvilChunkStorage;
        rootDir = session.getDirectory(WorldSavePath.ROOT);
        dataDir = session.getDirectory(WorldSavePath.LEVEL_DAT);
        this.worldDir = session.getWorldDirectory(this.worldRegistryKey);
    }

    public boolean isWorld(@Nullable RegistryKey<World> world) {
        if (world == null) {
            LOGGER.fatal(new NullPointerException("Comparison world is null (WorldData.java Line 57)").toString());
            return false;
        }
        return world.equals(this.serverWorld.getRegistryKey());
    }
}