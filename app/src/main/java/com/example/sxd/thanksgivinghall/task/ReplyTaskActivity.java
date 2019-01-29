package com.example.sxd.thanksgivinghall.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 任务回复
 */
public class ReplyTaskActivity extends BaseActivity implements View.OnTouchListener,ReplyTaskContract.View{

    @BindView(R.id.logon_bt_logon)
    Button logonBtLogon;
    @BindView(R.id.et_content)
    EditText et_content;
    String recordId="",replyFlag="",sendUser="",replyContent="";
    ReplyTaskContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_reply_add);
        ButterKnife.bind(this);
        setTitle("任务回复");
        setBackArrow();
        presenter = new ReplyTaskPresenterImpl(this,this);
        initView();
        logonBtLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replyContent= et_content.getText().toString();
                presenter.request(recordId,replyFlag,replyContent,sendUser);
            }
        });
        /**
         * 完美解决EditText和ScrollView的滚动冲突
         */
        et_content.setOnTouchListener(ReplyTaskActivity.this);
    }

    @Override
    protected void setRightTitleOnClick(View v) {

    }

    public void initView(){
        Intent intent = getIntent();
        recordId = intent.getStringExtra("recordId");
        replyFlag = intent.getStringExtra("replyFlag");
        sendUser= SharedPreUtils.getString(this, Constants.SP_LOGINER_ID);
    }

    @Override
    public void requestSuccess(Base value) {
        if(value.getSuccess().equals("true")){
            showMessage("回复成功");
            finish();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //触摸的是EditText而且当前EditText能够滚动则将事件交给EditText处理。否则将事件交由其父类处理
        if ((view.getId() == R.id.et_content && canVerticalScroll(et_content))) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    /**
     * EditText竖直方向能否够滚动
     * @param editText  须要推断的EditText
     * @return  true：能够滚动   false：不能够滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if(scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ReplyTaskContract.Presenter paramT) {

    }
}
