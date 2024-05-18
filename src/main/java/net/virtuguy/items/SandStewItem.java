package net.virtuguy.items;

import net.minecraft.client.item.TooltipType;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SandStewItem extends StewItem {
    public SandStewItem() {
        super(new Item.Settings().food(new FoodComponent.Builder()
                .nutrition(6)
                .saturationModifier(0.6f)
                .build()
        ).maxCount(1));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.eatsand.sand_stew.tooltip").formatted(Formatting.LIGHT_PURPLE));
    }
}
