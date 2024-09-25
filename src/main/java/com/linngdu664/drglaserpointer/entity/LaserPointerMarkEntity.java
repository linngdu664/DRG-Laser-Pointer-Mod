package com.linngdu664.drglaserpointer.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class LaserPointerMarkEntity extends Entity {
    private static final int MAX_TIME = 10 * 20;
    private static final EntityDataAccessor<String> OWNER_NAME = SynchedEntityData.defineId(LaserPointerMarkEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<BlockState> TARGET_BLOCK_STATE = SynchedEntityData.defineId(LaserPointerMarkEntity.class, EntityDataSerializers.BLOCK_STATE);
    private static final EntityDataAccessor<Integer> TARGET_ENTITY_ID = SynchedEntityData.defineId(LaserPointerMarkEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> COLOR = SynchedEntityData.defineId(LaserPointerMarkEntity.class, EntityDataSerializers.BYTE);

    private int timer = 0;
    private UUID targetEntityUuid = null;

    public LaserPointerMarkEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public LaserPointerMarkEntity(EntityType<?> entityType, Level level, Player owner, Vec3 location, int entityId, byte color) {
        super(entityType, level);
        Entity entity = level.getEntity(entityId);
        if (entity != null && !entity.isRemoved()) {
            targetEntityUuid = entity.getUUID();
            entityData.set(OWNER_NAME, owner.getName().getString());
            entityData.set(TARGET_ENTITY_ID, entityId);
            if (entity instanceof LivingEntity) {
                moveTo(entity.position());
            } else {
                entityData.set(COLOR, color);
                moveTo(location);
            }
        }
    }

    public LaserPointerMarkEntity(EntityType<?> entityType, Level level, Player owner, Vec3 location, BlockState blockState, byte color) {
        super(entityType, level);
        entityData.set(OWNER_NAME, owner.getName().getString());
        entityData.set(TARGET_BLOCK_STATE, blockState);
        entityData.set(COLOR, color);
        moveTo(location);
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(OWNER_NAME, "");
        entityData.define(TARGET_BLOCK_STATE, Blocks.AIR.defaultBlockState());
        entityData.define(TARGET_ENTITY_ID, -1);
        entityData.define(COLOR, (byte) -1);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        entityData.set(OWNER_NAME, compoundTag.getString("OwnerName"));
        if (compoundTag.hasUUID("TargetEntity")) {
            targetEntityUuid = compoundTag.getUUID("TargetEntity");
        }
        ItemStack stack = ItemStack.of(compoundTag.getCompound("TargetBlockItemStack"));
        if (stack.getItem() instanceof BlockItem blockItem) {
            entityData.set(TARGET_BLOCK_STATE, blockItem.getBlock().defaultBlockState());
        }
        entityData.set(COLOR, compoundTag.getByte("Color"));
        timer = compoundTag.getInt("Timer");
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        compoundTag.putString("OwnerName", entityData.get(OWNER_NAME));
        if (targetEntityUuid != null) {
            compoundTag.putUUID("TargetEntity", targetEntityUuid);
        }
        compoundTag.put("TargetBlockItemStack", entityData.get(TARGET_BLOCK_STATE).getBlock().asItem().getDefaultInstance().save(new CompoundTag()));
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

    public String getOwnerName() {
        return entityData.get(OWNER_NAME);
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
