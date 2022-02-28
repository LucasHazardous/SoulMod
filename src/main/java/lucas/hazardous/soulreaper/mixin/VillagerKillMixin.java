package lucas.hazardous.soulreaper.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import lucas.hazardous.soulreaper.VillagerKillInterface;

@Mixin(VillagerEntity.class)
public class VillagerKillMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/VillagerEntity;sayNo()V"), method = "interactMob", cancellable = true)
    private void onNo(final PlayerEntity player, final Hand hand, final CallbackInfoReturnable<Boolean> info) {
        ActionResult result = VillagerKillInterface.EVENT.invoker().interact(player, (VillagerEntity) (Object) this);

        if(result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
