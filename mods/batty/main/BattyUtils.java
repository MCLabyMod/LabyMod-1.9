package mods.batty.main;

import java.util.logging.Logger;

import net.minecraft.util.math.MathHelper;

public class BattyUtils {
	
	private static BattyUI ui = BattyMod.getInstance().getBatheartgui();
	
	public static int nameSearch(String[] names, String name)
	{
		for (int n = 0; n < names.length; n++) {
			if (names[n].equals(name)) {
				return n;
			}
		}
		return -1;
	}

	public static int getCardinalPoint(float par0)
	{
		double myPoint = MathHelper.wrapAngleTo180_float(par0) + 180.0D;
		myPoint += 22.5D;
		myPoint %= 360.0D;
		myPoint /= 45.0D;
		return MathHelper.floor_double(myPoint);
	}

	public static String constructCoordVisString()
	{
		String var1 = "";
		var1 = var1 + ui.showCoords;
		return var1;
	}

	public static String constructTimerVisString()
	{
		String var1;
		if (ui.hideTimer) {
			var1 = "false";
		} else {
			var1 = "true";
		}
		return var1;
	}

	public static String constructCoordLocString()
	{
		String var1 = "";
		var1 = var1 + ui.coordLocation;
		return var1;
	}

	public static String constructTimerRunString()
	{
		String var1;
		if (ui.timerRunning) {
			var1 = "true";
		} else {
			var1 = "false";
		}
		return var1;
	}

	public static String constructFPSVisString()
	{
		String var1;
		if (ui.hideFPS) {
			var1 = "false";
		} else {
			var1 = "true";
		}
		return var1;
	}

	public static String constructFPSLocString()
	{
		String var1 = "";
		var1 = var1 + ui.fpsLocation;
		return var1;
	}

	public static String constructTimerLocString()
	{
		String var1 = "";
		var1 = var1 + ui.timerLocation;
		return var1;
	}

	public static void parseTimeString(String var1)
	{
		Logger.getLogger("Minecraft").info(var1);
		String[] var2 = var1.split("\\|");
		ui.hourCounter = Integer.parseInt(var2[0]);
		ui.minuteCounter = Integer.parseInt(var2[1]);
		ui.secondCounter = Integer.parseInt(var2[2]);
	}

	public static String constructTimeString()
	{
		String var1 = "";
		var1 = var1 + (ui.hourCounter >= 10 ? "" : "0");
		var1 = var1 + ui.hourCounter;
		var1 = var1 + ":";
		var1 = var1 + (ui.minuteCounter >= 10 ? "" : "0");
		var1 = var1 + ui.minuteCounter;
		var1 = var1 + ":";
		var1 = var1 + (ui.secondCounter >= 10 ? "" : "0");
		var1 = var1 + ui.secondCounter;
		return var1;
	}

	public static String getSaveString()
	{
		return constructTimeString().replace(":", "|");
	}

	public static void resetTimer()
	{
		ui.resetTimer = false;
		ui.tickCounter = (ui.hourCounter = ui.minuteCounter = ui.secondCounter = 0);

		BattyConfig.storeRuntimeOptions();
	}

	public static void addOneSecond()
	{
		ui.secondCounter += 1;
		if (ui.secondCounter >= 60)
		{
			ui.secondCounter -= 60;
			ui.minuteCounter += 1;
		}
		if (ui.minuteCounter >= 60)
		{
			ui.minuteCounter -= 60;
			ui.hourCounter += 1;
		}
	}

	public static void updateTimer(int var1)
	{
		if (ui.resetTimer) {
			resetTimer();
		}
		if (ui.toggleTimer)
		{
			ui.toggleTimer = false;
			ui.tickCounter = 0;
			ui.timerRunning = (!ui.timerRunning);
			BattyConfig.storeRuntimeOptions();
		}
		if (ui.timerRunning)
		{
			if (ui.tickCounter == 0) {
				ui.tickCounter = var1;
			}
			if (var1 - ui.tickCounter >= 20)
			{
				addOneSecond();
				ui.tickCounter += 20;
			}
		}
	}
}
