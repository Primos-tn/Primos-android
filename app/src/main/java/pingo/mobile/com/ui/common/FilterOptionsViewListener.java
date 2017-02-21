package pingo.mobile.com.ui.common;

import pingo.mobile.com.api.models.SearchParamsOptions;

/**
 * Created by houssem.fathallah on 24/09/2016.
 */
public interface FilterOptionsViewListener {
    /**
     * Called when a filter options has been update
     *
     * @param filterOptions
     */
    public abstract void onFilterChanged(SearchParamsOptions filterOptions);

    public abstract void onFilterReset();



}