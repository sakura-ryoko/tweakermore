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

package me.fallenbreath.tweakermore.mixins.tweaks.features.serverMsptMetricsStatistic;

import me.fallenbreath.tweakermore.impl.features.serverMsptMetricsStatistic.MetricsDataWithRichStatistic;
import me.fallenbreath.tweakermore.impl.features.serverMsptMetricsStatistic.RichStatisticManager;
import net.minecraft.util.MetricsData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MetricsData.class)
public abstract class MetricsDataMixin implements MetricsDataWithRichStatistic
{
	private RichStatisticManager richStatisticManager$TKM = null;

	@Override
	public void enableRichStatistic()
	{
		if (this.richStatisticManager$TKM == null)
		{
			this.richStatisticManager$TKM = new RichStatisticManager();
		}
	}

	@Override
	@Nullable
	public RichStatisticManager getRichStatisticManager()
	{
		return this.richStatisticManager$TKM;
	}

	@Inject(method = "pushSample", at = @At("TAIL"))
	private void serverMsptMetricsStatistic_callback(long time, CallbackInfo ci)
	{
		if (this.richStatisticManager$TKM != null)
		{
			this.richStatisticManager$TKM.recordGameTickMetrics(time);
		}
	}
}
