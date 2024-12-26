package models.mob.bigboss_list;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.mob.BigBoss;
import models.mob.Mob;
import models.player.Player;
import services.Service;
import utils.Util;

public class RobotBaoVe extends BigBoss {

    public RobotBaoVe(Mob mob) {
        super(mob);
    }

    @Override
    public void update() {
        super.update();
        if (this.isDie()) {
            Service.gI().sendBigBoss(this.zone, 6, 0, -1, -1);
        }
    }

    @Override
    public void attack() {
        Player player = getPlayerCanAttack();
        if (!isDie() && !effectSkill.isHaveEffectSkill() && Util.canDoWithTime(lastBigBossAttackTime, 1000)) {
            if (player != null) {
                int dameMob = this.point.getDameAttack();
                int dame = player.injured(null, dameMob, false, true);
                action = 0;
                int dis = Util.getDistance(player, this);
                if (dis <= 100) {
                    action = 0;
                } else if (dis > 100 && dis <= 200) {
                    action = 1;
                } else if (dis > 200) {
                    action = 2;
                }
                this.location.x = (short) player.location.x;
                Service.gI().sendBigBoss(this.zone, action, 1, (int) player.id, dame);
            }
            lastBigBossAttackTime = System.currentTimeMillis();
        }
    }

}
