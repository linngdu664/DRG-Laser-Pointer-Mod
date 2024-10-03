package com.linngdu664.drglaserpointer.criterion_trigger;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.linngdu664.drglaserpointer.Main;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class MarkBlockTrigger extends SimpleCriterionTrigger<MarkBlockTrigger.TriggerInstance> {
    private static final ResourceLocation ID = Main.makeResLoc("mark_block_trigger");
    public static final MarkBlockTrigger INSTANCE = new MarkBlockTrigger();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public MarkBlockTrigger.TriggerInstance createInstance(JsonObject pJson, EntityPredicate.Composite pPredicate, DeserializationContext pDeserializationContext) {
        Block $$3 = deserializeBlock(pJson);
        StatePropertiesPredicate $$4 = StatePropertiesPredicate.fromJson(pJson.get("state"));
        if ($$3 != null) {
            $$4.checkState($$3.getStateDefinition(), (p_31274_) -> {
                throw new JsonSyntaxException("Block " + $$3 + " has no property " + p_31274_);
            });
        }
        return new MarkBlockTrigger.TriggerInstance(pPredicate, $$3, $$4);
    }

    @Nullable
    private static Block deserializeBlock(JsonObject pJson) {
        if (pJson.has("block")) {
            ResourceLocation $$1 = new ResourceLocation(GsonHelper.getAsString(pJson, "block"));
            return BuiltInRegistries.BLOCK.getOptional($$1).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + $$1 + "'"));
        }
        return null;
    }

    public void trigger(ServerPlayer player, BlockState state) {
        this.trigger(player, (p_31277_) -> p_31277_.matches(state));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        @Nullable
        private final Block block;
        private final StatePropertiesPredicate state;

        public TriggerInstance(EntityPredicate.Composite pPlayer, @Nullable Block pBlock, StatePropertiesPredicate pState) {
            super(MarkBlockTrigger.ID, pPlayer);
            this.block = pBlock;
            this.state = pState;
        }

        public JsonObject serializeToJson(SerializationContext pConditions) {
            JsonObject $$1 = super.serializeToJson(pConditions);
            if (this.block != null) {
                $$1.addProperty("block", BuiltInRegistries.BLOCK.getKey(this.block).toString());
            }

            $$1.add("state", this.state.serializeToJson());
            return $$1;
        }

        public boolean matches(BlockState pState) {
            if (this.block != null && !pState.is(this.block)) {
                return false;
            } else {
                return this.state.matches(pState);
            }
        }
    }
}
