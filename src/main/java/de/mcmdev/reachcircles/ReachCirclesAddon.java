package de.mcmdev.reachcircles;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;

import java.util.List;

/**
 * Test addon
 */
public class ReachCirclesAddon extends LabyModAddon {

    /**
     * Called when the addon gets enabled
     */
    @Override
    public void onEnable() {
        getApi().registerForgeListener(new ReachCirclesListener());
    }

    /**
     * Called when the addon gets disabled
     */
    @Override
    public void onDisable() {

    }

    /**
     * Called when this addon's config was loaded and is ready to use
     */
    @Override
    public void loadConfig() {
        if(!getConfig().has("enabled")) {
            getConfig().addProperty("enabled", false);
        }
        ReachCirclesListener.show = getConfig().get("enabled").getAsBoolean();
    }

    /**
     * Called when the addon's ingame settings should be filled
     *
     * @param subSettings a list containing the addon's settings' elements
     */
    @Override
    protected void fillSettings( List<SettingsElement> subSettings ) {
        subSettings.add(new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                ReachCirclesListener.show = aBoolean;
                getConfig().addProperty("enabled", aBoolean);
            }
        }, ReachCirclesListener.show));
    }

}