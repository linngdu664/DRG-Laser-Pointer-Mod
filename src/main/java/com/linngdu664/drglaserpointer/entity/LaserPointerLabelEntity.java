package com.linngdu664.drglaserpointer.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class LaserPointerLabelEntity extends Entity implements OwnableEntity {
    private static final int MAX_TIME = 10 * 20;
    private static final EntityDataAccessor<Integer> TARGET_ENTITY_ID = SynchedEntityData.defineId(LaserPointerLabelEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Optional<BlockPos>> TARGET_BLOCK_POS = SynchedEntityData.defineId(LaserPointerLabelEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(LaserPointerLabelEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private int timer;
    private UUID targetEntityUuid = null;

    public LaserPointerLabelEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public LaserPointerLabelEntity(EntityType<?> entityType, Level level, Player owner, Vec3 location, int entityId) {
        super(entityType, level);
        Entity entity = level.getEntity(entityId);
        if (entity != null && !entity.isRemoved()) {
            entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
            entityData.set(TARGET_ENTITY_ID, entityId);
            targetEntityUuid = entity.getUUID();
            if (!(entity instanceof LivingEntity)) {
                moveTo(location.x, location.y, location.z);
            }
        }
    }

    public LaserPointerLabelEntity(EntityType<?> entityType, Level level, Player owner, Vec3 location, BlockPos blockPos) {
        super(entityType, level);
        entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
        entityData.set(TARGET_BLOCK_POS, Optional.of(blockPos));
        moveTo(location.x, location.y, location.z);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(TARGET_ENTITY_ID, -1);
        builder.define(TARGET_BLOCK_POS, Optional.empty());
        builder.define(OWNER_UUID, Optional.empty());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.hasUUID("Owner")) {
            entityData.set(OWNER_UUID, Optional.of(compoundTag.getUUID("Owner")));
        }
        if (compoundTag.hasUUID("TargetEntity")) {
            targetEntityUuid = compoundTag.getUUID("TargetEntity");
        }
        int[] arr = compoundTag.getIntArray("TargetBlockPos");
        if (arr.length == 3) {
            entityData.set(TARGET_BLOCK_POS, Optional.of(new BlockPos(arr[0], arr[1], arr[2])));
        }
        timer = compoundTag.getInt("Timer");
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        if (getOwnerUUID() != null) {
            compoundTag.putUUID("Owner", getOwnerUUID());
        }
        BlockPos blockPos = getTargetBlockPos();
        if (blockPos != null) {
            compoundTag.putIntArray("TargetBlockPos", new int[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()});
        }
        if (targetEntityUuid != null) {
            compoundTag.putUUID("TargetEntity", targetEntityUuid);
        }
        compoundTag.putInt("Timer", timer);
    }

    @Override
    public void tick() {
        super.tick();
        Level level = level();
        if (!level.isClientSide()) {
            if (timer > MAX_TIME) {
                discard();
                return;
            }
            int targetEntityId = entityData.get(TARGET_ENTITY_ID);
            if (targetEntityId >= 0) {
                Entity entity = level.getEntity(targetEntityId);
                if (entity != null && !entity.isRemoved() && entity instanceof LivingEntity livingEntity) {
                    moveTo(livingEntity.position());
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 2, 1));
                }
            } else if (targetEntityUuid != null) {
                Entity entity = ((ServerLevel) level()).getEntity(targetEntityUuid);
                if (entity != null && !entity.isRemoved()) {
                    entityData.set(TARGET_ENTITY_ID, entity.getId());
                }
            }
            timer++;
        }
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double pDistance) {
        double d0 = 96 * getViewScale();
        return pDistance < d0 * d0;
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return entityData.get(OWNER_UUID).orElse(null);
    }

    public int getTargetEntityId() {
        return entityData.get(TARGET_ENTITY_ID);
    }

    public BlockPos getTargetBlockPos() {
        return entityData.get(TARGET_BLOCK_POS).orElse(null);
    }
}
