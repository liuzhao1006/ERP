package com.ycjt.sx.erp.personal.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.util.DisplayUtils;
import com.yanzhenjie.album.widget.recyclerview.AlbumVerticalGirdDecoration;
import com.yanzhenjie.durban.Durban;
import com.yanzhenjie.mediascanner.MediaScanner;
import com.yanzhenjie.nohttp.NoHttp;
import com.ycjt.sx.R;
import com.ycjt.sx.app.GlobalConstants;
import com.ycjt.sx.base.BaseActivity;
import com.ycjt.sx.base.MainActivity;
import com.ycjt.sx.erp.personal.adapter.Adapter;
import com.ycjt.sx.erp.personal.presenter.FeedBackPresenter;
import com.ycjt.sx.utils.BitmapUtils;
import com.ycjt.sx.utils.PrefUtils;
import com.ycjt.sx.utils.http.FileUtil;
import com.ycjt.sx.widget.fonts.FontTextView;
import com.ycjt.sx.widget.imageloder.GridAdapter;
import com.ycjt.sx.widget.imageloder.OnCompatItemClickListener;
import com.ycjt.sx.widget.view.CircularProgress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ycjt.sx.app.InterfaceConfig.APP_ORG_ID;
import static com.ycjt.sx.app.InterfaceConfig.URL_ADDRESS;
import static com.ycjt.sx.utils.ImageViewUtils.getSmallBitmap;

/**
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity implements View.OnClickListener, IFeedBack {


    private static final int ACTIVITY_REQUEST_SELECT_RADIO = 100;
    private static final int ACTIVITY_REQUEST_SELECT_CROP_RADIO = 101;
    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 102;
    private static final int ACTIVITY_REQUEST_TAKE_PICTURE = 103;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 104;


    private TextInputEditText etFeedBackTitle;//输入标题
    private TextInputEditText etFeedBackContect;//输入内容
    private FontTextView tvFeedBackNumber;

    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private GridView rlvItem;

    private ArrayList<String> mImageList;
    private ArrayList<Bitmap> mImage = new ArrayList<>();
    private Adapter adapter;
    private Bitmap bp;

    private CircularProgress cp;

    @Override
    protected void initView() {

        mImageList = new ArrayList<>();
        DisplayUtils.initScreen(this);
        /*=======头布局=======*/
        tvTopModdel.setText("意见反馈");
        ibTopLeft.setVisibility(View.VISIBLE);
        ivFalse.setVisibility(View.VISIBLE);
        ibTopRight.setVisibility(View.GONE);
        ibTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /*=======标题和内容获取,限制======*/
        etFeedBackTitle = (TextInputEditText) findViewById(R.id.et_feed_back_title);
        etFeedBackContect = (TextInputEditText) findViewById(R.id.et_feed_back_content);
        tvFeedBackNumber = (FontTextView) findViewById(R.id.tv_feed_back_number);
        etFeedBackContect.addTextChangedListener(new mTextWatcher());
        cp = (CircularProgress) findViewById(R.id.cp);
        cp.setVisibility(View.GONE);
        /*=======图片获取======*/
        // 设置默认图片为加号
        bp = BitmapFactory.decodeResource(getResources(), R.drawable.add);
        mImage.add(bp);


        //图片列表
        rlvItem = (GridView) findViewById(R.id.rlv_feed_book);

// 绑定Adapter
        adapter = new Adapter(getApplicationContext(), mImage, rlvItem);
        rlvItem.setAdapter(adapter);
