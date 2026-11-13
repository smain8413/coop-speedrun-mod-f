package com.github.smain8413.cmsm.mixin.server.mixins.set_seed.with_seed;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChunkGenerator.class)
public abstract class ChunkGeneratorMixin {
    /**
     * @author
     * @reason
     */
    @Unique
    @Environment(EnvType.SERVER)
    public abstract ChunkGenerator withSeed(long seed);

}
