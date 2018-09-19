package diamond.agent.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import diamond.agent.mvp.presenter.BasePresenter;

/**
 * @author by xiongyan on 2018/9/19.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    /**
     * View层持有的Presenter对象，用于调用业务逻辑处理请求的接口
     */
    protected T presenter;

    /**
     * 用来缓存已经创建好的View，防止多次View的创建
     */
    private View rootView;

    /**
     * Fragment持有的Context
     */
    protected Context context;

    /**
     * 通用初始化View布局
     */
    protected LayoutInflater mInflater;


    /**
     * 创建Fragment的时候需要初始化的一些操作，可以在之类重写部分init*方法实现对应Fragment的定制
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this.getActivity().getApplicationContext();
        initPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this, rootView);
            mInflater = LayoutInflater.from(context);
            initView(rootView);
            loadData();
        }
        ViewGroup parentView = (ViewGroup) rootView.getParent();
        if (parentView != null) {
            parentView.removeView(rootView);
        }
        return rootView;
    }

    protected abstract int getLayoutId();


    protected abstract void initPresenter();


    protected abstract void loadData();


    protected abstract void initView(View view);


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.release();
        }
    }


    /**
     * 隐藏正在加载图

     public final void dismissProgress() {
     try {
     this.getActivity().removeDialog(R.id.show_process);
     } catch (Exception e) {
     e.printStackTrace();
     }
     }

     public final void showProgress() {
     if (this.getActivity() != null) {
     if (!this.getActivity().isFinishing()) {
     try {
     this.getActivity().showDialog(R.id.show_process);
     } catch (Exception e) {
     e.printStackTrace();
     }
     }
     }
     }
     */


}
