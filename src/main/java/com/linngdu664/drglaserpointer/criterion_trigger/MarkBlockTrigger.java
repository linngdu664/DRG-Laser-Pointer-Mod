package com.linngdu664.drglaserpointer.criterion_trigger;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.critereon.EnterBlockTrigger;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MarkBlockTrigger extends SimpleCriterionTrigger<EnterBlockTrigger.TriggerInstance> {
    @Override
    public @NotNull Codec<EnterBlockTrigger.TriggerInstance> codec() {
        return EnterBlockTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, BlockState state) {
        this.trigger(player, (p_31277_) -> p_31277_.matches(state));
    }
}
