package diamond.agent.mvp.view;

import diamond.agent.mvp.data.BaseResultData;

/**
 * @author by xiongyan on 2018/9/12.
 */
public interface  BaseView {
    /**
     * 加载失败时调用方法
     */
    void loadFailure(Throwable throwable);

    /**
     * 当返回结果异常的时候调用，
     *
     * @param result
     */
    void onException(BaseResultData result);
}
