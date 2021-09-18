package micronaut.pf4j.poc.services;

import jakarta.inject.Singleton;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.pf4j.update.PluginInfo;
import org.pf4j.update.UpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Singleton
public class PluginService {

    private static final Logger log = LoggerFactory.getLogger(PluginService.class);
    private final PluginManager pluginManager = new DefaultPluginManager();
    /**
     * Initialize an empty UpdateRepository list to prevent NPE
     * if repositories.json does not exist
     */
    private final UpdateManager updateManager = new UpdateManager(pluginManager, Collections.emptyList());

    public void loadPlugins() {
        pluginManager.loadPlugins();
    }

    public void startPlugins() {
        pluginManager.startPlugins();
    }

    public void stopPlugins() {
        pluginManager.stopPlugins();
    }

    public void updatePlugins() {
        updateManager.refresh();
        // >> keep system up-to-date <<
        boolean systemUpToDate = true;

        // check for updates
        if (updateManager.hasUpdates()) {
            List<PluginInfo> updates = updateManager.getUpdates();
            log.debug("Found {} updates", updates.size());
            for (PluginInfo plugin : updates) {
                log.debug("Found update for plugin '{}'", plugin.id);
                PluginInfo.PluginRelease lastRelease = updateManager.getLastPluginRelease(plugin.id);
                String lastVersion = lastRelease.version;
                String installedVersion = pluginManager.getPlugin(plugin.id).getDescriptor().getVersion();
                log.debug("Update plugin '{}' from version {} to version {}", plugin.id, installedVersion, lastVersion);
                boolean updated = updateManager.updatePlugin(plugin.id, lastVersion);
                if (updated) {
                    log.debug("Updated plugin '{}'", plugin.id);
                } else {
                    log.error("Cannot update plugin '{}'", plugin.id);
                    systemUpToDate = false;
                }
            }
        } else {
            log.debug("No updates found");
        }

        // check for available (new) plugins
        if (updateManager.hasAvailablePlugins()) {
            List<PluginInfo> availablePlugins = updateManager.getAvailablePlugins();
            log.debug("Found {} available plugins", availablePlugins.size());
            for (PluginInfo plugin : availablePlugins) {
                log.debug("Found available plugin '{}'", plugin.id);
                PluginInfo.PluginRelease lastRelease = updateManager.getLastPluginRelease(plugin.id);
                String lastVersion = lastRelease.version;
                log.debug("Install plugin '{}' with version {}", plugin.id, lastVersion);
                boolean installed = updateManager.installPlugin(plugin.id, lastVersion);
                if (installed) {
                    log.debug("Installed plugin '{}'", plugin.id);
                } else {
                    log.error("Cannot install plugin '{}'", plugin.id);
                    systemUpToDate = false;
                }
            }
        } else {
            log.debug("No available plugins found");
        }

        if (systemUpToDate) {
            log.debug("System up-to-date");
        }
    }

    public <T> List<T> getExtensions(Class<T> type) {
        return pluginManager.getExtensions(type);
    }

    public <T> T getExtension(Class<T> type) {
        List<T> extensions = getExtensions(type);
        return extensions.isEmpty() ? null : extensions.get(0);
    }

}
