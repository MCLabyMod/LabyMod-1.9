package mods.batty.main;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.chunk.Chunk;

public class BattyUI extends Gui {
	public final Minecraft mc;
	int showCoords = 1;
	boolean shadedCoords = true;
	boolean hideTimer = false;
	boolean shadedTimer = true;
	public boolean timerRunning = false;
	public boolean toggleTimer = false;
	public boolean resetTimer = false;
	boolean shadedFPS = true;
	boolean hideFPS = false;
	boolean coordsCopyTPFormat = false;
	int myTitleText = 16746496;
	int myPosCoordText = 5636095;
	int myNegCoordText = 13434879;
	int myPosChunkText = 16777215;
	int myNegChunkText = 11184810;
	int myCompassText = 16746496;
	int myChevronText = 5636095;
	int myBiomeText = 11184810;
	int myRectColour = 1431655765;
	int myTimerStopText = 16746496;
	int myTimerRunText = 5636095;
	int myFPSText = 5636095;
	int myPosX;
	int myPosY;
	int myPosZ;
	boolean myXminus;
	boolean myZminus;
	int myAngle;
	int myDir;
	int myMoveX;
	int myMoveZ;
	int myFind;
	protected static final ResourceLocation batUIResourceLocation = new ResourceLocation("battymod/batheart_icon.png");
	static float batLogoScaler = 0.036F;
	static int batLogoU = 0;
	static int batLogoV = 0;
	static int batLogoX = (int) (256.0F * batLogoScaler);
	static int batLogoY = (int) (256.0D * batLogoScaler);
	int coordLocation = 0;
	int myXLine;
	int myYLine;
	int myZLine;
	int myBiomeLine;
	int myBaseOffset;
	int myCoord0Offset;
	int myCoord1Offset;
	int myCoord2Offset;
	int myRHSlocation;
	int coordBoxW;
	int coordBoxH;
	int coordBoxL;
	int coordBoxR;
	int coordBoxTop;
	int coordBoxBase;
	int timerLocation = 2;
	int clockBoxW;
	int clockBoxH;
	int clockBoxL;
	int clockBoxR;
	int clockBoxTop;
	int clockBoxBase;
	int myTimerLine;
	int myTimerOffset;
	int fpsLocation = 1;
	int fpsBoxW;
	int fpsBoxH;
	int fpsBoxL;
	int fpsBoxR;
	int fpsBoxTop;
	int fpsBoxBase;
	int myFPSLine;
	int myFPSOffset;
	public String myChevronUp = "+";
	public String myChevronDown = "-";
	public static final String[] myCardinalPoint = { "N", "NE", "E", "SE", "S", "SW", "W", "NW" };
	public static final String[] myColourList = { "black", "darkblue", "darkgreen", "darkaqua", "darkred", "purple",
			"brown", "grey", "darkgrey", "blue", "green", "aqua", "sage", "violet", "orange", "lime", "silver",
			"coolblue", "red", "gold", "oldgold", "lightpurple", "pink", "yellow", "white" };
	public static final int[] myColourCodes = { 0, 170, 43520, 43690, 11141120, 11141290, 11162880, 11184810, 5592405,
			5592575, 5635925, 5636095, 8956416, 8934860, 13391104, 13434624, 13421772, 13434879, 16733525, 16755200,
			16746496, 16733695, 16755370, 16777045, 16777215 };
	public int myMoon;
	public File optionsFile;
	public File runtimeFile;
	public int secondCounter = 0;
	public int minuteCounter = 0;
	public int hourCounter = 0;
	public int tickCounter = 0;
	Properties propts = new Properties();
	Properties proprt = new Properties();
	public static KeyBinding hideunhideCoordskey = new KeyBinding("Hide / Unhide Coords", 75, "Batty's Coordinates");
	public static KeyBinding moveCoordScreenPos = new KeyBinding("Change Coords Screen Position", 79,
			"Batty's Coordinates");
	public static KeyBinding copyCoordsClipboard = new KeyBinding("Copy Coords to Clipboard", 71,
			"Batty's Coordinates");
	public static KeyBinding hideunhideTimerkey = new KeyBinding("Hide / Unhide Timer", 76, "Batty's Timer");
	public static KeyBinding startstopTimerkey = new KeyBinding("Start / Stop Timer", 83, "Batty's Timer");
	public static KeyBinding resetTimerkey = new KeyBinding("Reset Timer to Zero", 82, "Batty's Timer");
	public static KeyBinding moveTimerScreenPos = new KeyBinding("Change Timer Screen Position", 80, "Batty's Timer");
	public static KeyBinding hideunhideFPSkey = new KeyBinding("Hide / Unhide FPS", 77, "Batty's FPS");
	public static KeyBinding moveFPSScreenPos = new KeyBinding("Change FPS Screen Position", 81, "Batty's FPS");

