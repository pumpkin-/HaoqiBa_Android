package com.shine.haoqiba.utils;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;


public class AnimatorUtils {
    public static Animator animViewFadeIn(View paramView) {
        return animViewFadeIn(paramView, 200L, null);
    }

    public static Animator animViewFadeIn(View paramView, long paramLong, Animator.AnimatorListener paramAnimatorListener) {
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[]{0.0F, 1.0F});
        localObjectAnimator.setDuration(paramLong);
        if (paramAnimatorListener != null)
            localObjectAnimator.addListener(paramAnimatorListener);
        localObjectAnimator.start();
        return localObjectAnimator;
    }

    public static Animator animViewFadeOut(View paramView) {
        return animViewFadeOut(paramView, 200L, null);
    }

    public static Animator animViewFadeOut(View paramView, long paramLong, Animator.AnimatorListener paramAnimatorListener) {
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[]{1.0F, 0.0F});
        localObjectAnimator.setDuration(paramLong);
        if (paramAnimatorListener != null)
            localObjectAnimator.addListener(paramAnimatorListener);
        localObjectAnimator.start();
        return localObjectAnimator;
    }


    /**
     * 移动ScrollView的x轴
     *
     * @param view      要移动的ScrollView
     * @param toX       要移动到的X轴坐标
     * @param time      动画持续时间
     * @param delayTime 延迟开始动画的时间
     * @param isStart   是否开始动画
     * @return
     */
    public static Animator moveScrollViewToX(View view, int toX, int time, int delayTime, boolean isStart) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(view, "scrollX", new int[]{toX});
        objectAnimator.setDuration(time);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setStartDelay(delayTime);
        if (isStart)
            objectAnimator.start();
        return objectAnimator;
    }

    /**
     * 将View的背景颜色更改，使背景颜色转换更和谐的过渡动画
     *
     * @param view      要改变背景颜色的View
     * @param preColor  上个颜色值
     * @param currColor 当前颜色值
     * @param duration  动画持续时间
     */
    public static void showBackgroundColorAnimation(View view, int preColor, int currColor, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofInt(view, "backgroundColor", new int[]{preColor, currColor});
        animator.setDuration(duration);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }

    /**
     * @param view                需要设置动画的view
     * @param translationY        偏移量
     * @param animatorTime        动画时间
     * @param isStartAnimator     是否开启指示器
     * @param isStartInterpolator 是否开始动画
     * @return 平移动画
     */
    public static Animator showUpAndDownBounce(View view, int translationY, int animatorTime, boolean isStartAnimator, boolean isStartInterpolator) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", translationY);
        if (isStartInterpolator) {
            objectAnimator.setInterpolator(new OvershootInterpolator());
        }
        objectAnimator.setDuration(animatorTime);
        if (isStartAnimator) {
            objectAnimator.start();
        }
        return objectAnimator;
    }

    /**
     * viewPager动画
     */
    public static class ZoomOutPageTransformer implements ViewPager.PageTransformer
    {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @SuppressLint("NewApi")
        public void transformPage(View view, float position)
        {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

           // Log.e("TAG", view + " , " + position + "");

            if (position < -1)
            { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0)
                {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else
                {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else
            { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}