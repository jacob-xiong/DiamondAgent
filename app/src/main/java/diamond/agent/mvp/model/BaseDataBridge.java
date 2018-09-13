package diamond.agent.mvp.model;

import diamond.agent.mvp.data.BaseResultData;

/**
 * @author by xiongyan on 2018/9/12.
 */
public interface BaseDataBridge<T extends BaseResultData> {
    /**
     * 通用成功回调方法
     *
     * @param data 成功后返回的数据
     */
    void onSuccess(T data);

    /**
     * 通用的失败回调方法
     */
    void onFailure(Throwable throwable);


}
