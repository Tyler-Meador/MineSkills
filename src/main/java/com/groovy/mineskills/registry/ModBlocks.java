package com.groovy.mineskills.registry;

import com.groovy.mineskills.MineSkills;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block ORICHALCITE_ORE = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0f));

    public static void registerBlocks(){
        Registry.register(Registry.BLOCK, new Identifier(MineSkills.MOD_ID, "orachalcite_ore"), ORICHALCITE_ORE);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "orachalcite_ore"), new BlockItem(
                ORICHALCITE_ORE, new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP)));
    }
}
