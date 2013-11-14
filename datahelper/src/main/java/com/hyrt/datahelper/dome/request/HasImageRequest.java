package com.hyrt.datahelper.dome.request;

import com.hyrt.datahelper.dome.model.ListHasImageModel;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Created by GYH on 13-10-28.
 */
public class HasImageRequest extends SpringAndroidSpiceRequest<ListHasImageModel> {

    private String keyword;

    public HasImageRequest(String keyword) {
        super(ListHasImageModel.class);
        this.keyword = keyword;
    }

    @Override
    public ListHasImageModel loadDataFromNetwork() throws Exception {
//		Uri.Builder uriBuilder = Uri.parse(
//				"http://search.twitter.com/search.json").buildUpon();
//		uriBuilder.appendQueryParameter("q", keyword);
//		uriBuilder.appendQueryParameter("rpp", "20");
//		uriBuilder.appendQueryParameter("lang", "en");

//		String url = uriBuilder.build().toString();
        String url =  "https://api.github.com/legacy/user/search/:" + keyword;
        return getRestTemplate().getForObject( url, ListHasImageModel.class );
    }
    /**
     * This method generates a unique cache key for this request. In this case our cache key depends just on the
     * keyword.
     *
     * @return
     */
    public String createCacheKey() {
        return "school_case" + keyword;
    }
}