// 设置点击监听事件
        rlvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mImage.size() == 6) {
                    Toast.makeText(FeedBackActivity.this, "图片数5张已满", Toast.LENGTH_SHORT).show();
                } else {
                    if (position == mImage.size() - 1) {
                        uploadHeadImage();
                    } else {
                        previewImage(position);

//                        Toast.makeText(FeedBackActivity.this, "长按删除此图片", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        // 设置长按事件
        rlvItem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (mImage.size() - 1 == position) {
                    return true;
                } else {
                    dialog(position);
                    return true;
                }
            }
        });


        TextView tvAdd = (TextView) findViewById(R.id.tv_feed_back_add);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadHeadImage();
            }
        });

        Button btnFeedBackSumbit = (Button) findViewById(R.id.btn_feed_back_submit);
        btnFeedBackSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedBackPresenter presenter = new FeedBackPresenter(FeedBackActivity.this);
                String title = etFeedBackTitle.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    showToast("请输入反馈信息标题", TOAST_SHORT);
                    return;
                }
                String content = etFeedBackContect.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    showToast("请输入反馈内容", TOAST_SHORT);
                    return;
                }

                if (mImageList == null || mImageList.size() < 1) {
                    showToast("请选择图片", TOAST_SHORT);
                    return;
                }
                String s2 = "";
                for (int i = 0; i < mImageList.size(); i++) {
                    String s = imgToBase64(mImage.get(i));
                    if (i == 0) {
                        s2 = s;

                    } else if (i == mImage.size() - 1) {
                    } else {
                        s2 += "," + imgToBase64(mImage.get(i));

                    }
                }
                Map<String, String> mapData = new HashMap<>();
                mapData.put("userId", PrefUtils.getString(FeedBackActivity.this, GlobalConstants.PERSONAL_USERID, null));
                mapData.put("deviceID", PrefUtils.getString(FeedBackActivity.this, GlobalConstants.PERSONAL_DEVICEID, null));
                mapData.put("organizeId", PrefUtils.getString(FeedBackActivity.this, APP_ORG_ID, null));
                mapData.put("Title", title);
                mapData.put("FeedContent", content);
                mapData.put("Imageslist", s2);

                presenter.postBitMap(url, mapData);
                cp.setVisibility(View.VISIBLE);
            }
        });
    }

    /*
     * Dialog对话框提示用户删除操作 position为删除图片位置
	 */
    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mImage.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void getBitmapObj(String imgPath) {
        if (mImage.size() == 1) {
            mImage.clear();
        } else {
            mImage.remove(mImage.get(mImage.size() - 1));
        }
        mImage.add(getSmallBitmap(imgPath, 120, 180));
        mImage.add(bp);
    }


    /**
     * @return
     */


    public static String imgToBase64(Bitmap bitmap) {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_feed_back;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void successed(String string) {
        cp.setVisibility(View.GONE);
        showToast("意见反馈成功, 管理员正在积极处理中......感谢您的意见.", TOAST_SHORT);
        finish();
    }

    @Override
    public void failed(String s) {
        cp.setVisibility(View.GONE);
        showToast(s, TOAST_SHORT);
    }

    /**
     * 文本框输入字数的限定
     */
    class mTextWatcher implements TextWatcher {
        private int maxLen = 300;

        public mTextWatcher() {

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Editable editable = etFeedBackContect.getText();
            int len = editable.length();
            if (len > maxLen) {
                int selEndIndex = Selection.getSelectionEnd(editable);
                String str = editable.toString();
                //截取新字符串
                String newStr = str.substring(0, maxLen);
                etFeedBackContect.setText(newStr);
                editable = etFeedBackContect.getText();
                //新字符串的长度
                int newLen = editable.length();
                //旧光标位置超过字符串长度
                if (selEndIndex > newLen) {
                    selEndIndex = editable.length();
                }
                //设置新光标所在的位置
                Selection.setSelection(editable, selEndIndex);
                tvFeedBackNumber.setText("剩余" + "" + (maxLen - newLen) + "个字");
            } else {

                tvFeedBackNumber.setText("剩余" + "" + (maxLen - len) + "个字");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_RADIO: {
                if (resultCode == RESULT_OK) {
                    // Image crop: https://github.com/yanzhenjie/Durban
                    Durban.with(this)
                            .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                            .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                            .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                            .inputImagePaths(Album.parseResult(data))
                            .outputDirectory(FileUtil.getRootPath(this).getAbsolutePath())
                            .aspectRatio(1, 1)
                            .maxWidthHeight(500, 500)
                            .requestCode(ACTIVITY_REQUEST_SELECT_CROP_RADIO)
                            .start();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
//                    Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case ACTIVITY_REQUEST_SELECT_CROP_RADIO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Durban.parseResult(data); // Parse path.
                    for (String s : imageList) {
                        Log.i("TAG", s);
                    }
                    new MediaScanner(this).scan(imageList); // Scan to system: https://github.com/yanzhenjie/MediaScanner
                    mImageList.addAll(imageList);


                    getBitmapObj(mImageList.get(mImageList.size() - 1));
                    refreshImage();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
//                    Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case ACTIVITY_REQUEST_SELECT_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    getBitmapObj(mImageList.get(mImageList.size() - 1));
                    refreshImage();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
//                    Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case ACTIVITY_REQUEST_TAKE_PICTURE: {
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.addAll(imageList);
                    getBitmapObj(mImageList.get(mImageList.size() - 1));
                    refreshImage();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
//                    Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    getBitmapObj(mImageList.get(mImageList.size() - 1));
                    refreshImage();
                }
                break;
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_upload_image, null);
        FontTextView btnCarema = (FontTextView) view.findViewById(R.id.btn_camera);
        FontTextView btnPhoto = (FontTextView) view.findViewById(R.id.btn_photo);
        FontTextView btnCancel = (FontTextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(FeedBackActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(FeedBackActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
//                    gotoCamera();
                    fromCamera();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(FeedBackActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(FeedBackActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
//                    gotoPhoto();
                    fromAlbumRadio();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                gotoCamera();
                fromCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                gotoPhoto();
                fromAlbumRadio();
            }
        }
    }


    /**
     * Preview image.
     *
     * @param position current position.
     */
    private void previewImage(int position) {
        Album.gallery(this)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .checkedList(mImageList) // Image list.
                .currentPosition(position) // Preview first to show the first few.
                .checkFunction(false) // Does the user have an anti-selection when previewing.
                .start(ACTIVITY_REQUEST_PREVIEW_PHOTO);

    }

    /**
     * Take a picture from fromCamera.
     */
    private void fromCamera() {
        Album.camera(this)
//                .imagePath() // Specify the image path, optional.
                .start(ACTIVITY_REQUEST_TAKE_PICTURE);
    }

    /**
     * Radio mode selection picture.
     */
    private void fromAlbumRadio() {
        Album.albumRadio(this)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
//                .start(ACTIVITY_REQUEST_SELECT_RADIO);//裁剪相片
                .start(ACTIVITY_REQUEST_TAKE_PICTURE);//不裁剪


    }

    /**
     * Process selection results.
     */
    private void refreshImage() {
        adapter.notifyDataSetChanged();
//        if (mImageList == null || mImageList.size() == 0) {
//            rlvItem.setVisibility(View.GONE);
//        } else {
//            rlvItem.setVisibility(View.VISIBLE);
//        }
    }

    /**
     * Select image from fromAlbum.
     */
    private void fromAlbum() {
        Album.album(this)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(9) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(mImageList) // The picture has been selected for anti-election.
                .start(ACTIVITY_REQUEST_SELECT_PHOTO);
    }


    public String url = URL_ADDRESS + "/Service1.svc/Feedback/Set";

}
