package com.groovy.mineskills.mixin;

import com.groovy.mineskills.interfaces.ItemsDroppedInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Supplier;

@Mixin(Block.class)
public abstract class BlockItemsDroppedMixin implements ItemsDroppedInterface {

    private static int itemsDroppedCount = 0;

    @Inject(method = "dropStack(Lnet/minecraft/world/World;Ljava/util/function/Supplier;Lnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private static void dropStack(World world, Supplier<ItemEntity> itemEntitySupplier, ItemStack stack, CallbackInfo ci){
        itemsDroppedCount = itemEntitySupplier.get().getStack().getCount();
    }

        @Override
    public int getItemsDroppedCount() {
        return itemsDroppedCount;
    }
}
