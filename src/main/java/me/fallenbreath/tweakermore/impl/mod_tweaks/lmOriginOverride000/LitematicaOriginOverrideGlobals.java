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

package me.fallenbreath.tweakermore.impl.mod_tweaks.lmOriginOverride000;

import net.minecraft.util.math.BlockPos;

public class LitematicaOriginOverrideGlobals
{
	public static final String ORIGIN_OVERRIDE_FLAG = "OriginOverride000";
	public static final BlockPos POS_000 = new BlockPos(0, 0, 0);

	public static final ThreadLocal<Boolean> IS_USER_LOADING_SCHEMATIC = ThreadLocal.withInitial(() -> false);
}
