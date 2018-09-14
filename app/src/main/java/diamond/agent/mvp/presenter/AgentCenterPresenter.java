package diamond.agent.mvp.presenter;

import android.content.Context;

import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.model.AgentCenterModel;
import diamond.agent.mvp.model.BaseDataBridge;
import diamond.agent.mvp.view.AgentCenterView;

/**
 * Created by Jacob on 2018-9-14.
 */

public class AgentCenterPresenter extends BasePresenter<AgentCenterView, AgentCenterModel> implements BaseDataBridge.AgentCenterDataBridge {
    /**
     * presenter构造方法，需要有View和上下文
     *
     * @param view
     * @param context
     */
    public AgentCenterPresenter(AgentCenterView view, Context context) {
        super(view, context);
        model = new AgentCenterModel(this);
    }

    @Override
    public void onSuccess(BaseResultData<AgentCenterData> data) {

    }


    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void getUserInfoSuccess(BaseResultData<AgentCenterData> resultData) {
        view.getUserInfoSuccess(resultData.getData());
    }

    @Override
    public void getUserInfoFail(String msg) {
        view.getUserInfoFail(msg);
    }

    @Override
    public void startWithDrawSuccess(BaseResultData baseResultData) {
        view.startWithDrawSuccess();
    }

    @Override
    public void startWithDrawFail(String msg) {
        view.startWithDrawFail(msg);
    }

    public void getUserInfo(String id) {
        addSubscription(model.getUserInfo(id));
    }

    public void startWithDraw(String id) {
        addSubscription(model.startWithDraw(id));
    }
}
