package com.dm66.leaguecraft.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTabs
{
    public static final CreativeModeTab LEAGUE_ITEMS = new CreativeModeTab("leagueItemsTab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.AUTO_ATTACK.get());
        }
    };

    public static final CreativeModeTab LEAGUE_ABILITIES = new CreativeModeTab("leagueAbilitiesTab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.AUTO_ATTACK.get());
        }
    };

    public static final CreativeModeTab MISC = new CreativeModeTab("leagueMiscTab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.AUTO_ATTACK.get());
        }
    };

}
