package com.dm66.leaguecraft;

import com.dm66.leaguecraft.block.ModBlocks;
import com.dm66.leaguecraft.effect.ModEffects;
import com.dm66.leaguecraft.entity.ModEntities;
import com.dm66.leaguecraft.entity.render.BasicAttackProjectileRenderer;
import com.dm66.leaguecraft.entity.render.BlueCasterMinionRenderer;
import com.dm66.leaguecraft.item.ModItems;
import com.dm66.leaguecraft.rendering.IngameHUD;
import com.dm66.leaguecraft.rendering.WorldProjectionRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LeagueCraftMod.MOD_ID)
public class LeagueCraftMod
{
    public static final String MOD_ID = "leaguecraft";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public LeagueCraftMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntities.register(eventBus);
        ModEffects.register(eventBus);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(new IngameHUD(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(WorldProjectionRenderer.class);
        MinecraftForge.EVENT_BUS.register(Summoner.class);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        EntityRenderers.register(ModEntities.AUTO_ATTACK_PROJECTILE.get(), BasicAttackProjectileRenderer::new);
        EntityRenderers.register(ModEntities.BLUE_CASTER_MINION.get(), BlueCasterMinionRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // TODO: Load Summoners profiles from DB and assign OFFLINE status
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event)
    {
        //if(Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
        {
            Summoner.addSummoner(event.getPlayer());  // TODO: set ONLINE status or add summoner if first time
        }
    }

}
