package com.groovy.mineskills;

import com.groovy.mineskills.structures.OreSpikeStructure;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class MSStructures {

    public static StructureFeature<DefaultFeatureConfig> ORESPIKE = new OreSpikeStructure(DefaultFeatureConfig.CODEC);

    public static void setupAndRegisterStructureFeatures() {
        FabricStructureBuilder.create(new Identifier(MineSkills.MOD_ID, "orespike"), ORESPIKE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(new StructureConfig(
                        2,
                        1,
                        12526555
                )).superflatFeature(ORESPIKE.configure(FeatureConfig.DEFAULT))
                .adjustsSurface()
                .register();
    }
}
