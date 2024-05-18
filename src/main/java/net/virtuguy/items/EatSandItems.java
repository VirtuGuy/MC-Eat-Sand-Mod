package net.virtuguy.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EatSandItems {
    public static final Item SAND_STEW = new SandStewItem();

    public static void registerItem(Item item, String id) {
        Registry.register(Registries.ITEM, new Identifier("eatsand", id), item);
    }

    public static void registerItems() {
        registerItem(SAND_STEW, "sand_stew");
    }
}
