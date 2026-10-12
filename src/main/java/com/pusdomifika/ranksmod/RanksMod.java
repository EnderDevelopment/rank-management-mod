package com.pusdomifika.ranksmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RanksMod implements ModInitializer {
    public static final String MOD_ID = "ranksmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing RanksMod");
        registerCommands();
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("ranks")
                .then(CommandManager.literal("create")
                    .then(CommandManager.argument("id", IntegerArgumentType.integer())
                        .then(CommandManager.argument("name", StringArgumentType.string())
                            .executes(context -> {
                                int id = IntegerArgumentType.getInteger(context, "id");
                                String name = StringArgumentType.getString(context, "name");
                                // Logic to create rank
                                context.getSource().sendFeedback(() -> Text.translatable("ranksmod.rank.created", name), false);
                                return 1;
                            }))))
                .then(CommandManager.literal("give")
                    .then(CommandManager.argument("player", StringArgumentType.string())
                        .then(CommandManager.argument("id", IntegerArgumentType.integer())
                            .executes(context -> {
                                String player = StringArgumentType.getString(context, "player");
                                int id = IntegerArgumentType.getInteger(context, "id");
                                // Logic to give rank
                                context.getSource().sendFeedback(() -> Text.translatable("ranksmod.rank.given", player, id), false);
                                return 1;
                            }))))
                .then(CommandManager.literal("remove")
                    .then(CommandManager.argument("player", StringArgumentType.string())
                        .then(CommandManager.argument("id", IntegerArgumentType.integer())
                            .executes(context -> {
                                String player = StringArgumentType.getString(context, "player");
                                int id = IntegerArgumentType.getInteger(context, "id");
                                // Logic to remove rank
                                context.getSource().sendFeedback(() -> Text.translatable("ranksmod.rank.removed", player, id), false);
                                return 1;
                            })))));
        });
    }
}