package diamond.agent.mvp.presenter;

import android.content.Context;

import diamond.agent.mvp.data.BaseResultData;
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
    public void onSuccess(BaseResultData<MemberListData> data) {

    }

    @Override
    public void onFailure(Throwable throwable) {
        view.loadFailure(throwable);
    }

    @Override
    public void getMemberListSuccess(BaseResultData<MemberListData> resultData) {
        view.getMemberListSuccess(resultData.getData());
    }

    @Override
    public void getMemberListFail(String msg) {
        view.getMemberListFail(msg);
    }

    public void getMemberListData(String id) {
        addSubscription(model.getMemberListData(id));
    }

}
