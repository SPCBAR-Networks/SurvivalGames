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
package io.github.m0pt0pmatt.spongesurvivalgames.task;

import static io.github.m0pt0pmatt.spongesurvivalgames.Util.getOrThrow;

import io.github.m0pt0pmatt.spongesurvivalgames.command.CommandKeys;
import io.github.m0pt0pmatt.spongesurvivalgames.data.MobSpawnArea;
import io.github.m0pt0pmatt.spongesurvivalgames.game.SurvivalGame;
import io.github.m0pt0pmatt.spongesurvivalgames.mobspawn.ActiveMobSpawnRepository;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.TextMessageException;
import org.spongepowered.api.world.World;

/** Starts the mob spawners, which will spawn entities continuously. */
public class StartMobSpawnersTask implements Task {

    private static final Task INSTANCE = new StartMobSpawnersTask();

    private StartMobSpawnersTask() {

    }

    @Override
    public void execute(SurvivalGame survivalGame) throws TextMessageException {
        String worldName = getOrThrow(survivalGame.getConfig().getWorldName(), CommandKeys.WORLD_NAME);
        World world = getOrThrow(Sponge.getServer().getWorld(worldName), CommandKeys.WORLD);

        for (MobSpawnArea mobSpawnArea: survivalGame.getConfig().getMobSpawnAreas()) {
            survivalGame.getActiveMobSpawners().add(ActiveMobSpawnRepository.start(survivalGame, mobSpawnArea, world));
        }
    }

    public static Task getInstance() {
        return INSTANCE;
    }
}
