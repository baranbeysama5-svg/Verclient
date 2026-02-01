package com.verclient.module.impl;

import com.verclient.module.Module;
import com.verclient.module.setting.BooleanSetting;
import com.verclient.module.setting.NumberSetting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import java.util.Comparator;
import java.util.List;

public class BowAimbot extends Module {

    public NumberSetting smoothness = new NumberSetting("Smoothness", 5.0, 1.0, 20.0, 1.0);
    public BooleanSetting glow = new BooleanSetting("Glow", true);

    public BowAimbot() {
        super("BowAimbot", "Automatically aims at entities when using a bow.");
        addSettings(smoothness, glow);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.level == null) return;

        if (mc.player.isUsingItem() && mc.player.getUseItem().getItem() instanceof BowItem) {
            LivingEntity target = getClosestTarget();
            if (target != null) {
                if (glow.enabled) {
                    target.setGlowingTag(true);
                }
                
                faceEntity(target);
            }
        } else {
             // Reset glow if we stop using bow? Ideally we track the last target.
             // For simplicity, let's just leave it for now or clear all in range.
        }
    }
    
    private LivingEntity getClosestTarget() {
        double range = 50.0;
        List<LivingEntity> targets = mc.level.getEntitiesOfClass(LivingEntity.class, mc.player.getBoundingBox().inflate(range), e -> e != mc.player && e.isAlive());
        return targets.stream().min(Comparator.comparingDouble(e -> e.distanceTo(mc.player))).orElse(null);
    }
    
    private void faceEntity(LivingEntity target) {
        double d0 = target.getX() - mc.player.getX();
        double d2 = target.getZ() - mc.player.getZ();
        double d1 = target.getEyeY() - mc.player.getEyeY();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        
        float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90F;
        float f1 = (float)(-(Mth.atan2(d1, d3) * (double)(180F / (float)Math.PI)));
        
        // Smoothness
        float smooth = (float) smoothness.value;
        mc.player.setYRot(interpolate(mc.player.getYRot(), f, smooth));
        mc.player.setXRot(interpolate(mc.player.getXRot(), f1, smooth));
    }
    
    private float interpolate(float current, float target, float smooth) {
        float diff = Mth.wrapDegrees(target - current);
        if (diff > smooth) diff = smooth;
        if (diff < -smooth) diff = -smooth;
        return current + diff;
    }
}
