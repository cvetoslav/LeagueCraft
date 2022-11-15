package com.dm66.leaguecraft.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientOnlineUsersPacket
{
    public ClientOnlineUsersPacket(FriendlyByteBuf buffer)
    {

    }

    public ClientOnlineUsersPacket()
    {

    }

    public void encode(FriendlyByteBuf buffer)
    {

    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx)
    {
        return true;
    }

}
