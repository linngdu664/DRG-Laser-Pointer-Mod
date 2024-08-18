package com.linngdu664.drglaserpointer.util;

import com.linngdu664.drglaserpointer.entity.LaserPointerLabelEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class LaserPointerHitHelper {
    public static final double LASER_MAX_DISTANCE = 64;
    public static final double LASER_MAX_DISTANCE_SQ = 64 * 64;


    private static class SingletonHandler {
        private static final LaserPointerHitHelper instance = new LaserPointerHitHelper();
    }

    private HitResult hitResult;
    private float laserDistance;

    private LaserPointerHitHelper() {}

    public static LaserPointerHitHelper getInstance() {
        return SingletonHandler.instance;
    }

    public void calcHitResult(Player player, float partialTick) {
        HitResult hitResult = player.pick(LASER_MAX_DISTANCE, partialTick, false);
        Vec3 traceBegin = player.getEyePosition(partialTick);
        double distance = hitResult.getLocation().distanceTo(traceBegin);
        Vec3 scaledViewVec = player.getViewVector(partialTick).scale(LASER_MAX_DISTANCE);
        Vec3 traceEnd = traceBegin.add(scaledViewVec);
        AABB aabb = player.getBoundingBox().expandTowards(scaledViewVec).inflate(1.0);
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(player, traceBegin, traceEnd, aabb, p -> !p.isSpectator() && !(p instanceof LaserPointerLabelEntity), LASER_MAX_DISTANCE_SQ);
        if (entityHitResult != null && entityHitResult.getLocation().distanceTo(traceBegin) < distance) {
            hitResult = entityHitResult;
        }
        this.hitResult = hitResult;
        this.laserDistance = (float) hitResult.getLocation().distanceTo(player.getEyePosition(partialTick));
    }

    public HitResult getHitResult() {
        return hitResult;
    }

    public float getLaserDistance() {
        return laserDistance;
    }
}
