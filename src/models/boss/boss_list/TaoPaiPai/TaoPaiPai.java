package models.boss.boss_list.TaoPaiPai;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.boss.Boss;
import models.boss.BossesData;
import static consts.BossType.FINAL;
import models.player.Player;
import services.EffectSkillService;
import services.Service;
import utils.Util;

public class TaoPaiPai extends Boss {

    public TaoPaiPai() throws Exception {
        super(FINAL, Util.randomBossId(), BossesData.TAU_PAY_PAY_DONG_NAM_KARIN);
    }

    @Override
    public synchronized int injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            if (damage >= 5000000) {
                damage = 5000000;
            }
            this.nPoint.dame = (int) damage / Util.nextInt(500, 1000);
            this.nPoint.subHP(damage);
            long tnSm = damage * Util.nextInt(20, 50);
            if (tnSm > 10000000) {
                tnSm = 10000000 - Util.nextInt(1000000);
            }
            if (plAtt.nPoint.power > 120_000_000_000L) {
                tnSm = Util.nextInt(1000);
            }
            Service.gI().addSMTN(plAtt, (byte) 2, tnSm, true);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);
            }
            return (int) damage;
        } else {
            return 0;
        }
    }
}
