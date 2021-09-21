package com.groovy.mineskills.registry;

import com.groovy.mineskills.MineSkills;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStats {
    public static final Identifier MINING_SKILL = new Identifier(MineSkills.MOD_ID, "mining_skill");

    public static void registerMining(){
        Registry.register(Registry.CUSTOM_STAT, "mining_skill", MINING_SKILL);
        Stats.CUSTOM.getOrCreateStat(MINING_SKILL, StatFormatter.DEFAULT);
    }
}
