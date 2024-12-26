package managers.tournament;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import models.map.Zone;
import models.tournament.DeathOrAliveArena;
import server.Maintenance;
import utils.Functions;
import utils.Util;

public class DeathOrAliveArenaManager implements Runnable {

    private static DeathOrAliveArenaManager instance;
    private volatile long lastUpdate;
    private static final List<DeathOrAliveArena> list = new ArrayList<>();

    public static DeathOrAliveArenaManager gI() {
        if (instance == null) {
            instance = new DeathOrAliveArenaManager();
        }
        return instance;
    }

    @Override
    public void run() {
        while (!Maintenance.isRunning) {
            try {
                long start = System.currentTimeMillis();
                update();
                Functions.sleep(Math.max(1000 - (System.currentTimeMillis() - start), 10));
            } catch (Exception ex) {
            }
        }
    }

    public void update() {
        if (Util.canDoWithTime(lastUpdate, 1000)) {
            lastUpdate = System.currentTimeMillis();
            for (int i = list.size() - 1; i >= 0; i--) {
                if (i < list.size()) {
                    list.get(i).update();
                }
            }
        }
    }

    public void add(DeathOrAliveArena vdst) {
        list.add(vdst);
    }

    public void remove(DeathOrAliveArena vdst) {
        list.remove(vdst);
    }

    public DeathOrAliveArena getVDST(@NonNull Zone zone) {
        for (DeathOrAliveArena vdst : list) {
            if (vdst.getZone().equals(zone)) {
                return vdst;
            }
        }
        return null;
    }
}
