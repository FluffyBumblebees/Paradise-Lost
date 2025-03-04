package net.id.paradiselost.client.rendering.entity.hostile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.id.paradiselost.ParadiseLost;
import net.id.paradiselost.client.model.ParadiseLostModelLayers;
import net.id.paradiselost.client.model.entity.CockatriceModel;
import net.id.paradiselost.entities.hostile.CockatriceEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class CockatriceRenderer extends MobEntityRenderer<CockatriceEntity, CockatriceModel> {
    private static final Identifier TEXTURE = ParadiseLost.locate("textures/entity/cockatrice.png");

    public CockatriceRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CockatriceModel(renderManager.getPart(ParadiseLostModelLayers.COCKATRICE)), 1.0F);
    }

    @Override
    protected float getAnimationProgress(CockatriceEntity cockatrice, float tickDelta) {
        float flap = MathHelper.lerp(tickDelta, cockatrice.prevFlapProgress, cockatrice.flapProgress);
        float deviation = MathHelper.lerp(tickDelta, cockatrice.prevMaxWingDeviation, cockatrice.maxWingDeviation);
        return (MathHelper.sin(flap) + 1.0F) * deviation;
    }

    @Override
    public Identifier getTexture(CockatriceEntity cockatrice) {
        return TEXTURE;
    }
}
