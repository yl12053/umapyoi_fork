package net.tracen.umapyoi.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;

import static net.minecraft.client.renderer.RenderStateShard.*;

public class RenderUtil {
    private static final BiFunction<ResourceLocation, Boolean, RenderType> _customEmissive = Util.memoize((r, b) -> {
        RenderType.CompositeState renderType = RenderType.CompositeState.builder()
                .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER)
                .setTextureState(new TextureStateShard(r, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setCullState(NO_CULL)
                .setWriteMaskState(COLOR_DEPTH_WRITE)
                .setDepthTestState(LEQUAL_DEPTH_TEST)
                .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                .setOverlayState(OVERLAY).createCompositeState(b);
        return RenderType.create("custom_emissive", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true, renderType);
    });

    public static RenderType CustomEmissive(ResourceLocation texture, boolean outline) {
        return _customEmissive.apply(texture, outline);
    }

    public static RenderType CustomEmissive(ResourceLocation texture) {
        return CustomEmissive(texture, false);
    }
}
