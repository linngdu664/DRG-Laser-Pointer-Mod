package com.linngdu664.drglaserpointer.registry;

import com.linngdu664.drglaserpointer.DrgLaserPointer;
import com.linngdu664.drglaserpointer.item.LaserPointerItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DrgLaserPointer.MODID);
    public static final DeferredItem<LaserPointerItem> LASER_POINTER = ITEMS.register("laser_pointer", LaserPointerItem::new);
}
