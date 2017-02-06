package mods.batty.main;

import de.labystudio.modapi.EventHandler;
import de.labystudio.modapi.Listener;
import de.labystudio.modapi.events.GameTickEvent;
import de.labystudio.modapi.events.InitScreenEvent;
import de.labystudio.modapi.events.RenderOverlayEvent;
import net.minecraft.client.gui.GuiMainMenu;

public class BattyListener implements Listener {

	@EventHandler
	public void onRenderOverlay(RenderOverlayEvent event) {
		BattyUtils.updateTimer(BattyMod.getInstance().getUpdateCounter());
		BattyMod.getInstance().getBatheartgui().renderPlayerInfo();
	}

	@EventHandler
	public void onInitScreen(InitScreenEvent event) {
		if(event.getScreen() instanceof GuiMainMenu) {
			BattyMod.getInstance().getBatheartgui().timerRunning = false;
			BattyConfig.storeRuntimeOptions();
		}
	}

	@EventHandler
	public void onGameTick(GameTickEvent event) {
		BattyMod.getInstance().upUpdateCounter();
		BattyUI gui = BattyMod.getInstance().getBatheartgui();
		if (BattyUI.hideunhideCoordskey.isPressed()) {
			gui.hideUnhideCoords();
		}
		if (BattyUI.hideunhideTimerkey.isPressed()) {
			gui.hideUnhideStopWatch();
		}
		if (BattyUI.resetTimerkey.isPressed()) {
			gui.resetTimer = true;
		}
		if (BattyUI.startstopTimerkey.isPressed()) {
			gui.toggleTimer = true;
		}
		if (BattyUI.moveCoordScreenPos.isPressed()) {
			gui.rotateScreenCoords();
		}
		if (BattyUI.copyCoordsClipboard.isPressed()) {
			gui.copyScreenCoords();
		}
		if (BattyUI.moveTimerScreenPos.isPressed()) {
			gui.rotateScreenTimer();
		}
		if (BattyUI.hideunhideFPSkey.isPressed()) {
			gui.hideUnhideFPS();
		}
		if (BattyUI.moveFPSScreenPos.isPressed()) {
			gui.rotateScreenFPS();
		}
	}
}
