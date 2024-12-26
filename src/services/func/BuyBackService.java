package services.func;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import models.item.Item;
import models.player.Player;
import services.ItemService;
import services.ShopService;

public class BuyBackService {

    private static final byte MAX_ITEM_IN_BOX = 100;

    private static BuyBackService i;

    public static BuyBackService gI() {
        if (i == null) {
            i = new BuyBackService();
        }
        return i;
    }

    public void addItem(Player player, Item item) {
        if (player.inventory.itemsDaBan.size() + 1 > MAX_ITEM_IN_BOX) {
            player.inventory.itemsDaBan.remove(0);
        }
        Item itemmua = ItemService.gI().copyItem(item);
        player.inventory.itemsDaBan.add(itemmua);
        if (player.idMark != null && player.idMark.getTagNameShop().equals("ITEMS_DABAN")) {
            ShopService.gI().opendShop(player, "ITEMS_DABAN", true);
        }
    }
}
