package com.github.smain8413.cmsm.mixin.server.mixins.set_seed.with_seed;

import com.github.smain8413.cmsm.utils.interfaces.SurfaceChunkGenExtra;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SurfaceChunkGenerator.class)
public abstract class SurfaceChunkGeneratorMixin extends ChunkGenerator implements SurfaceChunkGenExtra {
    public SurfaceChunkGeneratorMixin(BiomeSource biomeSource, StructuresConfig structuresConfig) {
        super(biomeSource, structuresConfig);
    }
    @Final
    @Shadow
    ChunkGeneratorType field_24774;

    public ChunkGenerator withSeed(long seed) {
        return new SurfaceChunkGenerator(this.biomeSource.withSeed(seed), seed, this.field_24774);
    }
}
