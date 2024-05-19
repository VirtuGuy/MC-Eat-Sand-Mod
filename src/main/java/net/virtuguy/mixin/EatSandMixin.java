package net.virtuguy.mixin;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.virtuguy.EatSandDamageTypes;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(AbstractBlock.class)
public class EatSandMixin {
	@Inject(at = @At("HEAD"), method = "onUse")
	private void eatSand(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir){
		// A list of blocks you can eat
		ArrayList<Block> blocksAllowed = new ArrayList<>();
		blocksAllowed.add(Blocks.SAND);
		blocksAllowed.add(Blocks.SUSPICIOUS_SAND);
		blocksAllowed.add(Blocks.SANDSTONE);
		blocksAllowed.add(Blocks.SANDSTONE_STAIRS);
		blocksAllowed.add(Blocks.SANDSTONE_SLAB);
		blocksAllowed.add(Blocks.SANDSTONE_WALL);
		blocksAllowed.add(Blocks.CHISELED_SANDSTONE);
		blocksAllowed.add(Blocks.SMOOTH_SANDSTONE);
		blocksAllowed.add(Blocks.SMOOTH_SANDSTONE_STAIRS);
		blocksAllowed.add(Blocks.SMOOTH_SANDSTONE_SLAB);
		blocksAllowed.add(Blocks.CUT_SANDSTONE);
		blocksAllowed.add(Blocks.CUT_SANDSTONE_SLAB);
		blocksAllowed.add(Blocks.RED_SAND);
		blocksAllowed.add(Blocks.RED_SANDSTONE);
		blocksAllowed.add(Blocks.RED_SANDSTONE_STAIRS);
		blocksAllowed.add(Blocks.RED_SANDSTONE_SLAB);
		blocksAllowed.add(Blocks.RED_SANDSTONE_WALL);
		blocksAllowed.add(Blocks.CHISELED_RED_SANDSTONE);
		blocksAllowed.add(Blocks.SMOOTH_RED_SANDSTONE);
		blocksAllowed.add(Blocks.SMOOTH_RED_SANDSTONE_STAIRS);
		blocksAllowed.add(Blocks.SMOOTH_RED_SANDSTONE_SLAB);
		blocksAllowed.add(Blocks.CUT_RED_SANDSTONE);
		blocksAllowed.add(Blocks.CUT_RED_SANDSTONE_SLAB);
		blocksAllowed.add(Blocks.SOUL_SAND);

		// A list of blocks that hurt when you eat them

        // Adds every block allowed into the list, but removes some of them after
        ArrayList<Block> hardBlocks = new ArrayList<>(blocksAllowed);

		// Removes the blocks that don't hurt
		hardBlocks.remove(Blocks.SAND);
		hardBlocks.remove(Blocks.SUSPICIOUS_SAND);
		hardBlocks.remove(Blocks.RED_SAND);
		hardBlocks.remove(Blocks.SOUL_SAND);

		// A list of blocks that are spicy
		ArrayList<Block> spicyBlocks = new ArrayList<>();
		spicyBlocks.add(Blocks.RED_SAND);
		spicyBlocks.add(Blocks.RED_SANDSTONE);
		spicyBlocks.add(Blocks.RED_SANDSTONE_STAIRS);
		spicyBlocks.add(Blocks.RED_SANDSTONE_SLAB);
		spicyBlocks.add(Blocks.RED_SANDSTONE_WALL);
		spicyBlocks.add(Blocks.CHISELED_RED_SANDSTONE);
		spicyBlocks.add(Blocks.SMOOTH_RED_SANDSTONE);
		spicyBlocks.add(Blocks.SMOOTH_RED_SANDSTONE_STAIRS);
		spicyBlocks.add(Blocks.SMOOTH_RED_SANDSTONE_SLAB);
		spicyBlocks.add(Blocks.CUT_RED_SANDSTONE);
		spicyBlocks.add(Blocks.CUT_RED_SANDSTONE_SLAB);

		// Booleans that check stuff
		boolean handsEmpty = player.getMainHandStack().isEmpty() && player.getOffHandStack().isEmpty();
		boolean isBlock = blocksAllowed.contains(state.getBlock());
		boolean isHard = hardBlocks.contains(state.getBlock());
		boolean isSpicy = spicyBlocks.contains(state.getBlock());

		// Actual sand eating code
		if (handsEmpty && isBlock) {
			if (!world.isClient()) {
				// Crunch sound
				world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1f, 1f);

				// OUCH! Too hard and sharp
				if (isHard)
					player.damage(EatSandDamageTypes.of(world, EatSandDamageTypes.ATE_SHARP_SAND), 2.5f);
				else if (!isSpicy)
					player.heal(1.5f);

				if (isSpicy) {
					if (!player.isCreative())
						player.setOnFireFor(10);
				}

				if (state.getBlock().equals(Blocks.SOUL_SAND))
					player.damage(EatSandDamageTypes.of(world, EatSandDamageTypes.ATE_SOUL_SAND), 5f);

				world.breakBlock(pos, false, null);
			}
		}
	}
}