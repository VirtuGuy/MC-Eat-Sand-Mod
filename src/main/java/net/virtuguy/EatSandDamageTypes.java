package net.virtuguy;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class EatSandDamageTypes {
    public static final RegistryKey<DamageType> ATE_SHARP_SAND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("eatsand", "ate_sharp_sand"));
    public static final RegistryKey<DamageType> ATE_SOUL_SAND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier("eatsand", "ate_soul_sand"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
