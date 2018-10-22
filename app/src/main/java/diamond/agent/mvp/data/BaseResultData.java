package diamond.agent.mvp.data;

import java.io.Serializable;

/**
 * @author by xiongyan on 2018/9/12.
 */
public class BaseResultData<T> implements Serializable {
    /**
     * 返回状态码
     */
    private String status;

    /**
     * 返回的message信息
     */
    private String message;

    /**
     * 返回数据data对象
     */
    T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
