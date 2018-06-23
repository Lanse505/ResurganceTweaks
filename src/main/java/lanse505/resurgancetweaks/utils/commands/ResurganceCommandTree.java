package lanse505.resurgancetweaks.utils.commands;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class ResurganceCommandTree extends CommandTreeBase {

    public ResurganceCommandTree() {
        addSubcommand(new MasterSwitchCommand());
    }

    @Override
    public String getName() {
        return "resurgance";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "resurgancetweaks.command.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
