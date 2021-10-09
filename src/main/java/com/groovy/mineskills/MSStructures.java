package com.groovy.mineskills;

import com.groovy.mineskills.structures.OrachalciteSpawnNodeStructure;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class MSStructures {
    public static StructureFeature<DefaultFeatureConfig> ORACHALCITE_SPAWN_NODE = new OrachalciteSpawnNodeStructure(DefaultFeatureConfig.CODEC);

    public static void setupAndRegisterStructureFeatures() {

        FabricStructureBuilder.create(new Identifier(MineSkills.MOD_ID, "orachalcite_spawn_node"), ORACHALCITE_SPAWN_NODE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(
                        2,
                        1,
                        352362362))
                .superflatFeature(ORACHALCITE_SPAWN_NODE.configure(FeatureConfig.DEFAULT))
                .adjustsSurface()
                .register();

    }
}
