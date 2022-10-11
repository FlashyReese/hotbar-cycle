package me.flashyreese.mods.hotbarcycle;

import me.flashyreese.mods.hotbarcycle.network.HotbarCyclePacket;
import net.fabricmc.api.ModInitializer;

public class HotbarCycleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        HotbarCyclePacket.registerReceivePacket();
    }
}
