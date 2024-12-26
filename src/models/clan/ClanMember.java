package models.clan;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.player.Player;
import utils.TimeUtil;
import java.util.Date;

public class ClanMember {

    public Clan clan;

    public int id;

    public short head;

    public short leg;

    public short body;

    public String name;

    public byte role;

    public long powerPoint;

    public int donate;

    public int receiveDonate;

    /**
     * Capsule cá nhân
     */
    public int memberPoint;

    /**
     * Capsule cho bang
     */
    public int clanPoint;

    public int lastRequest;

    public int joinTime;

    public long timeAskPea;

    public ClanMember() {
    }

    public ClanMember(Player player, Clan clan, byte role) {
        this.clan = clan;
        this.id = (int) player.id;
        this.head = player.getHead();
        this.body = player.getBody();
        this.leg = player.getLeg();
        this.name = player.name;
        this.role = role;
        this.powerPoint = player.nPoint.power;
        this.donate = 0;
        this.receiveDonate = 0;
        this.memberPoint = 0;
        this.clanPoint = 0;
        this.lastRequest = 0;
        this.joinTime = (int) (System.currentTimeMillis() / 1000);
    }

    public int getNumDateFromJoinTimeToToday() {
        return (int) TimeUtil.diffDate(new Date(), new Date(this.joinTime * 1000L), TimeUtil.DAY);
    }

    public void dispose() {
        this.clan = null;
        this.name = null;
    }

}
