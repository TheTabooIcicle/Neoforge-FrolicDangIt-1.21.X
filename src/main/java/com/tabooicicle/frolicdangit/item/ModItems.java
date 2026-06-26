package com.tabooicicle.frolicdangit.item;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.potion.ModPotions;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.tabooicicle.frolicdangit.potion.ModPotions.POTIONS;

public class ModItems  {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FrolicDangIt.MOD_ID);

    public static final DeferredItem<Item> ELDER_EYE = ITEMS.register("elder_eye_drop",
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
    public static final DeferredItem<Item> SMALL_HEATER = ITEMS.register("small_heater",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FERMENTED_ELDER_EYE = ITEMS.register("fermented_elder_eye",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CRYING_OBSIDIAN_DUST = ITEMS.register("crying_obsidian_dust",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEARL_CORE = ITEMS.register("pearl_core",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PROCESSING_GLASS_TUBE = ITEMS.register("processing_glass_tube",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEARL_PROCESSOR_UPPER = ITEMS.register("pp_item_upper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PEARL_PROCESSOR_LOWER = ITEMS.register("pp_item_lower",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ESSENCE_OF_SEEKING = ITEMS.register("essence_of_seeking",
            () -> new PotionItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
