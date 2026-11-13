package com.github.smain8413.cmsm;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.options.KeyBinding;

public class Cmsm implements ModInitializer {

    public static final String MODID = "cmsm";
    public static final Logger LOGGER = LogManager.getLogger();
    public static MinecraftServer SERVER;
    private static KeyBinding openScreenKey;

    public static String MODID = "cmsm";

    @Override
    public void onInitialize() {


    }
}
