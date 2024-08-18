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
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class LaserPointerLabelEntity extends Entity implements OwnableEntity {
    private static final int MAX_TIME = 10 * 20;
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(LaserPointerLabelEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<BlockState> TARGET_BLOCK_STATE = SynchedEntityData.defineId(LaserPointerLabelEntity.class, EntityDataSerializers.BLOCK_STATE);
    private static final EntityDataAccessor<Integer> TARGET_ENTITY_ID = SynchedEntityData.defineId(LaserPointerLabelEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> COLOR = SynchedEntityData.defineId(LaserPointerLabelEntity.class, EntityDataSerializers.BYTE);

    private int timer = 0;
    private UUID targetEntityUuid = null;

    public LaserPointerLabelEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public LaserPointerLabelEntity(EntityType<?> entityType, Level level, Player owner, Vec3 location, int entityId, byte color) {
        super(entityType, level);
        Entity entity = level.getEntity(entityId);
        if (entity != null && !entity.isRemoved()) {
            targetEntityUuid = entity.getUUID();
            entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
            entityData.set(TARGET_ENTITY_ID, entityId);
            if (entity instanceof LivingEntity) {
                moveTo(entity.position());
            } else {
                entityData.set(COLOR, color);
                moveTo(location);
            }
        }
    }

    public LaserPointerLabelEntity(EntityType<?> entityType, Level level, Player owner, Vec3 location, BlockPos blockPos, byte color) {
        super(entityType, level);
        entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
        entityData.set(TARGET_BLOCK_STATE, level.getBlockState(blockPos));
        entityData.set(COLOR, color);
        moveTo(location);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(OWNER_UUID, Optional.empty());
        builder.define(TARGET_BLOCK_STATE, Blocks.AIR.defaultBlockState());
        builder.define(TARGET_ENTITY_ID, -1);
        builder.define(COLOR, (byte) -1);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.hasUUID("Owner")) {
            entityData.set(OWNER_UUID, Optional.of(compoundTag.getUUID("Owner")));
        }
        if (compoundTag.hasUUID("TargetEntity")) {
            targetEntityUuid = compoundTag.getUUID("TargetEntity");
        }
        ItemStack stack = ItemStack.parseOptional(registryAccess(), compoundTag.getCompound("TargetBlockItemStack"));
        if (stack.getItem() instanceof BlockItem blockItem) {
            entityData.set(TARGET_BLOCK_STATE, blockItem.getBlock().defaultBlockState());
        }
        entityData.set(COLOR, compoundTag.getByte("Color"));
        timer = compoundTag.getInt("Timer");
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        if (getOwnerUUID() != null) {
            compoundTag.putUUID("Owner", getOwnerUUID());
        }
        if (targetEntityUuid != null) {
            compoundTag.putUUID("TargetEntity", targetEntityUuid);
        }
        compoundTag.put("TargetBlockItemStack", entityData.get(TARGET_BLOCK_STATE).getBlock().asItem().getDefaultInstance().saveOptional(registryAccess()));
        compoundTag.putInt("Color", entityData.get(COLOR));
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
            if (targetEntityUuid != null) {
                if (entityData.get(TARGET_ENTITY_ID) == -1) {
                    Entity entity = ((ServerLevel) level).getEntity(targetEntityUuid);
                    if (entity == null || entity.isRemoved()) {
                        discard();
                        return;
                    }
                    entityData.set(TARGET_ENTITY_ID, entity.getId());
                }
                Entity entity = level.getEntity(entityData.get(TARGET_ENTITY_ID));
                if (entity == null || entity.isRemoved()) {
                    discard();
                    return;
                }
                if (entity instanceof LivingEntity livingEntity) {
                    moveTo(livingEntity.position());
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 2, 1));
                }
            }
            timer++;
        }
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double pDistance) {
        double d0 = 64 * getViewScale();
        return pDistance < d0 * d0;
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return entityData.get(OWNER_UUID).orElse(null);
    }

    public BlockState getTargetBlockState() {
        return entityData.get(TARGET_BLOCK_STATE);
    }

    public int getTargetEntityId() {
        return entityData.get(TARGET_ENTITY_ID);
    }

    public byte getColor() {
        return entityData.get(COLOR);
    }
}
