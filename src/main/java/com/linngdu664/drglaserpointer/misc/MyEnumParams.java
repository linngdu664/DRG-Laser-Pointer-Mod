package com.linngdu664.drglaserpointer.misc;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

public class MyEnumParams {
    public static final EnumProxy<HumanoidModel.ArmPose> LASER_POINTER_ARM_POSE_ENUM_PROXY = new EnumProxy<>(
            HumanoidModel.ArmPose.class, false, (IArmPoseTransformer) (model, entity, arm) -> {
                if (arm == HumanoidArm.RIGHT) {
                    model.rightArm.xRot = -5 * Mth.PI / 12;
                } else {
                    model.leftArm.xRot = -5 * Mth.PI / 12;
                }
            }
    );
}
