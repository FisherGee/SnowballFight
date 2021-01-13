package me.rileykenny.snowballfight.event.listeners;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowballHitBlockListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e){
        if(!(e.getEntity() instanceof Snowball)){
            return;
        }

        Snowball snowBall = (Snowball) e.getEntity();
        NBTItem snowBallNBT = new NBTItem(snowBall.getItem());

        if (!snowBallNBT.hasKey("id")) {
            return;
        } if (!snowBallNBT.getString("id").equals("snowballfight")) {
            return;
        }

        //make sure it hit a block
        if(e.getHitEntity() != null){
            return;
        }

        Block hitBlock = e.getHitBlock();
        Player thrower = (Player) e.getEntity();
        hitBlock.setType(Material.AIR);
        thrower.playSound(thrower.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
    }
}
