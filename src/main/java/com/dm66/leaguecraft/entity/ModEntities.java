package com.dm66.leaguecraft.entity;

import com.dm66.leaguecraft.LeagueCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, LeagueCraftMod.MOD_ID);

    public static final RegistryObject<EntityType<BasicAttackProjectile>> AUTO_ATTACK_PROJECTILE = ENTITIES.register("autoattack_projectile",
            () -> EntityType.Builder.<BasicAttackProjectile>of(BasicAttackProjectile::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .build(new ResourceLocation(LeagueCraftMod.MOD_ID, "autoattack_projectile").toString()));

    public static final RegistryObject<EntityType<BlueCasterMinion>> BLUE_CASTER_MINION = ENTITIES.register("blue_caster_minion",
            () -> EntityType.Builder.of(BlueCasterMinion::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(LeagueCraftMod.MOD_ID, "blue_caster_minion").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
