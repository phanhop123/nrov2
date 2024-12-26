package interfaces;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import consts.BossStatus;
import models.player.Player;

public interface IBoss {

    void update();

    void updateInfo();

    void initBase();

    void changeStatus(BossStatus status);

    Player getPlayerAttack();

    void changeToTypePK();

    void changeToTypeNonPK();

    void moveToPlayer(Player player);

    void moveTo(int x, int y);

    void checkPlayerDie(Player player);

    void wakeupAnotherBossWhenAppear();

    void wakeupAnotherBossWhenDisappear();

    void reward(Player plKill);

    void attack();

    void rest();

    void respawn();

    void joinMap();

    boolean chatS();

    void doneChatS();

    void active();

    void chatM();

    void die(Player plKill);

    boolean chatE();

    void doneChatE();

    void leaveMap();

    void autoLeaveMap();

    void afk();

    short getHead();

    short getBody();

    short getLeg();

    short getFlagBag();

    byte getAura();

    byte getEffFront();
}
