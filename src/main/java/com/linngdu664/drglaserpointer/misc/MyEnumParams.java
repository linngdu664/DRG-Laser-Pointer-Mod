package com.linngdu664.drglaserpointer.misc;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;

@SuppressWarnings("unused")
public class MyEnumParams {
    public static final EnumProxy<HumanoidModel.ArmPose> LASER_POINTER_ARM_POSE_ENUM_PROXY = new EnumProxy<>(
            HumanoidModel.ArmPose.class, false, (IArmPoseTransformer) (model, entity, arm) -> {
        // copy bow's
        if (arm == HumanoidArm.RIGHT) {
            model.rightArm.yRot = -0.1F + model.head.yRot;
            model.rightArm.xRot = -1.5707964F + model.head.xRot;
        } else {
            model.leftArm.yRot = 0.1F + model.head.yRot;
            model.leftArm.xRot = -1.5707964F + model.head.xRot;
        }
    }
    );
}
