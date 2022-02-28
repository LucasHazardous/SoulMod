package lucas.hazardous.soulreaper;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface VillagerKillInterface {

    Event<VillagerKillInterface> EVENT = EventFactory.createArrayBacked(VillagerKillInterface.class,
            (listeners) -> (player, villager) -> {
                for(VillagerKillInterface listener: listeners) {
                    ActionResult result = listener.interact(player, villager);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });


    ActionResult interact(PlayerEntity player, VillagerEntity villager);
}
