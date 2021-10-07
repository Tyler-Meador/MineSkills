package com.groovy.mineskills;

import com.groovy.mineskills.registry.MineSkillsOreGeneration;
import com.groovy.mineskills.registry.ModBlocks;
import com.groovy.mineskills.registry.ModItems;
import com.groovy.mineskills.skills.MiningSkill;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.BlockRenderContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MineSkills implements ModInitializer {

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

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_ORACHALCITE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_ORACHALCITE_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_ORACHALCITE_BUD, RenderLayer.getCutout());


        mining.mineSkillsMiningHandler();
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        MineSkillsOreGeneration.registerOre();

        MSStructures.setupAndRegisterStructureFeatures();
        MSConfiguredStructures.registerConfiguredStructures();

        BiomeModifications.create(new Identifier(MOD_ID, "orachalcite_spike_addition"))
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.all(),
                        context -> {
                            context.getGenerationSettings().addBuiltInStructure(MSConfiguredStructures.CONFIGURED_ORACHALCITE_SPIKE);
                        }
                );
    }
}
