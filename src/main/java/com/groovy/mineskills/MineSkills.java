package com.groovy.mineskills;

import com.groovy.mineskills.registry.ModItems;
import com.groovy.mineskills.registry.ModStats;
import com.groovy.mineskills.skills.MineSkillsMining;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MineSkills implements ModInitializer {

    //Mod ID
    public static final String MOD_ID ="mineskills";

    //Mining Skill Class
    public MineSkillsMining mining = new MineSkillsMining();

    //Item Group
    public static final ItemGroup MINESKILLS_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "mine_skills_group"),
            () -> new ItemStack(ModItems.MINESKILLS_STAT_BOOK));

    @Override
    public void onInitialize() {
        mining.mineSkillsMiningHandler();
        ModStats.registerMining();
        ModItems.registerItems();
    }


}
