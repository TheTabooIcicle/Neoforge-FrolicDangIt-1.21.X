package com.tabooicicle.frolicdangit.potion;

import com.tabooicicle.frolicdangit.FrolicDangIt;
import com.tabooicicle.frolicdangit.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, FrolicDangIt.MOD_ID);

    public static final DeferredHolder<Potion, Potion> ESSENCE_OF_SEEKING = POTIONS.register("essence_of_seeking",
            () -> new Potion(
                    new MobEffectInstance(ModEffects.SNEAKY_BLIND, 4800, 2),
                    new MobEffectInstance(ModEffects.SNEAKY_INVIS, 4800,0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
