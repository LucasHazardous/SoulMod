package lucas.hazardous.soulreaper;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulExtractorMaterial implements ToolMaterial {
    public static final SoulExtractorMaterial INSTANCE = new SoulExtractorMaterial();

    @Override
    public int getDurability() {
        return 2000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0f;
    }

    @Override
    public float getAttackDamage() {
        return 5.2f;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Registry.ITEM.get(new Identifier("minecraft", "lightning_rod")));
    }
}
