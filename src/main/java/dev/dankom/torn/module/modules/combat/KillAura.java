package dev.dankom.torn.module.modules.combat;

import dev.dankom.torn.Torn;
import dev.dankom.torn.event.EventTarget;
import dev.dankom.torn.event.EventType;
import dev.dankom.torn.event.events.MotionUpdateEvent;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.util.ColorUtil;
import dev.dankom.torn.util.StringUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KillAura extends Module {

    private EntityLivingBase target;
    private long current, last;
    private int delay = 8;
    private float yaw, pitch;
    private boolean others;

    public KillAura() {
        super("Killaura", "Automatically hits entities and players", Category.COMBAT, -1, new Color(255, 92, 0), true, true);
        addSetting(new Setting("AutoBlock", this, false));
        addSetting(new Setting("Invisibles", this, true));
        addSetting(new Setting("Players", this, true));
        addSetting(new Setting("Animals", this, true));
        addSetting(new Setting("Monsters", this, true));
        addSetting(new Setting("Villagers", this, true));
        addSetting(new Setting("Teams", this, true));
        addSetting(new Setting("Crack Size", this, 5, 0, 15, true));
        addSetting(new Setting("Existed", this, 30, 0, 500, true));
        addSetting(new Setting("Killaura FOV", this, 360, 0, 360, true));
    }

    @EventTarget
    public void onPre(MotionUpdateEvent event) {
        if (event.getEventType().equals(EventType.PRE)) {
            setEnabledModName(ColorUtil.translate("KillAura " + StringUtil.wrapWithSquareBracket("FOV:" + getSetting("Killaura FOV").getValDouble())));
            target = getClosest(mc.playerController.getBlockReachDistance());
            if (target == null)
                return;
            updateTime();
            yaw = mc.thePlayer.rotationYaw;
            pitch = mc.thePlayer.rotationPitch;
            boolean block = target != null && getSetting("AutoBlock").getValBoolean() && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword;
            if (block && target.getDistanceToEntity(mc.thePlayer) < 8F)
                mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
            if (current - last > 1000 / delay) {
                attack(target);
                resetTime();
            }
        }
    }

    @EventTarget
    public void onPost(MotionUpdateEvent event) {
        if (event.getEventType().equals(EventType.POST)) {
            if (target == null)
                return;
            mc.thePlayer.rotationYaw = yaw;
            mc.thePlayer.rotationPitch = pitch;
        }
    }

    private void attack(Entity entity) {
        for(int i = 0; i < getSetting("Crack Size").getValDouble(); i++)
            mc.thePlayer.onCriticalHit(entity);

        mc.thePlayer.swingItem();
        mc.playerController.attackEntity(mc.thePlayer, entity);
    }

    private void updateTime() {
        current = (System.nanoTime() / 1000000L);
    }

    private void resetTime() {
        last = (System.nanoTime() / 1000000L);
    }

    private EntityLivingBase getClosest(double range) {
        double dist = range;
        EntityLivingBase target = null;
        for (Object object : getEntities()) {
            Entity entity = (Entity) object;
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase player = (EntityLivingBase) entity;
                if (canAttack(player)) {
                    double currentDist = mc.thePlayer.getDistanceToEntity(player);
                    if (currentDist <= dist) {
                        dist = currentDist;
                        target = player;
                    }
                }
            }
        }
        return target;
    }

    private boolean canAttack(EntityLivingBase player) {
        if(player instanceof EntityPlayer || player instanceof EntityAnimal || player instanceof EntityMob || player instanceof EntityVillager) {
            if (player instanceof EntityPlayer && !getSetting("Players").getValBoolean())
                return false;
            if (player instanceof EntityAnimal && !getSetting("Animals").getValBoolean())
                return false;
            if (player instanceof EntityMob && !getSetting("Monsters").getValBoolean())
                return false;
            if (player instanceof EntityVillager && !getSetting("Villagers").getValBoolean())
                return false;
        }
        if(player.isOnSameTeam(mc.thePlayer) && getSetting("Teams").getValBoolean())
            return false;
        if(player.isInvisible() && !getSetting("Invisibles").getValBoolean())
            return false;
        if(!isInKillaura(player, getSetting("Killaura FOV").getValDouble()))
            return false;
        return player != mc.thePlayer && player.isEntityAlive() && mc.thePlayer.getDistanceToEntity(player) <= mc.playerController.getBlockReachDistance() && player.ticksExisted > getSetting("Existed").getValDouble();
    }

    private boolean isInKillaura(EntityLivingBase entity, double angle) {
        angle *= .5D;
        double angleDiff = getAngleDifference(mc.thePlayer.rotationYaw, getRotations(entity.posX, entity.posY, entity.posZ)[0]);
        return (angleDiff > 0 && angleDiff < angle) || (-angle < angleDiff && angleDiff < 0);
    }

    private float getAngleDifference(float dir, float yaw) {
        float f = Math.abs(yaw - dir) % 360F;
        float dist = f > 180F ? 360F - f : f;
        return dist;
    }

    private float[] getRotations(double x, double y, double z) {
        double diffX = x + .5D - mc.thePlayer.posX;
        double diffY = (y + .5D) / 2D - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double diffZ = z + .5D - mc.thePlayer.posZ;

        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180D / Math.PI) - 90F;
        float pitch = (float)-(Math.atan2(diffY, dist) * 180D / Math.PI);

        return new float[] { yaw, pitch };
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public List<Entity> getEntities() {
        List<Entity> out = new ArrayList<>();
        for (Entity e : Torn.getWrapper().getWorld().loadedEntityList) {
            out.add(e);
        }
        for (Entity e : Torn.getWrapper().getWorld().playerEntities) {
            out.add(e);
        }
        return out;
    }
}
