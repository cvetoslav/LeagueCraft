package com.dm66.leaguecraft.effect;

import com.dm66.leaguecraft.LeagueCraftMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LeagueCraftMod.MOD_ID);

    public static final RegistryObject<MobEffect> STASIS = EFFECTS.register("stasis", StasisEffect::new);

    public static void register(IEventBus eventBus)
    {
        EFFECTS.register(eventBus);
    }
}
