package models.map;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.player.Player;
import services.player.PlayerService;
import services.func.EffectMapService;
import utils.Util;

public class TrapMap {

    public int x;
    public int y;
    public int w;
    public int h;
    public int effectId;
    public long dame;

    public void doPlayer(Player player) {
        if (this.effectId == 49) {
            if (!player.isDie() && Util.canDoWithTime(player.idMark.getLastTimeAnXienTrapBDKB(), 1000)) {
                player.injured(null, dame + (Util.nextLong(-10L, 10L) * dame / 100L), false, false);
                PlayerService.gI().sendInfoHp(player);
                EffectMapService.gI().sendEffectMapToAllInMap(player.zone, effectId, 2, 1, player.location.x - 32, 1040, 1);
                player.idMark.setLastTimeAnXienTrapBDKB(System.currentTimeMillis());
            }
        }
    }

}
