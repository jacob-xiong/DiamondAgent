package diamond.agent.mvp.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jacob on 2018-9-20.
 */

public class MemberGroupData implements Serializable {
    /**
     * 对应级别数据
     */
    private List<MemberItemData> memberLevelItemList;
    /**
     * 该级别总人数
     */
    private String memberLevelAllNum;
    /**
     * 该级别总消费金额
     */
    private String memberLevelAllConsumed;
    /**
     * 该级别提成金额
     */
    private String getMemberLevelAllNumCommission;

    private int totalPages;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MemberItemData> getMemberLevelItemList() {
        return memberLevelItemList;
    }

    public void setMemberLevelItemList(List<MemberItemData> memberLevelItemList) {
        this.memberLevelItemList = memberLevelItemList;
    }

    public String getMemberLevelAllNum() {
        return memberLevelAllNum;
    }

    public void setMemberLevelAllNum(String memberLevelAllNum) {
        this.memberLevelAllNum = memberLevelAllNum;
    }

    public String getMemberLevelAllConsumed() {
        return memberLevelAllConsumed;
    }

    public void setMemberLevelAllConsumed(String memberLevelAllConsumed) {
        this.memberLevelAllConsumed = memberLevelAllConsumed;
    }

    public String getGetMemberLevelAllNumCommission() {
        return getMemberLevelAllNumCommission;
    }

    public void setGetMemberLevelAllNumCommission(String getMemberLevelAllNumCommission) {
        this.getMemberLevelAllNumCommission = getMemberLevelAllNumCommission;
    }
}
