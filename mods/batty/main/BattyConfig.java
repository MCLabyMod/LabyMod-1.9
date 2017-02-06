package mods.batty.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class BattyConfig {

	private static BattyUI ui = BattyMod.getInstance().getBatheartgui();
	
	public static void retrieveOptions()
	{
		if (ui.optionsFile.exists()) {
			try
			{
				FileInputStream fis = new FileInputStream(ui.optionsFile);
				try
				{
					ui.propts.load(fis);
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
			catch (FileNotFoundException var5)
			{
				var5.printStackTrace();
			}
		}
		String myShade = ui.propts.getProperty("Coords.shade");
		String myTxtChr1 = ui.propts.getProperty("Coords.chars.Increase");
		String myTxtChr2 = ui.propts.getProperty("Coords.chars.Decrease");
		String myTxtCol1 = ui.propts.getProperty("Coords.colours.keyBindingsitleText");
		String myTxtCol6 = ui.propts.getProperty("Coords.colours.CoordText");
		String myTxtCol2 = ui.propts.getProperty("Coords.colours.PosCoordText");
		String myTxtCol7 = ui.propts.getProperty("Coords.colours.NegCoordText");
		String myTxtCol3 = ui.propts.getProperty("Coords.colours.CompassText");
		String myTxtCol4 = ui.propts.getProperty("Coords.colours.ChevronText");
		String myTxtCol5 = ui.propts.getProperty("Coords.colours.BiomeText");
		String myTxtCol8 = ui.propts.getProperty("Coords.colours.PosChunkText");
		String myTxtCol9 = ui.propts.getProperty("Coords.colours.NegChunkText");
		String myTxtFmt1 = ui.propts.getProperty("Coords.copy.tpFormat");
		if (myShade != null) {
			ui.shadedCoords = myShade.equals("true");
		}
		if (myTxtChr1 != null) {
			if (myTxtChr1.length() > 1) {
				ui.myChevronUp = myTxtChr1.substring(0, 1);
			} else {
				ui.myChevronUp = myTxtChr1;
			}
		}
		if (myTxtChr2 != null) {
			if (myTxtChr2.length() > 1) {
				ui.myChevronDown = myTxtChr2.substring(0, 1);
			} else {
				ui.myChevronDown = myTxtChr2;
			}
		}
		if (myTxtCol1 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol1);
			if (ui.myFind != -1) {
				ui.myTitleText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol2 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol2);
			if (ui.myFind != -1) {
				ui.myPosCoordText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol7 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol7);
			if (ui.myFind != -1) {
				ui.myNegCoordText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol3 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol3);
			if (ui.myFind != -1) {
				ui.myCompassText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol4 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol4);
			if (ui.myFind != -1) {
				ui.myChevronText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol5 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol5);
			if (ui.myFind != -1) {
				ui.myBiomeText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol6 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol6);
			if (ui.myFind != -1)
			{
				ui.myPosCoordText = ui.myColourCodes[ui.myFind];
				ui.myNegCoordText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol8 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol8);
			if (ui.myFind != -1) {
				ui.myPosChunkText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol9 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol9);
			if (ui.myFind != -1) {
				ui.myNegChunkText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtFmt1 != null) {
			ui.coordsCopyTPFormat = myTxtFmt1.equals("true");
		}
		myShade = ui.propts.getProperty("Timer.shade");
		myTxtCol1 = ui.propts.getProperty("Timer.colours.Stopped");
		myTxtCol2 = ui.propts.getProperty("Timer.colours.Running");
		if (myShade != null) {
			ui.shadedTimer = myShade.equals("true");
		}
		if (myTxtCol1 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol1);
			if (ui.myFind != -1) {
				ui.myTimerStopText = ui.myColourCodes[ui.myFind];
			}
		}
		if (myTxtCol2 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol2);
			if (ui.myFind != -1) {
				ui.myTimerRunText = ui.myColourCodes[ui.myFind];
			}
		}
		myShade = ui.propts.getProperty("FPS.shade");
		myTxtCol1 = ui.propts.getProperty("FPS.colours.Text");
		if (myShade != null) {
			ui.shadedFPS = myShade.equals("true");
		}
		if (myTxtCol1 != null)
		{
			ui.myFind = BattyUtils.nameSearch(ui.myColourList, myTxtCol1);
			if (ui.myFind != -1) {
				ui.myFPSText = ui.myColourCodes[ui.myFind];
			}
		}
	}

	public static void retrieveRuntimeOptions()
	{
		if (ui.runtimeFile.exists()) {
			try
			{
				FileInputStream fis = new FileInputStream(ui.runtimeFile);
				try
				{
					ui.proprt.load(fis);
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
			catch (FileNotFoundException var5)
			{
				var5.printStackTrace();
			}
		}
		String myTimeString = ui.proprt.getProperty("Timer.saved");
		if (myTimeString != null) {
			BattyUtils.parseTimeString(myTimeString);
		}
		String myCoordsVis = ui.proprt.getProperty("Coords.visible");
		if (myCoordsVis != null) {
			ui.showCoords = Integer.parseInt(myCoordsVis);
		}
		String myTimerVis = ui.proprt.getProperty("Timer.visible");
		if (myTimerVis != null) {
			ui.hideTimer = (!myTimerVis.equals("true"));
		}
		String myCoordsLoc = ui.proprt.getProperty("Coords.location");
		if (myCoordsLoc != null) {
			ui.coordLocation = Integer.parseInt(myCoordsLoc);
		}
		String myTimerLoc = ui.proprt.getProperty("Timer.location");
		if (myTimerLoc != null) {
			ui.timerLocation = Integer.parseInt(myTimerLoc);
		}
		String myTimerRuns = ui.proprt.getProperty("Timer.running");
		if (myTimerRuns != null) {
			ui.timerRunning = myTimerRuns.equals("true");
		}
		String myFPSVis = ui.proprt.getProperty("FPS.visible");
		if (myFPSVis != null) {
			ui.hideFPS = (!myFPSVis.equals("true"));
		}
		String myFPSLoc = ui.proprt.getProperty("FPS.location");
		if (myFPSLoc != null) {
			ui.fpsLocation = Integer.parseInt(myFPSLoc);
		}
	}

	public static void storeRuntimeOptions()
	{
		ui.proprt.setProperty("Timer.saved", BattyUtils.getSaveString());
		ui.proprt.setProperty("Coords.visible", BattyUtils.constructCoordVisString());
		ui.proprt.setProperty("Timer.visible", BattyUtils.constructTimerVisString());
		ui.proprt.setProperty("Coords.location", BattyUtils.constructCoordLocString());
		ui.proprt.setProperty("Timer.location", BattyUtils.constructTimerLocString());
		ui.proprt.setProperty("Timer.running", BattyUtils.constructTimerRunString());
		ui.proprt.setProperty("FPS.visible", BattyUtils.constructFPSVisString());
		ui.proprt.setProperty("FPS.location", BattyUtils.constructFPSLocString());
		try
		{
			FileOutputStream fos = new FileOutputStream(ui.runtimeFile);
			ui.proprt.store(fos, null);
			fos.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
