package diamond.agent.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import diamond.agent.R;
import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.presenter.AgentCenterPresenter;
import diamond.agent.mvp.view.AgentCenterView;

/**
 * @author by xiongyan on 2018/9/13.
 *         钻石代理商中心页面
 */
public class DiamondAgentCenterActivity extends BaseActivity<AgentCenterPresenter> implements AgentCenterView {
    private final int AMOUNT_DAY = 1;
    private final int AMOUNT_MONTH = 2;
    private final int AMOUNT_ALL = 3;

    @BindView(R.id.center_logo)
    ImageView mCenterLogo;
    @BindView(R.id.center_num)
    TextView mCenterNum;
    @BindView(R.id.center_superior)
    TextView mCenterSuperior;
    @BindView(R.id.center_today_amount)
    TextView mCenterTodayAmount;
    @BindView(R.id.center_this_month_amount)
    TextView mCenterMonthAmount;
    @BindView(R.id.center_all_amount)
    TextView mCenterAllAmount;
    @BindView(R.id.center_surplus_days)
    TextView mCenterSurplusDays;
    @BindView(R.id.qr_code_view)
    View mCenterQRCodeView;
    @BindView(R.id.member_list_view)
    View mCenterMemberListView;
    @BindView(R.id.balance_title)
    TextView mCenterBalanceTitle;
    @BindView(R.id.balance_take)
    TextView mCenterBalanceTake;
    @BindView(R.id.renew_title)
    TextView mCenterRenenTitle;
    @BindView(R.id.alipay_checkbox)
    CheckBox mCenterALiPayCheckBox;
    @BindView(R.id.center_buy_button)
    TextView mCenterBuyBt;
    private AgentCenterData mAgentCenterData;


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
        presenter = new AgentCenterPresenter(this, this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.diamond_agent_center_activity;
    }

    @Override
    protected void loadData() {
        getUserInfoData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initIntent() {

    }

    @OnClick({})
    private void onViewClick(View view) {

    }


    @Override
    public void getUserInfoSuccess(AgentCenterData data) {
        mAgentCenterData = data;
        if (mAgentCenterData != null) {
            setDataToView();
        }
    }

    private void setDataToView() {
        mCenterNum.setText(getString(R.string.diamond_agent_center_num, getDefaultEmpty(mAgentCenterData.getUserNum())));
        mCenterSuperior.setText(getString(R.string.diamond_agent_center_superior, getDefaultNull(mAgentCenterData.getUserSuperior())));
        mCenterTodayAmount.setText(getAmount(AMOUNT_DAY));
        mCenterMonthAmount.setText(getAmount(AMOUNT_MONTH));
        mCenterAllAmount.setText(getAmount(AMOUNT_ALL));
        mCenterSurplusDays.setText(getString(R.string.diamond_agent_center_surplus_days_title, getDefaultZero(mAgentCenterData.getSurplusDays())));

    }

    @Override
    public void getUserInfoFail(String msg) {

    }

    @Override
    public void startWithDrawSuccess() {

    }

    @Override
    public void startWithDrawFail(String msg) {

    }

    @Override
    public void loadFailure(Throwable throwable) {

    }

    @Override
    public void onException(BaseResultData result) {

    }

    private void getUserInfoData() {
        if (presenter != null) {
            presenter.getUserInfo("ID");
        }
    }

    private String getDefaultEmpty(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    private String getDefaultNull(String str) {
        return TextUtils.isEmpty(str) ? getString(R.string.diamond_agent_center_superior_null) : str;
    }

    private String getDefaultZero(String str) {
        return TextUtils.isEmpty(str) ? getString(R.string.diamond_agent_center_zero) : str;
    }

    private String getAmount(int type) {
        String amount = "";
        switch (type) {
            case AMOUNT_DAY:
                amount = mAgentCenterData.getTodayAmount();
                break;
            case AMOUNT_MONTH:
                amount = mAgentCenterData.getMonthAmount();
                break;
            case AMOUNT_ALL:
                amount = mAgentCenterData.getAllAmount();
                break;

        }
        return getString(R.string.diamond_agent_center_amount, getDefaultZero(amount));

    }

}
