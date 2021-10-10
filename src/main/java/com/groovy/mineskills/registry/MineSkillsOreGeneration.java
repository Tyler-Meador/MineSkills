package com.groovy.mineskills.registry;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class MineSkillsOreGeneration {
    private static final ConfiguredFeature<?, ?> ORE_DRAKOLITH_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES,
                    ModBlocks.DRAKOLITH_ORE.getDefaultState(),
                    8)) // <- vein size
            .range(new RangeDecoratorConfig(
                    // You can also use one of the other height providers if you don't want a uniform distribution
                    UniformHeightProvider.create(YOffset.aboveBottom(1), YOffset.fixed(16)))) // Inclusive min and max height
            .spreadHorizontally()
            .repeat(1); // Number of veins per chunk

    public static void registerOre(){
        RegistryKey<ConfiguredFeature<?, ?>> oreDrakolithOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("mineskills", "ore_drakolith_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreDrakolithOverworld.getValue(), ORE_DRAKOLITH_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreDrakolithOverworld);
    }


}
