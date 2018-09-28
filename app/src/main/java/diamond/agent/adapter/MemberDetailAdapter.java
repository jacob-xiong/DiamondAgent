package diamond.agent.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import diamond.agent.R;
import diamond.agent.mvp.data.MemberGroupData;
import diamond.agent.mvp.data.MemberItemData;
import diamond.agent.utils.ScreenUtils;

/**
 * @author by xiongyan on 2018/9/25.
 */
public class MemberDetailAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<MemberItemData> itemList;
    private MemberDetailListener listener;
    private MemberGroupData resultData;
    private int queryLevel;

    public void setGroupData(MemberGroupData data) {
        resultData = data;
        itemList = data.getMemberLevelItemList();
        notifyDataSetChanged();
    }

    public MemberDetailAdapter(Context context, MemberGroupData resultData, MemberDetailListener orderStateListener, int queryLevel) {
        this.resultData = resultData;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.itemList = resultData.getMemberLevelItemList();
        this.listener = orderStateListener;
        this.queryLevel = queryLevel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.member_group_value_item, parent, false);
        MemberDetailItemViewHolder viewHolder = new MemberDetailItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MemberDetailItemViewHolder itemViewHolder = (MemberDetailItemViewHolder) holder;
        View itemView = itemViewHolder.itemView;
        if (position == 0) {
            setTitleIsShow(itemViewHolder, false);
            itemView.setPadding(0, ScreenUtils.dip2px(mContext, 5), 0, ScreenUtils.dip2px(mContext, 8));
            itemViewHolder.consumerTitle.setText(getItemTitle(queryLevel));
        } else if (0 < position && position < itemList.size() + 1) {
            setTitleIsShow(itemViewHolder, false);
            MemberItemData itemData = itemList.get(position - 1);
            if (itemData == null) {
                return;
            }
            itemView.setPadding(0, 0, 0, 0);
            itemViewHolder.consumerTitle.setText(itemData.getMemberId());
            itemViewHolder.consumerAlreadyUse.setText(mContext.getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(itemData.getMemberConsumed())));
            itemViewHolder.consumerExtract.setText(mContext.getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(itemData.getMemberCommission())));

        } else if (position == itemList.size() + 1) {
            setTitleIsShow(itemViewHolder, true);
            itemViewHolder.consumerTitle.setText(mContext.getResources().getString(R.string.diamond_agent_center_person, getDefaultZero(resultData.getMemberLevelAllNum())));
            itemViewHolder.consumerAlreadyUse.setText(mContext.getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(resultData.getMemberLevelAllConsumed())));
            itemViewHolder.consumerExtract.setText(mContext.getResources().getString(R.string.diamond_agent_center_amount, getDefaultZero(resultData.getGetMemberLevelAllNumCommission())));
            itemView.setPadding(0, ScreenUtils.dip2px(mContext, 8), 0, ScreenUtils.dip2px(mContext, 5));
        }

    }

    private void setTitleIsShow(MemberDetailItemViewHolder itemViewHolder, boolean isShowTitle) {
        itemViewHolder.consumerInTotal.setVisibility(isShowTitle ? View.VISIBLE : View.INVISIBLE);
        itemViewHolder.consumerInTotal.setTextColor(Color.parseColor(isShowTitle ? "#FF8C00" : "#808080"));
        itemViewHolder.consumerTitle.setTextColor(Color.parseColor(isShowTitle ? "#FF8C00" : "#808080"));
        itemViewHolder.consumerAlreadyUse.setTextColor(Color.parseColor(isShowTitle ? "#FF8C00" : "#808080"));
        itemViewHolder.consumerExtract.setTextColor(Color.parseColor(isShowTitle ? "#FF8C00" : "#808080"));
    }

    @Override
    public int getItemCount() {
        if (itemList.size() == 0) {
            return 0;
        }
        return itemList.size() + 2;
    }


    public interface MemberDetailListener {
        void clickItem(MemberItemData couponDto);
    }


    public class MemberDetailItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.consumer_in_total)
        TextView consumerInTotal;
        @BindView(R.id.consumer_title)
        TextView consumerTitle;
        @BindView(R.id.consumer_already_use)
        TextView consumerAlreadyUse;
        @BindView(R.id.consumer_item_extract)
        TextView consumerExtract;


        public MemberDetailItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String getItemTitle(int i) {
        String title = "消费者";
        switch (i) {
            case 0:
                title = mContext.getResources().getString(R.string.diamond_agent_consumer_title_one);
                break;
            case 1:
                title = mContext.getResources().getString(R.string.diamond_agent_consumer_title_two);
                break;
            case 2:
                title = mContext.getResources().getString(R.string.diamond_agent_consumer_title_three);
                break;
            default:
                break;
        }
        return title;
    }

    private String getDefaultZero(String str) {
        return TextUtils.isEmpty(str) ? mContext.getString(R.string.diamond_agent_center_zero) : str;
    }
}
