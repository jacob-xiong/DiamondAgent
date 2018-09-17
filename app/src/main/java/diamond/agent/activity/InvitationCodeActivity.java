package diamond.agent.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import diamond.agent.R;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.InvitationCodeResultData;
import diamond.agent.mvp.presenter.InvitationCodePresenter;
import diamond.agent.mvp.view.InvitationCodeView;

/**
 * Created by Jacob on 2018-9-13.
 */

public class InvitationCodeActivity extends BaseActivity<InvitationCodePresenter> implements InvitationCodeView {
    @BindView(R.id.invitation_code)
    TextView mInvitationCode;


    @Override
    protected boolean isShowActionBarTitle() {
        return true;
    }

    @Override
    protected int getActionTitle() {
        return R.string.diamond_agent_center_qr_code_title;
    }

    @Override
    protected void initPresenter() {
        presenter = new InvitationCodePresenter(this, this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.invitation_code_activity;
    }

    @Override
    protected void loadData() {
        presenter.getInvitaionCode("id");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    public void getInvitationCodeSuccess(InvitationCodeResultData data) {
        if (data != null) {
            mInvitationCode.setVisibility(TextUtils.isEmpty(data.getInvitationCode()) ? View.GONE : View.VISIBLE);
            mInvitationCode.setText(getResources().getString(R.string.diamond_agent_invitation_code, data.getInvitationCode()));
        }
    }

    @Override
    public void getInvitationCodeFail(String msg) {
        mInvitationCode.setVisibility(View.GONE);
    }

    @Override
    public void loadFailure(Throwable throwable) {

    }

    @Override
    public void onException(BaseResultData result) {

    }
}
