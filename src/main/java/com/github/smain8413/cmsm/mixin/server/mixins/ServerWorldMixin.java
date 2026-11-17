package com.github.smain8413.cmsm.mixin.server.mixins;

import com.github.smain8413.cmsm.Cmsm;
import com.github.smain8413.cmsm.utils.object_oriented.QueuedAction;
import com.github.smain8413.cmsm.utils.interfaces.ServerWorldExtras;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin extends World implements ServerWorldAccess, ServerWorldExtras {
//    @Shadow
//    public abstract void tick(BooleanSupplier shouldKeepTicking);

    @Unique
    List<QueuedAction> tickQueueActions = new ArrayList<>();

    @Override
    public void cmsm$runNextTick(QueuedAction action) {
        tickQueueActions.add(action);
    }

    protected ServerWorldMixin(MutableWorldProperties mutableWorldProperties, RegistryKey<World> registryKey, RegistryKey<DimensionType> registryKey2, DimensionType dimensionType, Supplier<Profiler> profiler, boolean bl, boolean bl2, long l) {
        super(mutableWorldProperties, registryKey, registryKey2, dimensionType, profiler, bl, bl2, l);
    }

//    @Unique
//    private static List<QueuedAction> tickQueueActions = new ArrayList<>();


    @Inject(method = "tick", at = @At("TAIL"))
    public void onTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci){ // maybe if you tag it onto save instead
//        tickQueueActions.parallelStream().forEach((queuedAction -> {queuedAction.ticked(); tickQueueActions.remove(queuedAction);}));
        if (tickQueueActions.isEmpty()) return;
        QueuedAction first = tickQueueActions.get(0);
        first.ticked();
        if (Cmsm.debug) LOGGER.info("ticks: {}", first.getTicksTillRun());
        if (first.getTicksTillRun() <= 0) tickQueueActions.remove(first);
    }

    @Inject(method = "addPlayer", at = @At("TAIL"))
    private void onAddPlayer(ServerPlayerEntity player, CallbackInfo ci) { //DEBUG
        if (!Cmsm.debug) return;
        Vec3d playerPos = player.getPos();
        HorseEntity horseEntity = new HorseEntity(EntityType.HORSE, this);
        horseEntity.updatePosition(playerPos.x, playerPos.y, playerPos.z);
        spawnEntity(horseEntity);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstruct(MinecraftServer server, Executor workerExecutor, LevelStorage.Session session, ServerWorldProperties properties, RegistryKey registryKey, RegistryKey registryKey2, DimensionType dimensionType, WorldGenerationProgressListener generationProgressListener, ChunkGenerator chunkGenerator, boolean bl, long l, List list, boolean bl2, CallbackInfo ci)
    {

    }
}
