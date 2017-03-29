package com.diguagege.heartview;

/**
 * Created by linhanwei on 2017/3/28.
 */

public class Circle {
    public static final int MAX_RADIUS = 50;
    public static final float NARROW_RADIUS = 1.5f;
    private float radius = 40;
    private boolean isMinus = false;
    private boolean isStartAnim = false;
    private int delay = 130;
    private static final int RADIUS = 40;
    private float narrowRadius = 1.5f;

    public float getNarrowRadius() {
        return narrowRadius;
    }

    public void setNarrowRadius(float narrowRadius) {
        this.narrowRadius = narrowRadius;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isStartAnim() {
        return isStartAnim;
    }

    public void setStartAnim(boolean startAnim) {
        isStartAnim = startAnim;
    }

    public boolean isMinus() {
        return isMinus;
    }

    public void setMinus(boolean minus) {
        isMinus = minus;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void startCircleAnimation(Circle circle) {
        if (circle.getDelay() > 0) {
            circle.setDelay(circle.getDelay() - 1);
            return;
        }

        if (circle.isMinus()) {
            if (circle.getRadius() > Circle.MAX_RADIUS / 2) {
                circle.setRadius(circle.getRadius() - circle.getNarrowRadius());
            } else {
                circle.setRadius(circle.getRadius() + circle.getNarrowRadius());
                circle.setMinus(false);
            }
        } else {
            if (circle.getRadius() < Circle.MAX_RADIUS) {
                circle.setRadius(circle.getRadius() + circle.getNarrowRadius());
                if (circle.getRadius() == Circle.RADIUS) {
                    circle.setDelay(72);
                }
            } else {
                circle.setRadius(circle.getRadius() - circle.getNarrowRadius());
                circle.setMinus(true);
            }
        }
    }
}
