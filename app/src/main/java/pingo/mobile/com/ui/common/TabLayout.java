/**
 * File SlidingTabLayout
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * To implement sliding tabs in material design style
 */
package pingo.mobile.com.ui.common;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pingo.mobile.com.R;


public class TabLayout extends HorizontalScrollView {
    /**
     *
     */
    public static final int ICON_MODE = 2;
    /**
     *
     */
    public static final int TEXT_MODE = 1;

    /**
     *
     */
    public static final int TEXT_ICON_MODE = 3;

    /**
     * Allows complete control over the colors drawn in the tab layout. Set with
     * {@link #setCustomTabColorizer(TabColorizer)}.
     */
    public interface TabColorizer {

        /**
         * @return return the color of the indicator used when {@code position} is selected.
         */
        int getIndicatorColor(int position);

    }

    private static final int TITLE_OFFSET_DIPS = 5;
    private static final int TAB_VIEW_PADDING_DIPS = 2;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;

    private int titleOffset;

    private int tabViewLayoutId;
    private int tabViewTextViewId;
    private boolean distributeEvenly;

    private ViewPager viewPager;
    private SparseArray<String> contentDescriptions = new SparseArray<String>();
    private ViewPager.OnPageChangeListener viewPagerPageChangeListener;

    private final SlidingTabStrip slidingTabStrip;

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        /**
         * Disable the Scroll Bar
         */
        setHorizontalScrollBarEnabled(false);
        /**
         * Make sure that the Tab Strips fills this View
         */
        setFillViewport(true);

        titleOffset = (int) (TITLE_OFFSET_DIPS * getResources().getDisplayMetrics().density);

        slidingTabStrip = new SlidingTabStrip(context);
        addView(slidingTabStrip, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * Set the custom {@link TabColorizer} to be used.
     * <p>
     * If you only require simple custmisation then you can use
     * {@link #setSelectedIndicatorColors(int...)} to achieve
     * similar effects.
     */
    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        slidingTabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setDistributeEvenly(boolean distributeEvenly) {
        this.distributeEvenly = distributeEvenly;
    }

    /**
     * Sets the colors to be used for indicating the selected tab. These colors are treated as a
     * circular array. Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setSelectedIndicatorColors(int... colors) {
        slidingTabStrip.setSelectedIndicatorColors(colors);
    }

    /**
     * Set the {@link ViewPager.OnPageChangeListener}. When using {@link TabLayout} you are
     * required to set any {@link ViewPager.OnPageChangeListener} through this method. This is so
     * that the layout can update it's scroll position correctly.
     *
     * @see ViewPager#setOnPageChangeListener(ViewPager.OnPageChangeListener)
     */
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        viewPagerPageChangeListener = listener;
    }

    /**
     * Set the custom layout to be inflated for the tab views.
     *
     * @param layoutResId Layout id to be inflated
     * @param textViewId  id of the {@link TextView} in the inflated view
     */
    public void setCustomTabView(int layoutResId, int textViewId) {
        tabViewLayoutId = layoutResId;
        tabViewTextViewId = textViewId;
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void setViewPager(ViewPager viewPager, int mode) {
        slidingTabStrip.removeAllViews();
        this.viewPager = viewPager;
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip(mode);
        }
    }


    /**
     * @param adapter
     * @param clickListener
     */
    void setTextIconMode(BasePagerAdapter adapter, OnClickListener clickListener) {
        TextView textView;
        LinearLayout linearLayout;
        Context context = getContext();
        int width = (int) (getResources().getDisplayMetrics().widthPixels / viewPager.getAdapter().getCount());
        ImageView tabIconView = null;
        for (int i = 0; i < adapter.getCount(); i++) {
            linearLayout = new LinearLayout(context);
            textView = new TextView(context);
            textView.setText(adapter.getPageTitle(i));
            textView.setPadding(10, 10, 10, 10);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(context.getResources().getColorStateList(R.color.tabs, context.getTheme()));
            } else {
                textView.setTextColor(context.getResources().getColorStateList(R.color.tabs));
            }
            if (viewPager.getCurrentItem() == i) {
                textView.setSelected(true);
            }
            textView.setAllCaps(true);
            linearLayout.addView(textView);
            tabIconView = createDefaultImageView(getContext());
            if (viewPager.getCurrentItem() == i) {
                tabIconView.setImageDrawable(getResources().getDrawable(adapter.getDrawableId(i)));
            } else {
                tabIconView.setImageDrawable(getResources().getDrawable(adapter.getDrawableOffId(i)));
            }
            linearLayout.addView(tabIconView);
            linearLayout.setOnClickListener(clickListener);
            linearLayout.setMinimumWidth(width);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            params.height = LayoutParams.MATCH_PARENT;
            params.width = width ;
            params.gravity = Gravity.CENTER;
            linearLayout.setLayoutParams(params);
            slidingTabStrip.addView(linearLayout);
        }
    }

