package com.groovy.mineskills;

import com.groovy.mineskills.structures.OrachalciteSpikeStructure;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class MSStructures {
    public static StructureFeature<DefaultFeatureConfig> ORACHALCITE_SPIKE = new OrachalciteSpikeStructure(DefaultFeatureConfig.CODEC);

    public static void setupAndRegisterStructureFeatures() {

        FabricStructureBuilder.create(new Identifier(MineSkills.MOD_ID, "orachalcite_spike"), ORACHALCITE_SPIKE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(
                        2,
                        1,
                        352362362
                )).superflatFeature(ORACHALCITE_SPIKE.configure(FeatureConfig.DEFAULT))
                .adjustsSurface()
                .register();

    }
}
