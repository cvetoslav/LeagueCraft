package com.dm66.leaguecraft.utils;

import com.dm66.leaguecraft.LeagueCraftMod;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.PointerBuffer;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class AnimatedGif
{
    private static final int GIF_TICKS_PER_SECOND = 1000;
    private static final int MC_TICKS_PER_SECOND = 20;
    private static final int MIN_GIF_TICKS = Mth.ceil(0.01f * GIF_TICKS_PER_SECOND); // Most browsers have approximately 0.1s minimum interval between frames. Let's respect that!
    private static final int MIN_MC_TICKS = Mth.ceil(MIN_GIF_TICKS * (float)MC_TICKS_PER_SECOND / GIF_TICKS_PER_SECOND);

    public static AnimatedGif fromPath(Path path) throws IOException
    {
        byte[] bytes = Files.readAllBytes(path);
        return fromMemory(bytes);
    }

    public static AnimatedGif fromInputStream(InputStream is)
    {
        try
        {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            int n;
            byte[] data = new byte[4096];
            while ((n = is.read(data)) != -1)
            {
                bs.write(data, 0, n);
            }
            bs.flush();
            return fromMemory(bs.toByteArray());
        }
        catch (Exception e){e.printStackTrace();}
        return null;
    }

    public static AnimatedGif fromMemory(byte[] fileData)
    {
        try
        {
            final ByteBuffer gif = MemoryUtil.memAlloc(fileData.length);
            gif.put(fileData);
            gif.position(0);
            try (final MemoryStack stack = MemoryStack.stackPush())
            {
                final PointerBuffer delaysBuffer = stack.mallocPointer(1);
                final IntBuffer x = stack.mallocInt(1);
                final IntBuffer y = stack.mallocInt(1);
                final IntBuffer z = stack.mallocInt(1);
                final IntBuffer channels = stack.mallocInt(1);

                final ByteBuffer image = STBImage.stbi_load_gif_from_memory(gif, delaysBuffer, x, y, z, channels, 0);
                try
                {
                    if (image == null)
                    {
                        throw new RuntimeException(STBImage.stbi_failure_reason()); // assumes program termination: if not, cleanup of resources is required
                    }

                    int nch = channels.get();
                    if (nch != 4)
                    {
                        throw new RuntimeException("Unexpected number of channels " + nch + ", expected 4");
                    }

                    int width = x.get();
                    int height = y.get();
                    int frames = z.get();

                    IntBuffer delaysIntBuffer = delaysBuffer.getIntBuffer(frames);
                    int[] delays = new int[frames];
                    delaysIntBuffer.get(delays);

                    IntBuffer pixelData = image.asIntBuffer();
                    int[] pixels = new int[width * height * frames];
                    pixelData.get(pixels);

                    return new AnimatedGif(width, height, frames, pixels, delays);
                }
                catch (Exception e){e.printStackTrace();}
                finally
                {
                    if (image != null)
                        STBImage.stbi_image_free(image);
                }
            }
            catch (Exception e){e.printStackTrace();}
            finally
            {
                MemoryUtil.memFree(gif);
            }
        }
        catch (Exception e){e.printStackTrace();}
        return null;
    }

    private final int width;
    private final int height;
    private final int frames;
    private final int[] pixels;
    private final int[] delays;

    public AnimatedGif(int width, int height, int frames, int[] pixels, int[] delays)
    {
        this.width = width;
        this.height = height;
        this.frames = frames;
        this.pixels = pixels;
        this.delays = delays;
        if (pixels.length != width * height * frames)
            throw new IllegalArgumentException("Pixels array length must be == width*height*frames, was " + pixels.length);
        if (delays.length != frames)
            throw new IllegalArgumentException("Delays array length must be == frames, was " + delays.length);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getFrames()
    {
        return frames;
    }

    public NativeImage toNativeImage()
    {
        NativeImage img = new NativeImage(NativeImage.Format.RGBA, width, height * frames, false);
        for (int y = 0; y < height * frames; y++)
        {
            for (int x = 0; x < width; x++)
            {
                img.setPixelRGBA(x, y, pixels[y * width + x]);
            }
        }
        return img;
    }

    public int convertToMcTick(int delay)
    {
        return Mth.ceil(delay * (float)MC_TICKS_PER_SECOND / GIF_TICKS_PER_SECOND); // gif delays at in 1/100s increments, mc ticks are 1/20
    }

    // UNTESTED!!!
//    public TextureAtlasSprite toAnimatedSprite(ResourceLocation location, AtlasTexture atlas, int mipmapLevels, int atlasWidth, int atlasHeight, int atlasX, int atlasY)
//    {
//        NativeImage img = toNativeImage();
//        List<AnimationFrame> frameList = Lists.newArrayList();
//        int accDelay = 0;
//        for (int i = 0; i < frames; i++)
//        {
//            frameList.add(new AnimationFrame(i, accDelay));
//            accDelay += Math.max(convertToMcTick(delays[i]), MIN_MC_TICKS);
//        }
//        AnimationMetadataSection animation = new AnimationMetadataSection(frameList, width, height, 0, false);
//        TextureAtlasSprite.Info info = new TextureAtlasSprite.Info(location, width, height * frames, animation);
//        return new TextureAtlasSprite(atlas, info, mipmapLevels, atlasWidth, atlasHeight, atlasX, atlasY, img)
//        {
//        };
//    }

    public void exportToMcAnim(Path path) throws IOException
    {
        try (NativeImage nativeImage = toNativeImage())
        {
            nativeImage.writeToFile(path);
        }

        JsonObject meta = new JsonObject();
        JsonObject anim = new JsonObject();
        anim.addProperty("frametime", 0);
        anim.addProperty("interpolate", false);
        JsonArray frameTimes = new JsonArray();
        for (int delay : delays) frameTimes.add(Math.max(convertToMcTick(delay),MIN_MC_TICKS));
        anim.add("frames", frameTimes);
        meta.add("animation", anim);
        try (FileWriter writer = new FileWriter(path.toFile().getAbsolutePath() + ".mcmeta"))
        {
            writer.write(new Gson().toJson(meta));
        }
    }

    public GifPlayer makeGifPlayer(String id)
    {
        return new GifPlayer(id);
    }

    public class GifPlayer implements AutoCloseable
    {
        private ResourceLocation glTexture = null;
        private final int totalFrameTicks;
        private boolean playing;
        private int animationProgress;
        private int lastFrame;
        /*
         While playing, holds the partial offset from the start tick.
         While stopped, holds the partial tick progress at the point it was stopped.
         */
        private float partialStart;
        private boolean autoplay;
        private boolean looping = true;
        private final TextureManager tm = Minecraft.getInstance().textureManager;

        private GifPlayer(String id)
        {
            totalFrameTicks = Arrays.stream(delays).map(d -> Math.max(MIN_GIF_TICKS, d)).sum();
            glTexture = new ResourceLocation(LeagueCraftMod.MOD_ID, "dyn_tex/gif_" + id);
            tm.register(glTexture, new DynamicTexture(toNativeImage()));
        }

        public void reset()
        {
            animationProgress = 0;
            partialStart = 0;
            playing = false;
        }

        public void restart(float partialTicks)
        {
            reset();
            start(partialTicks);
        }

        public void start(float partialTicks)
        {
            playing = true;
            partialStart = partialTicks - partialStart;
        }

        public void stop(float partialTicks)
        {
            partialStart = partialTicks - partialStart;
            playing = false;
            autoplay = false;
        }

        /**
         * Call this on your gui tick. Necessary to keep proper time.
         */
        public void tick()
        {
            if (playing)
            {
                animationProgress++;
            }
        }

        public void render(PoseStack matrixStack, int x, int y, int w, int h, float partialTicks)
        {

            if (totalFrameTicks == 0)
                return;

            if (!playing && autoplay)
                start(partialTicks);

            if (playing)
            {
                float frameTime = ((animationProgress + partialTicks - partialStart) * (float)GIF_TICKS_PER_SECOND) / MC_TICKS_PER_SECOND;
                int frameNumber = Mth.floor(frameTime) % totalFrameTicks;
                int frameIndex = -1;
                for (int i = 0; i < delays.length; i++)
                {
                    int d = Math.max(delays[i], MIN_GIF_TICKS);
                    if (d > frameNumber)
                    {
                        frameIndex = i;
                        break;
                    }
                    frameNumber -= d;
                }
                if (frameIndex < 0)
                {
                    if (looping)
                    {
                        frameIndex = 0;
                    }
                    else
                    {
                        playing = false;
                        return;
                    }
                }
                lastFrame = frameIndex;
            }

            //RenderSystem.enableTexture();
            //RenderSystem.enableAlphaTest();
            RenderSystem.disableBlend();

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, glTexture);

            Gui.blit(matrixStack, x, y, w, h, 0, lastFrame * height, width, height, width, height * frames);
        }

        public void close()
        {
            tm.release(glTexture);
        }

        public boolean isPlaying(){return playing;}

        public void setAutoplay(boolean autoplay)
        {
            this.autoplay = autoplay;
        }

        public boolean getAutoplay()
        {
            return autoplay;
        }

        public void setLooping(boolean looping)
        {
            this.looping = looping;
        }

        public boolean getLooping()
        {
            return looping;
        }
    }
}