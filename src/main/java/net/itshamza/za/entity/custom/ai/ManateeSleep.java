package net.itshamza.za.entity.custom.ai;

import net.itshamza.za.entity.custom.ManateeEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ManateeSleep extends Goal {
    private final ManateeEntity manatee;

    public ManateeSleep(ManateeEntity manatee) {
        this.manatee = manatee;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return manatee.isSleeping();
    }

    @Override
    public void start() {
        this.manatee.getNavigation().stop();
        this.manatee.getDeltaMovement().add(0f, -1f, 0f);
    }
}
