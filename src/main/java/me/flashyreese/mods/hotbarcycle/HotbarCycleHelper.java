package me.flashyreese.mods.hotbarcycle;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;

public class HotbarCycleHelper {
    public static void cycleHotBar(ServerPlayerEntity player, boolean reverse) {
        // Copy Inventory and HotBar
        DefaultedList<ItemStack> inventoryCopy = DefaultedList.ofSize(36, ItemStack.EMPTY);
        for (int i = 0; i < inventoryCopy.size(); i++) {
            inventoryCopy.set(i, player.getInventory().main.get(i));
        }
        // Populate inventory and hotbar
        if (reverse) {
            for (int i = 9; i < inventoryCopy.size(); i++) {
                player.getInventory().main.set(i, inventoryCopy.get(i - 9));
            }
            for (int i = 0; i < 9; i++) {
                player.getInventory().main.set(i, inventoryCopy.get(i + 27));
            }
        } else {
            for (int i = 9; i < inventoryCopy.size(); i++) {
                player.getInventory().main.set(i - 9, inventoryCopy.get(i));
            }
            for (int i = 0; i < 9; i++) {
                player.getInventory().main.set(i + 27, inventoryCopy.get(i));
            }
        }
        player.getInventory().markDirty();
        // Clear inventory copy
        inventoryCopy.clear();
    }
}
