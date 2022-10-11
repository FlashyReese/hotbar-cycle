package me.flashyreese.mods.hotbarcycle;

import me.flashyreese.mods.hotbarcycle.network.HotbarCyclePacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class HotbarCycleClientMod implements ClientModInitializer {

    private static final KeyBinding cycleHotBar = new KeyBinding("key.hotbar-cycle.cycle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_TAB, "category.hotbar-cycle");

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(cycleHotBar);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (cycleHotBar.wasPressed()) {
                if (client.player == null)
                    return;

                HotbarCyclePacket.sendCyclePacket(Screen.hasShiftDown());
            }
        });
    }
}
