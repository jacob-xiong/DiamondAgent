package diamond.agent.client;

import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberLevelData;
import diamond.agent.mvp.data.MemberListData;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author by xiongyan on 2018/9/12.
 */
public interface ApiService {
    /**
     * 二维码页面
     *
     * @param id
     * @return
     */
    @POST("XXX/XXX")
    Observable<BaseResultData<InvitationCodeResultData>> getInvitationCode(@Query("id") String id);

    /**
     * 钻石代理商个人中心页面
     *
     * @param id
     * @return
     */
    @POST("/bee/a/api/bee/getMyInfo")
    @FormUrlEncoded
    Observable<BaseResultData<AgentCenterData>> getUserInfo(@Field("userId") String id);

    /**
     * 提现
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("/bee/a/api/bee/transfer")
    Observable<BaseResultData> startWithDraw(@Field("userId") String userId,@Field("amount")String amount,@Field("payee_account")String payee_account,@Field("payee_real_name") String payee_real_name,@Field("remark")String remark);

    /**
     * 成员列表页面
     */
    @FormUrlEncoded
    @POST("/bee/a/api/bee/getLowerLevel")
    Observable<BaseResultData<MemberLevelData>> getMemberListData(@Field("userId") String userId, @Field("time") String time);

    /**
     * 各级成员列表页面
     */
    @FormUrlEncoded
    @POST("XXX/XXX")
    Observable<BaseResultData<MemberGroupData>> getMemberDetailListData(@Field("id") String id, @Field("level") String level, @Field("pageNo") int pageNo);

    @FormUrlEncoded
    @POST("/bee/a/api/bee/addLowerLevel")
    Observable<BaseResultData> addLowerLevel(@Field("userId") String userId,@Field("code")String code);

}
