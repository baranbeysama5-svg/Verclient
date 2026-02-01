package com.verclient.module.impl;

import com.verclient.module.Module;
import com.verclient.module.setting.BooleanSetting;
import com.verclient.module.setting.NumberSetting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KillAura extends Module {

    public NumberSetting range = new NumberSetting("Range", 4.0, 1.0, 6.0, 0.1);
    public BooleanSetting players = new BooleanSetting("Players", true);
    public BooleanSetting mobs = new BooleanSetting("Mobs", false);
    public BooleanSetting rotation = new BooleanSetting("Rotation", true);

    public KillAura() {
        super("KillAura", "Automatically attacks entities around you.");
        addSettings(range, players, mobs, rotation);
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.level == null) return;

        double rangeVal = range.value;
        List<LivingEntity> targets = mc.level.getEntitiesOfClass(LivingEntity.class, mc.player.getBoundingBox().inflate(rangeVal))
                .stream()
                .filter(e -> e != mc.player)
                .filter(e -> e.distanceTo(mc.player) <= rangeVal)
                .filter(this::isValid)
                .sorted(Comparator.comparingDouble(e -> e.distanceTo(mc.player)))
                .collect(Collectors.toList());

        if (!targets.isEmpty()) {
            LivingEntity target = targets.get(0);

            if (rotation.enabled) {
                float[] rots = getRotations(target);
                mc.player.setYRot(rots[0]);
                mc.player.setXRot(rots[1]);
            }
            
            if (mc.player.getAttackStrengthScale(0) >= 1.0f) {
                mc.gameMode.attack(mc.player, target);
                mc.player.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
            }
        }
    }

    private boolean isValid(LivingEntity entity) {
        if (!entity.isAlive()) return false;
        if (entity instanceof Player && !players.enabled) return false;
        if (entity instanceof Monster && !mobs.enabled) return false;
        return true;
    }
    
    private float[] getRotations(Entity target) {
        double d0 = target.getX() - mc.player.getX();
        double d1 = target.getY() + target.getEyeHeight() - (mc.player.getY() + mc.player.getEyeHeight());
        double d2 = target.getZ() - mc.player.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90F;
        float f1 = (float)(-(Mth.atan2(d1, d3) * (double)(180F / (float)Math.PI)));
        return new float[]{f, f1};
    }
}
