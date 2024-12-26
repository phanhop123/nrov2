package managers;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.tournament.super_rank.SuperRank;
import utils.Functions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.NonNull;
import models.map.Zone;
import models.Template.WaitSuperRank;
import models.player.Player;
import server.Client;
import server.Maintenance;

public class SuperRankManager implements Runnable {

    private final List<WaitSuperRank> waitList;

    private final List<SuperRank> list;

    private static SuperRankManager instance;

    public static SuperRankManager gI() {
        if (instance == null) {
            instance = new SuperRankManager();
        }
        return instance;
    }

    public SuperRankManager() {
        waitList = new ArrayList<>();
        list = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!Maintenance.isRunning) {
            long startTime = System.currentTimeMillis();
            try {
                Iterator<WaitSuperRank> iterator = waitList.iterator();
                while (iterator.hasNext()) {
                    WaitSuperRank wsp = iterator.next();
                    Player wPl = Client.gI().getPlayer(wsp.playerId);
                    if (wPl != null && wPl.zone != null && wPl.zone.map.mapId == 113) {
                        if (!SPRCheck(wPl.zone)) {
                            list.add(new SuperRank(wPl, wsp.rivalId, wPl.zone));
                            iterator.remove();
                        }
                    } else {
                        iterator.remove();
                    }
                }

            } catch (Exception e) {
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = 500 - elapsedTime;
            Functions.sleep(Math.max(sleepTime, 10));
        }
    }

    public boolean canCompete(Player player) {
        return !currentlyCompeting(player.id) && !awaitingCompetition(player.id);
    }

    public boolean currentlyCompeting(long playerId) {
        for (int i = list.size() - 1; i >= 0; i--) {
            SuperRank spr = list.get(i);
            if (spr.getPlayerId() == playerId || spr.getRivalId() == playerId) {
                return true;
            }
        }
        return false;
    }

    public boolean awaitingCompetition(long playerId) {
        for (int i = waitList.size() - 1; i >= 0; i--) {
            WaitSuperRank wspr = waitList.get(i);
            if (wspr.playerId == playerId || wspr.rivalId == playerId) {
                return true;
            }
        }
        return false;
    }

    public boolean awaiting(Player player) {
        for (int i = waitList.size() - 1; i >= 0; i--) {
            WaitSuperRank wspr = waitList.get(i);
            if (wspr.playerId == player.id) {
                return true;
            }
        }
        return false;
    }

    public boolean SPRCheck(@NonNull Zone zone) {
        for (int i = list.size() - 1; i >= 0; i--) {
            SuperRank spr = list.get(i);
            if (spr.getZone() != null && spr.getZone().equals(zone)) {
                return true;
            }
        }
        return false;
    }

    public int ordinal(long id) {
        for (int i = 0; i < waitList.size(); i++) {
            if (waitList.get(i).playerId == id) {
                return i + 1;
            }
        }
        return -1;
    }

    public String getCompeting(long plId) {
        for (int i = list.size() - 1; i >= 0; i--) {
            SuperRank spr = list.get(i);
            if (spr.getPlayerId() == plId) {
                return "VS " + spr.getRival().name + " kv: " + spr.getZone().zoneId;
            } else if (spr.getRivalId() == plId) {
                return "VS " + spr.getPlayer().name + " kv: " + spr.getZone().zoneId;
            }
        }
        return "";
    }

    public void addSPR(SuperRank spr) {
        list.add(spr);
    }

    public void removeSPR(SuperRank spr) {
        list.remove(spr);
    }

    public void addWSPR(long playerId, long rivalId) {
        waitList.add(new WaitSuperRank(playerId, rivalId));
    }

}