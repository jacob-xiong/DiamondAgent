package diamond.agent.client;

import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author by xiongyan on 2018/9/12.
 */
public interface ApiService {
    @POST()
    Observable<BaseResultData<InvitationCodeResultData>> getInvitationCode(@Query("id") String id);
}
