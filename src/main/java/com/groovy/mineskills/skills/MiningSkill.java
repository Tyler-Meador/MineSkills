package com.groovy.mineskills.skills;

import com.groovy.mineskills.interfaces.ItemsDroppedInterface;
import com.groovy.mineskills.interfaces.MineSkillsInterface;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class MiningSkill {
    private final Map<Integer, Long> totalLvlXpMap = new HashMap<>();
    private final Map<Block, Integer> blockXpValues = new HashMap<>();
    private final Map<Block, Integer> miningMilestones = new HashMap<>();

    public MiningSkill(){
        populateXpMap();
        populateBlockXpValuesMap();
        populateMilestoneLevels();
    }

    private void populateMilestoneLevels(){
        miningMilestones.put(Block.getBlockFromItem(Items.STONE), 1);
        miningMilestones.put(Block.getBlockFromItem(Items.COAL_ORE), 1);
        miningMilestones.put(Block.getBlockFromItem(Items.COPPER_ORE), 5);
        miningMilestones.put(Block.getBlockFromItem(Items.IRON_ORE), 10);
        miningMilestones.put(Block.getBlockFromItem(Items.GOLD_ORE), 15);
        miningMilestones.put(Block.getBlockFromItem(Items.REDSTONE_ORE), 20);
        miningMilestones.put(Block.getBlockFromItem(Items.LAPIS_ORE), 30);
        miningMilestones.put(Block.getBlockFromItem(Items.DIAMOND_ORE), 35);
        miningMilestones.put(Block.getBlockFromItem(Items.EMERALD_ORE), 40);
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

    private void populateBlockXpValuesMap(){
        blockXpValues.put(Block.getBlockFromItem(Items.STONE), 2);
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
        PlayerBlockBreakEvents.BEFORE.register((((world, player, pos, state, blockEntity) -> {
            if(miningMilestones.containsKey(state.getBlock())){
                if(miningMilestones.get(state.getBlock()) > ((MineSkillsInterface)player).getMiningLvl()){
                    player.sendMessage(Text.of("Your Mining Level is Too Low!!"), true);
                    return false;
                }
            }
            if (blockXpValues.containsKey(state.getBlock())) {
                performMiningIncrease(player, blockXpValues.get(state.getBlock()), state);
            }
            return true;
        })));
    }
    public void performMiningIncrease(PlayerEntity player, int xp, BlockState state){
        int tempXp = ((ItemsDroppedInterface)state.getBlock()).getItemsDroppedCount() * xp;
        if(tempXp == 0){
            tempXp = xp;
        }
        ((MineSkillsInterface)player).addMiningXp(tempXp);
        player.sendMessage(Text.of("+" + tempXp), false);

        while(((MineSkillsInterface)player).getMiningXp() > totalLvlXpMap.get(((MineSkillsInterface)player).getMiningLvl())){
            performLevelUp(player);
        }
    }

    public void performLevelUp(PlayerEntity player){
        ((MineSkillsInterface)player).addMiningLvl();
        player.sendMessage(Text.of("Congratulations! Your Mining Level Has Increased To: " + ((MineSkillsInterface)player).getMiningLvl()), true);
        if(miningMilestones.containsValue((((MineSkillsInterface)player).getMiningLvl()))){
            System.out.println("Milestone Level Increase Time");
            int[] temp = new int[1];
            int[] milestone;
            if(((MineSkillsInterface)player).getMiningMilestone() != null){
                milestone = new int[temp.length + 1];
            }else{
                milestone = temp;
            }
            for(int i : temp){
                milestone[i] = temp[i];
            }
            milestone[milestone.length - 1] = ((MineSkillsInterface)player).getMiningLvl();
            ((MineSkillsInterface)player).setMiningMilestone(milestone);
            player.sendMessage(Text.of("Congratulations! You Reached A New Milestone!: "), false);
        }

    }

}
