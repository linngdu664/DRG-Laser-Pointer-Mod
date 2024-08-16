package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.Main;
import com.linngdu664.drglaserpointer.item.LaserPointerItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MODID);
    public static final DeferredItem<LaserPointerItem> LASER_POINTER = ITEMS.register("laser_pointer", LaserPointerItem::new);
}
