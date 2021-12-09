package com.dm66.leaguecraft.effect;

import com.dm66.leaguecraft.LeagueCraftMod;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects
{
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, LeagueCraftMod.MOD_ID);

    public static final RegistryObject<Effect> STASIS = EFFECTS.register("stasis", () -> new StasisEffect(EffectType.NEUTRAL, 0xf5ef42));

    public static void register(IEventBus eventBus)
    {
        EFFECTS.register(eventBus);
    }
}
