package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.component.LaserData;
import com.linngdu664.drglaserpointer.registry.DataComponentRegister;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.util.LaserPointerHitHelper;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.Tags;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEventHandler {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ItemRegister.LASER_POINTER.get(), Main.makeResLoc("light_color"), (itemStack, level, livingEntity, num) -> itemStack.getOrDefault(DataComponentRegister.LASER_DATA, LaserData.EMPTY).colorId());
            ItemProperties.register(ItemRegister.LASER_POINTER.get(), Main.makeResLoc("screen_color"), (itemStack, level, livingEntity, num) -> {
                HitResult hitResult = LaserPointerHitHelper.getInstance().getHitResult();
                if (hitResult == null) return 0;    // dark green
                if (hitResult.getType() == HitResult.Type.ENTITY) {
                    Entity entity = ((EntityHitResult) hitResult).getEntity();
                    if (entity instanceof Enemy) return 2;          // red
                    return 1;       // green
                }
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockState blockState = level.getBlockState(((BlockHitResult) hitResult).getBlockPos());
                    if (blockState.is(Tags.Blocks.ORES)) return 1;          // green
                    if (blockState.getBlock() == Blocks.DIRT) return 3;     // yellow
                    return 0;       // dark green
                }
                return 0;           // dark green
            });
        });
    }
}
