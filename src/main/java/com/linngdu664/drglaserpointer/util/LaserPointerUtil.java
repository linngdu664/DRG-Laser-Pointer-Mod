package com.linngdu664.drglaserpointer.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LaserPointerUtil {
    public static final double LASER_MAX_DISTANCE = 64;
    public static final double LASER_MAX_DISTANCE_SQ = 64 * 64;
    public static final double LASER_WIDTH = 0.005;

    public static HitResult getHitResult(Player player, float partialTick) {
        HitResult hitResult = player.pick(LASER_MAX_DISTANCE, partialTick, false);
        Vec3 traceBegin = player.getEyePosition(partialTick);
        double distance = hitResult.getLocation().distanceTo(traceBegin);
        Vec3 scaledViewVec = player.getViewVector(partialTick).scale(LASER_MAX_DISTANCE);
        Vec3 traceEnd = traceBegin.add(scaledViewVec);
        AABB aabb = player.getBoundingBox().expandTowards(scaledViewVec).inflate(1.0);
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(player, traceBegin, traceEnd, aabb, p -> !p.isSpectator() && p.isPickable(), LASER_MAX_DISTANCE_SQ);
        if (entityHitResult != null && entityHitResult.getLocation().distanceTo(traceBegin) < distance) {
            hitResult = entityHitResult;
        }
        return hitResult;
    }
}
