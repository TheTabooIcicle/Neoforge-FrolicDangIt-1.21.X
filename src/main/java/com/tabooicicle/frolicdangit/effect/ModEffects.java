package com.tabooicicle.frolicdangit.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, "frolicdangitptone");

    public static final Holder<MobEffect> SNEAKY_BLIND = MOB_EFFECTS.register("sneaky_blind",
            SneakyBlindEffect::new);

    public static final Holder<MobEffect> SNEAKY_INVIS = MOB_EFFECTS.register("sneaky_invis",
            SneakyInvisEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
