package event;

/*
 * @Author: DienCoLamCoi
 * @Description: Điện Cơ Lâm Còi - Chuyên cung cấp thiết bị điện cơ uy tín chất lượng cao.
 * @Group Zalo: Giao lưu chia sẻ kinh nghiệm code - https://zalo.me/g/lsqfzx907
 */


import event.event_list.TopUp;
import event.event_list.TrungThu;
import event.event_list.HungVuong;
import event.event_list.Christmas;
import event.event_list.Halloween;
import event.event_list.LunarNewYear;
import event.event_list.Default;
import event.event_list.InternationalWomensDay;

public class EventManager {

    private static EventManager instance;

    public static boolean LUNNAR_NEW_YEAR = true;

    public static boolean INTERNATIONAL_WOMANS_DAY = true;

    public static boolean CHRISTMAS = true;

    public static boolean HALLOWEEN = true;

    public static boolean HUNG_VUONG = true;

    public static boolean TRUNG_THU = true;

    public static boolean TOP_UP = true;

    public static EventManager gI() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void init() {
        new Default().init();
        if (LUNNAR_NEW_YEAR) {
            new LunarNewYear().init();
        }
        if (INTERNATIONAL_WOMANS_DAY) {
            new InternationalWomensDay().init();
        }
        if (HALLOWEEN) {
            new Halloween().init();
        }
        if (CHRISTMAS) {
            new Christmas().init();
        }
        if (HUNG_VUONG) {
            new HungVuong().init();
        }
        if (TRUNG_THU) {
            new TrungThu().init();
        }
        if (TOP_UP) {
            new TopUp().init();
        }
    }
}
