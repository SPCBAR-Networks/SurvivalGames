/*
 * This file is part of SpongeSurvivalGamesPlugin, licensed under the MIT License (MIT).
 *
 * Copyright (c) Matthew Broomfield <m0pt0pmatt17@gmail.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.m0pt0pmatt.spongesurvivalgames.command.game.stopped;

import io.github.m0pt0pmatt.spongesurvivalgames.command.CommandArgs;
import org.bukkit.command.CommandSender;
import org.spongepowered.api.command.spec.CommandExecutor;

import java.util.Map;

public class SetDeathmatchRadiusCommand implements CommandExecutor {
    @Override
    public boolean execute(CommandSender sender, Map<CommandArgs, String> arguments) {

        if (!super.execute(sender, arguments)) {
            return false;
        }

        if (!arguments.containsKey(CommandArgs.DEATHMATCHRADIUS)) {
            sender.sendMessage("No deathmatch radius specified");
            return false;
        }
        String deathmatchRadiusString = arguments.get(CommandArgs.DEATHMATCHRADIUS);

        int deathmatchRadius;
        try {
            deathmatchRadius = Integer.parseInt(deathmatchRadiusString);
        } catch (NumberFormatException e) {
            sender.sendMessage("Unable to convert String to Integer");
            return false;
        }

        try {
            game.setDeathmatchRadius(deathmatchRadius);
        } catch (NegativeNumberException e) {
            sender.sendMessage("Negative deathmatch radius is not valid");
            return false;
        }

        sender.sendMessage("Game \"" + game.getID() + "\" now has a deathmatch radius of \"" + deathmatchRadius + "\" blocks.");
        return true;
    }
}