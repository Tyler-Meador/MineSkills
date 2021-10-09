package com.groovy.mineskills;

import com.groovy.mineskills.mixin.StructuresConfigAccessor;
import com.groovy.mineskills.registry.MineSkillsOreGeneration;
import com.groovy.mineskills.registry.ModBlocks;
import com.groovy.mineskills.registry.ModItems;
import com.groovy.mineskills.skills.MiningSkill;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class MineSkills implements ModInitializer, DedicatedServerModInitializer, ClientModInitializer {

    //logger
    public static final Logger LOGGER = LogManager.getLogger();

    //Mod ID
    public static final String MOD_ID ="mineskills";

    //Mining Skill Class
    public MiningSkill mining = new MiningSkill();
    //Item Group
    public static final ItemGroup MINESKILLS_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "mine_skills_group"),
            () -> new ItemStack(ModItems.MINESKILLS_STAT_BOOK));

    @Override
    @SuppressWarnings("deprecation")
    public void onInitialize() {



        mining.mineSkillsMiningHandler();
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        MineSkillsOreGeneration.registerOre();

        MSStructures.setupAndRegisterStructureFeatures();
        MSConfiguredStructures.registerConfiguredStructures();

        BiomeModifications.create(new Identifier(MOD_ID, "orachalcite_spawn_node_addition"))
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.all(),
                        context -> {
                            context.getGenerationSettings().addBuiltInStructure(MSConfiguredStructures.CONFIGURED_ORACHALCITE_SPAWN_NODE);
                        }
                );
    }

    @Override
    public void onInitializeServer(){
        removeStructureSpawningFromSelectedDimension();
    }

    @Override
    public void onInitializeClient(){
        removeStructureSpawningFromSelectedDimension();
    }

    public static void removeStructureSpawningFromSelectedDimension(){
        ServerWorldEvents.LOAD.register((MinecraftServer minecraftServer, ServerWorld serverWorld)->{
            Map<StructureFeature<?>, StructureConfig> tempMap = new HashMap<>(serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig().getStructures());

            if(!serverWorld.getRegistryKey().getValue().getNamespace().equals("minecraft")){
                tempMap.keySet().remove(MSStructures.ORACHALCITE_SPAWN_NODE);
            }

            ((StructuresConfigAccessor)serverWorld.getChunkManager().getChunkGenerator().getStructuresConfig()).setStructures(tempMap);
        });
    }
}
