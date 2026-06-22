package com.tabooicicle.frolicdangit.item;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems  {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FrolicDangIt.MOD_ID);

    public static final DeferredItem<Item> ELDER_EYE = ITEMS.register("elder_eye",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GROUND_TOTEM = ITEMS.register("ground_totem",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GROUND_SHARD = ITEMS.register("ground_shard",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEARL_STAGE_1 = ITEMS.register("eye_stage1",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEARL_STAGE_2 = ITEMS.register("eye_stage2",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEARL_STAGE_3 = ITEMS.register("eye_stage3",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEARL_STAGE_4 = ITEMS.register("eye_stage4",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
