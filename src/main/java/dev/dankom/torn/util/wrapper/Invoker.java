package dev.dankom.torn.util.wrapper;

import dev.dankom.torn.Torn;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Invoker {
    private Wrapper wrapper;

    public Invoker(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    private String entityLivingBaseLoc = "net.minecraft.entity.EntityLivingBase";

    public void sendChatMessage(String msg){
        wrapper.getPlayer().sendChatMessage(msg);
    }

    public float getRotationYaw(){
        return wrapper.getPlayer().rotationYaw;
    }

    public float getRotationPitch(){
        return wrapper.getPlayer().rotationPitch;
    }

    public void setRotationYaw(float yaw){
        wrapper.getPlayer().rotationYaw = yaw;
    }

    public void setRotationPitch(float pitch){
        wrapper.getPlayer().rotationPitch = pitch;
    }

    public void setSprinting(boolean sprinting){
        wrapper.getPlayer().setSprinting(sprinting);
    }

    public boolean isOnLadder(){
        return wrapper.getPlayer().isOnLadder();
    }

    public float moveForward(){
        return wrapper.getPlayer().moveForward;
    }

    public boolean isCollidedHorizontally(){
        return wrapper.getPlayer().isCollidedHorizontally;
    }

    public void setMotionX(double x){
        wrapper.getPlayer().motionX = x;
    }

    public void setMotionY(double y){
        wrapper.getPlayer().motionY = y;
    }

    public void setMotionZ(double z){
        wrapper.getPlayer().motionZ = z;
    }

    public double getMotionX(){
        return wrapper.getPlayer().motionX;
    }

    public double getMotionY(){
        return wrapper.getPlayer().motionY;
    }

    public double getMotionZ(){
        return wrapper.getPlayer().motionZ;
    }

    public void setLandMovementFactor(float newFactor){
        try{
            Class elb = Class.forName(entityLivingBaseLoc);
            Field landMovement = elb.getDeclaredField("landMovementFactor");
            landMovement.setAccessible(true);
            landMovement.set(wrapper.getPlayer(), newFactor);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setJumpMovementFactor(float newFactor){
        try{
            Class elb = Class.forName(entityLivingBaseLoc);
            Field landMovement = elb.getDeclaredField("jumpMovementFactor");
            landMovement.setAccessible(true);
            landMovement.set(wrapper.getPlayer(), newFactor);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public float getGammaSetting(){
        return wrapper.getGameSettings().gammaSetting;
    }

    public void setGammaSetting(float newSetting){
        wrapper.getGameSettings().gammaSetting = newSetting;
    }

    public int getJumpCode(){
        return wrapper.getGameSettings().keyBindJump.getKeyCode();
    }

    public int getForwardCode(){
        return wrapper.getGameSettings().keyBindForward.getKeyCode();
    }

    public synchronized void displayScreen(GuiScreen screen){
        wrapper.getMinecraft().displayGuiScreen(screen);
    }

    public List getEntityList(){
        if (Minecraft.getMinecraft().currentScreen == null) {
            return Minecraft.getMinecraft().theWorld.loadedEntityList;
        }
        return new ArrayList();
    }

    public float getDistanceToEntity(Entity from, Entity to){
        return from.getDistanceToEntity(to);
    }

    public boolean isEntityDead(Entity e){
        return e.isDead;
    }

    public boolean canEntityBeSeen(Entity e){
        return wrapper.getPlayer().canEntityBeSeen(e);
    }

    public void attackEntity(Entity e){
        wrapper.getPlayerController().attackEntity(wrapper.getPlayer(), e);
    }

    public void swingItem(){
        wrapper.getPlayer().swingItem();
    }

    public float getEyeHeight(){
        return wrapper.getPlayer().getEyeHeight();
    }

    public float getEyeHeight(Entity e){
        return e.getEyeHeight();
    }

    public double getPosX(){
        return wrapper.getPlayer().posX;
    }

    public double getPosY(){
        return wrapper.getPlayer().posY;
    }

    public double getPosZ(){
        return wrapper.getPlayer().posZ;
    }

    public double getPosX(Entity e){
        return e.posX;
    }

    public double getPosY(Entity e){
        return e.posY;
    }

    public double getPosZ(Entity e){
        return e.posZ;
    }

    public void setInvSlot(int slot){
        wrapper.getPlayer().inventory.currentItem = slot;
    }

    public int getCurInvSlot(){
        return wrapper.getPlayer().inventory.currentItem;
    }

    public ItemStack getCurrentItem(){
        return wrapper.getPlayer().getCurrentEquippedItem();
    }

    public ItemStack getItemAtSlot(int slot){
        return wrapper.getPlayer().inventoryContainer.getSlot(slot).getStack();
    }

    public ItemStack getItemAtSlotHotbar(int slot){
        return wrapper.getPlayer().inventory.getStackInSlot(slot);
    }

    public int getIdFromItem(Item item){
        return Item.getIdFromItem(item);
    }

    public void clickWindow(int slot, int mode, int button, EntityPlayer player){
        wrapper.getPlayerController().windowClick(player.inventoryContainer.windowId, slot, button, mode, player);
    }

    public void clickWindow(int id, int slot, int mode, int button, EntityPlayer player){
        wrapper.getPlayerController().windowClick(id, slot, button, mode, player);
    }

    public void sendUseItem(ItemStack itemStack, EntityPlayer player){
        try {
            if (wrapper != null && itemStack != null && player != null && wrapper.getWorld() != null) {
                return;
            }
            wrapper.getPlayerController().sendUseItem(player, wrapper.getWorld(), itemStack);
        } catch (NullPointerException e) {
            return;
        }
    }

    public Item getItemById(int id){
        return Item.getItemById(id);
    }

    public void dropItemStack(int slot){
        for(int i=0; i< wrapper.getPlayer().inventory.getStackInSlot(slot).stackSize; i++){
            wrapper.getPlayer().dropOneItem(false);
        }
    }

    public Entity getEntityById(int id){
        return wrapper.getWorld().getEntityByID(id);
    }

    public MovingObjectPosition getObjectMouseOver(){
        return wrapper.getMinecraft().objectMouseOver;
    }

    public void useItemRightClick(ItemStack item){
        item.useItemRightClick(wrapper.getWorld(), wrapper.getPlayer());
    }

    public ItemStack[] getArmourInventory(){
        return wrapper.getPlayer().inventory.armorInventory;
    }

    public void enableStandardItemLighting(){
        RenderHelper.enableStandardItemLighting();
    }

    public void disableStandardItemLighting(){
        RenderHelper.disableStandardItemLighting();
    }

    public String stripControlCodes(String s){
        return StringUtils.stripControlCodes(s);
    }

    public double getBoundboxMinY(Entity e){
        return e.getEntityBoundingBox().minY;
    }

    public double getBoundboxMaxY(Entity e){
        return e.getEntityBoundingBox().maxY;
    }

    public double getBoundboxMinX(Entity e){
        return e.getEntityBoundingBox().minX;
    }

    public double getBoundboxMaxX(Entity e){
        return e.getEntityBoundingBox().maxX;
    }

    public double getBoundboxMinZ(Entity e){
        return e.getEntityBoundingBox().minZ;
    }

    public double getBoundboxMaxZ(Entity e){
        return e.getEntityBoundingBox().maxZ;
    }

    public int getDisplayHeight(){
        return wrapper.getMinecraft().displayHeight;
    }

    public int getDisplayWidth(){
        return wrapper.getMinecraft().displayWidth;
    }

    public GuiScreen getCurrentScreen(){
        return wrapper.getMinecraft().currentScreen;
    }

    public int getScaledWidth(ScaledResolution sr){
        return sr.getScaledWidth();
    }

    public int getScaledHeight(ScaledResolution sr){
        return sr.getScaledHeight();
    }

    public ServerData getCurrentServerData(){
        return wrapper.getMinecraft().getCurrentServerData();
    }

    public boolean isInCreativeMode(){
        return wrapper.getPlayerController().isInCreativeMode();
    }

    public void setStackDisplayName(ItemStack is, String s){
        is.setStackDisplayName(s);
    }

    public String getItemDisplayName(ItemStack is){
        return is.getDisplayName();
    }

    public void sendPacket(Packet p){
        wrapper.getPlayer().sendQueue.addToSendQueue(p);
    }

    public void addEnchantment(ItemStack is, Enchantment e, int level){
        is.addEnchantment(e, 127);
    }

    public double getLastTickPosX(Entity e){
        return e.lastTickPosX;
    }

    public double getLastTickPosY(Entity e){
        return e.lastTickPosY;
    }

    public double getLastTickPosZ(Entity e){
        return e.lastTickPosZ;
    }

    public float getEntityHeight(Entity e){
        return e.height;
    }

    public void loadRenderers(){
        if(wrapper.getMinecraft().renderGlobal != null){
            wrapper.getMinecraft().renderGlobal.loadRenderers();
        }
    }

    public void setSmoothLighting(int mode){
        wrapper.getGameSettings().ambientOcclusion = mode;
    }

    public int getSmoothLighting(){
        return wrapper.getGameSettings() == null ? 2 : wrapper.getGameSettings().ambientOcclusion;
    }

    public int getIdFromBlock(Block block) {
        return Block.getIdFromBlock(block);
    }

    public boolean isBurning(){
        return wrapper.getPlayer().isBurning();
    }

    public int getItemInUseDuration(){
        return wrapper.getPlayer().getItemInUseDuration();
    }

    public boolean isOnGround() {
        return wrapper.getPlayer().onGround;
    }

    public boolean isInWater() {
        return wrapper.getPlayer().isInWater();
    }

    public void setFallDistance(float f) {
        wrapper.getPlayer().fallDistance = f;
    }

    public void setOnGround(boolean b) {
        wrapper.getPlayer().onGround = b;
    }

    public int getFoodLevel(){
        return wrapper.getPlayer().getFoodStats().getFoodLevel();
    }

    public float getHealth(){
        return wrapper.getPlayer().getHealth();
    }

    public void removeEntityFromWorld(int id){
        wrapper.getWorld().removeEntityFromWorld(id);
    }

    public void addEntityToWorld(Entity e, int id){
        wrapper.getWorld().addEntityToWorld(id, e);
    }

    public void jump(){
        wrapper.getPlayer().jump();
    }

    public void setPositionAndRotation(Entity e, double x, double y, double z, float yaw, float pitch){
        e.setPositionAndRotation(x, y, z, yaw, pitch);
    }

    public void copyInventory(EntityPlayer from, EntityPlayer to){
        from.inventory.copyInventory(to.inventory);
    }

    public void setNoClip(boolean state){
        wrapper.getPlayer().noClip = state;
    }

    public boolean isSneaking(){
        return wrapper.getPlayer().isSneaking();
    }

    public void setStepHeight(float value) {
        wrapper.getPlayer().stepHeight = value;
    }

    public int getWidth(){
        return getDisplayWidth()/2;
    }

    public int getHeight(){
        return getDisplayHeight()/2;
    }

    public int getId(GuiButton btn){
        return btn.id;
    }

    public Wrapper getWrapper(){
        return wrapper;
    }

    public MovingObjectPosition rayTraceBlocks(Vec3 vecFromPool, Vec3 vecFromPool2) {
        return wrapper.getWorld().rayTraceBlocks(vecFromPool, vecFromPool2);
    }

    public float getFallDistance(Entity e){
        return e.fallDistance;
    }

    public boolean isInvisible(Entity e){
        return e.isInvisible();
    }

    public GuiIngame getIngameGUI(){
        return wrapper.getMinecraft().ingameGUI;
    }

    public int getPacketVelocityEntityId(S12PacketEntityVelocity p){
        return p.getEntityID();
    }

    public int getXMovePacketVel(S12PacketEntityVelocity p){
        return p.getMotionX();
    }

    public int getYMovePacketVel(S12PacketEntityVelocity p){
        return p.getMotionY();
    }

    public int getZMovePacketVel(S12PacketEntityVelocity p){
        return p.getMotionZ();
    }

    public List getTileEntityList(){
        return wrapper.getWorld().loadedTileEntityList;
    }

    public int getChestX(TileEntityChest chest){
        return chest.getPos().getX();
    }

    public int getChestY(TileEntityChest chest){
        return chest.getPos().getY();
    }

    public int getChestZ(TileEntityChest chest){
        return chest.getPos().getZ();
    }

    public void setKeybind(int keycode, boolean b) {
        KeyBinding.setKeyBindState(keycode, b);
    }

    public void setSprint(boolean b) {
        setKeybind(wrapper.getMinecraft().gameSettings.keyBindSprint.getKeyCode(), b);
    }

    public void setShift(boolean b) {
        setKeybind(wrapper.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), b);
    }

    public void setJump(boolean b) {
        setKeybind(wrapper.getMinecraft().gameSettings.keyBindJump.getKeyCode(), b);
    }

    public void setUseItemKeyPressed(boolean pressed){
        setKeybind(wrapper.getGameSettings().keyBindUseItem.getKeyCode(), true);
    }

    public void moveRight(double amt) {

    }

    public void moveLeft(double amt) {

    }
}
