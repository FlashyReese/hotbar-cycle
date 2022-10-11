package me.flashyreese.mods.hotbarcycle.network;

import io.netty.buffer.Unpooled;
import me.flashyreese.mods.hotbarcycle.HotbarCycleHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class HotbarCyclePacket {
    private static final Identifier HOTBAR_CYCLE = new Identifier("hotbar-cycle", "cycle_hotbar");

    public static void registerReceivePacket() {
        ServerPlayNetworking.registerGlobalReceiver(HOTBAR_CYCLE, ((server, player, handler, buf, responseSender) -> {
            boolean reverse = buf.readBoolean();
            server.execute(() -> HotbarCycleHelper.cycleHotBar(player, reverse));
        }));
    }

    @Environment(EnvType.CLIENT)
    public static void sendCyclePacket(boolean reverse) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBoolean(reverse);
        ClientPlayNetworking.send(HOTBAR_CYCLE, new PacketByteBuf(buf));
    }
}
