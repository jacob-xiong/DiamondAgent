package diamond.agent.mvp.data;

import java.io.Serializable;

/**
 * Created by Jacob on 2018-9-13.
 */

public class InvitationCodeResultData implements Serializable {
    /**
     * 邀请码
     */
    private String invitationCode;

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
