package models.matches.pvp;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.matches.PVP;
import models.matches.TYPE_LOSE_PVP;
import models.matches.TYPE_PVP;
import models.player.Player;
import services.EffectSkillService;
import services.Service;

public class PKCommeson extends PVP {

    public PKCommeson(Player p1, Player p2) {
        super(TYPE_PVP.THACH_DAU, p1, p2);
    }

    @Override
    public void finish() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void update() {
    }

    @Override
    public void sendResult(Player plLose, TYPE_LOSE_PVP typeLose) {
        if (typeLose == TYPE_LOSE_PVP.RUNS_AWAY) {
            Player pl = p1.isPl() ? p1 : p2;
            EffectSkillService.gI().removePKCommeson(pl);
            if (pl.equals(plLose)) {
                Service.gI().sendThongBao(pl, "Bạn đã thất bại, ngày mai hãy thử sức tiếp");
            }
        }
    }

    @Override
    public void reward(Player plWin) {

    }

}
