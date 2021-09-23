package com.groovy.mineskills.skills;

import com.groovy.mineskills.interfaces.ItemsDroppedInterface;
import com.groovy.mineskills.interfaces.MineSkillsInterface;
import com.groovy.mineskills.registry.ModStats;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;


import java.util.HashMap;
import java.util.Map;

public class MiningSkill {


    private final Map<Integer, Long> totalLvlXpMap = new HashMap<>();
    private final Map<Block, Integer> blockXpValues = new HashMap<>();

    public MiningSkill(){
        populateXpMap();
        populateBlockXpValuesMap();
    }

    public int count = 1;

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

    private void populateBlockXpValuesMap(){
        blockXpValues.put(Block.getBlockFromItem(Items.COAL_ORE), 17);
        blockXpValues.put(Block.getBlockFromItem(Items.COPPER_ORE), 35);
        blockXpValues.put(Block.getBlockFromItem(Items.IRON_ORE), 40);
        blockXpValues.put(Block.getBlockFromItem(Items.GOLD_ORE), 80);
        blockXpValues.put(Block.getBlockFromItem(Items.REDSTONE_ORE), 85);
        blockXpValues.put(Block.getBlockFromItem(Items.LAPIS_ORE), 100);
        blockXpValues.put(Block.getBlockFromItem(Items.DIAMOND_ORE), 125);
        blockXpValues.put(Block.getBlockFromItem(Items.EMERALD_ORE), 175);
    }

    public void mineSkillsMiningHandler(){
        PlayerBlockBreakEvents.AFTER.register(((world, player, pos, state, blockEntity) -> {
            if (blockXpValues.containsKey(state.getBlock())) {
                performMiningIncrease(player, blockXpValues.get(state.getBlock()), state);
            }
        }));
    }


    public void performMiningIncrease(PlayerEntity player, int xp, BlockState state){
        int tempXp = ((ItemsDroppedInterface)state.getBlock()).getItemsDroppedCount() * xp;
        player.increaseStat(ModStats.MINING_SKILL, tempXp);
        ((MineSkillsInterface)player).addMiningXp(tempXp);
        player.sendMessage(Text.of("+" + tempXp), false);

        while(((MineSkillsInterface)player).getMiningXp() > totalLvlXpMap.get(((MineSkillsInterface)player).getMiningLvl())){
            ((MineSkillsInterface)player).addMiningLvl();
            player.sendMessage(Text.of("Congratulations! Your Mining Level Has Increased To: " + ((MineSkillsInterface)player).getMiningLvl()), true);
        }
    }

}
