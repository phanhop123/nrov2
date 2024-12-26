package models.npc.npc_list;

/*
 * @Author: Ngọc Rồng Online V2
 * @Description: Ngọc Rồng Online V2 Free ALL chỉ cần anh em cày
 */


import consts.ConstNpc;
import database.daos.PlayerDAO;
import models.item.Item;
import models.npc.Npc;
import models.player.Player;
import server.Maintenance;
import services.player.InventoryService;
import services.ItemService;
import services.Service;
import services.TaskService;
import services.func.Input;
import utils.Util;

public class OngGohan extends Npc {

    public OngGohan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Ngọc Rồng Online V2 Server Hoàn Toàn Miễn Phí",
                        "Đổi Mật Khẩu", "Nhận 2m ngọc xanh", "Nhận\nVàng", "Giftcode", "Mở Thành Viên ");

            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.idMark.isBaseMenu()) {
                switch (select) {
                    case 0 -> Input.gI().createFormChangePassword(player);
    
                    case 1 -> {
                        if (player.inventory.gem >= 20_000_000) {
                            this.npcChat(player, "Tham Lam!");
                        } else {
                            player.inventory.gem += 2_000_000;
                            Service.gI().sendMoney(player);
                            Service.gI().sendThongBao(player, "Bạn vừa nhận được 2M ngọc xanh!");
                        }
                    }
    
                    case 2 -> {
                        if (Maintenance.isRunning) {
                            break;
                        }
                        if (!Util.isAfterMidnight(player.lastRewardGoldBarTime) && !player.isAdmin()) {
                            Service.gI().sendThongBaoOK(player, "Hãy chờ đến ngày mai");
                            this.npcChat(player, "Chỉ có làm mới có ăn, không làm mà đòi có ăn chỉ có ăn đb, ăn c*t thôi con nhé!");
                            return;
                        }
                        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                            int quantity = player.danhanthoivang ? (player.getSession().vip > 0 ? 10000 : 3000) : 10000;
                            if (player.isAdmin()) {
                                quantity = 2_000_000_000;
                            }
                            Item goldBar = ItemService.gI().createNewItem((short) 457, quantity);
                            InventoryService.gI().addItemBag(player, goldBar);
                            InventoryService.gI().sendItemBags(player);
                            this.npcChat(player, "Ta đã gửi " + quantity + " thỏi vàng vào hành trang của con\n con hãy kiểm tra ");
                            player.danhanthoivang = true;
                            player.lastRewardGoldBarTime = System.currentTimeMillis();
                        } else {
                            this.npcChat(player, "Hãy chừa cho ta 1 ô trống");
                        }
                    }

                    case 3 -> Input.gI().createFormGiftCode(player);
    
                    case 4 ->  this.createOtherMenu(player, ConstNpc.CONFIRM_ACTIVE, 
                    "|5|Mở thành Viên Free \n"+ "|1|Hệ thống sẽ tặng cho bạn 1 cái nịt \n"+"|5|Số Sức mạnh của bạn hiện tại :" 
                    + player.nPoint.power + " Sức mạnh" + "\n|7|Trạng thái : " 
                    + (player.getSession().actived ? "Đã kích Hoạt" : "Chưa kích hoạt"), "Kích hoạt", "Đóng");
                   
                }
                
            } else if (player.idMark.getIndexMenu() == ConstNpc.CONFIRM_ACTIVE) {
                switch (select) {
                    case 0 -> {
                        if (!player.getSession().actived) {
                            if (player.nPoint.power >= 340000) {
                                player.getSession().actived = true;
                                // if (PlayerDAO.subvnd(player, 0)) {
                                //     Item goldBar = ItemService.gI().createNewItem((short) 457, 25);
                                //     player.inventory.ruby += 20000;
                                //     InventoryService.gI().addItemBag(player, goldBar);
                                //     InventoryService.gI().sendItemBags(player);
                                //     Service.getInstance().sendMoney(player);
                                //     Service.gI().sendThongBao(
                                //         player,
                                //         "|7|Kích hoạt thành công, bạn nhận được thêm 25 Thỏi Vàng và 10k Hồng Ngọc"
                                //     );
                                // }
                            } else {
                                this.npcChat(player, "Không Đủ Sức mạnh mở thành viên");
                            }
                        } else {
                            this.npcChat(player, "Bạn đã mở thành viên rồi!");
                        }
                    }
    
                    case 1 -> this.npcChat(player, "Lần sau mở nha con!!!");
                }
                
            }
        }
       
}
}
