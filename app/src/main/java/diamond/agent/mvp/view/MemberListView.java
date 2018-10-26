package diamond.agent.mvp.view;

import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberLevelData;
import diamond.agent.mvp.data.MemberListData;

/**
 * Created by Jacob on 2018-9-20.
 */

public interface MemberListView extends BaseView {
    void getMemberListSuccess(MemberLevelData resultData);

    void getMemberListFail(String msg);

    void getMemberDetailListSuccess(MemberGroupData resultData);

    void getMemberDetailListFail(String msg);
}
