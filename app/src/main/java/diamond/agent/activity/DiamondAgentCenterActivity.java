package diamond.agent.activity;



import diamond.agent.R;

/**
 * @author by xiongyan on 2018/9/13.
 * 钻石代理商中心页面
 */
public class DiamondAgentCenterActivity extends BaseActivity {


    @Override
    protected boolean isShowActionBarTitle() {
        return true;
    }

    @Override
    protected int getActionTitle() {
        return R.string.diamond_agent_center_title;
    }

    @Override
    protected int getRightBtnRes() {
        return R.string.diamond_agent_center_right;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.diamond_agent_center_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initIntent() {

    }




}
