package models.npc.npc_list;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import consts.ConstNpc;
import models.npc.Npc;
import models.player.Player;
import services.TaskService;

public class LyTieuNuong extends Npc {

    public LyTieuNuong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
            createOtherMenu(player, 0, "Ngọc Rồng V2 Nhà Cái Uy Tín Đến Từ Châu Á", "Thể lệ", "Chọn\nAi Đây", "Tài Xỉu MD5", "Con Số\nMay Mắn", "Xóc Đĩa", "Xì Tố", "Tài Xỉu", "Bầu Cua");
        }
    }

    @Override
    public void confirmMenu(Player pl, int select) {
        if (canOpenNpc(pl)) {
            switch (pl.idMark.getIndexMenu()) {
                case 0 -> {
                    switch (select) {
                        case 0 ->
                            createOtherMenu(pl, ConstNpc.IGNORE_MENU, "Lưu ý: Sẽ phải nộp thuế VAT 10% cho giải thưởng nhận được! Trong quá trình diễn ra khi đặt cược nếu thoát game mọi phần đặt đều sẽ bị hủy", "Ok");
                        default -> {
                        }
                    }
                }
            }
        }
    }
}
