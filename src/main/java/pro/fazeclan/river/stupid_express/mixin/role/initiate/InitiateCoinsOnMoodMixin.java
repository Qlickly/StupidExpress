package pro.fazeclan.river.stupid_express.mixin.role.initiate;

import dev.doctor4t.wathe.api.WatheRoles;
import dev.doctor4t.wathe.cca.GameWorldComponent;
import dev.doctor4t.wathe.cca.PlayerMoodComponent;
import dev.doctor4t.wathe.cca.PlayerShopComponent;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pro.fazeclan.river.stupid_express.constants.SERoles;

@Mixin(PlayerMoodComponent.class)
public abstract class InitiateCoinsOnMoodMixin {

    @Shadow
    @Final
    private Player player;

    @Shadow
    public abstract float getMood();

    @Inject(method = "setMood", at = @At("HEAD"))
    private void initiateCoinsForMoodUp(float mood, CallbackInfo ci) {
        var gameWorldComponent = GameWorldComponent.KEY.get(player.level());
        if (mood < getMood()) {
            return;
        }
        if (!gameWorldComponent.isRole(player, SERoles.INITIATE)) {
            return;
        }
        if (WatheRoles.ROLES.stream().anyMatch(role -> role.identifier().getNamespace().equals("noellesroles"))) {
            return;
        }
        var shopComponent = PlayerShopComponent.KEY.get(player);
        shopComponent.addToBalance(50);
    }

}
