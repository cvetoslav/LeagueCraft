package com.dm66.leaguecraft.networking;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientOnlineUsersPacket
{
    public ClientOnlineUsersPacket(PacketBuffer buffer)
    {

    }

    public ClientOnlineUsersPacket()
    {

    }

    public void encode(PacketBuffer buffer)
    {

    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx)
    {
        return true;
    }

}
