package diamond.agent.mvp.model;

import diamond.agent.client.ApiHost;
import diamond.agent.client.NetWorkClient;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberListData;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jacob on 2018-9-20.
 */


public class MemberListModel extends BaseModel<BaseDataBridge.MemberDataListBridge> {

    public MemberListModel(BaseDataBridge.MemberDataListBridge dataListBridge) {
        this.dataBridge = dataListBridge;
    }

    public Subscription getMemberListData(String id) {
        return NetWorkClient.getApiService().getMemberListData(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseResultData<MemberListData>>() {
            @Override
            public void call(BaseResultData<MemberListData> resultData) {
                if (ApiHost.CLIENT_SUCCESS_CODE.equals(resultData.getStatus())) {
                    dataBridge.getMemberListSuccess(resultData);
                } else {
                    dataBridge.getMemberListFail(resultData.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dataBridge.onFailure(throwable);
            }
        });
    }


    public Subscription getMemberDetailListData(String id, String level,int pageNo) {
        return NetWorkClient.getApiService().getMemberDetailListData(id, level, pageNo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseResultData<MemberGroupData>>() {
            @Override
            public void call(BaseResultData<MemberGroupData> resultData) {
                if (ApiHost.CLIENT_SUCCESS_CODE.equals(resultData.getStatus())) {
                    dataBridge.getMemberDetailListSuccess(resultData);
                } else {
                    dataBridge.getMemberDetailListFail(resultData.getMessage());
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
