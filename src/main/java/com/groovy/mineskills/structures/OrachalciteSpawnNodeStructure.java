package com.groovy.mineskills.structures;

import com.groovy.mineskills.MineSkills;
import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.apache.logging.log4j.Level;

public class OrachalciteSpawnNodeStructure extends StructureFeature<DefaultFeatureConfig> {

    public OrachalciteSpawnNodeStructure(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return OrachalciteSpawnNodeStructure.Start::new;
    }

    private static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_MONSTERS = Pool.of(
    );

    private static final Pool<SpawnSettings.SpawnEntry> STRUCTURE_CREATURES = Pool.of(
    );

    @Override
    public Pool<SpawnSettings.SpawnEntry> getMonsterSpawns(){
        return STRUCTURE_MONSTERS;
    }

    @Override
    public Pool<SpawnSettings.SpawnEntry> getCreatureSpawns(){
        return STRUCTURE_CREATURES;
    }

    public static class Start extends MarginedStructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos, int referenceIn, long seedIn) {
            super(structureIn, chunkPos, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome,
                         DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            int x = chunkPos.x * 16;
            int z = chunkPos.z * 16;

            BlockPos.Mutable centerPos = new BlockPos.Mutable(x, 0, z);

            StructurePoolFeatureConfig structureSettingsAndStartPool = new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY)
                    .get(new Identifier(MineSkills.MOD_ID, "orachalcite_spawn_node/start_pool")),
                    10);

            StructurePoolBasedGenerator.generate(
                    dynamicRegistryManager,
                    structureSettingsAndStartPool,
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    centerPos,
                    this,
                    this.random,
                    false,
                    true,
                    heightLimitView
            );

            this.children.forEach(piece -> piece.translate(0, 1, 0));
            this.children.forEach(piece -> piece.getBoundingBox().move(0, -1, 0));

            this.setBoundingBoxFromChildren();
        }
    }
}
