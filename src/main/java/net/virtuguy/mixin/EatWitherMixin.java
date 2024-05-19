package net.virtuguy.mixin;

import net.minecraft.sound.SoundEvents;
import net.virtuguy.EatSandDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EatWitherMixin {
    @Shadow public abstract EntityType<?> getType();

    @Shadow public abstract World getWorld();

    @Shadow public abstract void discard();

    @Shadow public abstract @Nullable ItemEntity dropStack(ItemStack stack);

    @Inject(at = @At("HEAD"), method = "interact")
    private void eatWither(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (getType() == EntityType.WITHER) {
            if (!getWorld().isClient()) {
                // Crunch sound
                getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1f, 1f);

                // Drops the wither skeleton skulls and the nether star
                dropStack(new ItemStack(Items.WITHER_SKELETON_SKULL, 3));
                dropStack(new ItemStack(Items.NETHER_STAR));

                // You ate soul sand, still really hurts
                player.damage(EatSandDamageTypes.of(getWorld(), EatSandDamageTypes.ATE_SOUL_SAND), 10f);

                // Despawns the entity
                discard();
            }
        }
    }
}
