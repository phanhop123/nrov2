package database.daos;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import database.AlyraManager;
import org.json.simple.JSONArray;
import models.player.Player;

public class TraningDAO {

    public static void updatePlayer(Player player) {
        if (player != null && player.idMark.isLoadedAllDataPlayer()) {
            try {
                JSONArray dataArray = new JSONArray();
                dataArray.add(player.levelLuyenTap);
                dataArray.add(player.dangKyTapTuDong);
                dataArray.add(player.mapIdDangTapTuDong);
                dataArray.add(player.tnsmLuyenTap);
                if (player.isOffline) {
                    dataArray.add(player.lastTimeOffline);
                } else {
                    dataArray.add(System.currentTimeMillis());
                }
                dataArray.add(player.traning.getTop());
                dataArray.add(player.traning.getTime());
                dataArray.add(player.traning.getLastTime());
                dataArray.add(player.traning.getLastTop());
                dataArray.add(player.traning.getLastRewardTime());

                String dataLuyenTap = dataArray.toJSONString();
                dataArray.clear();

                String query = "UPDATE player SET data_luyentap = ? WHERE id = ?";
                AlyraManager.executeUpdate(query, dataLuyenTap, player.id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
