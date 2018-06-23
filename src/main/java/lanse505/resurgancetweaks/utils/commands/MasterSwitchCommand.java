package lanse505.resurgancetweaks.utils.commands;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class MasterSwitchCommand extends CommandBase {

    private static List<String> stages = new ArrayList<>();

    static {
        stages.add("adventurer");
        stages.add("artisan");
        stages.add("ranger");
        stages.add("miner");
        stages.add("engineer");
        stages.add("logistic");
        stages.add("processing");
        stages.add("electrical");
        stages.add("mage");
        stages.add("cosmologist");
        stages.add("druid");
        stages.add("thaumaturge");
    }

    @Override
    public String getName() {
        return "AllYourBaseAreBelongToUs";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "resurgancetweaks.command.mastercommand.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player;
        if (sender.getCommandSenderEntity() instanceof EntityPlayer) {
            player = (EntityPlayer) sender.getCommandSenderEntity();
            for (String string : stages) {
                GameStageHelper.addStage(player, string);
            }
        }
    }
}
