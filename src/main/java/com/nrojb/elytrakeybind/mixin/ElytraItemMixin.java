package com.nrojb.elytrakeybind.mixin;

import com.nrojb.elytrakeybind.ElytraKeybind;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraItem.class)
public class ElytraItemMixin {

    @Shadow
    private static boolean isFlyEnabled(ItemStack stack) {
        throw new AssertionError();
    }

    @Inject(method = "canElytraFly", at = @At("HEAD"), cancellable = true)
    private void injectedCanElytraFly(ItemStack stack, LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {

        if (!ElytraKeybind.elytraToggleEnabled) {
            cir.setReturnValue(false);
            return;
        }

        cir.setReturnValue(isFlyEnabled(stack));
    }

//    @Inject(method = "isFlyEnabled", at = @At("HEAD"), cancellable = true)
//    private static void injectedisFlyEnabled(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
//
//        if (!ElytraKeybind.elytraToggleEnabled) {
//            cir.setReturnValue(false);
//            return;
//        }
//
//        cir.setReturnValue(stack.getDamageValue() < stack.getMaxDamage() - 1);
//    }
}