	@SuppressWarnings("resource")
	public BattyUI(Minecraft par1Minecraft) {
		BattyMod.getInstance().setBatheartgui(this);
		mc = par1Minecraft;

		optionsFile = new File(mc.mcDataDir, "BatMod.properties");
		runtimeFile = new File(mc.mcDataDir, "BatMod.runtime");

		BattyConfig.retrieveOptions();
		BattyConfig.retrieveRuntimeOptions();

		List<KeyBinding> keybindings = Arrays.asList(hideunhideCoordskey, moveCoordScreenPos, copyCoordsClipboard,
				hideunhideTimerkey, moveTimerScreenPos, startstopTimerkey, hideunhideFPSkey, moveFPSScreenPos);

		try {
			BufferedReader bufferedreader = new BufferedReader(new FileReader(new File(mc.mcDataDir, "options.txt")));

			String s = "";
			while ((s = bufferedreader.readLine()) != null) {
				for (KeyBinding keybinding : keybindings) {
					String[] astring = s.split(":");
					if (astring[0].equals("key_" + keybinding.getKeyDescription())) {
						keybinding.setKeyCode(Integer.parseInt(astring[1]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (KeyBinding keybinding : keybindings) {
			mc.gameSettings.keyBindings = ((KeyBinding[]) ArrayUtils.add(mc.gameSettings.keyBindings, keybinding));
		}
	}

	public void drawTexture(int x, int y, int u, int v, int width, int height, ResourceLocation resourceLocation,
			float scaler) {
		x = (int) (x / scaler);
		y = (int) (y / scaler);

		GL11.glPushMatrix();
		GL11.glDisable(2896);
		GL11.glScalef(scaler, scaler, scaler);

		mc.getTextureManager().bindTexture(resourceLocation);
		drawTexturedModalRect(x, y, u, v, width, height);

		GL11.glPopMatrix();
	}

	protected void drawLogoTexture(int x, int y) {
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(255.0F, 255.0F, 255.0F, 255.0F);

		drawTexture(x, y, batLogoU, batLogoV, (int) (batLogoX / batLogoScaler), (int) (batLogoY / batLogoScaler),
				batUIResourceLocation, batLogoScaler);

		GL11.glDisable(3042);
	}

	public void renderPlayerCoords() {
		GL11.glEnable(3008);
		FontRenderer var8 = mc.fontRendererObj;

		ScaledResolution myRes = new ScaledResolution(mc);
		BlockPos var1 = new BlockPos(mc.getRenderViewEntity().posX,
				mc.getRenderViewEntity().getEntityBoundingBox().minY, mc.getRenderViewEntity().posZ);
		myPosX = MathHelper.floor_double(mc.thePlayer.posX);
		myXminus = (mc.thePlayer.posX < 0.0D);
		myPosY = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minY);
		myPosZ = MathHelper.floor_double(mc.thePlayer.posZ);
		myZminus = (mc.thePlayer.posZ < 0.0D);
		myAngle = BattyUtils.getCardinalPoint(mc.thePlayer.rotationYaw);

		myDir = (MathHelper.floor_double(mc.thePlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3);

		int compassW = mc.fontRendererObj.getStringWidth(myCardinalPoint[7]);
		if (showCoords > 2) {
			coordBoxW = 104;
			coordBoxH = 40;
		} else {
			coordBoxW = 80;
			coordBoxH = 30;
		}
		switch (coordLocation) {
		case 0:
			coordBoxR = (coordBoxW + 1);
			coordBoxBase = (coordBoxH + 1);
			break;
		case 1:
			coordBoxR = (myRes.getScaledWidth() - 1);
			coordBoxBase = (coordBoxH + 1);
			break;
		case 2:
			coordBoxR = (myRes.getScaledWidth() - 1);
			coordBoxBase = (myRes.getScaledHeight() - 1);
			break;
		case 3:
			coordBoxR = (coordBoxW + 1);
			coordBoxBase = (myRes.getScaledHeight() - 1);
		}
		coordBoxL = (coordBoxR - coordBoxW);
		coordBoxTop = (coordBoxBase - coordBoxH);
		myXLine = (coordBoxTop + 1);
		myYLine = (myXLine + 10);
		myZLine = (myYLine + 10);
		myBiomeLine = (myZLine + 10);
		myBaseOffset = (coordBoxL + 1);
		myCoord0Offset = (myBaseOffset + 10);
		myCoord1Offset = (myBaseOffset + 16);
		myCoord2Offset = (myBaseOffset + 39);
		if (showCoords == 3) {
			myRHSlocation = (coordBoxR - compassW - 14);
		} else if(showCoords > 2) {
			myRHSlocation = (coordBoxR - compassW - 1);
		} else {
			myRHSlocation = (myBaseOffset + 64);
		}
		if (shadedCoords) {
			drawRect(coordBoxL, coordBoxTop, coordBoxR, coordBoxBase, myRectColour);
		}
		var8.drawStringWithShadow(String.format("x: ", new Object[0]), myBaseOffset, myXLine, myTitleText);

		var8.drawStringWithShadow(String.format("y: ", new Object[0]), myBaseOffset, myYLine, myTitleText);

		var8.drawStringWithShadow(String.format("z: ", new Object[0]), myBaseOffset, myZLine, myTitleText);
		if (showCoords < 4) {
			if (!myXminus) {
				var8.drawStringWithShadow(String.format("%d", new Object[] { Integer.valueOf(myPosX) }), myCoord1Offset,
						myXLine, myPosCoordText);
			} else {
				var8.drawStringWithShadow("-", myCoord0Offset, myXLine, myNegCoordText);
				var8.drawStringWithShadow(String.format("%d", new Object[] { Integer.valueOf(Math.abs(myPosX)) }),
						myCoord1Offset, myXLine, myNegCoordText);
			}
			var8.drawStringWithShadow(String.format("%d", new Object[] { Integer.valueOf(myPosY) }), myCoord1Offset,
					myYLine, myPosCoordText);
			if (!myZminus) {
				var8.drawStringWithShadow(String.format("%d", new Object[] { Integer.valueOf(myPosZ) }), myCoord1Offset,
						myZLine, myPosCoordText);
			} else {
				var8.drawStringWithShadow("-", myCoord0Offset, myZLine, myNegCoordText);
				var8.drawStringWithShadow(String.format("%d", new Object[] { Integer.valueOf(Math.abs(myPosZ)) }),
						myCoord1Offset, myZLine, myNegCoordText);
			}
		} else {
			if (myPosX >= 0) {
				var8.drawStringWithShadow(String.format("c%d ", new Object[] { Integer.valueOf(myPosX >> 4) }),
						myCoord2Offset, myXLine, myPosChunkText);

				var8.drawStringWithShadow(String.format("b%d", new Object[] { Integer.valueOf(myPosX & 0xF) }),
						myCoord1Offset, myXLine, myPosChunkText);
			} else {
				var8.drawStringWithShadow(String.format("c%d ", new Object[] { Integer.valueOf(myPosX >> 4) }),
						myCoord2Offset, myXLine, myNegChunkText);

				var8.drawStringWithShadow(String.format("b%d", new Object[] { Integer.valueOf(myPosX & 0xF) }),
						myCoord1Offset, myXLine, myPosChunkText);
			}
			var8.drawStringWithShadow(String.format("%d", new Object[] { Integer.valueOf(myPosY) }), myCoord1Offset,
					myYLine, myPosCoordText);
			if (myPosZ >= 0) {
				var8.drawStringWithShadow(String.format("c%d ", new Object[] { Integer.valueOf(myPosZ >> 4) }),
						myCoord2Offset, myZLine, myPosChunkText);

				var8.drawStringWithShadow(String.format("b%d", new Object[] { Integer.valueOf(myPosZ & 0xF) }),
						myCoord1Offset, myZLine, myPosChunkText);
			} else {
				var8.drawStringWithShadow(String.format("c%d ", new Object[] { Integer.valueOf(myPosZ >> 4) }),
						myCoord2Offset, myZLine, myNegChunkText);

				var8.drawStringWithShadow(String.format("b%d", new Object[] { Integer.valueOf(myPosZ & 0xF) }),
						myCoord1Offset, myZLine, myPosChunkText);
			}
		}
		drawLogoTexture(myRHSlocation - 12, myYLine - 1);

		var8.drawStringWithShadow(myCardinalPoint[myAngle], myRHSlocation, myYLine, myCompassText);
		if (showCoords > 1) {
			switch (myAngle) {
			case 0:
				var8.drawStringWithShadow(myChevronDown + myChevronDown, myRHSlocation, myZLine, myNegCoordText);

				break;
			case 1:
				var8.drawStringWithShadow(myChevronDown, myRHSlocation, myZLine, myNegCoordText);

				var8.drawStringWithShadow(myChevronUp, myRHSlocation, myXLine, myPosCoordText);

				break;
			case 2:
				var8.drawStringWithShadow(myChevronUp + myChevronUp, myRHSlocation, myXLine, myPosCoordText);

				break;
			case 3:
				var8.drawStringWithShadow(myChevronUp, myRHSlocation, myXLine, myPosCoordText);

				var8.drawStringWithShadow(myChevronUp, myRHSlocation, myZLine, myPosCoordText);

				break;
			case 4:
				var8.drawStringWithShadow(myChevronUp + myChevronUp, myRHSlocation, myZLine, myPosCoordText);

				break;
			case 5:
				var8.drawStringWithShadow(myChevronUp, myRHSlocation, myZLine, myPosCoordText);

				var8.drawStringWithShadow(myChevronDown, myRHSlocation, myXLine, myNegCoordText);

				break;
			case 6:
				var8.drawStringWithShadow(myChevronDown + myChevronDown, myRHSlocation, myXLine, myNegCoordText);

				break;
			case 7:
				var8.drawStringWithShadow(myChevronDown, myRHSlocation, myXLine, myNegCoordText);

				var8.drawStringWithShadow(myChevronDown, myRHSlocation, myZLine, myNegCoordText);
			}
		}
		if ((showCoords > 2) && (mc.theWorld != null) && (mc.theWorld.isBlockLoaded(var1))) {
			Chunk var26 = mc.theWorld.getChunkFromBlockCoords(var1);
			var8.drawStringWithShadow(var26.getBiome(var1, this.mc.theWorld.getWorldChunkManager()).getBiomeName(),
					this.myBaseOffset, this.myBiomeLine, this.myBiomeText);
		}
	}

	public void renderPlayerTimer() {
		GL11.glEnable(3008);
		ScaledResolution myRes = new ScaledResolution(mc);
		String myTime = BattyUtils.constructTimeString();
		int timeStringWid = mc.fontRendererObj.getStringWidth(myTime);
		clockBoxW = (12 + timeStringWid);
		clockBoxH = 10;
		switch (timerLocation) {
		case 0:
			clockBoxR = (clockBoxW + 1);
			clockBoxBase = (clockBoxH + 1);
			if (coordLocation == 0) {
				clockBoxBase += coordBoxH + 1;
			}
			break;
		case 1:
			clockBoxR = (myRes.getScaledWidth() / 2 + clockBoxW / 2);
			clockBoxBase = (clockBoxH + 1);
			break;
		case 2:
			clockBoxR = (myRes.getScaledWidth() - 1);
			clockBoxBase = (clockBoxH + 1);
			if (coordLocation == 1) {
				clockBoxBase += coordBoxH + 1;
			}
			break;
		case 3:
			clockBoxR = (myRes.getScaledWidth() - 1);
			clockBoxBase = (myRes.getScaledHeight() - 1);
			if (coordLocation == 2) {
				clockBoxBase -= coordBoxH + 1;
			}
			break;
		case 4:
			clockBoxR = (clockBoxW + 1);
			clockBoxBase = (myRes.getScaledHeight() - 15);
			if (coordLocation == 3) {
				clockBoxBase -= coordBoxH + 1;
			}
			break;
		}
		clockBoxL = (clockBoxR - clockBoxW);
		clockBoxTop = (clockBoxBase - clockBoxH);

		myTimerLine = (clockBoxTop + 1);
		myTimerOffset = (clockBoxL + 6);
		if (shadedTimer) {
			drawRect(clockBoxL, clockBoxTop, clockBoxR, clockBoxBase, myRectColour);
		}
		if (timerRunning) {
			mc.fontRendererObj.drawStringWithShadow(myTime, myTimerOffset, myTimerLine, myTimerRunText);
		} else {
			mc.fontRendererObj.drawStringWithShadow(myTime, myTimerOffset, myTimerLine, myTimerStopText);
		}
	}

	public void renderPlayerFPS() {
		GL11.glEnable(3008);
		ScaledResolution myRes = new ScaledResolution(mc);
		String myFPS = mc.debug.split(" ")[0] + " FPS";
		int fpsStringWid = mc.fontRendererObj.getStringWidth(myFPS);
		fpsBoxW = (12 + fpsStringWid);
		fpsBoxH = 10;
		switch (fpsLocation) {
		case 0:
			fpsBoxR = (fpsBoxW + 1);
			fpsBoxBase = (fpsBoxH + 1);
			if (timerLocation == 0) {
				fpsBoxBase += clockBoxH + 1;
			}
			if (coordLocation == 0) {
				fpsBoxBase += coordBoxH + 1;
			}
			break;
		case 1:
			fpsBoxR = (myRes.getScaledWidth() / 2 + fpsBoxW / 2);
			fpsBoxBase = (fpsBoxH + 1);
			if (timerLocation == 1) {
				fpsBoxBase += clockBoxH + 1;
			}
			break;
		case 2:
			fpsBoxR = (myRes.getScaledWidth() - 1);
			fpsBoxBase = (fpsBoxH + 1);
			if (timerLocation == 2) {
				fpsBoxBase += clockBoxH + 1;
			}
			if (coordLocation == 1) {
				fpsBoxBase += coordBoxH + 1;
			}
			break;
		case 3:
			fpsBoxR = (myRes.getScaledWidth() - 1);
			fpsBoxBase = (myRes.getScaledHeight() - 1);
			if (timerLocation == 3) {
				fpsBoxBase -= clockBoxH + 1;
			}
			if (coordLocation == 2) {
				fpsBoxBase -= coordBoxH + 1;
			}
			break;
		case 4:
			fpsBoxR = (fpsBoxW + 1);
			fpsBoxBase = (myRes.getScaledHeight() - 15);
			if (timerLocation == 4) {
				fpsBoxBase -= clockBoxH + 1;
			}
			if (coordLocation == 3) {
				fpsBoxBase -= coordBoxH + 1;
			}
			break;
		}
		fpsBoxL = (fpsBoxR - fpsBoxW);
		fpsBoxTop = (fpsBoxBase - fpsBoxH);

		myFPSLine = (fpsBoxTop + 1);
		myFPSOffset = (fpsBoxL + 6);
		if (shadedFPS) {
			drawRect(fpsBoxL, fpsBoxTop, fpsBoxR, fpsBoxBase, myRectColour);
		}
		mc.fontRendererObj.drawStringWithShadow(myFPS, myFPSOffset, myFPSLine, myFPSText);
	}

	public void hideUnhideCoords() {
		showCoords += 1;
		if (showCoords > 4) {
			showCoords = 0;
		}
		BattyConfig.storeRuntimeOptions();
	}

	public void rotateScreenCoords() {
		coordLocation += 1;
		if (coordLocation > 2) {
			coordLocation = 0;
		}
		BattyConfig.storeRuntimeOptions();
	}

	public void copyScreenCoords() {
		StringSelection myCoordString;
		if (coordsCopyTPFormat) {
			myCoordString = new StringSelection(myPosX + " " + myPosY + " " + myPosZ);
		} else {
			myCoordString = new StringSelection("x:" + myPosX + " y:" + myPosY + " z:" + myPosZ);
		}
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(myCoordString, null);
	}

	public void hideUnhideStopWatch() {
		hideTimer = (!hideTimer);
		BattyConfig.storeRuntimeOptions();
	}

	public void rotateScreenTimer() {
		timerLocation += 1;
		if (timerLocation > 4) {
			timerLocation = 0;
		}
		BattyConfig.storeRuntimeOptions();
	}

	public void hideUnhideFPS() {
		hideFPS = (!hideFPS);
		BattyConfig.storeRuntimeOptions();
	}

	public void rotateScreenFPS() {
		fpsLocation += 1;
		if (fpsLocation > 3) {
			fpsLocation = 0;
		}
		BattyConfig.storeRuntimeOptions();
	}

	public void renderPlayerInfo() {
		if (!mc.gameSettings.showDebugInfo) {
			if (showCoords > 0) {
				renderPlayerCoords();
			} else {
				coordBoxH = 0;
			}
			if (hideTimer) {
				clockBoxH = 0;
			} else {
				renderPlayerTimer();
			}
			if (!hideFPS) {
				renderPlayerFPS();
			}
		}
	}
}
