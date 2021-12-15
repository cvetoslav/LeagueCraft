package com.dm66.leaguecraft.item;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.item.ability_items.BasicAttack;
import com.dm66.leaguecraft.item.ability_items.XerathBasicAttack;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeagueCraftMod.MOD_ID);


    public static final RegistryObject<Item> AUTO_ATTACK = ITEMS.register("autoattack",
            () -> new BasicAttack(new Item.Properties().group(ModItemGroups.LEAGUE_ITEMS).maxStackSize(1).maxDamage(10)));

    public static final RegistryObject<Item> XERATH_BASIC_ATTACK = ITEMS.register("xerath_basic_attack",
            () -> new XerathBasicAttack(new Item.Properties().group(ModItemGroups.LEAGUE_ITEMS).maxStackSize(1).maxDamage(10)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

}
