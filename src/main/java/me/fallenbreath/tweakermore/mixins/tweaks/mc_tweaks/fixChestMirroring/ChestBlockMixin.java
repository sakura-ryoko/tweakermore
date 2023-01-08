/*
 * This file is part of the TweakerMore project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2023  Fallen_Breath and contributors
 *
 * TweakerMore is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TweakerMore is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TweakerMore.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.fallenbreath.tweakermore.mixins.tweaks.mc_tweaks.fixChestMirroring;

import me.fallenbreath.tweakermore.config.TweakerMoreConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlock.class)
public abstract class ChestBlockMixin
{
	@Shadow @Final public static EnumProperty<ChestType> CHEST_TYPE;

	@Inject(method = "mirror", at = @At("RETURN"), cancellable = true)
	private void fixChestMirroring(BlockState state, BlockMirror mirror, CallbackInfoReturnable<BlockState> cir)
	{
		if (TweakerMoreConfigs.FIX_CHEST_MIRRORING.getBooleanValue())
		{
			ChestType chestType = state.get(CHEST_TYPE);
			if (mirror != BlockMirror.NONE && chestType != ChestType.SINGLE)
			{
				// the chest type is always reverted when mirrored
				cir.setReturnValue(cir.getReturnValue().with(CHEST_TYPE, chestType.getOpposite()));
			}
		}
	}
}
