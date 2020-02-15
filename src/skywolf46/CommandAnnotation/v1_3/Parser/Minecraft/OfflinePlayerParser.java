package skywolf46.CommandAnnotation.v1_3.Parser.Minecraft;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import skywolf46.CommandAnnotation.v1_3.API.ParameterParser;
import skywolf46.CommandAnnotation.v1_3.Data.ParameterIterator;

public class OfflinePlayerParser extends ParameterParser<OfflinePlayer> {
    @Override
    public OfflinePlayer readParameter(ParameterIterator it) {
        return Bukkit.getOfflinePlayer(it.next());
    }
}
