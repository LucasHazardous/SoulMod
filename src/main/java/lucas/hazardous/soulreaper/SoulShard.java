package lucas.hazardous.soulreaper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class SoulShard extends Item {
    public SoulShard(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(world.isNight()) {
            HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
            if(hitResult.getType() == HitResult.Type.BLOCK) {
                EntityType<?> entityType = EntityType.EVOKER;
                BlockHitResult blockHitResult = (BlockHitResult)hitResult;
                BlockPos blockPos = blockHitResult.getBlockPos();
                if(entityType.spawnFromItemStack((ServerWorld)world, itemStack, user, blockPos, SpawnReason.SPAWN_EGG, false, false) == null) {
                    return TypedActionResult.pass(itemStack);
                } else {
                    itemStack.decrement(1);
                    return TypedActionResult.consume(itemStack);
                }
            }

            world.emitGameEvent(GameEvent.ENTITY_PLACE, user);
        } else {
            itemStack.decrement(1);
            user.kill();
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

}
