package diamond.agent.mvp.view;

import diamond.agent.mvp.data.MemberListData;

/**
 * Created by Jacob on 2018-9-20.
 */

public interface MemberListView extends BaseView {
    void getMemberListSuccess(MemberListData resultData);

    void getMemberListFail(String msg);
}
