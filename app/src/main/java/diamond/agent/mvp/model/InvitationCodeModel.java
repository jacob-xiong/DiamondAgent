package diamond.agent.mvp.model;

import diamond.agent.client.ApiHost;
import diamond.agent.client.NetWorkClient;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jacob on 2018-9-13.
 */

public class InvitationCodeModel extends  BaseModel<BaseDataBridge.InvitationCodeDataBridge> {
    public InvitationCodeModel(BaseDataBridge.InvitationCodeDataBridge dataBridge){
        this.dataBridge=dataBridge;
    }
    public Subscription getInvitationCode(String id){
        return NetWorkClient.getApiService().getInvitationCode(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<BaseResultData<InvitationCodeResultData>>() {
            @Override
            public void call(BaseResultData<InvitationCodeResultData> resultData) {
                if(ApiHost.CLIENT_SUCESS_CODE.equals(resultData.getStatusCode())){
                    dataBridge.getInvitationCodeSuccess(resultData);
                }else{
                    dataBridge.getInvitationCodeFail(resultData.getMessage());
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
