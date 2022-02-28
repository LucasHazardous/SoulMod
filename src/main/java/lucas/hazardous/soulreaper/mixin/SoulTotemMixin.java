package lucas.hazardous.soulreaper.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import lucas.hazardous.soulreaper.Soulreaper;

@Mixin(PlayerEntity.class)
public abstract class SoulTotemMixin {
    @Inject(at = @At(value = "HEAD"), method = "getHurtSound")
    private void removePlayerKiller(DamageSource source, CallbackInfoReturnable<SoundEvent> cir) {
        PlayerEntity player = ((PlayerEntity) (Object) this);
        if(player.isHolding(Soulreaper.SOUL_TOTEM)) {
            if(source.getAttacker() != null) {
                if(source.getAttacker().isAlive()) {
                    source.getAttacker().damage(DamageSource.player(player), 99999.9f);
                }
            }
        }
    }
}
