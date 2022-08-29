package com.dm66.leaguecraft.networking;

import com.sun.jna.platform.win32.WinDef;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class Networking
{
    private static final String PROTOCOL_VERSION = "1";
    private static int ID = 0;
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("leaguecraft", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerMessages()
    {
        INSTANCE.registerMessage(ID++, ClientOnlineUsersPacket.class, ClientOnlineUsersPacket::encode, ClientOnlineUsersPacket::new, ClientOnlineUsersPacket::handle);
    }

    public static void sendToServer(Object packet)
    {
        INSTANCE.sendToServer(packet);
    }

    public static void sendToClient(Object packet, ServerPlayerEntity player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static void sendToAllClients(Object packet)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
    }

}
