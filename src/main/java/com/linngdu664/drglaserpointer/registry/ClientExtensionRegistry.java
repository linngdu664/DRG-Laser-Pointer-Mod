package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.DrgLaserPointer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jspecify.annotations.NonNull;

@EventBusSubscriber(modid = DrgLaserPointer.MODID, value = Dist.CLIENT)
public class ClientExtensionRegistry {
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel.ArmPose getArmPose(@NonNull LivingEntity entity, @NonNull InteractionHand hand, @NonNull ItemStack itemStack) {
                return HumanoidModel.ArmPose.valueOf("DRGLASERPOINTER_LASER_POINTER");
            }
        }, ItemRegistry.LASER_POINTER);
    }
}
