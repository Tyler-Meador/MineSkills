package com.groovy.mineskills.skills;

import com.groovy.mineskills.interfaces.MineSkillsInterface;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import com.groovy.mineskills.variables.MineSkillsVariables;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.Random;

public class MiningSkill extends MineSkillsVariables {

    public MiningSkill(){
    }

    public void mineSkillsMiningHandler(){
        PlayerBlockBreakEvents.BEFORE.register((((world, player, pos, state, blockEntity) -> {
            if(super.oreLevelUnlock.containsKey(state.getBlock())){
                if(super.oreLevelUnlock.get(state.getBlock()) > ((MineSkillsInterface)player).getMiningLvl()){
                    player.sendMessage(Text.of("Your Mining Level is Too Low!!"), true);
                    return false;
                }
            }
            if (super.blockXpValues.containsKey(state.getBlock())) {
                performMiningIncrease(player, super.blockXpValues.get(state.getBlock()), state);
                determineBonus(player, state);
            }
            return true;
        })));
    }
    public void performMiningIncrease(PlayerEntity player, int xp, BlockState state){
        ((MineSkillsInterface)player).addMiningXp(xp);
        player.sendMessage(Text.of("+" + xp), false);

        while(((MineSkillsInterface)player).getMiningXp() > super.totalLvlXpMap.get(((MineSkillsInterface)player).getMiningLvl())){
            performLevelUp(player);
        }
    }

    public void performLevelUp(PlayerEntity player){
        ((MineSkillsInterface)player).addMiningLvl();
        player.sendMessage(Text.of("Congratulations! Your Mining Level Has Increased To: " + ((MineSkillsInterface)player).getMiningLvl()), true);
        if(super.oreLevelUnlock.containsValue((((MineSkillsInterface)player).getMiningLvl()))){
            System.out.println("Milestone Level Increase Time");
            int[] temp = new int[1];
            int[] levelUnlock;
            if(((MineSkillsInterface)player).getOreUnlocks() != null){
                levelUnlock = new int[temp.length + 1];
            }else{
                levelUnlock = temp;
            }
            for(int i : temp){
                levelUnlock[i] = temp[i];
            }
            levelUnlock[levelUnlock.length - 1] = ((MineSkillsInterface)player).getMiningLvl();
            ((MineSkillsInterface)player).setOreUnlocks(levelUnlock);
            player.sendMessage(Text.of("Congratulations! You Reached A New Milestone!: "), false);
        }

    }

    public void determineBonus(PlayerEntity player, BlockState state){
        Random rn = new Random();
        int chance;
        int level = ((MineSkillsInterface)player).getMiningLvl();
        if(state.getBlock().equals(Block.getBlockFromItem(Items.COAL_ORE))){
            if( level >= 5) {
                chance = (rn.nextInt(10) + 1);
                bonusItem(Items.COAL, chance, player);
            }else if(level == 4){
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.COAL, chance, player);
            }
        } else if(state.getBlock().equals(Block.getBlockFromItem(Items.COPPER_ORE))){
            if(level >= 13){
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.RAW_COPPER, chance, player);
            }
        } else if(state.getBlock().equals((Block.getBlockFromItem(Items.IRON_ORE)))){
            if(level >= 13){
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.RAW_IRON, chance, player);
            }
        } else if(state.getBlock().equals((Block.getBlockFromItem(Items.GOLD_ORE)))) {
            if (level >= 20) {
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.RAW_GOLD, chance, player);
            }
        } else if(state.getBlock().equals((Block.getBlockFromItem(Items.REDSTONE_ORE)))) {
            if (level >= 27) {
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.REDSTONE, chance, player);
            }
        } else if(state.getBlock().equals((Block.getBlockFromItem(Items.LAPIS_ORE)))) {
            if (level >= 36) {
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.LAPIS_LAZULI, chance, player);
            }
        } else if(state.getBlock().equals((Block.getBlockFromItem(Items.DIAMOND_ORE)))) {
            if (level >= 42) {
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.DIAMOND, chance, player);
            }
        } else if(state.getBlock().equals((Block.getBlockFromItem(Items.EMERALD_ORE)))) {
            if (level >= 49) {
                chance = rn.nextInt(20) + 1;
                bonusItem(Items.EMERALD, chance, player);
            }
        }
    }

    public void bonusItem(ItemConvertible item, int chance, PlayerEntity player){
        if(chance == 1){
            player.dropItem(item);
            player.sendMessage(Text.of("You found an extra ore while mining..."), false);
        }
    }

}
