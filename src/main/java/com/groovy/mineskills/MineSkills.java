package com.groovy.mineskills;

import com.groovy.mineskills.registry.ModStats;
import com.groovy.mineskills.skills.MineSkillsMining;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.Inject;

public class MineSkills implements ModInitializer {

    //Mod ID
    public static final String MOD_ID ="mineskills";

    public MineSkillsMining mining = new MineSkillsMining();

    @Override
    public void onInitialize() {
        mining.mineSkillsMiningHandler();
        ModStats.registerMining();
    }


}
