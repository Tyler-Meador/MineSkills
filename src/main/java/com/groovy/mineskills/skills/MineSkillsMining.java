package com.groovy.mineskills.skills;

import com.groovy.mineskills.MineSkillsInterface;
import com.groovy.mineskills.registry.ModStats;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

public class MineSkillsMining {

    private final Map<Integer, Long> totalLvlXpMap = new HashMap<>();

    public MineSkillsMining(){
        populateXpMap();
    }

    private void populateXpMap(){
        int count = 2;
        long xpDiff = 83;
        long xp = 83;
        totalLvlXpMap.put(0, 0L);
        totalLvlXpMap.put(1, 0L);
        while(count < 100){
            totalLvlXpMap.put(count, xp);
            xpDiff = (long) (xpDiff * (1.10495));
            xp += xpDiff;
            count++;
        }
    }

    public void mineSkillsMiningHandler(){
        PlayerBlockBreakEvents.AFTER.register(((world, player, pos, state, blockEntity) -> {
            if (state.getBlock().equals(Block.getBlockFromItem(Items.COAL_ORE))) {
                performMiningIncrease(player, 17);
            } else if (state.getBlock().equals(Block.getBlockFromItem(Items.COPPER_ORE))) {
                performMiningIncrease(player, 35);
            } else if (state.getBlock().equals(Block.getBlockFromItem(Items.IRON_ORE))){
                performMiningIncrease(player, 40);
            } else if (state.getBlock().equals(Block.getBlockFromItem(Items.GOLD_ORE))){
                performMiningIncrease(player, 80);
            } else if (state.getBlock().equals(Block.getBlockFromItem(Items.REDSTONE_ORE))){
                performMiningIncrease(player, 85);
            } else if (state.getBlock().equals(Block.getBlockFromItem(Items.LAPIS_ORE))){
                performMiningIncrease(player, 100);
            } else if (state.getBlock().equals(Block.getBlockFromItem(Items.DIAMOND_ORE))){
                performMiningIncrease(player, 125);
            } else if (state.getBlock().equals(Block.getBlockFromItem(Items.EMERALD_ORE))){
                performMiningIncrease(player, 175);
            }
        }));
    }


    public void performMiningIncrease(PlayerEntity player, int xp){
        player.increaseStat(ModStats.MINING_SKILL, xp);
        ((MineSkillsInterface)player).addMiningXp(xp);
        player.sendMessage(Text.of("+" + xp), false);

        while(((MineSkillsInterface)player).getMiningXp() > totalLvlXpMap.get(((MineSkillsInterface)player).getMiningLvl())){
            ((MineSkillsInterface)player).addMiningLvl();
            player.sendMessage(Text.of("Congratulations! Your Mining Level Has Increased To: " + ((MineSkillsInterface)player).getMiningLvl()), true);
        }
    }

}
