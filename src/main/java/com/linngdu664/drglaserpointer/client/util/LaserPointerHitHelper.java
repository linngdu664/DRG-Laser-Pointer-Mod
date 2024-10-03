package com.linngdu664.drglaserpointer.client.util;

import com.linngdu664.drglaserpointer.config.CommonConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraftforge.common.Tags;

public class LaserPointerHitHelper {
    public static final double LASER_RANGE = CommonConfig.LASER_RANGE.getConfigValue();
    public static final double LASER_RANGE_SQ = LASER_RANGE * LASER_RANGE;

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
        HitResult hitResult = player.pick(LASER_RANGE, partialTick, false);
        Vec3 traceBegin = player.getEyePosition(partialTick);
        double distance = hitResult.getLocation().distanceTo(traceBegin);
        Vec3 scaledViewVec = player.getViewVector(partialTick).scale(LASER_RANGE);
        Vec3 traceEnd = traceBegin.add(scaledViewVec);
        AABB aabb = player.getBoundingBox().expandTowards(scaledViewVec).inflate(1.0);
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(player, traceBegin, traceEnd, aabb, p -> !p.isSpectator() && (p.isPickable() || p instanceof ItemEntity || p instanceof Projectile), LASER_RANGE_SQ);
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

    public byte getScreenColor(Level level) {
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
    }
}
