package com.groovy.mineskills;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class MSConfiguredStructures {
    public static ConfiguredStructureFeature<?, ?> CONFIGURED_ORACHALCITE_SPIKE = MSStructures.ORACHALCITE_SPIKE.configure(DefaultFeatureConfig.DEFAULT);

    public static void registerConfiguredStructures(){
        Registry<ConfiguredStructureFeature<?,?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new Identifier(MineSkills.MOD_ID, "configured_orachalcite_spike"), CONFIGURED_ORACHALCITE_SPIKE);
    }
}
