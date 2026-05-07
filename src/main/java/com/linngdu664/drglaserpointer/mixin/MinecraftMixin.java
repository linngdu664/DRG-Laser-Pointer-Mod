package com.linngdu664.drglaserpointer.mixin;

import com.linngdu664.drglaserpointer.event.ClientTickEventHandler;
import com.mojang.blaze3d.platform.WindowEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import net.minecraft.util.thread.ReentrantBlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.client.extensions.IMinecraftExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin extends ReentrantBlockableEventLoop<Runnable> implements WindowEventHandler, IMinecraftExtension {
    public MinecraftMixin(final GameConfig gameConfig) {
        super("Client", true);
    }

    @Inject(method = "shouldEntityAppearGlowing", at = @At(value = "HEAD"), cancellable = true)
    private void shouldEntityAppearGlowing(Entity pEntity, CallbackInfoReturnable<Boolean> cir) {
        if (pEntity != null && ClientTickEventHandler.glowingEntityIds.contains(pEntity.getId())) {
            cir.setReturnValue(true);
        }
    }
}
