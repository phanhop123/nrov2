package server;

import consts.ConstNpc;
import managers.GiftCodeManager;
import managers.boss.*;
import models.item.Item;
import models.player.Pet;
import models.player.Player;
import network.session.SessionManager;
import services.ItemService;
import services.PetService;
import services.Service;
import services.func.Input;
import services.map.ChangeMapService;
import services.map.NpcService;
import services.player.InventoryService;
import utils.SystemMetrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Command {

    private static Command instance;

    private final Map<String, Consumer<Player>> adminCommands = new HashMap<>();
    private final Map<String, BiConsumer<Player, String>> parameterizedCommands = new HashMap<>();

    public static Command gI() {
        if (instance == null) {
            instance = new Command();
        }
        return instance;
    }

    private Command() {
        initAdminCommands();
        initParameterizedCommands();
    }

    private void initAdminCommands() {
        adminCommands.put("giftcode", player -> GiftCodeManager.gI().checkInfomationGiftCode(player));
        adminCommands.put("listboss", player -> BossManager.gI().showListBoss(player));
        adminCommands.put("lbb", player -> BrolyManager.gI().showListBoss(player));
        adminCommands.put("lbpb", player -> OtherBossManager.gI().showListBoss(player));
        adminCommands.put("lbdt", player -> RedRibbonHQManager.gI().showListBoss(player));
        adminCommands.put("lbbdkb", player -> TreasureUnderSeaManager.gI().showListBoss(player));
        adminCommands.put("lbcdrd", player -> SnakeWayManager.gI().showListBoss(player));
        adminCommands.put("lbkghd", player -> GasDestroyManager.gI().showListBoss(player));
        adminCommands.put("lbtt", player -> TrungThuEventManager.gI().showListBoss(player));
        adminCommands.put("menu", player -> NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_ADMIN, -1,
                "|0|Time start: " + ServerManager.timeStart + "\nClients: " + Client.gI().getPlayers().size() +
                        " người chơi\n Sessions: " + SessionManager.gI().getNumSession() + "\nThreads: " + Thread.activeCount() +
                        " luồng" + "\n" + SystemMetrics.ToString(),
                "Ngọc rồng", "Đệ tử", "Bảo trì", "Tìm kiếm\nngười chơi", "Boss", "Đóng"));
        adminCommands.put("item", player -> Input.gI().createFormGiveItem(player));
        adminCommands.put("getitem", player -> Input.gI().createFormGetItem(player));
        adminCommands.put("d", player -> Service.gI().setPos(player, player.location.x, player.location.y + 10));
    }

    private void initParameterizedCommands() {
        parameterizedCommands.put("m ", (player, text) -> {
            int mapId = Integer.parseInt(text.replace("m ", ""));
            ChangeMapService.gI().changeMapInYard(player, mapId, -1, -1);
        });

        parameterizedCommands.put("i ", (player, text) -> {
            int itemId = Integer.parseInt(text.replace("i ", ""));
            Item item = ItemService.gI().createNewItem(((short) itemId));
            List<Item.ItemOption> ops = ItemService.gI().getListOptionItemShop((short) itemId);
            if (!ops.isEmpty()) {
                item.itemOptions = ops;
            }
            InventoryService.gI().addItemBag(player, item);
            InventoryService.gI().sendItemBags(player);
            Service.gI().sendThongBao(player, "GET " + item.template.name + " [" + item.template.id + "] SUCCESS !");
        });
    }

    public void chat(Player player, String text) {
        if (!check(player, text)) {
            Service.gI().chat(player, text);
        }
    }

    public boolean check(Player player, String text) {
        if (player.isAdmin()) {
            if (adminCommands.containsKey(text)) {
                adminCommands.get(text).accept(player);
                return true;
            }

            for (Map.Entry<String, BiConsumer<Player, String>> entry : parameterizedCommands.entrySet()) {
                if (text.startsWith(entry.getKey())) {
                    entry.getValue().accept(player, text);
                    return true;
                }
            }
        }

        if (text.startsWith("ten con la ")) {
            PetService.gI().changeNamePet(player, text.replaceAll("ten con la ", ""));
        }

        if (player.pet != null) {
            switch (text) {
                case "di theo", "follow" -> player.pet.changeStatus(Pet.FOLLOW);
                case "bao ve", "protect" -> player.pet.changeStatus(Pet.PROTECT);
                case "tan cong", "attack" -> player.pet.changeStatus(Pet.ATTACK);
                case "ve nha", "go home" -> player.pet.changeStatus(Pet.GOHOME);
                case "bien hinh" -> player.pet.transform();
            }
        }
        return false;
    }
}