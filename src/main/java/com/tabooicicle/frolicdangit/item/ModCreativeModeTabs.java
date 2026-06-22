package com.tabooicicle.frolicdangit.item;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
                                output.accept(ModItems.GROUND_SHARD);
                                output.accept(ModItems.GROUND_TOTEM);
                                output.accept(ModItems.ELDER_EYE);
                                output.accept(ModItems.PEARL_STAGE_1);
                                output.accept(ModItems.PEARL_STAGE_2);
                                output.accept(ModItems.PEARL_STAGE_3);
                                output.accept(ModItems.PEARL_STAGE_4);
                                output.accept(ModBlocks.PEARL_PROCESSOR);


                            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
