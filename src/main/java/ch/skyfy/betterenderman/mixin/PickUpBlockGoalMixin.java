package ch.skyfy.betterenderman.mixin;

import ch.skyfy.betterenderman.config.Configs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/entity/mob/EndermanEntity$PickUpBlockGoal")
public class PickUpBlockGoalMixin {

    @Inject(method = "tick",at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci){
        if(!Configs.INSTANCE.getENDERMAN_CONFIG().data.allowEndermanToPickup)ci.cancel();
    }

}
