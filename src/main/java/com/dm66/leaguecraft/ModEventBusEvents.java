package com.dm66.leaguecraft;

import com.dm66.leaguecraft.entity.BlueCasterMinion;
import com.dm66.leaguecraft.entity.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LeagueCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.BLUE_CASTER_MINION.get(), BlueCasterMinion.setAttributes());
    }
}
