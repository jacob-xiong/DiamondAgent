package diamond.agent.mvp.data;

import java.io.Serializable;

/**
 * Created by Jacob on 2018-9-14.
 */

public class AgentCenterData implements Serializable {

    private RebateBean rebate;
    private UserInfo info;

    private String renewPrice = String.valueOf(199);


    public RebateBean getRebate() {
        return rebate;
    }

    public void setRebate(RebateBean rebate) {
        this.rebate = rebate;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public String getRenewPrice() {
        return renewPrice;
    }

    public void setRenewPrice(String renewPrice) {
        this.renewPrice = renewPrice;
    }
}
