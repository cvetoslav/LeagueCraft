package com.dm66.leaguecraft.block;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.item.ModCreativeTabs;
import com.dm66.leaguecraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LeagueCraftMod.MOD_ID);

    public static final RegistryObject<Block> STEALTH_WARD = registerBlock("stealth_ward", ModCreativeTabs.LEAGUE_ITEMS,
            () -> new StealthWardBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)));

    public static final RegistryObject<Block> LEAGUE_CLIENT = registerBlock("league_client", ModCreativeTabs.MISC,
            () -> new LeagueClientBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, CreativeModeTab tab, Supplier<T> block)
    {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        registerBlockItem(name, ret, tab);
        return ret;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
