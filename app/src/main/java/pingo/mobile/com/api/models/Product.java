
package pingo.mobile.com.api.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import pingo.mobile.com.api.models.brands.BrandShortInfo;

public class Product {
    @SerializedName("info")
    private ProductInfo productInfo;
    @SerializedName("brand")
    private BrandShortInfo brandShortInfo;
    private List<Picture> pictures;
    private int id;
    @SerializedName("is_voted")
    private boolean isVoted;
    /*@SerializedName("stores")
    private List<Location> locations;*/

    /**
     * @return
     */
    public ProductInfo getProductInfo() {
        return productInfo;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @return
     */
    public BrandShortInfo getBrandShortInfo() {
        return brandShortInfo;
    }


    /**
     * @return
     */
    public boolean isVoted() {
        return isVoted;
    }

    /**
     * @return
     */
    public List<Picture> getPictures() {
        return pictures;
    }

    /**
     * @return
     */
    public boolean isInCollection() {
        return true;
    }

    /**
     *
     */
    public class ProductInfo {
        /**
         * "id": 7,
         * "name": " 3 Test",
         * "brand_id": 1,
         * "created_at": "2017-01-03T09:18:15.333Z",
         * "updated_at": "2017-02-18T15:37:59.440Z",
         * "last_launch": "2017-02-18T00:00:00.000Z",
         * "user_product_views_count": 0,
         * "user_product_wishes_count": 0,
         * "available_in_count": 0,
         * "comments_count": 0,
         * "properties": "faa09873-4d7d-4f7b-a795-0ebd6a4d4278.jpeg",
         * "coupons_counts": 1,
         * "old_price": 30,
         * "new_price": 20,
         * "currrency": null,
         * "votes_count": 0
         */
        private String name;
        @SerializedName("brand_id")
        private int brandId;
        @SerializedName("available_in_count")
        private int availableCount;
        @SerializedName("coupons_counts")
        private int couponsCounts;
        @SerializedName("votes_count")
        private int votesCount;
        @SerializedName("new_price")
        private float newPrice;
        @SerializedName("old_price")
        private float oldPrice;

        /**
         * Returns the old item price
         *
         * @return
         */
        public float getOldPrice() {
            return oldPrice;
        }

        /**
         * Returns the old item price
         *
         * @return
         */
        public float getPromotion() {
            if (newPrice != 0) {
                return oldPrice / newPrice;
            }
            return 0;

        }

        public float getNewPrice() {
            return newPrice;
        }

        public int getVotesCount() {
            return votesCount;
        }

        public int getCouponsCounts() {
            return couponsCounts;
        }

        /**
         *
         * @return
         */
        public int getStoresCount() {
            return 2;
        }

        /**
         *
         * @return
         */
        public int getAvailableCount() {
            return availableCount;
        }

        public int getBrandId() {
            return brandId;
        }

        public String getName() {
            return name;
        }

        public Boolean inCollection() {
            return true;
        }
    }
}