    /**
     * @param adapter
     * @param clickListener
     */
    void setTextMode(BasePagerAdapter adapter, OnClickListener clickListener) {
        TextView textView;
        Context context = getContext();
        for (int i = 0; i < adapter.getCount(); i++) {
            textView = new TextView(context);
            textView.setText(adapter.getPageTitle(i));
            textView.setPadding(50, 40, 30, 60);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(context.getResources().getColorStateList(R.color.tabs, context.getTheme()));
            } else {
                textView.setTextColor(context.getResources().getColorStateList(R.color.tabs));
            }
            if (viewPager.getCurrentItem() == i) {
                textView.setSelected(true);
            }
            textView.setAllCaps(true);
            textView.setOnClickListener(clickListener);
            slidingTabStrip.addView(textView);
        }
    }

    /**
     * @param adapter
     * @param clickListener
     */
    void setIconMode(BasePagerAdapter adapter, OnClickListener clickListener) {
        ImageView tabIconView = null;
        for (int i = 0; i < adapter.getCount(); i++) {
            tabIconView = createSingleImageView(getContext());
            tabIconView.setImageDrawable(getResources().getDrawable(adapter.getDrawableId(i)));
            if (viewPager.getCurrentItem() == i) {
                tabIconView.setSelected(true);
            }
            tabIconView.setOnClickListener(clickListener);
            slidingTabStrip.addView(tabIconView);
        }
    }

    /**
     * populateTabStrip() This is the method that sets icons into tabs
     */
    private void populateTabStrip(int mode) {
        final BasePagerAdapter adapter = (BasePagerAdapter) viewPager.getAdapter();
        final View.OnClickListener tabClickListener = new TabClickListener();
        switch (mode) {
            case 3:
                setTextIconMode(adapter, tabClickListener);
                break;
            case 2:
                setIconMode(adapter, tabClickListener);
                break;
            case 1:
                setTextMode(adapter, tabClickListener);
                break;
        }
    }

    /**
     * Create imageView to inject into tabs header
     *
     * @return an imageView
     */
    protected ImageView createDefaultImageView(Context context) {
        ImageView imageView = new ImageView(context);
        int padding = (int) (TAB_VIEW_PADDING_DIPS * getResources().getDisplayMetrics().density);
        imageView.setPadding(padding, padding, padding, padding);
        return imageView;
    }

    /**
     * Create imageView to inject into tabs header
     *
     * @return an imageView
     */
    protected ImageView createSingleImageView(Context context) {
        ImageView imageView = new ImageView(context);
        int padding = (int) (TAB_VIEW_PADDING_DIPS * getResources().getDisplayMetrics().density);
        imageView.setPadding(padding, padding, padding, padding);

        int width = (int) (getResources().getDisplayMetrics().widthPixels / viewPager.getAdapter().getCount());
        imageView.setMinimumWidth(width);
        return imageView;
    }

    /**
     * Called when the view is attached to a window
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (viewPager != null) {
            scrollToTab(viewPager.getCurrentItem(), 0);
        }
    }

    /**
     * Scrolls to a particular tab if tab scrolling is enabled
     *
     * @param tabIndex       index to scroll
     * @param positionOffset
     */
    private void scrollToTab(int tabIndex, int positionOffset) {
        final int tabStripChildCount = slidingTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }
        View selectedChild = slidingTabStrip.getChildAt(tabIndex);
        if (selectedChild != null) {
            int targetScrollX = selectedChild.getLeft() + positionOffset;
            /**
             *   If we're not at the first child and are mid-scroll, make sure we obey the offset
             */
            if (tabIndex > 0 || positionOffset > 0) {

                targetScrollX -= titleOffset;
            }

            scrollTo(targetScrollX, 0);
        }
    }


    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        /**
         * This method will be invoked when the current page is scrolled
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = slidingTabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }

            slidingTabStrip.onViewPagerPageChanged(position, positionOffset);

            View selectedTitle = slidingTabStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null)
                    ? (int) (positionOffset * selectedTitle.getWidth())
                    : 0;
            scrollToTab(position, extraOffset);

            if (viewPagerPageChangeListener != null) {
                viewPagerPageChangeListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        }

        /**
         * Called when the scroll state changes
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;

            if (viewPagerPageChangeListener != null) {
                viewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        /**
         * Called when select on page
         */
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < slidingTabStrip.getChildCount(); i++) {
                slidingTabStrip.getChildAt(i).setSelected(false);
            }
            slidingTabStrip.getChildAt(position).setSelected(true);

            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                slidingTabStrip.onViewPagerPageChanged(position, 0f);
                scrollToTab(position, 0);
            }

            if (viewPagerPageChangeListener != null) {
                viewPagerPageChangeListener.onPageSelected(position);
            }
        }

    }

    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     */
    private class TabClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < slidingTabStrip.getChildCount(); i++) {
                if (v == slidingTabStrip.getChildAt(i)) {
                    viewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }

}