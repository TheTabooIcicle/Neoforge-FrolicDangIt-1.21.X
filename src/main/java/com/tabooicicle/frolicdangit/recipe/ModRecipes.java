package com.tabooicicle.frolicdangit.recipe;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FrolicDangIt.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, FrolicDangIt.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PearlProcessorRecipe>> PEARL_PROCESSOR_SERIALIZER =
            SERIALIZERS.register("pearl_processor_crafting", PearlProcessorRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<PearlProcessorRecipe>> PEARL_PROCESSOR_TYPE =
            TYPES.register("pearl_processor_crafting", () -> new RecipeType<PearlProcessorRecipe>() {
                @Override
                public String toString() {
                    return "pearl_processor_crafting";
                }
            });


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
