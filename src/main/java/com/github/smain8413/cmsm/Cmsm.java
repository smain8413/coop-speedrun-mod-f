package com.github.smain8413.cmsm;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.options.KeyBinding;

import java.util.Arrays;
import java.util.List;

public class Cmsm implements ModInitializer {

    public static final String MODID = "cmsm";
    public static final Logger LOGGER = LogManager.getLogger();
    public static String[] launchArgs;
    public static final List<String> launchArgsTemplate = List.of("--debug");
    public static final boolean debug = Arrays.asList(FabricLoader.getInstance().getLaunchArguments(true)).contains("--debug");
    public static MinecraftServer SERVER;
    private static KeyBinding openScreenKey;

    @Override
    public void onInitialize() {
        launchArgs = FabricLoader.getInstance().getLaunchArguments(true);
        System.out.println(Arrays.toString(launchArgs));

    }
}
