/*
 * The MIT License
 *
 * Copyright 2014 Aleksander.
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

package pl.grzegorz2047.openguild2047.commands.arguments;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.grzegorz2047.openguild2047.GenConf;
import pl.grzegorz2047.openguild2047.managers.MsgManager;
import pl.grzegorz2047.openguild2047.utils.ItemGUI;
import pl.grzegorz2047.openguild2047.utils.ItemGUI.ItemGUIClickEvent;

public class ItemsArg {
    
    public static boolean execute(CommandSender sender, String[] args) {
        if(GenConf.reqitems == null && !GenConf.reqitems.isEmpty()) {
            sender.sendMessage(MsgManager.get("reqitemsoff"));
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(MsgManager.cmdonlyforplayer);
            return true;
        }
        Player player = (Player) sender;
        
        if(GenConf.reqitems.size() > 0) {
            int inventorySize = 9;
            
            // I can't think of any better way to do this right now.
            if(GenConf.reqitems.size() > 9) {
                inventorySize = 18;
            }
            else if(GenConf.reqitems.size() > 18) {
                inventorySize = 27;
            }
            else if(GenConf.reqitems.size() > 27) {
                inventorySize = 36;
            }
            else if(GenConf.reqitems.size() > 36) {
                inventorySize = 45;
            }
            else if(GenConf.reqitems.size() > 45) {
                inventorySize = 54;
            }
            
            ItemGUI itemsGUI = new ItemGUI(MsgManager.getIgnorePref("gui-items"), inventorySize);
            for(ItemStack item : GenConf.reqitems) {
                ItemStack cloned = item.clone();
                ItemMeta meta = cloned.getItemMeta();
                
                int amount = getAmount(player, cloned);
                
                if(amount < cloned.getAmount()) {
                    meta.setLore(Arrays.asList(
                        ChatColor.RED + "" + amount + "/" + cloned.getAmount()
                    ));
                } else {
                    meta.setLore(Arrays.asList(
                        ChatColor.GREEN + "" + amount + "/" + cloned.getAmount()
                    ));
                }
                cloned.setItemMeta(meta);
                
                itemsGUI.addItem(cloned, new ItemGUI.ItemGUIClickEventHandler() {
                    @Override
                    public void handle(ItemGUIClickEvent event) {
                        
                    }
                });
            }
            player.openInventory(itemsGUI.getInventory());
        }
        
        return true;
    }
    
    private static int getAmount(Player player, ItemStack item) {
        int amount = 0;
        
        for(ItemStack i : player.getInventory().getContents()) {
            if(i != null && i.isSimilar(item)) {
                amount += i.getAmount();
            }
        }
        
        return amount;
    }
    
}
