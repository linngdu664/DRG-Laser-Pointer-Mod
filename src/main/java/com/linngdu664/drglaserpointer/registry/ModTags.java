package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MUSHROOMS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(Main.MODID, "mushrooms"));
        public static final TagKey<Block> RICH_BLOCKS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(Main.MODID, "rich_blocks"));
    }
}
