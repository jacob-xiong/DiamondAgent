package diamond.agent.mvp.model;

import diamond.agent.client.ApiHost;
import diamond.agent.client.NetWorkClient;
import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jacob on 2018-9-14.
 */

public class AgentCenterModel extends BaseModel<BaseDataBridge.AgentCenterDataBridge> {
    public AgentCenterModel(BaseDataBridge.AgentCenterDataBridge dataBridge) {
        this.dataBridge = dataBridge;
    }

    public Subscription getUserInfo(String id) {
        return NetWorkClient.getApiService().getUserInfo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseResultData<AgentCenterData>>() {
            @Override
            public void call(BaseResultData<AgentCenterData> resultData) {
                if (ApiHost.CLIENT_SUCCESS_CODE.equals(resultData.getStatus())) {
                    dataBridge.getUserInfoSuccess(resultData);
                } else {
                    dataBridge.getUserInfoFail(resultData.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dataBridge.onFailure(throwable);
            }
        });
    }

    public Subscription startWithDraw(String id) {
        return NetWorkClient.getApiService().startWithDraw(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseResultData>() {
            @Override
            public void call(BaseResultData baseResultData) {
                if (ApiHost.CLIENT_SUCCESS_CODE.equals(baseResultData.getStatus())) {
                    dataBridge.startWithDrawSuccess(baseResultData);
                } else {
                    dataBridge.startWithDrawFail(baseResultData.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dataBridge.onFailure(throwable);
            }
        });
    }


    public Subscription addLowerLevel(String userId,String code){
        return NetWorkClient.getApiService().addLowerLevel(userId,code).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseResultData>() {
            @Override
            public void call(BaseResultData baseResultData) {
                if (ApiHost.CLIENT_SUCCESS_CODE.equals(baseResultData.getStatus())) {
                    dataBridge.addLowerLevelSucess(baseResultData);
                } else {
                    dataBridge.addLowerLevelFail(baseResultData.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dataBridge.onFailure(throwable);
            }
        });
    }
}
