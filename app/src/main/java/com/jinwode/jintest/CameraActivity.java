package com.jinwode.jintest;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.jin.jinutility.jinapplication.JinPermisssion;
import com.jinwode.jintest.utility.JinOkHttp;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jinwode.jintest.R.id;
import static com.jinwode.jintest.R.layout;

public class CameraActivity extends AppCompatActivity implements Serializable
{

    private static final int CHOOSE_PHOTO = 2;
    private static final int TAKE_PHOTO = 1;
    @BindView(id.button)
    Button button;
    @BindView(id.buttonChoose)
    Button buttonChoose;
    @BindView(id.imageView)
    ImageView imageView;

    @BindView(id.buttonRestart)
    Button buttonRestart;
    @BindView(id.buttonPause)
    Button buttonPause;
    @BindView(id.videoView)
    VideoView videoView;

    Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_camera);
        ButterKnife.bind(this);
        videoView.setClickable( true );
        videoView.setOnClickListener(v ->{
        if(videoView.isPlaying())
        {
            videoView.pause();
        }else
        {
            videoView.start();
        }
    });
    }

    @OnClick(id.buttonChoose)
    void clickButtonChoose()
    {

        //AskUserToStore();
        openAlbum();
    }

    private void AskUserToStore()
    {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        //intent.setType("video/*");
        intent.setType("image/*");
        // intent.setType("text/*");
        // intent.setType("audio/*");
        // intent.setType(" application/pdf");
        startActivityForResult(intent, 3);

    }


    public void SetOS()
    {
        //  this.getSystemService(StorageManager::getClass())

    }

    @OnClick(id.buttonPause)
    void clickButtonPause()
    {
        if(videoView.isPlaying())
        {
            videoView.pause();
        }else
        {
            videoView.start();
        }
    }
    @OnClick(id.button)
    void clickButton()
    {
        try
        {
            File fileInner = getFilesDir();
            File fileOut = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File fileOdut = Environment.getDataDirectory();

            File file = new File(getExternalCacheDir() + "/jinJpg.jpg");
            if (file.exists())
            {
                file.delete();
            }
            file.createNewFile();
            if (Build.VERSION.SDK_INT >= 24)
            {//文件提供者怎么来获取。
                imageUri = FileProvider.getUriForFile(this, "com.jin.jintest.provider", file);


            } else
            {
                imageUri = Uri.fromFile(file);
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            if (JinPermisssion.grantedPermission(this, new String[]{Manifest.permission.CAMERA}))
            {
                startActivityForResult(intent, TAKE_PHOTO);
            }

        }
        catch (Exception exception)
        {
            String str = exception.toString();
            exception.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        switch (requestCode)
        {
            case TAKE_PHOTO:
                // if (resultCode == RESULT_OK)
                if (true)
                {
                    try
                    {

                        InputStream inputStream = getContentResolver().openInputStream(imageUri);

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.setImageBitmap(bitmap);
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:

                if (resultCode == RESULT_OK)
                {
                    Uri uri = data.getData();
                    String type ;
                    String imagePath = "";
                    if (Build.VERSION.SDK_INT >= 19) // 4.4及以上系统使用这个方法处理图片。
                    {
                        // dumpImageMetaData(uri);
                        if (DocumentsContract.isDocumentUri(this, uri))  // 文档类型的uri ，通过document id 来处理。
                        {
                            String docId = DocumentsContract.getDocumentId(uri);
                            type =  docId.split(":")[0];
                            Cursor cursor = getContentResolver().query(uri,null,null,null,null );
                            int count = cursor.getCount();
                            if (cursor.moveToFirst())
                            {
                                try
                                {
                                    ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
                                    FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                                    switch (type)
                                    {
                                        case  "image":
                                            imageView.setImageBitmap( BitmapFactory.decodeFileDescriptor( fileDescriptor));
                                            break;
                                        case  "video":
                                            videoView.setVideoURI(uri);
                                     

                                            videoView.start();
                                            break;
                                        default:
                                            break;

                                    }

                                    parcelFileDescriptor.close();
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                            }





                            if ("com.android.providers.media.documents".equals(uri.getAuthority()))
                            {
                                String id = docId.split(":")[1];   //%3A 就是 “：” 的ascii码。
                                String selection = MediaStore.Images.Media._ID + "=" + id;
//                                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//                                imagePath = getImagePath(MediaStore.Images.Media.INTERNAL_CONTENT_URI, selection);
                                //imagePath = getImagePath(uri, null);
                            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority()))
                            {
                                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                                imagePath = getImagePath(contentUri, null);
                            }

                        } else if ("content".equalsIgnoreCase(uri.getScheme())) // content 类型的uri，则使用普通方式处理。
                        {
                            imagePath = getImagePath(uri, null);
                        } else if ("file".equalsIgnoreCase(uri.getScheme())) // file类型的早，则直接获取图片路径即可。
                        {
                            imagePath = uri.getPath();
                        }


                    } else
                    {
                        imagePath = getImagePath(uri, null);
                    }
                    //    dispalyImage(imagePath, imageView);
                }


                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (videoView!=null)
        {
            videoView.suspend();
        }
    }

    /*
     * 获取真是的图片路径，文件地址。
     *
     *
     */
    private String getImagePath(Uri uri, String selection)
    {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null)
        {
            int count = cursor.getCount();

            if (cursor.moveToFirst())//如果cursor 是empty ，空的话，会false 。
            {


            }
            cursor.close(); // cursor 为null，close就报错了。
        }
        return path;
    }

    private void dispalyImage(String imagePath, ImageView imageView)
    {
        if (imagePath != null)
        {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else
        {
            Toast.makeText(this, "加载图片失败！", Toast.LENGTH_SHORT);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        //表示运行创建的 生效，进行显示。 否则， return false 时候，不让你设置的进行显示。
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case id.item_add:
                Toast.makeText(this, "添加成功了！", Toast.LENGTH_LONG).show();
                break;
            case id.item_modify:
                Toast.makeText(this, "修改了 ！", Toast.LENGTH_SHORT).show();

                break;
            case id.item_remove:
                Toast.makeText(this, "要移除了 ！", Toast.LENGTH_LONG).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openFileSelector()
    {

    }

    private void openAlbum()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        //Intent intent = new Intent(this, ImageGridActivity.class);


        String[] mimeTypes = {MimeType.DOC, MimeType.DOCX, MimeType.PDF, MimeType.PPT, MimeType.PPTX, MimeType.XLS, MimeType.XLSX, "txt", "text/plain", "image/jpeg", " image/png"};
        //intent.setType("image/*");
        //  intent.setType("*");
        intent.addCategory(Intent.CATEGORY_OPENABLE); // 有的文件， 不是可以直接打开的 二进制码。  排除 virtual File
        intent.setType("*/*");
        //  intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        startActivityForResult(intent, CHOOSE_PHOTO);
    }


    public void dumpImageMetaData(Uri uri)
    {
        Cursor cursor = getContentResolver()
                .query(uri, null, null, null, null, null);
        try
        {
            if (cursor != null && cursor.moveToFirst())
            {
                String displayName = cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.e("HeHe", "Display Name: " + displayName);
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                String size = null;
                if (!cursor.isNull(sizeIndex))
                {
                    size = cursor.getString(sizeIndex);
                } else
                {
                    size = "Unknown";
                }
                Log.e("HeHe", "Size: " + size);
            }
        }
        finally
        {
            cursor.close();
        }
    }

    private void displayImage(String imagePath)
    {
        if (!TextUtils.isEmpty(imagePath))
        {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        } else
        {


        }


    }

    /**
     * Is virtual file int.
     * 检查是否是一个虚拟的文件。
     *
     * @param uri the uri
     * @return the int
     */
    int isVirtualFile(Uri uri)
    {
        if (!DocumentsContract.isDocumentUri(this, uri))
        {
            return 0;
        }
        Cursor cursor = getContentResolver().query(uri, new String[]{
                DocumentsContract.Document.COLUMN_FLAGS}, null, null, null);
        int flags = 0;
        if (cursor.moveToFirst())
        {
            flags = cursor.getInt(0);
        }
        cursor.close();
        return flags & DocumentsContract.Document.FLAG_VIRTUAL_DOCUMENT;
    }

    public class MimeType
    {
        public static final String DOC = "application/msword";
        public static final String DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        public static final String XLS = "application/vnd.ms-excel application/x-excel";
        public static final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        public static final String PPT = "application/vnd.ms-powerpoint";
        public static final String PPTX = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        public static final String PDF = "application/pdf";
    }


}