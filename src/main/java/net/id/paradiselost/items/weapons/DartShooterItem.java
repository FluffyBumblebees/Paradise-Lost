package net.id.paradiselost.items.weapons;

import net.id.paradiselost.util.ParadiseLostSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DartShooterItem extends Item {

    private final DartItem ammo;

    public DartShooterItem(DartItem ammo, Settings settings) {
        super(settings);
        this.ammo = ammo;
    }

    protected ItemStack findDartStack(PlayerEntity playerIn) {
        if (playerIn.getStackInHand(Hand.OFF_HAND).getItem() == this.ammo) {
            return playerIn.getStackInHand(Hand.OFF_HAND);
        } else if (playerIn.getStackInHand(Hand.MAIN_HAND).getItem() == this.ammo) {
            return playerIn.getStackInHand(Hand.MAIN_HAND);
        } else {
            for (int index = 0; index < playerIn.getInventory().size(); ++index) {
                ItemStack stack = playerIn.getInventory().getStack(index);
                if (stack.getItem() == ammo) {
                    return stack;
                }
            }
            return ItemStack.EMPTY;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getStackInHand(handIn);
        boolean bypassDartCheck = playerIn.isCreative() || EnchantmentHelper.getLevel(Enchantments.INFINITY, heldItem) > 0;

        ItemStack stack = this.findDartStack(playerIn);
        if (!stack.isEmpty() || bypassDartCheck) {
            if (stack.isEmpty()) {
                stack = new ItemStack(ammo);
            }

            PersistentProjectileEntity projectile = this.ammo.createDart(worldIn, heldItem, playerIn);

            if (!worldIn.isClient) {
                projectile.setVelocity(playerIn, playerIn.getPitch(), playerIn.getYaw(), 0.0F, 1.0F, 1.0F);
                worldIn.spawnEntity(projectile);

                if (!playerIn.isCreative()) {
                    projectile.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                    stack.decrement(1);
                } else if (playerIn.isCreative()) {
                    projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                }
            }
            worldIn.playSound(playerIn, playerIn.getBlockPos(), ParadiseLostSoundEvents.ITEM_DART_SHOOTER_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        return super.use(worldIn, playerIn, handIn);
    }
}