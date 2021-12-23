package com.dm66.leaguecraft.entity;

import com.dm66.leaguecraft.LeagueCraftMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, LeagueCraftMod.MOD_ID);

    public static final RegistryObject<EntityType<BasicAttackProjectile>> AUTO_ATTACK_PROJECTILE = ENTITIES.register("autoattack_projectile",
            () -> EntityType.Builder.<BasicAttackProjectile>create(BasicAttackProjectile::new, EntityClassification.MISC)
                    .size(0.25f, 0.25f)
                    .build(new ResourceLocation(LeagueCraftMod.MOD_ID, "autoattack_projectile").toString()));

    public static final RegistryObject<EntityType<BlueCasterMinion>> BLUE_CASTER_MINION = ENTITIES.register("blue_caster_minion",
            () -> EntityType.Builder.<BlueCasterMinion>create(BlueCasterMinion::new, EntityClassification.CREATURE)
                    .size(1.0f, 1.0f)
                    .build(new ResourceLocation(LeagueCraftMod.MOD_ID, "blue_caster_minion").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
