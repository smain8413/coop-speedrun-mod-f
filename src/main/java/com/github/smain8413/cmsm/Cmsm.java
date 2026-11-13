package com.github.smain8413.cmsm;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cmsm implements ModInitializer {

    public static final String MODID = "cmsm";
    public static final Logger LOGGER = LogManager.getLogger();
    public static MinecraftServer SERVER;

    @Override
    public void onInitialize() {
    }
}
