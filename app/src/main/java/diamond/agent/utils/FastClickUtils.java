package diamond.agent.utils;

/**
 * @author by xiongyan on 2018/9/17.
 * 方式连续快速点击
 */
public class FastClickUtils {
    private static final int MIN_CLICK_DELAY_TIME = 1200;
    private static long lastClickTime;

    public static boolean isNormalClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
