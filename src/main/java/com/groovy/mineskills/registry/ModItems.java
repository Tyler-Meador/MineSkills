package com.groovy.mineskills.registry;

import com.groovy.mineskills.MineSkills;
import com.groovy.mineskills.items.MineSkillsStatBookItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item MINESKILLS_STAT_BOOK = new MineSkillsStatBookItem(new Item.Settings().group(MineSkills.MINESKILLS_GROUP));
    public static final Item RAW_ORACHALCITE = new Item(new FabricItemSettings().group(MineSkills.MINESKILLS_GROUP));


    public static void registerItems(){
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "mineskills_stat_book"), MINESKILLS_STAT_BOOK);
        Registry.register(Registry.ITEM, new Identifier(MineSkills.MOD_ID, "raw_orachalcite"), RAW_ORACHALCITE);
    }
}
