package lucas.hazardous.soulreaper;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class SoulShardMaterial implements ArmorMaterial {
    private static final int[] DURABILITIES = {32, 34, 0, 0};
    private static final int[] PROTECTION = {4, 5, 0, 0};
    private final Identifier SOUL_SHARD_ID = new Identifier("soulmod", "soul_shard");

    @Override
    public int getDurability(EquipmentSlot slot) {
        return DURABILITIES[slot.getEntitySlotId()];
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Registry.ITEM.get(SOUL_SHARD_ID));
    }

    @Override
    public String getName() {
        return "soul_shard";
    }

    @Override
    public float getToughness() {
        return 9.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.2F;
    }
}
