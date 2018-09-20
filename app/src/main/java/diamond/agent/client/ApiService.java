package diamond.agent.client;

import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
import diamond.agent.mvp.data.MemberListData;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author by xiongyan on 2018/9/12.
 */
public interface ApiService {
    /**
     * 二维码页面
     * @param id
     * @return
     */
    @POST()
    Observable<BaseResultData<InvitationCodeResultData>> getInvitationCode(@Query("id") String id);

    /**
     * 钻石代理商个人中心页面
     * @param id
     * @return
     */
    @POST("XXX/XXX")
    Observable<BaseResultData<AgentCenterData>> getUserInfo(@Query("id") String id);

    /**
     * 发起退款
     * @param id
     * @return
     */
    @POST("XXX/XXX")
    Observable<BaseResultData> startWithDraw(@Query("id") String id);

    /**
     * 成员列表页面
     */
    Observable<BaseResultData<MemberListData>> getMemberListData(@Field("id")String id);
}
