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

package io.github.m0pt0pmatt.spongesurvivalgames.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Class which houses loot and can produce a piece of loot on command
 *
 */
public class LootGenerator {

    private static final Random rand = new Random();
    private final List<Loot> loot = new ArrayList<>();
    private double weight = 0;

    /**
     * Adds a piece of loot to this generator's list of loot items.
     */
    public void addLoot(Loot loot) {
        this.loot.add(loot);
        weight += loot.getWeight();
    }

    /**
     * Clears out all previously-added loot items, returning this loot generator to a clean status
     */
    public void clearLoot() {
        loot.clear();
        weight = 0;
    }

    /**
     * Generates a random piece of loot from the supplied loot.
     *
     * @return A newly generated piece of loot, or <i>null</i> on error
     */
    public Optional<Loot> generate() {
        if (loot.isEmpty()) {
            return Optional.empty();
        }

        double pos = rand.nextDouble() * weight;
        double index = 0.0;

        Loot chosenLoot = null;

        for (Loot item : loot) {
            index += item.getWeight();
            if (index >= pos) {
                //finally got up to the item with the generated weight
                chosenLoot = item;
                break;
            }
        }

        return chosenLoot == null ? Optional.empty() : Optional.of(chosenLoot);
    }
}