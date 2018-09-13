package diamond.agent;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import diamond.agent.activity.BaseActivity;
import diamond.agent.activity.DiamondAgentCenterActivity;

/**
 * @author by xiongyan on 2018/9/13.
 */
public class MainActivity extends BaseActivity {
    private Button mJump;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        mJump = (Button) findViewById(R.id.jump);
        mJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiamondAgentCenterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initIntent() {

    }
}
