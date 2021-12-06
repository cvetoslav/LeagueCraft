package com.dm66.leaguecraft.block;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.dm66.leaguecraft.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LeagueCraftMod.MOD_ID);

    public static final RegistryObject<Block> STEALTH_WARD = registerBlock("stealth_ward",
            () -> new StealthWardBlock(AbstractBlock.Properties.create(Material.IRON).notSolid()));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        registerBlockItem(name, ret);
        return ret;
    }

    private static <T extends Block> void registerBlockItem(String name, Supplier<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
