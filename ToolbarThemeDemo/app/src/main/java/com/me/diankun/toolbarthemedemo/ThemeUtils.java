package com.me.diankun.toolbarthemedemo;

import android.app.Activity;

/**
 * Created by diank on 2015/9/16.
 */
public class ThemeUtils {


    public static void changeTheme(Activity activity, Theme theme) {

        int style = R.style.RedTheme;
        switch (theme) {
            case RED:
                style = R.style.RedTheme;
                break;
            case BROWN:
                style = R.style.BrownTheme;
                break;
            case BLUE:
                style = R.style.BlueTheme;
                break;
            case BLUE_GREY:
                style = R.style.BlueGreyTheme;
                break;
            case YELLOW:
                style = R.style.YellowTheme;
                break;
            case DEEP_PURPLE:
                style = R.style.DeepPurpleTheme;
                break;
            case PINK:
                style = R.style.PinkTheme;
                break;
            case GREEN:
                style = R.style.GreenTheme;
                break;
            default:
                break;
        }
        activity.setTheme(style);
    }


    public enum Theme {
        RED(0x00),
        BROWN(0x01),
        BLUE(0X02),
        BLUE_GREY(0X03),
        YELLOW(0X04),
        DEEP_PURPLE(0X05),
        PINK(0X06),
        GREEN(0X07);

        private int mValue;

        Theme(int value) {
            this.mValue = value;
        }

        public Theme getDefault() {
            return RED;
        }

        public int getIntValue() {
            return mValue;
        }

        /**
         *  根据整数映射出Theme
         *
         * @param value
         * @return Theme
         */
        public static Theme mapValue2Theme(int value) {
            for (Theme theme : Theme.values()) {
                if (theme.getIntValue() == value) {
                    return theme;
                }
            }
            return RED;
        }
    }
}
