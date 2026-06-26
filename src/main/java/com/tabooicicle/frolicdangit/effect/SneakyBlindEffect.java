package com.tabooicicle.frolicdangit.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SneakyBlindEffect extends MobEffect {
    public SneakyBlindEffect() {
        super(MobEffectCategory.HARMFUL, 2696993);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.isSprinting()) {
            livingEntity.setSprinting(false);
        }
        return true;
    }
}
