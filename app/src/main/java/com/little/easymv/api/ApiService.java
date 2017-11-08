package com.little.easymv.api;


import com.little.easymv.responsebean.AuthorDesResponse;
import com.little.easymv.responsebean.CategoryResponse;
import com.little.easymv.responsebean.ClassifyResponse;
import com.little.easymv.responsebean.ComicPageResponse;
import com.little.easymv.responsebean.ComicResponse1;
import com.little.easymv.responsebean.HotResponse;
import com.little.easymv.responsebean.RecommendResponse;
import com.little.easymv.responsebean.SearchResponse;
import com.little.easymv.responsebean.SubjectDesResponse;
import com.little.easymv.responsebean.SubjectResopnse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/13.
 */
//http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
public interface ApiService {

    @Headers("Referer:http://images.dmzj.com/")
    @GET("recommend.json")
    Observable<List<RecommendResponse>> getRecommend();


    @Headers("Referer:http://images.dmzj.com/")
    @GET("{url}")
    Observable<ComicResponse1> getComic(@Path("url") String url);

    @Headers("Referer:http://images.dmzj.com/")
    @GET("0/category.json")
    Observable<List<CategoryResponse>> getCategory();


    @Headers("Referer:http://images.dmzj.com/")
    @GET("{url}")
    Observable<ComicPageResponse> getChapter(@Path("url") String url);


    @Headers("Referer:http://images.dmzj.com/")
    @GET("classify/{tag_id}/0/{page}.json")
    Observable<List<ClassifyResponse>> getClassify(@Path("tag_id") int tagid,@Path("page")int page);

//    @Headers("Referer:http://images.dmzj.com/")
    @GET("{url}")
    Observable<List<ClassifyResponse>> getClassify(@Path("url") String url);

    @Headers("Referer:http://images.dmzj.com/")
    @GET("search/show/0/{keyword}/0.json")
    Observable<List<SearchResponse>> getSearch(@Path(value="keyword", encoded=true) String keyword);    //encoded 解决传入参数乱码问题

    @Headers("Referer:http://images.dmzj.com/")
    @GET("{url}")
    Observable<List<HotResponse>> getHot(@Path("url") String url);

    @GET("/unableape/url/{name}/get")
    Observable getUnableApeName(@Header("Authorization") String authorization, @Path("name") String name);

    //http://v2.api.dmzj.com/UCenter/comics/100085704.json
    @Headers("Referer:http://images.dmzj.com/")
    @GET("{url}")
    Observable<AuthorDesResponse> getAuthorDes(@Path("url") String url);

    @Headers("Referer:http://images.dmzj.com/")
    @GET("{url}")
    Observable<List<SubjectResopnse>> getSubject(@Path("url") String ur);

    //http://v2.api.dmzj.com/subject/172.json
    @GET("{url}")
    Observable<SubjectDesResponse> getSubjectDes(@Path("url") String url);


    /* 用法:
     Observable<SimpleResponse<List<RecommendResponse>>> getRecommend();

    Api.getDefault(HostType.KaBu).getRecommend()
                .compose(RxHelper.<List<RecommendResponse>>handleErrTransformer())
            .subscribe(new BaseSubscriber<List<RecommendResponse>>() {
        @Override
        protected void _onNext(List<RecommendResponse> recommendResponses) {

        }
    }))*/





}

