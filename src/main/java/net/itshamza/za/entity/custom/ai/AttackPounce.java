package net.itshamza.za.entity.custom.ai;

import net.itshamza.za.entity.custom.JaguarEntity;
import net.itshamza.za.item.ModItems;
import net.itshamza.za.misc.ZAAdvancementTriggerRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class AttackPounce extends Goal {

    private final JaguarEntity jaguar;
    private LivingEntity target;
    private boolean willJump = true;
    private boolean hasJumped = false;
    private boolean clockwise = false;
    private int pursuitTime = 0;
    private int maxPursuitTime = 0;
    private BlockPos pursuitPos = null;
    private int startingOrbit = 0;
    public int numOfTeeth = 4;

    public AttackPounce(JaguarEntity jaguar) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.jaguar = jaguar;
    }

    @Override
    public boolean canUse() {
        if(jaguar.getTarget() != null && jaguar.getTarget().isAlive()) {
            return true;
        }
        return false;
    }

    public void start() {
        target = jaguar.getTarget();
        willJump = true;
        hasJumped = false;
        clockwise = jaguar.getRandom().nextBoolean();
        pursuitPos = null;
        pursuitTime = 0;
        maxPursuitTime = 40 + jaguar.getRandom().nextInt(1);
        startingOrbit = jaguar.getRandom().nextInt(360);
        this.jaguar.frostJump();
    }

    public void tick() {
        jaguar.setBipedal(true);
        boolean flag = false;
        if ((hasJumped || jaguar.isTackling()) && jaguar.isOnGround()) {
            hasJumped = false;
            willJump = false;
            jaguar.setTackling(false);
        }
        if (target != null && target.isAlive()) {
            if (pursuitTime < maxPursuitTime) {
                pursuitTime++;
                pursuitPos = getBlockNearTarget();

                float extraSpeed = 0.2F * Math.max(5F - jaguar.distanceTo(target), 0F);
                if (pursuitPos != null) {
                    jaguar.getNavigation().moveTo(pursuitPos.getX(), pursuitPos.getY(), pursuitPos.getZ(), 1.0F + extraSpeed);
                }else{
                    jaguar.getNavigation().moveTo(target, 1.0F);
                }
            } else if (willJump && pursuitTime == maxPursuitTime) {
                jaguar.lookAt(target, 180F, 10F);
                if (jaguar.distanceTo(target) > 20F) {
                    jaguar.getNavigation().moveTo(target, 1.0F);
                } else if (jaguar.isOnGround() && jaguar.hasLineOfSight(target)) {
                    this.jaguar.setTackling(true);
                    hasJumped = true;
                    jaguar.setStealth(false);
                    Vec3 vector3d = this.jaguar.getDeltaMovement();
                    Vec3 vector3d1 = new Vec3(this.target.getX() - this.jaguar.getX(), 0.0D, this.target.getZ() - this.jaguar.getZ());
                    if (vector3d1.lengthSqr() > 1.0E-7D) {
                        vector3d1 = vector3d1.normalize().scale(0.9D).add(vector3d.scale(0.8D));
                    }
                    this.jaguar.setDeltaMovement(vector3d1.x, 0.6F, vector3d1.z);
                } else {
                    flag = true;
                }
            } else {
                if (!jaguar.isTackling()) {
                    jaguar.getNavigation().moveTo(target, 1.0F);
                }
            }
            if (jaguar.isTackling() && jaguar.distanceTo(target) <= jaguar.getBbWidth() + target.getBbWidth() + 1.1F) {
                if(target instanceof Player){
                    Player player = (Player)target;
                    if(player.isBlocking()){
                        if(numOfTeeth > 0){
                            jaguar.spawnAtLocation(ModItems.JAGUAR_TOOTH.get());
                            numOfTeeth--;
                        }else{
                            for(ServerPlayer serverplayerentity : jaguar.level.getEntitiesOfClass(ServerPlayer.class, jaguar.getBoundingBox().inflate(40.0D, 25.0D, 40.0D))) {
                                ZAAdvancementTriggerRegistry.TOOTHLESS.trigger(serverplayerentity);
                            }
                        }
                    }
                }
                jaguar.doHurtTarget(target);
                start();
            }
            if (!flag) {
                if (jaguar.distanceTo(target) <= jaguar.getBbWidth() + target.getBbWidth() + 1.1F) {
                    if (pursuitTime == maxPursuitTime) {
                        if (!jaguar.isTackling()) {
                            if(target instanceof Player){
                                Player player = (Player)target;
                                if(player.isBlocking()){
                                    if(numOfTeeth > 0){
                                        jaguar.spawnAtLocation(ModItems.JAGUAR_TOOTH.get());
                                        numOfTeeth--;
                                    }else if(numOfTeeth <= 0){
                                        for(ServerPlayer serverplayerentity : jaguar.level.getEntitiesOfClass(ServerPlayer.class, jaguar.getBoundingBox().inflate(40.0D, 25.0D, 40.0D))) {
                                            ZAAdvancementTriggerRegistry.TOOTHLESS.trigger(serverplayerentity);
                                        }
                                    }
                                }
                            }
                        }
                        jaguar.doHurtTarget(target);
                        start();
                    }
                }
            }
        }
        if (!jaguar.isOnGround()) {
            jaguar.lookAt(target, 180F, 10F);
            jaguar.yBodyRot = jaguar.getYRot();
        }
    }

    public BlockPos getBlockNearTarget() {
        float radius = jaguar.getRandom().nextInt(5) + 3 + target.getBbWidth();
        float neg = jaguar.getRandom().nextBoolean() ? 1 : -1;
        float renderYawOffset = jaguar.yBodyRot;
        int orbit = (int) (startingOrbit + (pursuitTime / (float) maxPursuitTime) * 360);
        float angle = (0.01745329251F * (clockwise ? -orbit : orbit));
        double extraX = radius * Mth.sin((float) (Math.PI + angle));
        double extraZ = radius * Mth.cos(angle);
        BlockPos circlePos = new BlockPos(target.getX() + extraX, target.getEyeY(), target.getZ() + extraZ);
        while (!jaguar.level.getBlockState(circlePos).isAir() && circlePos.getY() < jaguar.level.getMaxBuildHeight()) {
            circlePos = circlePos.above();
        }
        while (!jaguar.level.getBlockState(circlePos.below()).entityCanStandOn(jaguar.level, circlePos.below(), jaguar) && circlePos.getY() > 1) {
            circlePos = circlePos.below();
        }
        if (jaguar.getWalkTargetValue(circlePos) > -1) {
            return circlePos;
        }
        return null;
    }

    public void stop() {
        jaguar.setTackling(false);
    }
}
