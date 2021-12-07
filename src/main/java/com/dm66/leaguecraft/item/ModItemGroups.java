package com.dm66.leaguecraft.item;

import com.dm66.leaguecraft.LeagueCraftMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups
{
    public static final ItemGroup LEAGUE_ITEMS = new ItemGroup("leagueItemsTab")
    {
        @Override
        public ItemStack createIcon()
        {
            return null;
        }
    };

    public static final ItemGroup LEAGUE_CHAMPIONS = new ItemGroup("leagueChampionsTab")
    {
        @Override
        public ItemStack createIcon()
        {
            return null;
        }
    };

    public static final ItemGroup LEAGUE_ABILITIES = new ItemGroup("leagueAbilitiesTab")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.AUTO_ATTACK.get());
        }
    };

}
