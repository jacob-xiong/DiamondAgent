package diamond.agent.mvp.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jacob on 2018-9-20.
 */

public class MemberListData implements Serializable {
    /**
     * 一级消费者人数
     */
    private String firstMemberNum;
    /**
     * 二级消费者人数
     */
    private String secondMemberNum;
    /**
     * 三级消费者人数
     */
    private String threeMemberNum;
    /**
     * 总人数
     */
    private String totalNum;
    /**
     * 总消费金额
     */
    private String totalConsumed;
    /**
     * 总提成金额
     */
    private String totalCommission;
    /**
     * 各级对应数据
     */
    private List<MemberGroupData> memberLevelDataList;

    public String getFirstMemberNum() {
        return firstMemberNum;
    }

    public void setFirstMemberNum(String firstMemberNum) {
        this.firstMemberNum = firstMemberNum;
    }

    public String getSecondMemberNum() {
        return secondMemberNum;
    }

    public void setSecondMemberNum(String secondMemberNum) {
        this.secondMemberNum = secondMemberNum;
    }

    public String getThreeMemberNum() {
        return threeMemberNum;
    }

    public void setThreeMemberNum(String threeMemberNum) {
        this.threeMemberNum = threeMemberNum;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getTotalConsumed() {
        return totalConsumed;
    }

    public void setTotalConsumed(String totalConsumed) {
        this.totalConsumed = totalConsumed;
    }

    public String getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(String totalCommission) {
        this.totalCommission = totalCommission;
    }

    public List<MemberGroupData> getMemberLevelDataList() {
        return memberLevelDataList;
    }

    public void setMemberLevelDataList(List<MemberGroupData> memberLevelDataList) {
        this.memberLevelDataList = memberLevelDataList;
    }
}
