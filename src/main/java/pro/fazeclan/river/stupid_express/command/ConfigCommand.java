package pro.fazeclan.river.stupid_express.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import pro.fazeclan.river.stupid_express.cca.SEConfig;

public class ConfigCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("stupid_express:config")
                        .requires(ctx -> ctx.hasPermission(2))
                        .then(Commands.literal("necromancer_has_shop")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ConfigCommand::necromancerHasShopExecute)
                                )
                        )
                        .then(Commands.literal("arsonist_keeps_game_going")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ConfigCommand::arsonistKeepAliveExecute)
                                )
                        )
                        .then(Commands.literal("lovers_win_with_civilians")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ConfigCommand::loversWinCiviliansExecute)
                                )
                        )
                        .then(Commands.literal("lovers_win_with_killers")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ConfigCommand::loversWinKillersExecute)
                                )
                        )
                        .then(Commands.literal("lovers_know_immediately")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ConfigCommand::loversKnowImmediatelyExecute)
                                )
                        )
        );
    }

    private static int necromancerHasShopExecute(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();
        var config = SEConfig.KEY.get(source.getLevel());
        var value = ctx.getArgument("value", Boolean.class);

        config.setNecromancerHasShop(value);
        config.sync();

        source.sendSystemMessage(Component.translatable("commands.stupid_express.set_config_value", "necromancer_has_shop", value));
        return 1;
    }

    private static int arsonistKeepAliveExecute(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();
        var config = SEConfig.KEY.get(source.getLevel());
        var value = ctx.getArgument("value", Boolean.class);

        config.setArsonistKeepsGameGoing(value);
        config.sync();

        source.sendSystemMessage(Component.translatable("commands.stupid_express.set_config_value", "arsonist_keeps_game_going", value));
        return 1;
    }

    private static int loversWinCiviliansExecute(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();
        var config = SEConfig.KEY.get(source.getLevel());
        var value = ctx.getArgument("value", Boolean.class);

        config.setLoversWinWithCivilians(value);
        config.sync();

        source.sendSystemMessage(Component.translatable("commands.stupid_express.set_config_value", "lovers_win_with_civilians", value));
        return 1;
    }

    private static int loversWinKillersExecute(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();
        var config = SEConfig.KEY.get(source.getLevel());
        var value = ctx.getArgument("value", Boolean.class);

        config.setLoversWinWithKillers(value);
        config.sync();

        source.sendSystemMessage(Component.translatable("commands.stupid_express.set_config_value", "lovers_win_with_killers", value));
        return 1;
    }

    private static int loversKnowImmediatelyExecute(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();
        var config = SEConfig.KEY.get(source.getLevel());
        var value = ctx.getArgument("value", Boolean.class);

        config.setLoversKnowImmediately(value);
        config.sync();

        source.sendSystemMessage(Component.translatable("commands.stupid_express.set_config_value", "lovers_know_immediately", value));
        return 1;
    }

}
