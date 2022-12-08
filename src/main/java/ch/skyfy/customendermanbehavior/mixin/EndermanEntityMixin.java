package ch.skyfy.customendermanbehavior.mixin;

import ch.skyfy.customendermanbehavior.config.Configs;
import kotlin.random.Random;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {

    @ModifyArgs(
            method = "teleportRandomly",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/EndermanEntity;teleportTo(DDD)Z"
            )
    )
    public void teleportRandomly(Args args) {
        var random = Random.Default;
        var negativeRandom = random.nextInt(0, 2);
        var negative = negativeRandom == 0 ? 1 : -1;

        var data = Configs.INSTANCE.getENDERMAN_CONFIG().data;
        if(!data.enableCustomTeleportationDistance)return;

        var entity = (EndermanEntity)(Object)this;

        var x = Double.valueOf(entity.getX() + (random.nextInt(data.teleportationDistanceXMin, data.teleportationDistanceXMax) * negative));
        var y = Double.valueOf(entity.getY() + (random.nextInt(data.teleportationDistanceYMin, data.teleportationDistanceYMax) * negative));
        var z = Double.valueOf(entity.getZ() + (random.nextInt(data.teleportationDistanceZMin, data.teleportationDistanceZMax) * negative));
        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }

    @Inject(method = "teleportTo(DDD)Z", at = @At("HEAD"), cancellable = true)
    public void teleport(double x, double y, double z, CallbackInfoReturnable<Boolean> cir){
        if(Configs.INSTANCE.getENDERMAN_CONFIG().data.preventEndermanFromTeleporting){
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Inject(method = "isPlayerStaring", at = @At("RETURN"), cancellable = true)
    public void staring(PlayerEntity player, CallbackInfoReturnable<Boolean> cir){
        if(Configs.INSTANCE.getENDERMAN_CONFIG().data.preventEndermanFromAttackingFirst) cir.setReturnValue(false);
    }

}
