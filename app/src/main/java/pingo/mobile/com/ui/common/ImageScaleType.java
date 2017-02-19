package pingo.mobile.com.ui.common;

import android.graphics.Matrix;
import android.graphics.Rect;

import com.facebook.drawee.drawable.ScalingUtils;

/**
 * Created by houssem.fathallah on 16/10/2016.
 */

public class ImageScaleType implements ScalingUtils.ScaleType {
    @Override
    public Matrix getTransform(Matrix outTransform, Rect parentRect, int childWidth, int childHeight, float focusX, float focusY) {
        // calculate scale; we take the smaller of the horizontal and vertical scale factor so that the image always fits
        final float scaleX = (float) parentRect.width() / (float) childWidth;
        final float scaleY = (float) parentRect.height() / (float) childHeight;
        float scale = Math.min(scaleX, scaleY);

        // calculate translation; we offset by parent bounds, and by half of the empty space
        // note that the child dimensions need to be adjusted by the scale factor
        float dx = parentRect.left + (parentRect.width() - childWidth * scale) * 0.5f;
        float dy = parentRect.top + (parentRect.height() - childHeight * scale) * 0.5f;

        // finally, set and return the transform
        outTransform.setScale(scale, scale);
        outTransform.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
        return outTransform;
    }
}
