package net.virtuguy;

import net.virtuguy.items.EatSandItemGroups;
import net.virtuguy.items.EatSandItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EatSand implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("eatsand");

	@Override
	public void onInitialize() {
		EatSandItems.registerItems();
		EatSandItemGroups.registerItemGroups();
	}
}