package com.github.smain8413.cmsm.mixin.server.mixins.debug;


import com.github.smain8413.cmsm.Cmsm;
import com.github.smain8413.cmsm.reset.WorldReset;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements ScreenHandlerListener {

    @Shadow @Final public MinecraftServer server;

//    @Shadow public abstract void teleport(ServerWorld targetWorld, double x, double y, double z, float yaw, float pitch);

    public ServerPlayerEntityMixin(World world, BlockPos blockPos, GameProfile gameProfile) {
        super(world, blockPos, gameProfile);
    }

    @Inject(method = "startRiding", at = @At("HEAD"), cancellable = true)
    public void startRiding(Entity entity, boolean force, CallbackInfoReturnable<Boolean> cir){
        if (!Cmsm.debug) return;
        LOGGER.info("resetting world");
        WorldReset.Reset(server);
        cir.cancel();
    }
}
