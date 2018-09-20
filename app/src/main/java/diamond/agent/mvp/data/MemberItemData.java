package diamond.agent.mvp.data;

import java.io.Serializable;

/**
 * Created by Jacob on 2018-9-20.
 */

public class MemberItemData implements Serializable {
    /**
     * 消费者成员ID
     */
    private String memberId;
    /**
     * 已消费金额
     */
    private String memberConsumed;
    /**
     * 提成金额
     */
    private String memberCommission;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberConsumed() {
        return memberConsumed;
    }

    public void setMemberConsumed(String memberConsumed) {
        this.memberConsumed = memberConsumed;
    }

    public String getMemberCommission() {
        return memberCommission;
    }

    public void setMemberCommission(String memberCommission) {
        this.memberCommission = memberCommission;
    }
}
