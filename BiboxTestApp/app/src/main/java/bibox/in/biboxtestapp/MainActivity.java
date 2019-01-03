package bibox.in.biboxtestapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity  {

    int selectedImageId;
    ImageView man,mobile,cycle,bike,car,dmobile,dcycle,dbike,dcar;
    float manX,manY;
    RelativeLayout layout;
    DrawView mobileLine,cycleLine,bikeLine,carLine;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.drop_here);
        man = (ImageView) findViewById(R.id.man);
        mobile = (ImageView) findViewById(R.id.mobile);
        cycle = (ImageView) findViewById(R.id.cycle);
        bike = (ImageView) findViewById(R.id.bike);
        car = (ImageView) findViewById(R.id.car);
        dmobile = (ImageView) findViewById(R.id.dmobile);
        dcycle = (ImageView) findViewById(R.id.dcycle);
        dbike = (ImageView) findViewById(R.id.dbike);
        dcar = (ImageView) findViewById(R.id.dcar);
        dmobile.setVisibility(View.INVISIBLE);
        dcycle.setVisibility(View.INVISIBLE);
        dbike.setVisibility(View.INVISIBLE);
        dcar.setVisibility(View.INVISIBLE);

        manX = man.getX()-man.getWidth()/2;
        manY = man.getY()-man.getHeight()/2;
        mobileLine = new DrawView(this,manX,manY);
        cycleLine = new DrawView(this,manX,manY);
        bikeLine = new DrawView(this,manX,manY);
        carLine = new DrawView(this,manX,manY);

        View.OnDragListener onDragListener = new View.OnDragListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch(action){
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DROP:
                        dropImage(v,event);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;
                    default:break;
                }
                return true;
            }

        };
        layout.setOnDragListener(onDragListener);
        mobile.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 1;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(mobile);
                v.startDrag(data, shadow, null,0 );
                return true;
            }
        });
        cycle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 2;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(cycle);
                v.startDrag(data, shadow, null, 0);
                return true;
            }
        });
        bike.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 3;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(bike);
                v.startDrag(data, shadow,null ,0 );
                return true;
            }
        });
        car.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 4;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(car);
                v.startDrag(data, shadow,null ,0 );
                return true;
            }
        });
    }

    void dropImage(View v,DragEvent event){
        int[] location = new int[2];
        v.getLocationInWindow(location);
        int mx = location[0] + v.getWidth() / 2;
        int my = location[1] + v.getHeight() / 2;

        float x = event.getX();
        float y = event.getY();
        switch(selectedImageId){
            case 1 : dmobile.setX(x - dmobile.getWidth()/2);
                dmobile.setY(y - dmobile.getHeight()/2);
                dmobile.setVisibility(View.VISIBLE);
                connect(mobileLine,mx,my,x - dmobile.getWidth()/2,y - dmobile.getHeight()/2);
                break;
            case 2 : dcycle.setX(x - dmobile.getWidth()/2);
                dcycle.setY(y - dmobile.getHeight()/2);
                dcycle.setVisibility(View.VISIBLE);
                connect(cycleLine,mx,my,x - dmobile.getWidth()/2,y - dmobile.getHeight()/2);
                break;
            case 3 : dbike.setX(x - dmobile.getWidth()/2);
                dbike.setY(y - dmobile.getHeight()/2);
                dbike.setVisibility(View.VISIBLE);
                connect(bikeLine,mx,my,x - dmobile.getWidth()/2,y - dmobile.getHeight()/2);
                break;
            case 4 : dcar.setX(x - dmobile.getWidth()/2);
                dcar.setY(y - dmobile.getHeight()/2);
                dcar.setVisibility(View.VISIBLE);
                connect(carLine,mx,my,x - dmobile.getWidth()/2,y - dmobile.getHeight()/2);
                break;
            default : break;
        }
    }

    protected void connect(DrawView drawView,float sx, float sy, float ex, float ey) {
        drawView.clearAnimation();
        layout.removeView(drawView);
        drawView.setA(sx-350);
        drawView.setB(sy-260);
        drawView.setC(ex+100);
        drawView.setD(ey+100);
        layout.addView(drawView);
    }
}
