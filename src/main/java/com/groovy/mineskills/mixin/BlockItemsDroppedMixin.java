package com.groovy.mineskills.mixin;

import com.groovy.mineskills.interfaces.ItemsDroppedInterface;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Block.class)
public class BlockItemsDroppedMixin implements ItemsDroppedInterface {

    private static int itemsDroppedCount;

    @Inject(method = "dropStack(Lnet/minecraft/world/World;Ljava/util/function/Supplier;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"))
    private static void dropStack(World world, Supplier<ItemEntity> itemEntitySupplier, ItemStack stack, CallbackInfo ci){
        if (!world.isClient && !stack.isEmpty() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            ItemEntity itemEntity = (ItemEntity)itemEntitySupplier.get();
            itemsDroppedCount = itemEntity.getStack().getCount();
        }
    }

    @Override
    public int getItemsDroppedCount() {
        return itemsDroppedCount;
    }
}
