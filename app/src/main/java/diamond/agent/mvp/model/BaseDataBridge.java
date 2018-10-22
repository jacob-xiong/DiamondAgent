package diamond.agent.mvp.model;

import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberListData;

/**
 * @author by xiongyan on 2018/9/12.
 */
public interface BaseDataBridge<T extends BaseResultData> {
    /**
     * 通用成功回调方法
     *
     * @param data 成功后返回的数据
     */
    void onSuccess(T data);


    /**
     * 通用的失败回调方法
     */
    void onFailure(Throwable throwable);


    /**
     * 获取邀请码回调
     */
    /**
     * 测试回调
     */
    interface InvitationCodeDataBridge extends BaseDataBridge<BaseResultData<InvitationCodeResultData>> {


        void getInvitationCodeSuccess(BaseResultData<InvitationCodeResultData> resultData);


        void getInvitationCodeFail(String msg);

    }

    interface AgentCenterDataBridge extends BaseDataBridge<BaseResultData<AgentCenterData>> {
        void getUserInfoSuccess(BaseResultData<AgentCenterData> resultData);

        void getUserInfoFail(String msg);

        void startWithDrawSuccess(BaseResultData baseResultData);

        void startWithDrawFail(String msg);
        void addLowerLevelSucess(BaseResultData baseResultData);
        void addLowerLevelFail(String msg);

    }

    interface MemberDataListBridge extends BaseDataBridge<BaseResultData<MemberListData>> {
        void getMemberListSuccess(BaseResultData<MemberListData> resultData);

        void getMemberListFail(String msg);


        void getMemberDetailListSuccess(BaseResultData<MemberGroupData> resultData);

        void getMemberDetailListFail(String msg);

    }

}
