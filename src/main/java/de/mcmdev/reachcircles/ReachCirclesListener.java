package de.mcmdev.reachcircles;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ReachCirclesListener {

    private static Minecraft mc = Minecraft.getMinecraft();
    public static boolean show = true;

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event)    {
        GL11.glPushMatrix();
        mc.entityRenderer.disableLightmap();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(false);
        for (Object o : mc.theWorld.loadedEntityList) {
            Entity entity = (Entity)o;
            if (entity instanceof EntityLivingBase && !entity.isInvisible() && entity != mc.thePlayer && ((EntityLivingBase)entity).canEntityBeSeen(mc.thePlayer) && !entity.isInvisible() && entity instanceof net.minecraft.entity.player.EntityPlayer && show) {
                double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * event.partialTicks - (mc.getRenderManager()).viewerPosX;
                double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * event.partialTicks - (mc.getRenderManager()).viewerPosY;
                double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * event.partialTicks - (mc.getRenderManager()).viewerPosZ;
                circle(posX, posY, posZ, mc.playerController.isInCreativeMode() ? 4.7D : 3.4D);
            }
        }
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        mc.entityRenderer.enableLightmap();
        GL11.glPopMatrix();
    }

    private void circle(double x, double y, double z, double rad) {
        GL11.glPushMatrix();
        double tau = 6.283185307179586D;
        double fans = 45.0D;
        GL11.glLineWidth(1.0F);
        GL11.glColor3f(255.0F, 255.0F, 255.0F);
        GL11.glBegin(1);
        for (int i = 0; i <= 90; i++) {
            GL11.glColor4f(255.0F, 255.0F, 255.0F, 60.0F);
            GL11.glVertex3d(x + rad * Math.cos(i * 6.283185307179586D / 45.0D), y, z + rad * Math.sin(i * 6.283185307179586D / 45.0D));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

}
