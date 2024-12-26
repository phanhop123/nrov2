package models.npc.npc_list;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.npc.Npc;
import models.player.Player;
import services.map.NpcService;
import services.Service;

public class DocNhan extends Npc {

    public DocNhan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (mapId == 57) {
                if (!player.clan.doanhTrai.winDT) {
                    NpcService.gI().createTutorial(player, tempId, this.avartar, "Bọn mi đừng hòng thoát khỏi nơi đây");
                } else {
                    NpcService.gI().createTutorial(player, tempId, this.avartar, "Ta chịu thua, nhưng các ngươi đừng có mong lấy được ngọc của ra\nta đã giấu ngọc 4 sao và 1 đống ngọc 7 sao trong doanh trại này...\nCác ngươi chỉ có 5 phút đi tìm, đố các ngươi tìm ra hahaha");
                    if (!player.clan.doanhTrai.isTimePicking) {
                        Service.gI().sendThongBao(player, "Trại Độc Nhãn đã bị tiêu diệt, bạn có 5 phút để tìm kiếm viên ngọc 4 sao trước khi phi thuyền đến đón");
                        player.clan.doanhTrai.isTimePicking = true;
                        player.clan.doanhTrai.lastTimePick = System.currentTimeMillis();
                        player.clan.doanhTrai.randomNR();
                        player.clan.doanhTrai.sendTextTimePickDoanhTrai();
                    }
                }

            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
    }
}
