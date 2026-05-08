package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import net.minecraft.resources.ResourceKey;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.pipeline.PipelineModifier;
import net.neoforged.neoforge.client.pipeline.RegisterPipelineModifiersEvent;

@EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class PipelineModifierRegister {
    public static final PipelineModifier LASER_PIPELINE_MODIFIER = (pipeline, name) -> pipeline
            .toBuilder()
            .withCull(false)
            .withColorTargetState(new ColorTargetState(BlendFunction.TRANSLUCENT))
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .build();

    @SubscribeEvent
    public static void registerPipelineModifiers(RegisterPipelineModifiersEvent event) {
        event.register(ResourceKey.create(PipelineModifier.MODIFIERS_KEY, Main.makeMyIdentifier("laser_pipeline_modifier")), LASER_PIPELINE_MODIFIER);
    }
}
