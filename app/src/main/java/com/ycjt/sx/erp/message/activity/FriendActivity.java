package com.ycjt.sx.erp.message.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.ycjt.sx.R;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.erp.message.bean.FullImageInfo;
import com.ycjt.sx.erp.message.bean.MessageInfo;
import com.ycjt.sx.erp.message.bean.Messagebean;
import com.ycjt.sx.erp.message.fragment.child.ChatEmotionFragment;
import com.ycjt.sx.erp.message.fragment.child.ChatFunctionFragment;
import com.ycjt.sx.widget.NoScrollViewPager;
import com.ycjt.sx.widget.chatkeymanager.Constants;
import com.ycjt.sx.widget.chatkeymanager.EmotionInputDetector;
import com.ycjt.sx.widget.chatkeymanager.GlobalOnItemClickManagerUtils;
import com.ycjt.sx.widget.chatkeymanager.MediaManager;
import com.ycjt.sx.widget.chatkeymanager.adapter.ChatAdapter;
import com.ycjt.sx.widget.chatkeymanager.adapter.CommonFragmentPagerAdapter;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.recycleline.customrecycle.CustomRecycleView;
import com.ycjt.sx.widget.recycleline.customrecycle.customstatus.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liuchao on 2017/7/19.
 */

public class FriendActivity extends BaseActivity {

    @BindView(R.id.chat_list)
    CustomRecycleView chatList;
    @BindView(R.id.emotion_voice)
    ImageView emotionVoice;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.voice_text)
    FontTextView voiceText;
    @BindView(R.id.emotion_button)
    ImageView emotionButton;
    @BindView(R.id.emotion_add)
    ImageView emotionAdd;
    @BindView(R.id.emotion_send)
    StateButton emotionSend;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.emotion_layout)
    RelativeLayout emotionLayout;
    private EmotionInputDetector mDetector;
    private Messagebean messagebean;
    private ArrayList<Fragment> fragments;//键盘
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;
    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    private MessageInfo messageInfo2;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;

    private Handler mHandler = new Handler();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_friend;
    }


    @Override
    protected void initView() {
        init();//页面跳转过来,默认初始化工作
        initKey();//键盘工具类
        LoadData();

    }


    /**
     * 页面跳转过来,默认初始化工作
     */
    private void init() {
        EventBus.getDefault().register(this);//注册事件
        ibTopLeft.setOnClickListener(view -> finish());

        //跳转过来,获取数据
        Intent intent = getIntent();
        if (intent != null) {
            Bundle agencyDataJson = intent.getBundleExtra("Messagebean");
            messagebean = (Messagebean) agencyDataJson.getSerializable("Messagebean.class");
            LogUtils.i(messagebean);
        }
    }


    /**
     * 处理键盘的工具类
     */
    private void initKey() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToAddButton(emotionAdd)
                .bindToSendButton(emotionSend)
                .bindToVoiceButton(emotionVoice)
                .bindToVoiceText(voiceText)
                .build();

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);

        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Toast.makeText(FriendActivity.this, "SCROLL_STATE_SETTLING", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Toast.makeText(FriendActivity.this, "onScroll", Toast.LENGTH_SHORT).show();
            }
        });

        //刷新数据, 访问数据库查找历史记录
        chatList.setRefreshListener(() -> Toast.makeText(FriendActivity.this, "无历史信息", Toast.LENGTH_SHORT).show());

        chatAdapter.addItemClickListener(itemClickListener);

    }


    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            Toast.makeText(FriendActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(View view, int position) {
            int location[] = new int[2];
            view.getLocationOnScreen(location);
            FullImageInfo fullImageInfo = new FullImageInfo();
            fullImageInfo.setLocationX(location[0]);
            fullImageInfo.setLocationY(location[1]);
            fullImageInfo.setWidth(view.getWidth());
            fullImageInfo.setHeight(view.getHeight());
            fullImageInfo.setImageUrl(messageInfos.get(position).getImageUrl());
            EventBus.getDefault().postSticky(fullImageInfo);
            startActivity(new Intent(FriendActivity.this, FullImageActivity.class));
            overridePendingTransition(0, 0);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };


    /**
     * 构造聊天数据
     */
    private void LoadData() {
        messageInfos = new ArrayList<>();

        //将数据拿出来


//        List<MessageInfo> users = messageInfoDao.loadAll();
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setContent("你好，欢迎使用Rance的聊天界面框架");
        messageInfo.setTime(String.valueOf(1454666700000l));
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
        messageInfos.add(messageInfo);
//        if (users != null || users.size() < 0) {
//            for (int i = 0; i < users.size(); i++) {
//                messageInfos.add(users.get(i));
//            }
//        }


//
//
        MessageInfo messageInfo1 = new MessageInfo();
        messageInfo1.setFilepath("http://www.trueme.net/bb_midi/welcome.wav");
        messageInfo1.setVoiceTime(3000);
        messageInfo1.setTime(String.valueOf(1454666700000l + 1000 * 60 * 60 * 24 * 120));
        messageInfo1.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo1.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        messageInfo1.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfos.add(messageInfo1);

        messageInfo2 = new MessageInfo();
        messageInfo2.setImageUrl("http://img4.imgtn.bdimg.com/it/u=1800788429,176707229&fm=21&gp=0.jpg");
        messageInfo2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo2.setTime(String.valueOf(1454666700000l + 1000 * 60 * 60 * 24 * 240));
        messageInfo2.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
        messageInfos.add(messageInfo2);

        MessageInfo messageInfo3 = new MessageInfo();
        messageInfo3.setTime(String.valueOf(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 3));
        messageInfo3.setContent("[微笑][色][色][色]");
        messageInfo3.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo3.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
        messageInfo3.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfos.add(messageInfo3);
        chatAdapter.addAll(messageInfos);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {

        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setTime(String.valueOf(System.currentTimeMillis()));
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        messageInfos.add(messageInfo);
        chatAdapter.add(messageInfo);
        chatList.scrollToPosition(chatAdapter.getCount() - 1);
        mHandler.postDelayed(() -> {
            messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            chatAdapter.notifyDataSetChanged();
        }, 2000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);//移除事件
        EventBus.getDefault().unregister(this);//注销事件
    }

}
