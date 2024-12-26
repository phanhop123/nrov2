package models.npc.npc_list;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import consts.ConstNpc;
import models.npc.Npc;
import models.player.Player;
import services.map.ChangeMapService;

public class Jaco extends Npc {

    public Jaco(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 24 ->
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Gô Tên, Calích và Monaka đang gặp chuyện ở hành tinh\nPotaufeu\nHãy đến đó ngay", "Đến\nPotaufeu", "Từ chối");
                case 139 ->
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây.\nCậu muốn đi đâu?", "Đến\nTrái Đất", "Đến\nNamếc", "Đến\nXayda", "Từ chối");
                default -> {
                }
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.idMark.isBaseMenu()) {
                switch (this.mapId) {
                    case 24 -> {
                        if (select == 0) {
                            ChangeMapService.gI().goToPotaufeu(player);
                        }
                    }
                    case 139 -> {
                        switch (select) {
                            case 0 ->
                                ChangeMapService.gI().changeMapBySpaceShip(player, 24, -1, -1);
                            case 1 ->
                                ChangeMapService.gI().changeMapBySpaceShip(player, 25, -1, -1);
                            case 2 ->
                                ChangeMapService.gI().changeMapBySpaceShip(player, 26, -1, -1);
                        }
                    }
                }
            }
        }
    }
}
