package com.tabooicicle.frolicdangit.datagen;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.item.ModItems;
import com.tabooicicle.frolicdangit.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, FrolicDangIt.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("elder_eye_to_elder_guardian",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(
                                ResourceLocation.withDefaultNamespace("entities/elder_guardian")
                        ).build(),
                        LootItemRandomChanceCondition.randomChance(0.95f).build()
                },
                        ModItems.ELDER_EYE.get()
                )
        );
    }
}
