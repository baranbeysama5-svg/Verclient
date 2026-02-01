package com.verclient.module.impl;

import com.verclient.module.Module;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class Telekinesis extends Module {
    
    private Entity target = null;
    private boolean wasDown = false;

    public Telekinesis() {
        super("Telekinesis", "Pick up and throw entities with right click.");
    }

    @Override
    public void onTick() {
        if (mc.player == null || mc.level == null) return;
        
        boolean isDown = mc.options.keyUse.isDown();
        
        if (isDown && !wasDown) {
            // Just pressed
            if (mc.crosshairPickEntity != null) {
                target = mc.crosshairPickEntity;
            }
        }
        
        if (isDown && target != null) {
            // Holding
            if (!target.isAlive()) {
                target = null;
            } else {
                Vec3 forward = mc.player.getLookAngle();
                Vec3 pos = mc.player.getEyePosition().add(forward.scale(3.0)); // 3 blocks in front
                
                target.setPos(pos);
                target.setDeltaMovement(0, 0, 0); // Freeze gravity
                target.fallDistance = 0;
            }
        }
        
        if (!isDown && wasDown) {
            // Released
            if (target != null) {
                Vec3 forward = mc.player.getLookAngle();
                target.setDeltaMovement(forward.scale(1.5)); // Throw
                target = null;
            }
        }
        
        wasDown = isDown;
    }
}
