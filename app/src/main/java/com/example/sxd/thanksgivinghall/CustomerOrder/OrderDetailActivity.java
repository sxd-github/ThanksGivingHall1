package com.example.sxd.thanksgivinghall.CustomerOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.CustomerOrderDetailEntity;
/*import com.example.sxd.thanksgivinghall.CustomerOrder.OrderDetailContract;
import com.example.sxd.thanksgivinghall.CustomerOrder.OrderDetailPresenterImpl;*/
import com.example.sxd.thanksgivinghall.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 客户详细信息
 */
public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View{


    @BindView(R.id.order_username)
    TextView order_name;
    @BindView(R.id.order_goods)
    TextView order_goods;
    @BindView(R.id.order_nums)
    TextView order_nums;
    @BindView(R.id.order_price)
    TextView order_price;
    @BindView(R.id.order_sumprice)
    TextView order_sumprice;
    @BindView(R.id.order_time)
    TextView order_time;

    OrderDetailContract.Presenter mPresenter;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        setTitle("订单详细信息");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        mPresenter = new OrderDetailPresenterImpl(OrderDetailActivity.this, this);
        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mPresenter.request(id);
    }

    @Override
    protected void setRightTitleOnClick(View v) {
    }


    @Override
    public void requestSuccess(final CustomerOrderDetailEntity value) {
        order_name.setText(value.getData().getCusname());
        order_goods.setText(value.getData().getGoods());
        order_nums.setText(value.getData().getGoodsnum());
        order_price.setText(value.getData().getPrice());
        order_sumprice.setText(value.getData().getSumprice());
        /*order_time.setText(value.getData().getDate());*/
        String urlBase= String.format("http://%s:%s/", new Object[] { SharedPreUtils.getString(this,"SP_IP"),  SharedPreUtils.getString(this,"SP_PORT") });

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(OrderDetailContract.Presenter paramT) {

    }
}
