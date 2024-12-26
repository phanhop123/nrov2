package models.boss.boss_list.Training;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import consts.BossID;
import consts.BossStatus;
import models.boss.BossesData;
import static consts.BossType.PHOBAN;
import models.player.Player;
import services.map.ChangeMapService;
import utils.Util;

public class Karin extends TrainingBoss {

    private long lastTimeBay;
    private long lastTimeBay2;

    public Karin(Player player) throws Exception {
        super(PHOBAN, BossID.KARIN, BossesData.KARIN);
        this.playerAtt = player;
    }

    @Override
    public void joinMap() {
        if (playerAtt.zone != null) {
            this.zone = playerAtt.zone;
            ChangeMapService.gI().changeMap(this, this.zone, 420, 408);
            this.changeStatus(BossStatus.CHAT_S);
        }
    }

    @Override
    public boolean chatS() {
        if (Util.canDoWithTime(lastTimeChatS, timeChatS)) {
            if (this.doneChatS) {
                return true;
            }
            String textChat = this.data[this.currentLevel].getTextS()[playerAtt.isThachDau ? 1 : 0];
            int prefix = Integer.parseInt(textChat.substring(1, textChat.lastIndexOf("|")));
            textChat = textChat.substring(textChat.lastIndexOf("|") + 1);
            if (!this.chat(prefix, textChat)) {
                return false;
            }
            this.moveToPlayer(playerAtt);
            this.lastTimeChatS = System.currentTimeMillis();
            this.timeChatS = 2000;
            doneChatS = true;
        }
        return false;
    }

    @Override
    public void bayLungTung() {
        if (Util.canDoWithTime(lastTimeBay, 5000)) {
            goToXY(playerAtt.location.x + (Util.getOne(-1, 1) * Util.nextInt(20, 80)), this.location.y - 100, false);
            lastTimeBay = System.currentTimeMillis();
        }
        if (Util.canDoWithTime(lastTimeBay2, 5500)) {
            goToXY(playerAtt.location.x + (Util.getOne(-1, 1) * Util.nextInt(20, 80)), this.location.y - 100, false);
            lastTimeBay2 = System.currentTimeMillis();
        }
    }

    @Override
    public void afk() {
        if (Util.canDoWithTime(lastTimeMove, 1500)) {
            this.goToXY(playerAtt.location.x, playerAtt.location.y);
            this.lastTimeMove = System.currentTimeMillis();
        }
        if (Util.canDoWithTime(lastTimeAFK, 5000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }
}
