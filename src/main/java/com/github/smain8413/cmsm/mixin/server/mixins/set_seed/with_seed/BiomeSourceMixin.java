package com.github.smain8413.cmsm.mixin.server.mixins.set_seed.with_seed;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BiomeSource.class)
public abstract class BiomeSourceMixin implements BiomeAccess.Storage {
    @Unique
    @Environment(EnvType.SERVER)
    public abstract BiomeSource withSeed(long seed);

}
