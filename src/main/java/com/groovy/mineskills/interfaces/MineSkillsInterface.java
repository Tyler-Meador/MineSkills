package com.groovy.mineskills.interfaces;

import java.util.List;

public interface MineSkillsInterface {
    void addMiningXp(int amount);
    int getMiningXp();
    int getMiningLvl();
    void addMiningLvl();
    int[]  getMiningMilestone();
    void setMiningMilestone(int[] miningMilestone);
}
