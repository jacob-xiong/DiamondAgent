package diamond.agent.mvp.data;

import java.io.Serializable;

/**
 * @author by xiongyan on 2018/10/22.
 */
public class RebateBean implements Serializable {
    private String all;
    private String month;
    private String day;

    public String getAll() {
        return all == null ? "" : all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getMonth() {
        return month == null ? "" : month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day == null ? "" : day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
