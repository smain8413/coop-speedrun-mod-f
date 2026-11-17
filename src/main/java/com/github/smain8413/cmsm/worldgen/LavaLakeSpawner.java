package com.github.smain8413.cmsm.worldgen;


import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.ChunkRandom;

import java.util.Collections;
import java.util.List;

public class LavaLakeSpawner {
    private static final List<Vec3i> DIRECTIONS = Lists.newArrayList(new Vec3i(1, 0, 0), new Vec3i(-1, 0, 0), new Vec3i(0, 0, 1), new Vec3i(0, 0, -1));
    private static ChunkRandom random;

    public static void create(BlockPos center, ServerWorldAccess world, int radius, int range) {
        random = new ChunkRandom();
        random.setPopulationSeed(world.getSeed(), center.getX(), center.getZ());
        center = getRandomPos(center, world, radius, range);
    }

    protected static BlockPos getRandomPos(BlockPos center, ServerWorldAccess world,int radius, int range) {
        List<Vec3i> dirs = getRandomDirections();
        for (int dir = 0; dir < dirs.size(); dir++) {
            int dirX = dirs.get(dir).getX();
            int dirY = dirs.get(dir).getY();
            int distance = range + random.nextInt(radius); {}
            int offset = random.nextInt(range * 2) - range;
        }

        return center;
    }

    protected static int getTopYNoTrees(BlockPos pos, ServerWorldAccess world) {
        int i;
        for (i = world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ()); i >= 0 ; --i) {
            BlockState state = world.getBlockState(pos.add(pos.getX(), i, pos.getY()));
            if (state.getMaterial().blocksMovement() && !(state.getBlock() instanceof LeavesBlock) && state.getMaterial() != Material.WOOD) break;
        }
        return i;
    }

    protected static List<Vec3i> getRandomDirections() {
        List<Vec3i> dir = Lists.newArrayList(DIRECTIONS);
        Collections.shuffle(dir, random);
        return dir;
    }
}
