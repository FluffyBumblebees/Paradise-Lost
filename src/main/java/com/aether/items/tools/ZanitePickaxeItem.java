package com.aether.items.tools;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class ZanitePickaxeItem extends PickaxeItem {
    public ZanitePickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return super.getMiningSpeedMultiplier(stack, state) + ZaniteTool.calculateSpeedBoost(stack);
    }
}
