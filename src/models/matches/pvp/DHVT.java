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

public class DHVT extends PVP {

    public DHVT(Player p1, Player p2) {
        super(TYPE_PVP.THACH_DAU, p1, p2);
    }

    @Override
    public void start() {
        started = true;
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
    public void reward(Player plWin) {
    }

    @Override
    public void sendResult(Player plLose, TYPE_LOSE_PVP typeLose) {
    }

}
