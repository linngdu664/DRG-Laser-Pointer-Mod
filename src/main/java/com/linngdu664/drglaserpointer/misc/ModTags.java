package com.linngdu664.drglaserpointer.misc;

import com.linngdu664.drglaserpointer.Main;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MUSHROOMS = BlockTags.create(Main.makeResLoc("mushrooms"));
        public static final TagKey<Block> RICH_BLOCKS = BlockTags.create(Main.makeResLoc("rich_blocks"));
    }
}
