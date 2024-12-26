package services.dungeon;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.dungeon.RedRibbonHQ;
import models.map.Zone;
import models.player.Player;
import services.Service;
import services.map.ChangeMapService;
import java.util.ArrayList;
import java.util.List;
import utils.Util;

public class RedRibbonHQService {

    private static RedRibbonHQService instance;

    public static RedRibbonHQService gI() {
        if (RedRibbonHQService.instance == null) {
            RedRibbonHQService.instance = new RedRibbonHQService();
        }
        return RedRibbonHQService.instance;
    }

    public List<RedRibbonHQ> doanhTrais;

    private RedRibbonHQService() {
        this.doanhTrais = new ArrayList<>();
        for (int i = 0; i < RedRibbonHQ.AVAILABLE; i++) {
            this.doanhTrais.add(new RedRibbonHQ(i));
        }
    }

    public void addMapDoanhTrai(int id, Zone zone) {
        this.doanhTrais.get(id).getZones().add(zone);
    }

    public void joinDoanhTrai(Player pl) {
        if (pl.clan == null) {
            Service.gI().sendThongBao(pl, "Không thể thực hiện");
            return;
        }
        if (pl.clan.haveGoneDoanhTrai && !Util.isAfterMidnight(pl.clan.lastTimeOpenDoanhTrai)) {
            Service.gI().sendThongBao(pl, "Vui lòng chờ đến ngày mai");
            return;
        }
        if (pl.clanMember.getNumDateFromJoinTimeToToday() < 1) {
            return;
        }
        if (pl.clan.doanhTrai != null) {
            pl.lastTimeJoinDT = System.currentTimeMillis();
            pl.clan.doanhTrai.updateHPDame();
            ChangeMapService.gI().changeMapInYard(pl, 53, -1, 60);
            return;
        }
        RedRibbonHQ doanhTrai = null;
        for (RedRibbonHQ dt : this.doanhTrais) {
            if (dt.getClan() == null) {
                doanhTrai = dt;
                break;
            }
        }
        if (doanhTrai == null) {
            Service.gI().sendThongBao(pl, "Doanh trại đã đầy, hãy quay lại vào lúc khác!");
            return;
        }
        doanhTrai.openDoanhTrai(pl);
    }
}
