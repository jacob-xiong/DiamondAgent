package diamond.agent.activity;


import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import diamond.agent.R;
import diamond.agent.mvp.data.AgentCenterData;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.presenter.AgentCenterPresenter;
import diamond.agent.mvp.view.AgentCenterView;
import diamond.agent.utils.FastClickUtils;
import diamond.agent.utils.ToastUtils;

/**
 * @author by xiongyan on 2018/9/13.
 * 钻石代理商中心页面
 */
public class DiamondAgentCenterActivity extends BaseActivity<AgentCenterPresenter> implements AgentCenterView {
    private final int AMOUNT_DAY = 1;
    private final int AMOUNT_MONTH = 2;
    private final int AMOUNT_ALL = 3;
    public static final String USER_ID = "user_id";

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
    private boolean isGetDataSuccess = false;


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

    @OnClick({R.id.alipay_checkbox, R.id.center_buy_button, R.id.balance_take, R.id.action_right_tv, R.id.qr_code_view, R.id.member_list_view})
    void onViewClick(View view) {
        if (isGetDataSuccess) {
            switch (view.getId()) {
                case R.id.alipay_checkbox:
                    mCenterALiPayCheckBox.setChecked(!mCenterALiPayCheckBox.isChecked());
                    break;
                case R.id.center_buy_button:
                    if (FastClickUtils.isNormalClick()) {
                        if (mCenterALiPayCheckBox.isChecked()) {
                            /**
                             * 发起支付
                             */
                        } else {
                            ToastUtils.show(this, R.string.diamond_agent_center_no_pay_type_toast, Toast.LENGTH_SHORT);
                        }
                    }
                    break;
                case R.id.balance_take:
                    /**
                     * 提现
                     */
                    if (FastClickUtils.isNormalClick()) {

                    }
                    break;
                case R.id.action_right_tv:
                    /**
                     * 说明
                     */
                    if (FastClickUtils.isNormalClick()) {

                    }
                    break;
                case R.id.qr_code_view:
                    /**
                     * 跳转二维码页面
                     */
                    if (FastClickUtils.isNormalClick()) {
                        Intent intent = new Intent(DiamondAgentCenterActivity.this, InvitationCodeActivity.class);
                        intent.putExtra(USER_ID, "ID");
                        startActivity(intent);

                    }
                    break;
                case R.id.member_list_view:
                    /**
                     * 跳转成员列表
                     */
                    if (FastClickUtils.isNormalClick()) {
                        Intent intent = new Intent(DiamondAgentCenterActivity.this, MemberListActivity.class);
                        intent.putExtra(USER_ID, "ID");
                        startActivity(intent);
                    }
                    break;

                default:
                    break;
            }
        }

    }


    @Override
    public void getUserInfoSuccess(AgentCenterData data) {
        mAgentCenterData = data;
        if (mAgentCenterData != null) {
            isGetDataSuccess = true;
            mCenterALiPayCheckBox.setEnabled(true);
            setDataToView();
        }
    }

    private void setDataToView() {
        DecimalFormat df = new DecimalFormat("######0");
        mCenterNum.setText(getString(R.string.diamond_agent_center_num, getDefaultEmpty(mAgentCenterData.getInfo().getUserId())));
        mCenterSuperior.setText(getString(R.string.diamond_agent_center_superior, getDefaultNull(mAgentCenterData.getInfo().getLevel1Name())));
        mCenterTodayAmount.setText(getAmount(AMOUNT_DAY));
        mCenterMonthAmount.setText(getAmount(AMOUNT_MONTH));
        mCenterAllAmount.setText(getAmount(AMOUNT_ALL));
        mCenterSurplusDays.setText(getString(R.string.diamond_agent_center_surplus_days_title, (df.format(mAgentCenterData.getInfo().getValidDays()) + "")));
        mCenterBalanceTitle.setText(getBalanceTitle(getResources().getString(R.string.diamond_agent_center_balance_title, ("￥" + getDefaultZero(mAgentCenterData.getInfo().getMoney())))));
        mCenterRenenTitle.setText(getRenenTitle(getResources().getString(R.string.diamond_agent_center_renew_logo_title, getDefaultZero(mAgentCenterData.getRenewPrice()))));
    }

    @Override
    public void getUserInfoFail(String msg) {
        isGetDataSuccess = false;
        mCenterALiPayCheckBox.setEnabled(false);
        ToastUtils.show(this, msg, Toast.LENGTH_SHORT);
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
            presenter.getUserInfo("2");
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
                amount = mAgentCenterData.getRebate().getDay();
                break;
            case AMOUNT_MONTH:
                amount = mAgentCenterData.getRebate().getMonth();
                break;
            case AMOUNT_ALL:
                amount = mAgentCenterData.getRebate().getAll();
                break;
            default:
                break;

        }
        return getString(R.string.diamond_agent_center_amount, getDefaultZero(amount));

    }

    private SpannableStringBuilder getRenenTitle(String str) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan((new ForegroundColorSpan(Color.parseColor("#999999"))), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan((new ForegroundColorSpan(Color.parseColor("#1E90FF"))), 3, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan((new ForegroundColorSpan(Color.parseColor("#000000"))), 18, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    private SpannableStringBuilder getBalanceTitle(String str) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan((new ForegroundColorSpan(Color.parseColor("#999999"))), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan((new ForegroundColorSpan(Color.parseColor("#BA55D3"))), 3, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

}
