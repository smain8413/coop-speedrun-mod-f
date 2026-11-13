package com.github.smain8413.cmsm.mixin.server.mixins;

import com.github.smain8413.cmsm.Cmsm;
import com.github.smain8413.cmsm.utils.object_oriented.ProgressData;
import com.github.smain8413.cmsm.utils.interfaces.ServerExtras;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTask;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.snooper.SnooperListener;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

@Mixin(MinecraftServer.class)
public abstract class ServerMixin extends ReentrantThreadExecutor<ServerTask> implements SnooperListener, CommandOutput, AutoCloseable, ServerExtras {


    @Unique
    protected ProgressData progressData;

    public ServerMixin(String string) {
        super(string);
    }

    @Inject(method = "startServer", at = @At("RETURN"))
    private static <S extends MinecraftServer> void onStartServer(Function<Thread, S> serverFactory, CallbackInfoReturnable<S> cir) {
        MinecraftServer ts = cir.getReturnValue();
        Cmsm.SERVER = ts;
        ((ServerExtras)ts).minecraftCoopMultiplayerSpeedrunMod$setProgressData(new ProgressData(ts));
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void onShutdown(CallbackInfo ci) {
        Cmsm.SERVER = null;
    }

    @Inject(method = "save", at = @At("TAIL"))
    private void onSave(boolean bl, boolean bl2, boolean bl3, CallbackInfoReturnable<Boolean> cir) {
        Path savePath = ProgressData.getProgressDataPath(((MinecraftServer)(Object)this));
        try {
            //noinspection ResultOfMethodCallIgnored
            savePath.toFile().createNewFile();
        } catch (IOException e) {
            Cmsm.LOGGER.fatal(e.fillInStackTrace());
        }
        try {
            NbtIo.writeCompressed(progressData.getSaveDataNBT(), Files.newOutputStream(savePath));
        } catch (IOException e) {
            Cmsm.LOGGER.fatal(e.fillInStackTrace());
        }
    }

    @Unique
    public ProgressData minecraftCoopMultiplayerSpeedrunMod$getProgressData() {
        return progressData;
    }

    @Unique
    public void minecraftCoopMultiplayerSpeedrunMod$setProgressData(ProgressData data) {
        progressData = data;
    }

//    @Inject(method = "<init>", at = @At("RETURN"))
//    private void onConstruct(Thread thread, RegistryTracker.Modifiable modifiable, LevelStorage.Session session, SaveProperties saveProperties, ResourcePackManager resourcePackManager, Proxy proxy, DataFixer dataFixer, ServerResourceManager serverResourceManager, MinecraftSessionService minecraftSessionService, GameProfileRepository gameProfileRepository, UserCache userCache, WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory, CallbackInfo ci) {
////        MinecraftServer ts = (MinecraftServer)((Object)this);
////        Cmsm.SERVER = (ts);
////        this.minecraftCoopMultiplayerSpeedrunMod$setProgressData(new ProgressData(ts));
//    }
}
