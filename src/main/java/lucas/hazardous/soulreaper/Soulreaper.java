package lucas.hazardous.soulreaper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.*;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Soulreaper implements ModInitializer{
    	public static final ItemGroup SOUL_GROUP = FabricItemGroupBuilder.create(new Identifier("soulmod", "soulmod_group")).build();

            public static final Item SOUL_SHARD = new SoulShard(new FabricItemSettings().group(SOUL_GROUP).maxCount(1).fireproof());
            public static final Block SOUL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(10.0f).resistance(2000.0f).luminance(2000).requiresTool());

            public static final ArmorMaterial SOUL_SHARD_MATERIAL = new SoulShardMaterial();

            public static final Item SOUL_SHARD_BOOTS = new ArmorItem(SOUL_SHARD_MATERIAL, EquipmentSlot.FEET, new FabricItemSettings().group(SOUL_GROUP).fireproof());

            public static final ToolItem SOUL_EXTRACTOR = new SoulExtractorItem(SoulExtractorMaterial.INSTANCE, 5,1.2f, new FabricItemSettings().group(SOUL_GROUP).maxCount(1));

            public static final Item SOUL_TOTEM = new Item(new FabricItemSettings().group(SOUL_GROUP).fireproof().maxCount(1).rarity(Rarity.EPIC));

            @Override
            public void onInitialize() {
                Registry.register(Registry.ITEM, new Identifier("soulmod", "soul_shard"), SOUL_SHARD);

                Registry.register(Registry.BLOCK, new Identifier("soulmod", "soul_block"), SOUL_BLOCK);
                Registry.register(Registry.ITEM, new Identifier("soulmod", "soul_block"), new BlockItem(SOUL_BLOCK, new FabricItemSettings().group(SOUL_GROUP)));

                Registry.register(Registry.ITEM, new Identifier("soulmod", "soul_shard_boots"), SOUL_SHARD_BOOTS);

                Registry.register(Registry.ITEM, new Identifier("soulmod", "soul_extractor"), SOUL_EXTRACTOR);

                Registry.register(Registry.ITEM, new Identifier("soulmod", "soul_totem"), SOUL_TOTEM);

                VillagerKillInterface.EVENT.register((player, villager) -> {
                    if(player.isHolding(SOUL_EXTRACTOR)) {
                        villager.setHealth(0.0f);
                        ItemStack stack = new ItemStack(SOUL_SHARD);
                        ItemEntity itemEntity = new ItemEntity(player.world, villager.getX(), villager.getY(), villager.getZ(), stack);
                        player.sendMessage(new LiteralText("You reaped his soul."), false);
                        player.world.spawnEntity(itemEntity);
                    }
                    return ActionResult.SUCCESS;
                });
           }
}
