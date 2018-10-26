package diamond.agent.mvp.presenter;

import android.content.Context;

import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberLevelData;
import diamond.agent.mvp.data.MemberListData;
import diamond.agent.mvp.model.BaseDataBridge;
import diamond.agent.mvp.model.MemberListModel;
import diamond.agent.mvp.view.MemberListView;

/**
 * Created by Jacob on 2018-9-20.
 */

public class MemberListPresenter extends BasePresenter<MemberListView, MemberListModel> implements BaseDataBridge.MemberDataListBridge {
    /**
     * presenter构造方法，需要有View和上下文
     *
     * @param view
     * @param context
     */
    public MemberListPresenter(MemberListView view, Context context) {
        super(view, context);
        model = new MemberListModel(this);
    }

    @Override
    public void onSuccess(BaseResultData<MemberLevelData> data) {

    }

    @Override
    public void onFailure(Throwable throwable) {
        view.loadFailure(throwable);
    }

    @Override
    public void getMemberListSuccess(BaseResultData<MemberLevelData> resultData) {
        view.getMemberListSuccess(resultData.getData());
    }

    @Override
    public void getMemberListFail(String msg) {
        view.getMemberListFail(msg);
    }

    @Override
    public void getMemberDetailListSuccess(BaseResultData<MemberGroupData> resultData) {
        view.getMemberDetailListSuccess(resultData.getData());
    }

    @Override
    public void getMemberDetailListFail(String msg) {
        view.getMemberDetailListFail(msg);
    }


    public void getMemberListData(String userId,String type) {
        addSubscription(model.getMemberListData(userId,type));
    }

    public void getMemberDetailListData(String id, String level,int pageNo) {
        addSubscription(model.getMemberDetailListData(id, level,pageNo));
    }

}
