/*
 * The MIT License
 *
 * Copyright 2014 Grzegorz.
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
package com.github.grzegorz2047.openguild;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 *
 * @author Grzegorz
 */
public class Cuboid {

    private Location center;
    private int radius;
    private String owner;

    public Location getCenter() {
        return this.center;
    }

    public int getRadius() {
        return this.radius;
    }

    public boolean isinCuboid(Location loc) {
        Vector v = loc.toVector();
        return v.isInAABB(this.getMin().toVector(), this.getMax().toVector());
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String tag) {
        this.owner = tag;
    }

    public Location getMin() {
        return new Location(this.center.getWorld(), this.center.getBlockX() - this.radius, 0, this.center.getBlockZ() - this.radius);
    }

    public Location getMax() {
        return new Location(this.center.getWorld(), this.center.getBlockX() + this.radius, this.center.getWorld().getMaxHeight(), this.center.getBlockZ() + this.radius);
    }

}
