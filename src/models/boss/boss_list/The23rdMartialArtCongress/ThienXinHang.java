package models.boss.boss_list.The23rdMartialArtCongress;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import consts.BossID;
import models.boss.BossesData;
import static consts.BossType.PHOBAN;
import models.player.Player;
import services.EffectSkillService;
import utils.Util;

public class ThienXinHang extends The23rdMartialArtCongress {

    private long lastTimePhanThan = System.currentTimeMillis();

    public ThienXinHang(Player player) throws Exception {
        super(PHOBAN, BossID.THIEN_XIN_HANG, BossesData.THIEN_XIN_HANG);
        this.playerAtt = player;
    }

    @Override
    public void attack() {
        try {
            EffectSkillService.gI().removeStun(this);
            if (Util.canDoWithTime(lastTimePhanThan, 30000)) {
                lastTimePhanThan = System.currentTimeMillis();
                phanThan();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.attack();
    }

    private void phanThan() {
        try {
            new ThienXinHangClone(BossID.THIEN_XIN_HANG_CLONE, playerAtt);
            new ThienXinHangClone(BossID.THIEN_XIN_HANG_CLONE1, playerAtt);
            new ThienXinHangClone(BossID.THIEN_XIN_HANG_CLONE2, playerAtt);
            new ThienXinHangClone(BossID.THIEN_XIN_HANG_CLONE3, playerAtt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
