package com.kuo.cooldatepicker_library.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuo.cooldatepicker_library.R;
import com.kuo.cooldatepicker_library.adapter.RecyclerViewAdapter;
import com.kuo.cooldatepicker_library.item.CalendarItem;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by User on 2015/10/4.
 */
public class CoolDatePicker extends LinearLayout {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<CalendarItem> calendarItems = new ArrayList<>();

    private LinearLayout titleLinearLayout, titleTextLinearLayout;
    private TextView textMonth, textYear, textDay, textDayOfWeek;

    private OnDateChangedListener onDateChangedListener;

    private static int calendarViewTextSize = 70;
    private static int calendarViewTextColor = Color.parseColor("#424242");
    private static int calendarViewTextMonthColor = Color.parseColor("#2800bcd4");
    private static int calendarViewTextFoucsColor = Color.parseColor("#FAFAFA");
    private static int calendarViewCircleFoucsColor = Color.parseColor("#00BCD4");
    private static int calendarViewBackground = Color.parseColor("#0000E4FF");

    private static int titleTextColor = Color.parseColor("#FAFAFA");
    private static int titleBackgroundColor = Color.parseColor("#00BCD4");

    public interface OnDateChangedListener {
        void onDateChanged(int year, int month, int day, String subText);
    }

    public CoolDatePicker(Context context) {
        super(context);

        initPicker();
    }

    public CoolDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        initStylealbe(context, attrs);
        initPicker();
    }

    private void initStylealbe(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CoolDatePicker);

        calendarViewTextSize = typedArray.getInteger(R.styleable.CoolDatePicker_calendar_textSize, 70);
        calendarViewTextColor = typedArray.getColor(R.styleable.CoolDatePicker_calendar_textColor, Color.parseColor("#424242"));
        calendarViewTextMonthColor = typedArray.getColor(R.styleable.CoolDatePicker_calendar_textMonthColor, Color.parseColor("#2800bcd4"));
        calendarViewTextFoucsColor = typedArray.getColor(R.styleable.CoolDatePicker_calendar_textFoucsColor, Color.parseColor("#FAFAFA"));
        calendarViewCircleFoucsColor = typedArray.getColor(R.styleable.CoolDatePicker_calendar_circleFoucsColor, Color.parseColor("#00BCD4"));
        calendarViewBackground = typedArray.getColor(R.styleable.CoolDatePicker_calendar_backgroundColor, Color.parseColor("#0000E4FF"));

    }

    private void initPicker() {

        this.setOrientation(VERTICAL);
        onAddTitleLayout();
        onAddRecyclerView();

    }

    private void onAddRecyclerView() {

        ViewGroup.LayoutParams recyclerLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(recyclerLayoutParams);

        linearLayoutManager = new LinearLayoutManager(getContext());

        onInitCalendarItem();

        this.addView(recyclerView);
    }

    private void onAddTitleLayout() {

        LinearLayout.LayoutParams titleLinearLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        titleTextLinearLayout = new LinearLayout(getContext());
        titleLinearLayout = new LinearLayout(getContext());
        textYear = new TextView(getContext());
        textMonth = new TextView(getContext());
        textDay = new TextView(getContext());
        textDayOfWeek = new TextView(getContext());

        titleTextLinearLayout.setLayoutParams(titleLinearLayoutParams);
        titleTextLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        titleTextLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        textDay.setLayoutParams(textLayoutParams);
        textDay.setTextSize(60);
        textDay.setText("4");
        textDay.setTextColor(titleTextColor);

        textDayOfWeek.setLayoutParams(textLayoutParams);
        textDayOfWeek.setTextSize(30);
        textDayOfWeek.setText("星期日");
        textDayOfWeek.setTextColor(titleTextColor);

        titleTextLinearLayout.addView(textDay);
        titleTextLinearLayout.addView(textDayOfWeek);

        titleLinearLayout.setLayoutParams(titleLinearLayoutParams);
        titleLinearLayout.setOrientation(LinearLayout.VERTICAL);
        titleLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        textMonth.setLayoutParams(textLayoutParams);
        textMonth.setTextSize(40);
        textMonth.setText("十月");
        textMonth.setTextColor(titleTextColor);

        textYear.setLayoutParams(textLayoutParams);
        textYear.setTextSize(40);
        textYear.setText("2015");
        textYear.setTextColor(titleTextColor);

        titleLinearLayout.setBackgroundColor(titleBackgroundColor);
        titleLinearLayout.addView(textMonth);
        titleLinearLayout.addView(titleTextLinearLayout);
        titleLinearLayout.addView(textYear);

        this.addView(titleLinearLayout);
    }

    private void onInitCalendarItem() {

        for(int i = 0 ; i < 12 ; i++) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(Calendar.YEAR), (i+1), 0);

            CalendarView calendarView = new CalendarView(getContext());
            calendarView.setCalendar(calendar);
            calendarView.setBackgroundColor(calendarViewBackground);
            calendarView.setTextSize(calendarViewTextSize);
            calendarView.setTextColor(calendarViewTextColor);
            calendarView.setTextFoucsColor(calendarViewTextFoucsColor);
            calendarView.setTextMonthColor(calendarViewTextMonthColor);
            calendarView.setCircleFoucsColor(calendarViewCircleFoucsColor);

            CalendarItem calendarItem = new CalendarItem();
            calendarItem.setCalendar(calendar);
            calendarItem.setCalendarView(calendarView);
            calendarItems.add(calendarItem);

        }

        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), calendarItems);
        recyclerViewAdapter.setOnDateChangeListener(onDateChangeListener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        Calendar calendar = Calendar.getInstance();
        recyclerView.scrollToPosition(calendar.get(Calendar.MONTH));

    }

    private RecyclerViewAdapter.OnDateChangeListener onDateChangeListener = new RecyclerViewAdapter.OnDateChangeListener() {
        @Override
        public void onDateChange(int year, int month, int day, String subText) {

            textYear.setText("" + year);
            textMonth.setText("" + month + " Month");
            textDay.setText("" + day);
            textDayOfWeek.setText("星期" + subText);

            if(onDateChangedListener != null) {
                onDateChangedListener.onDateChanged(year, month, day, subText);
            }

        }
    };

    public void setOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        this.onDateChangedListener = onDateChangedListener;
    }

    public void setTitleBackgroundColor(int color) {
        titleLinearLayout.setBackgroundColor(color);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setTitleBackground(Drawable drawable) {
        titleLinearLayout.setBackground(drawable);
    }

    public void setTitleBackgroundResource(int resId) {
        titleLinearLayout.setBackgroundResource(resId);
    }

    public void setTitleTextYearSize(int size) {
        textYear.setTextSize(size);
    }

    public void setTitleTextMonthSize(int size) {
        textMonth.setTextSize(size);
    }

    public void setTitleTextDaySize(int size) {
        textDay.setTextSize(size);
    }

    public void setTitleTextDayOfWeekSize(int size) {
        textDayOfWeek.setTextSize(size);
    }

    public void setTitleTextYearColor(int color) {
        textYear.setTextColor(color);
    }

    public void setTitleTextMonthColor(int color) {
        textMonth.setTextColor(color);
    }

    public void setTitleTextDayColor(int color) {
        textDay.setTextColor(color);
    }

    public void setTitleTextDayOfWeekColor(int color) {
        textDayOfWeek.setTextColor(color);
    }

}
