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

public class DauThan extends Npc {

    public DauThan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            player.magicTree.openMenuTree();
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            TaskService.gI().checkDoneTaskConfirmMenuNpc(player, this, (byte) select);
            switch (player.idMark.getIndexMenu()) {
                case ConstNpc.MAGIC_TREE_NON_UPGRADE_LEFT_PEA -> {
                    switch (select) {
                        case 0 ->
                            player.magicTree.harvestPea();
                        case 1 -> {
                            if (player.magicTree.level == 10) {
                                player.magicTree.fastRespawnPea();
                            } else {
                                player.magicTree.showConfirmUpgradeMagicTree();
                            }
                        }
                        case 2 ->
                            player.magicTree.fastRespawnPea();
                        default -> {
                        }
                    }
                }

                case ConstNpc.MAGIC_TREE_NON_UPGRADE_FULL_PEA -> {
                    if (select == 0) {
                        player.magicTree.harvestPea();
                    } else if (select == 1) {
                        player.magicTree.showConfirmUpgradeMagicTree();
                    }
                }
                case ConstNpc.MAGIC_TREE_CONFIRM_UPGRADE -> {
                    if (select == 0) {
                        player.magicTree.upgradeMagicTree();
                    }
                }
                case ConstNpc.MAGIC_TREE_UPGRADE -> {
                    if (select == 0) {
                        player.magicTree.fastUpgradeMagicTree();
                    } else if (select == 1) {
                        player.magicTree.showConfirmUnuppgradeMagicTree();
                    }
                }
                case ConstNpc.MAGIC_TREE_CONFIRM_UNUPGRADE -> {
                    if (select == 0) {
                        player.magicTree.unupgradeMagicTree();
                    }
                }
            }
        }
    }
}
