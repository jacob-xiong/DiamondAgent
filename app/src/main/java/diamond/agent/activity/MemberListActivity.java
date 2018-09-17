package diamond.agent.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import diamond.agent.R;

/**
 * @author by xiongyan on 2018/9/17.
 */
public class MemberListActivity extends BaseActivity {
    @BindView(R.id.member_group_list_view)
    LinearLayout mGroupListView;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.member_list_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        for(int i=0;i<3;i++){
            View view=LayoutInflater.from(this).inflate(R.layout.member_group_list_item, mGroupListView, false);
            LinearLayout list_item_view= (LinearLayout) view.findViewById(R.id.member_group_list_item_view);
            for(int j=0;j<4;j++){
                View itemView=LayoutInflater.from(this).inflate(R.layout.member_group_value_item, list_item_view, false);
                if(j==0){
                    itemView.setPadding(0,0,0,10);
                }

                list_item_view.addView(itemView);


            }
            mGroupListView.addView(view);
        }
    }

    @Override
    protected void initIntent() {

    }
}
