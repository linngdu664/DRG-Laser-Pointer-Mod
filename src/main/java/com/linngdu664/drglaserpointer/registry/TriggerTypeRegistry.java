package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.DrgLaserPointer;
import com.linngdu664.drglaserpointer.criterion_trigger.MarkBlockTrigger;
import net.minecraft.advancements.*;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TriggerTypeRegistry {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGER_TYPES = DeferredRegister.create(Registries.TRIGGER_TYPE, DrgLaserPointer.MODID);
    public static final DeferredHolder<CriterionTrigger<?>, MarkBlockTrigger> MARK_BLOCK_TRIGGER = TRIGGER_TYPES.register("mark_block_trigger", MarkBlockTrigger::new);
}
