package com.groovy.mineskills.mixin;

import com.groovy.mineskills.interfaces.MineSkillsInterface;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class MineSkillsMixin extends LivingEntity implements MineSkillsInterface {

    @Unique
    public int miningXp;
    @Unique
    public int miningLvl = 1;


    protected MineSkillsMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    public void addMiningXp(int xp){
        miningXp += xp;
    }

    public int getMiningXp(){
        return miningXp;
    }
    public void addMiningLvl(){
        miningLvl++;
    }

    public int getMiningLvl(){
        return miningLvl;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci){
        nbt.putInt("miningXp", this.miningXp);
        nbt.putInt("miningLvl", this.miningLvl);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci){
        miningXp = nbt.getInt("miningXp");
        miningLvl = nbt.getInt("miningLvl");
    }
}
