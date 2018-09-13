package diamond.agent.mvp.presenter;

import android.content.Context;

import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
import diamond.agent.mvp.model.BaseDataBridge;
import diamond.agent.mvp.model.InvitationCodeModel;
import diamond.agent.mvp.view.InvitationCodeView;

/**
 * Created by Jacob on 2018-9-13.
 */

public class InvitationCodePresenter extends BasePresenter<InvitationCodeView,InvitationCodeModel> implements BaseDataBridge.InvitationCodeDataBridge{
    /**
     * presenter构造方法，需要有View和上下文
     *
     * @param view
     * @param context
     */
    public InvitationCodePresenter(InvitationCodeView view, Context context) {
        super(view, context);
        model = new InvitationCodeModel(this);
    }

    @Override
    public void onSuccess(BaseResultData<InvitationCodeResultData> data) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void getInvitationCodeSuccess(BaseResultData<InvitationCodeResultData> resultData) {
        view.getInvitationCodeSuccess(resultData.getData());
    }

    @Override
    public void getInvitationCodeFail(String msg) {
        view.getInvitationCodeFail(msg);
    }

    public void getInvitaionCode(String id){

        addSubscription(model.getInvitationCode(id));
    }
}
