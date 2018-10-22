package diamond.agent.mvp.data;

import java.io.Serializable;

/**
 * @author by xiongyan on 2018/10/22.
 */
public class UserInfo implements Serializable {
    private String id;
    private boolean isNewRecord;
    private String createDate;
    private String updateDate;
    private String userId;
    private String userName;
    private String money;
    private String validTime;
    private double validDays;
    private String level1;//上一级用户ID
    private String level1Name;//上一级用户名字
    private String level2;//上两级 用户ID
    private String level2Name;//上两级用户名字
    private String level3;//上三级用户ID
    private String level3Name;//上三级用户名字

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getCreateDate() {
        return createDate == null ? "" : createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate == null ? "" : updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserId() {
        return userId == null ? "" : userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMoney() {
        return money == null ? "" : money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getValidTime() {
        return validTime == null ? "" : validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public double getValidDays() {
        return validDays;
    }

    public void setValidDays(double validDays) {
        this.validDays = validDays;
    }

    public String getLevel1() {
        return level1 == null ? "" : level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel1Name() {
        return level1Name == null ? "" : level1Name;
    }

    public void setLevel1Name(String level1Name) {
        this.level1Name = level1Name;
    }

    public String getLevel2() {
        return level2 == null ? "" : level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel2Name() {
        return level2Name == null ? "" : level2Name;
    }

    public void setLevel2Name(String level2Name) {
        this.level2Name = level2Name;
    }

    public String getLevel3() {
        return level3 == null ? "" : level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel3Name() {
        return level3Name == null ? "" : level3Name;
    }

    public void setLevel3Name(String level3Name) {
        this.level3Name = level3Name;
    }
}
