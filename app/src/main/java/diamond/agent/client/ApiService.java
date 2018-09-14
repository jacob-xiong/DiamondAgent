package diamond.agent.client;

import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
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
    @POST()
    Observable<BaseResultData<AgentCenterData>> getUserInfo(@Query("id") String id);

    /**
     * 发起退款
     * @param id
     * @return
     */
    @POST()
    Observable<BaseResultData> startWithDraw(@Query("id") String id);
}
