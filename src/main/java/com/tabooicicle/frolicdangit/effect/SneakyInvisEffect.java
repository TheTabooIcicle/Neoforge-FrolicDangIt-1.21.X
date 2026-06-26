package com.tabooicicle.frolicdangit.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class SneakyInvisEffect extends MobEffect {
    public SneakyInvisEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x7F8393);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide()) {
            livingEntity.addEffect(new MobEffectInstance(
                    MobEffects.INVISIBILITY,
                    4800,
                    0,
                    false,
                    false,
                    false
            ));
        }
        return true;
    }
}
