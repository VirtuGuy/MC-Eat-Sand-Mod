package net.virtuguy.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EatSandItemGroups {
    public static final ItemGroup FOODS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(EatSandItems.SAND_STEW))
            .displayName(Text.translatable("itemGroup.eatsand.foods_group"))
            .entries((context, entries) -> {
                entries.add(EatSandItems.SAND_STEW);
            })
            .build();

    public static void registerItemGroup(ItemGroup itemGroup, String id) {
        Registry.register(Registries.ITEM_GROUP, new Identifier("eatsand", id), itemGroup);
    }

    public static void registerItemGroups() {
        registerItemGroup(FOODS_GROUP, "foods_group");
    }
}
