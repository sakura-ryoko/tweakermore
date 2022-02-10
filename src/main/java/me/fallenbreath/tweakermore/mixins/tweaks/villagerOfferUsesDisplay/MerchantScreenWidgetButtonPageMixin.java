package me.fallenbreath.tweakermore.mixins.tweaks.villagerOfferUsesDisplay;

import me.fallenbreath.tweakermore.config.TweakerMoreConfigs;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.screen.ingame.MerchantScreen$WidgetButtonPage")
public abstract class MerchantScreenWidgetButtonPageMixin extends ButtonWidget
{
	@SuppressWarnings("target")
	@Shadow @Final MerchantScreen field_19166;

	@Shadow @Final int index;

	public MerchantScreenWidgetButtonPageMixin(int x, int y, int width, int height, Text message, PressAction onPress)
	{
		super(x, y, width, height, message, onPress);
	}

	@Inject(
			method = "renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;II)V",
			at = @At(
					value = "FIELD",
					target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen$WidgetButtonPage;x:I",
					ordinal = 0
			)
	)
	private void renderMaxUsesAmount(MatrixStack matrices, int mouseX, int mouseY, CallbackInfo ci)
	{
		if (TweakerMoreConfigs.VILLAGER_OFFER_USES_DISPLAY.getBooleanValue())
		{
			if (this.x + 50 <= mouseX && mouseX <= this.x + 65)
			{
				TradeOffer offer = this.field_19166.getScreenHandler().getRecipes().get(this.index + ((MerchantScreenAccessor)this.field_19166).getIndexStartOffset());
				this.field_19166.renderTooltip(matrices, new LiteralText(String.format("%d / %d", offer.getUses(), offer.getMaxUses())), mouseX, mouseY);
			}
		}
	}
}
