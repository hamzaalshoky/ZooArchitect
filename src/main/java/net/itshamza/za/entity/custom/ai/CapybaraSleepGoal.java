package net.itshamza.za.entity.custom.ai;

import net.itshamza.za.entity.custom.CapybaraEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

public class CapybaraSleepGoal extends Goal {
    private final CapybaraEntity capy;

    public CapybaraSleepGoal(CapybaraEntity capy) {
        this.capy = capy;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return capy.isSleeping();
    }

    @Override
    public void start() {
        this.capy.getNavigation().stop();
    }
}