package database.daos;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.AlyraManager;
import lombok.Getter;
import lombok.Setter;
import utils.Logger;

public class EventDAO {

    @Setter
    @Getter
    private static long remainingTimeToIncreasePotentialAndPower = 0;
    @Setter
    @Getter
    private static long remainingTimeToIncreaseHP = 0;
    @Setter
    @Getter
    private static long remainingTimeToIncreaseMP = 0;
    @Setter
    @Getter
    private static long remainingTimeToIncreaseDame = 0;

    public static void loadInternationalWomensDayEvent() {
        try (Connection con = AlyraManager.getConnection();) {
            PreparedStatement ps = con.prepareStatement("SELECT `data` FROM `event` WHERE `name` = 'international_womens_day'");
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(String.valueOf(rs.getString("data")), JsonObject.class);
                remainingTimeToIncreaseDame = jsonObject.getAsJsonPrimitive("damePrecent").getAsLong();
                remainingTimeToIncreaseHP = jsonObject.getAsJsonPrimitive("hpPrecent").getAsLong();
                remainingTimeToIncreaseMP = jsonObject.getAsJsonPrimitive("mpPrecent").getAsLong();
                remainingTimeToIncreasePotentialAndPower = jsonObject.getAsJsonPrimitive("papPrecent").getAsLong();
            }
        } catch (Exception ex) {
        }
    }

    public static void save() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("damePrecent", remainingTimeToIncreaseDame);
            jsonObject.addProperty("hpPrecent", remainingTimeToIncreaseHP);
            jsonObject.addProperty("mpPrecent", remainingTimeToIncreaseMP);
            jsonObject.addProperty("papPrecent", remainingTimeToIncreasePotentialAndPower);

            String jsonData = jsonObject.toString();

            AlyraManager.executeUpdate("UPDATE `event` SET `data` = ? WHERE `name` = 'international_womens_day'", jsonData);
        } catch (Exception e) {
            Logger.error("Lỗi save Event Data\n");
        }

    }

}
