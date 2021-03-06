package com.groovy.mineskills.registry;

import com.groovy.mineskills.MineSkills;
import com.groovy.mineskills.blocks.BuddingOreNode;
import com.groovy.mineskills.blocks.OreClusterBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block ORACHALCITE_NODE = new BuddingOreNode(FabricBlockSettings.of(Material.METAL).strength(5.0f).ticksRandomly().requiresTool(), 1);
    public static final Block ORACHALCITE_CLUSTER = new OreClusterBlock(16, 0, FabricBlockSettings.of(Material.METAL).ticksRandomly().strength(5.0f));
    public static final Block LARGE_ORACHALCITE_BUD = new OreClusterBlock(12,2, FabricBlockSettings.of(Material.METAL).nonOpaque());
    public static final Block MEDIUM_ORACHALCITE_BUD = new OreClusterBlock(8, 4, FabricBlockSettings.of(Material.METAL).nonOpaque());
    public static final Block SMALL_ORACHALCITE_BUD = new OreClusterBlock(4, 6, FabricBlockSettings.of(Material.METAL).nonOpaque().luminance((state)->{return 4;}));
    public static final Block DRAKOLITH_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(5.0f));

    public static void registerBlocks(){

        //ORACHALCITE NODE
        Registry.register(Registry.BLOCK, new Identifier(MineSkills.MOD_ID, "orachalcite_node"), ORACHALCITE_NODE);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "orachalcite_node"), new BlockItem(
                ORACHALCITE_NODE, new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MineSkills.MOD_ID, "orachalcite_cluster"), ORACHALCITE_CLUSTER);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "orachalcite_cluster"), new BlockItem(
                ORACHALCITE_CLUSTER, new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MineSkills.MOD_ID, "large_orachalcite_bud"), LARGE_ORACHALCITE_BUD);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "large_orachalcite_bud"), new BlockItem(
                LARGE_ORACHALCITE_BUD, new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MineSkills.MOD_ID, "medium_orachalcite_bud"), MEDIUM_ORACHALCITE_BUD);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "medium_orachalcite_bud"), new BlockItem(
                MEDIUM_ORACHALCITE_BUD, new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MineSkills.MOD_ID, "small_orachalcite_bud"), SMALL_ORACHALCITE_BUD);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "small_orachalcite_bud"), new BlockItem(
                SMALL_ORACHALCITE_BUD, new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP)));

        //Drakolith Ore
        Registry.register(Registry.BLOCK, new Identifier(MineSkills.MOD_ID, "drakolith_ore"), DRAKOLITH_ORE);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "drakolith_ore"), new BlockItem(
                DRAKOLITH_ORE, new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP)));
    }
}
