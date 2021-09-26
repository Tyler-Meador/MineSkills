package com.groovy.mineskills.variables;

import net.minecraft.block.Block;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;

public class MineSkillsVariables {

    public final Map<Integer, Long> totalLvlXpMap = new HashMap<>();
    public final Map<Block, Integer> blockXpValues = new HashMap<>();
    public final Map<Block, Integer> oreLevelUnlock = new HashMap<>();

    public MineSkillsVariables(){
        populateXpMap();
        populateBlockXpValuesMap();
        populateMilestoneLevels();
    }


    private void populateMilestoneLevels(){
        oreLevelUnlock.put(Block.getBlockFromItem(Items.STONE), 1);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.COAL_ORE), 1);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.COPPER_ORE), 5);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.IRON_ORE), 10);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.GOLD_ORE), 15);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.REDSTONE_ORE), 20);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.LAPIS_ORE), 30);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.DIAMOND_ORE), 35);
        oreLevelUnlock.put(Block.getBlockFromItem(Items.EMERALD_ORE), 40);
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

}
