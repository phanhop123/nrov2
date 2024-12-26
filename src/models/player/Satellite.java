package models.player;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import utils.Util;

public class Satellite {

    public boolean isHP;
    public boolean isMP;
    public boolean isIntelligent;
    public boolean isDefend;
    public long lastHPTime;
    public long lastMPTime;
    public long lastIntelligentTime;
    public long lastDefendTime;

    public void update() {
        if (isHP && Util.canDoWithTime(lastHPTime, 3000)) {
            isHP = false;
        }
        if (isMP && Util.canDoWithTime(lastMPTime, 3000)) {
            isMP = false;
        }
        if (isIntelligent && Util.canDoWithTime(lastIntelligentTime, 3000)) {
            isIntelligent = false;
        }
        if (isDefend && Util.canDoWithTime(lastDefendTime, 3000)) {
            isDefend = false;
        }
    }
}
