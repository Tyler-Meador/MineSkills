package com.groovy.mineskills.interfaces;


public interface MineSkillsInterface {
    void addMiningXp(int amount);
    int getMiningXp();
    int getMiningLvl();
    void addMiningLvl();
    int[] getOreUnlocks();
    void setOreUnlocks(int[] miningMilestone);
}
