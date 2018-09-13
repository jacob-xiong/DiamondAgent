package diamond.agent.mvp.view;


import diamond.agent.mvp.data.InvitationCodeResultData;

/**
 * Created by Jacob on 2018-9-13.
 */

public interface InvitationCodeView extends BaseView {

    void getInvitationCodeSuccess(InvitationCodeResultData data);
    void getInvitationCodeFail(String msg);

}
