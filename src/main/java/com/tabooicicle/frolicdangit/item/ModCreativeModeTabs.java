package com.tabooicicle.frolicdangit.item;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.block.ModBlocks;
import com.tabooicicle.frolicdangit.potion.ModPotions;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FrolicDangIt.MOD_ID);

    public static final Supplier<CreativeModeTab> FROLIC_PT1_TAB = CREATIVE_MODE_TAB.register("frolic_pt1_tab",
    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PEARL_STAGE_4.get()))
                    .title(Component.translatable("creativetab.frolicdangitptone.frolic_pt1"))
                            .displayItems((itemDisplayParameters, output) -> {
                                //ADD ITEMS/BLOCKS HERE, copy below for ease of use.
                                output.accept(ModItems.PROCESSING_GLASS_TUBE);
                                output.accept(ModItems.SMALL_HEATER);
                                output.accept(ModItems.PEARL_CORE);
                                output.accept(ModItems.PEARL_PROCESSOR_UPPER);
                                output.accept(ModItems.PEARL_PROCESSOR_LOWER);
                                output.accept(ModBlocks.PEARL_PROCESSOR);

                                output.accept(ModItems.CRYING_OBSIDIAN_DUST);
                                output.accept(ModItems.GROUND_SHARD);
                                output.accept(ModItems.ELDER_EYE);
                                output.accept(ModItems.FERMENTED_ELDER_EYE);
                                output.accept(createPotionStack(Items.POTION, ModPotions.ESSENCE_OF_SEEKING));
                                output.accept(ModItems.GROUND_TOTEM);
                                output.accept(ModItems.PEARL_STAGE_1);
                                output.accept(ModItems.PEARL_STAGE_2);
                                output.accept(ModItems.PEARL_STAGE_3);
                                output.accept(ModItems.PEARL_STAGE_4);






                            }).build());

    private static ItemStack createPotionStack(Item potion, Holder<Potion> essenceOfSeeking) {
        ItemStack stack = new ItemStack(potion);
        stack.set(DataComponents.POTION_CONTENTS, new PotionContents(essenceOfSeeking));
        return stack;
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
