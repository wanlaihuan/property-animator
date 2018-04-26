package scut.carson_ho.valueanimator_ofobject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnticipateOvershootInterpolator;

public class MainActivity extends AppCompatActivity {

    MyView2 myView2;
    MyView3 myView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView2 = (MyView2) findViewById(R.id.MyView2);

        /**
         *
         * ObjectAnimator: 作用于某个视图对象(Object)的动画。第二个参数一定是要和 View set 方法对应起来。比如 setColor1（一定是这么写）
         * TypeEvaluator: 用来根据动画控制（比如插值）来计算值，进而控制 ofObject 作用的数据变化并返回，返回后会自动调用视图的 set 方法
         * ofObject: 作用于某个数据对象（自定义的数据结构类型）的动画 (被控制数据的变化)
         * ofInt：系统默认的。作用于整数的动画  (控制整数的变化)
         * ofFloat: 系统默认的。作用于整数的动画 (控制浮点数的变化)
         */
        ObjectAnimator anim = ObjectAnimator.ofObject(myView2, "color1", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        anim.setDuration(8000);
        anim.start();

        myView3 = (MyView3) findViewById(R.id.MyView3);

        // (重点关注)将属性动画作用到View中
        // 步骤1:创建初始动画时的对象点  & 结束动画时的对象点
        Point startPoint = new Point(500, 200);// 初始点为圆心(70,70)
        Point endPoint = new Point(200, 800);// 结束点为(700,1000)

        /**
         * TODO: 步骤2:创建动画对象 & 设置参数
         * ObjectAnimator: 作用于某个视图对象(Object)的动画。第二个参数一定是要和 View set 方法对应起来。比如 setCurrentPoint（一定是这么写）
         * ofObject: 作用于某个数据对象（自定义的数据结构类型）的动画 (被控制数据的变化)
         * TypeEvaluator: 用来根据动画控制（比如插值）来计算值，进而控制 ofObject 作用的数据变化并返回，返回后会自动调用视图的 set 方法
         * ofInt：系统默认的。作用于整数的动画  (控制整数的变化)
         * ofFloat: 系统默认的。作用于整数的动画 (控制浮点数的变化)
         */
        ObjectAnimator anim3 = ObjectAnimator.ofObject(myView3, "currentPoint", new PointEvaluator(), startPoint, endPoint);

        // 参数说明
        // 参数1：TypeEvaluator 类型参数 - 使用自定义的PointEvaluator(实现了TypeEvaluator接口)
        // 参数2：初始动画的对象点
        // 参数3：结束动画的对象点

        anim3.setDuration(5000);

        anim3.setInterpolator(new AnticipateOvershootInterpolator());
        anim3.start();
        // 启动动画
    }
}
