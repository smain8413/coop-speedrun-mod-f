package com.github.smain8413.cmsm.mixin.server.mixins.set_seed.with_seed;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(VanillaLayeredBiomeSource.class)
public abstract class VanillaLayeredBiomeSourceMixin extends BiomeSource {
    @Final
    @Shadow private boolean field_24498;
    @Final
    @Shadow private boolean field_24729;

    protected VanillaLayeredBiomeSourceMixin(List<Biome> biomes) {
        super(biomes);
    }

    @Environment(EnvType.SERVER)
    public BiomeSource withSeed(long seed) {
        return new VanillaLayeredBiomeSource(seed, this.field_24498, this.field_24729);
    }
}
