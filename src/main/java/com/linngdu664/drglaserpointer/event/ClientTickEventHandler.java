package com.linngdu664.drglaserpointer.event;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.config.ClientConfig;
import com.linngdu664.drglaserpointer.entity.LaserPointerMarkEntity;
import com.linngdu664.drglaserpointer.network.LaserDistanceRequestPayload;
import com.linngdu664.drglaserpointer.network.LaserDistanceUpdatePayload;
import com.linngdu664.drglaserpointer.network.LaserPlaySoundPayload;
import com.linngdu664.drglaserpointer.registry.ItemRegister;
import com.linngdu664.drglaserpointer.util.LaserPointerHitHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientTickEventHandler {
    private static short distance0 = -1;
    private static Item lastMainHandItem = Items.AIR;
    private static Item lastOffHandItem = Items.AIR;
    public static int mainHandLaserTick;
    public static int offHandLaserTick;
    public static HashSet<Integer> glowingEntityIds = new HashSet<>();

    @SubscribeEvent
    public static void onTick(ClientTickEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player != null) {
            Item laserPointerItem = ItemRegister.LASER_POINTER.get();
            ItemStack mainHandStack = player.getMainHandItem();
            ItemStack offHandStack = player.getOffhandItem();
            if (mainHandStack.is(laserPointerItem)) {
                mainHandLaserTick++;
            } else {
                mainHandLaserTick = 0;
            }
            if (offHandStack.is(laserPointerItem)) {
                offHandLaserTick++;
            } else {
                offHandLaserTick = 0;
            }

            // == is safe here
            if (mainHandStack.is(laserPointerItem) && lastMainHandItem != laserPointerItem || offHandStack.is(laserPointerItem) && lastOffHandItem != laserPointerItem) {
                PacketDistributor.sendToServer(new LaserPlaySoundPayload(true));
                distance0 = -1;
            } else if (!mainHandStack.is(laserPointerItem) && lastMainHandItem == laserPointerItem || !offHandStack.is(laserPointerItem) && lastOffHandItem == laserPointerItem) {
                PacketDistributor.sendToServer(new LaserPlaySoundPayload(false));
            }

            if (mainHandStack.is(laserPointerItem) || offHandStack.is(laserPointerItem)) {
                short dis = (short) Math.round(LaserPointerHitHelper.getInstance().getLaserDistance() * 64F);
                if (distance0 != dis) {
                    PacketDistributor.sendToServer(new LaserDistanceUpdatePayload(dis));
                    distance0 = dis;
                }
            }

            lastMainHandItem = mainHandStack.getItem();
            lastOffHandItem = offHandStack.getItem();

            Level level = player.level();
            double markDisplayRangeSq = ClientConfig.MARK_DISPLAY_RANGE.getConfigValue() * ClientConfig.MARK_DISPLAY_RANGE.getConfigValue();
            List<LaserPointerMarkEntity> list = level.getEntitiesOfClass(LaserPointerMarkEntity.class, player.getBoundingBox().inflate(ClientConfig.MARK_DISPLAY_RANGE.getConfigValue()), p -> player.distanceToSqr(p) <= markDisplayRangeSq);
            glowingEntityIds.clear();
            for (LaserPointerMarkEntity markEntity : list) {
                Entity entity = level.getEntity(markEntity.getTargetEntityId());
                if (entity instanceof LivingEntity) {
                    glowingEntityIds.add(markEntity.getTargetEntityId());
                }
            }

            List<AbstractClientPlayer> playerList = mc.level.players();
            AABB aabb = player.getBoundingBox().inflate(LaserPointerHitHelper.LASER_RANGE);
            ArrayList<Integer> ids = new ArrayList<>();
            for (AbstractClientPlayer p : playerList) {
                if (aabb.contains(p.getPosition(1)) && !p.isSpectator() && (p.getMainHandItem().is(laserPointerItem) || p.getOffhandItem().is(laserPointerItem)) && !p.equals(player)) {
                    ids.add(p.getId());
                }
            }
            if (!ids.isEmpty()) {
                PacketDistributor.sendToServer(new LaserDistanceRequestPayload(ids));
            }
        }
    }
}
