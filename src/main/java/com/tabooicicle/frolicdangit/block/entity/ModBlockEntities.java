package com.tabooicicle.frolicdangit.block.entity;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FrolicDangIt.MOD_ID);
public static final Supplier<BlockEntityType<PearlProcessorBlockEntity>> PEARL_PROCESSOR_BE =
        BLOCK_ENTITIES.register("pearl_processor_be", () -> BlockEntityType.Builder.of(
                PearlProcessorBlockEntity::new, ModBlocks.PEARL_PROCESSOR.get()).build(null));



    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
