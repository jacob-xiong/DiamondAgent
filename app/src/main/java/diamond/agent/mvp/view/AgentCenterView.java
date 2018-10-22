package diamond.agent.mvp.view;

import diamond.agent.mvp.data.AgentCenterData;

/**
 * Created by Jacob on 2018-9-14.
 */

public interface AgentCenterView extends BaseView{
    void getUserInfoSuccess(AgentCenterData data);

    void getUserInfoFail(String msg);

    void startWithDrawSuccess();

    void startWithDrawFail(String msg);
    void addLowerLevelSucess();
    void addLowerLevelFail(String msg);
}
