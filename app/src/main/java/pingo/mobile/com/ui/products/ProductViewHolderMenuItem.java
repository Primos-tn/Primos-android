package pingo.mobile.com.ui.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Product;
import pingo.mobile.com.ui.brands.activities.BrandProfileActivity;
import pingo.mobile.com.ui.common.Dialogs;
import pingo.mobile.com.ui.common.activities.HomeAcctivity;
import pingo.mobile.com.ui.common.activities.HomeActivity;
import pingo.mobile.com.ui.products.activities.ContactBrandAboutProductActivity;
import pingo.mobile.com.ui.products.activities.ReportProductActivity;
import pingo.mobile.com.utils.constants.Bundles;

/**
 * Click listener for popup menu items
 */
class ProductViewHolderMenuItem implements PopupMenu.OnMenuItemClickListener {
    Context context;
    int productId;

    public ProductViewHolderMenuItem(Context context, int productId) {
        this.context = context;
        this.productId = productId;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.product_item_popup_menu_get_coupons:
                Dialogs.requireLogin(context);
                break;
            case R.id.product_item_popup_menu_contact_brand:
                openActivity(ContactBrandAboutProductActivity.class);
                break;
            case R.id.product_item_popup_menu_report:
                openActivity(ReportProductActivity.class);
                break;
        }
        return false;
    }

    private void openActivity(Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        Bundle mBundle = new Bundle();
        mBundle.putInt(Bundles.OPENED_POPUP_MENU_PRODUCT_ID, productId);
        intent.putExtras(mBundle);
        context.startActivity(intent);
    }
}