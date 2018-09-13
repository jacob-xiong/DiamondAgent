package diamond.agent.client;

import diamond.agent.mvp.data.BaseResultData;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author by xiongyan on 2018/9/12.
 */
public interface ApiService {
    @POST("http://10.25.32.231:80/demo/TestServlet")
    Observable<BaseResultData<String>> testLogin(@Query("billType") int billType);
}
