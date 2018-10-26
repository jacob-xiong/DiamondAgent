package diamond.agent.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import diamond.agent.R;
import diamond.agent.adapter.MemberDetailAdapter;
import diamond.agent.mvp.data.BaseResultData;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberItemData;
import diamond.agent.mvp.data.MemberLevelData;
import diamond.agent.mvp.data.MemberListData;
import diamond.agent.mvp.presenter.MemberListPresenter;
import diamond.agent.mvp.view.MemberListView;
import diamond.agent.utils.ToastUtils;

/**
 * @author by xiongyan on 2018/9/25.
 * 各级别成员列表
 */
public class MemberDetailActivity extends BaseActivity<MemberListPresenter> implements MemberDetailAdapter.MemberDetailListener, MemberListView, XRecyclerView.LoadingListener {
    public static final String QUERY_LEVEL = "query_level";
    private String queryLevel;
    private int pageNo = 1;
    private int pageCount;
    @BindView(R.id.member_detail_list_view)
    XRecyclerView mXRecyclerView;
    private MemberDetailAdapter mMemberDetailAdapter;
    private MemberGroupData mMemberGroupData = new MemberGroupData();
    private boolean isShowHint = true;


    @Override
    protected boolean isShowActionBarTitle() {
        return true;
    }

    @Override
    protected int getActionTitle() {
        return R.string.diamond_agent_center_member_list_title;
    }


    @Override
    protected void initPresenter() {
        presenter = new MemberListPresenter(this, this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.member_detail_activity;
    }

    @Override
    protected void loadData() {
        pageNo = 1;
        getData();
    }

    @Override
    protected void initView() {
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mXRecyclerView.setPullRefreshEnabled(false);
        mXRecyclerView.setLoadingListener(this);
        mXRecyclerView.setLoadingMoreEnabled(true);
        mMemberGroupData.setMemberLevelItemList(new ArrayList<MemberItemData>());
        mMemberDetailAdapter = new MemberDetailAdapter(this, mMemberGroupData, this, Integer.parseInt(queryLevel));
        mXRecyclerView.setAdapter(mMemberDetailAdapter);
    }

    @Override
    protected void initIntent() {
        queryLevel = getIntent().getStringExtra(QUERY_LEVEL);
        queryLevel = TextUtils.isEmpty(queryLevel) ? "-1" : queryLevel;
    }


    @Override
    public void getMemberListSuccess(MemberLevelData resultData) {

    }

    @Override
    public void getMemberListFail(String msg) {

    }

    @Override
    public void getMemberDetailListSuccess(MemberGroupData resultData) {
        mXRecyclerView.loadMoreComplete();
        pageCount = resultData.getTotalPages();
        if (resultData.getMemberLevelItemList() != null && resultData.getMemberLevelItemList().size() > 0) {
            mMemberGroupData = resultData;
            mMemberDetailAdapter.setGroupData(mMemberGroupData);
        }
    }

    @Override
    public void getMemberDetailListFail(String msg) {
        ToastUtils.showShort(this, msg);
    }

    @Override
    public void loadFailure(Throwable throwable) {
        mMemberGroupData = new MemberGroupData();
        mMemberGroupData.setGetMemberLevelAllNumCommission("300");
        mMemberGroupData.setMemberLevelAllConsumed("500");
        mMemberGroupData.setMemberLevelAllNum("100");
        ArrayList<MemberItemData> itemDataList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            MemberItemData itemData = new MemberItemData();
            itemData.setMemberId("WH" + "00" + i);
            itemData.setMemberCommission(i * 200 + "");
            itemData.setMemberConsumed(i * 100 + "");
            itemDataList.add(itemData);
        }
        mMemberGroupData.setMemberLevelItemList(itemDataList);
        mMemberDetailAdapter.setGroupData(mMemberGroupData);

    }

    @Override
    public void onException(BaseResultData result) {

    }

    private void getData() {
        if (presenter != null) {
            presenter.getMemberDetailListData("id", queryLevel, pageNo);
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        pageNo++;
        if (pageNo > pageCount) {
            if (isShowHint) {
                ToastUtils.showShort(this, "没有更多数据了");
            }

            isShowHint = false;
            mXRecyclerView.loadMoreComplete();
        } else {
            isShowHint = true;
            getData();

        }
    }

    @Override
    public void clickItem(MemberItemData couponDto) {

    }
}
