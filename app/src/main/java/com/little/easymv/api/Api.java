package com.little.easymv.api;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.SparseArray;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.NetWorkUtils;
import com.little.easymv.app.MyApplication;
import com.little.easymv.constants.UrlConstants;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * des:retorfit api
 * Created by Littlezuo
 * on 2016.06.15:47
 */
public class Api {
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;
    private final int mHostType;

    //    public Retrofit retrofit;
    public ApiService movieService;

    private static SparseArray<Api> sRetrofitManager = new SparseArray<>();

    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";
    private SSLContext sslContext;
    private final Retrofit.Builder mRetrofitBuilder;


    //构造方法私有

    //构造方法私有
    private Api(int hostType) {
        mHostType = hostType;
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(MyApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
                //增加头部信息
                Interceptor headerInterceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request build = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .removeHeader("User-Agent")
                                .addHeader("User-Agent", UrlConstants.COMMON_UA_STR)
                                .build();
                        return chain.proceed(build);
                    }
                };

//                BasicParamsInterceptor basicParamsInterceptor =
//                        new BasicParamsInterceptor.Builder()
//                                //                        .addParamsMap(RequestUtil.getDeviceInfo())
//                                .addParamsMap(RequestUtil.getCommonParams())
//                                .build();


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        overlockCard();
//        if (sslContext != null) {
//            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//            builder.sslSocketFactory(sslSocketFactory);
//
//        }

//        builder.hostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });


        OkHttpClient okHttpClient = builder
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(mParamsInterceptor)//添加公共参数
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .cache(cache)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        //        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").serializeNulls().create();
        mRetrofitBuilder = new Retrofit.Builder();
        Retrofit retrofit = mRetrofitBuilder
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HostConstants.getHost(hostType))
                .build();

        movieService = retrofit.create(ApiService.class);
    }



    //忽略证书
    private void overlockCard() {
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(
                            X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        X509Certificate[] x509Certificates = new X509Certificate[0];
                        return x509Certificates;
                    }
                }};
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (Exception e) {
            LogUtils.loge("ssl出现异常");
        }
    }

    /**
     * @param hostType
     */
    public static ApiService getDefault(int hostType) {
        Api retrofitManager = sRetrofitManager.get(hostType);

        if (retrofitManager == null) {
            retrofitManager = new Api(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.movieService;
    }

    public static ApiService getDefault() {
        return getDefault(HostType.KaBu);
    }

    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    public static String getCacheControl() {
        return NetWorkUtils.isNetConnected(MyApplication.getAppContext()) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();  //获取请求api里的@header的cacheControl ----->@Header("Cache-Control") String cacheControl,
            CacheControl requestCacheControl1 = TextUtils.isEmpty(cacheControl) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE;
            if (!NetWorkUtils.isNetConnected(MyApplication.getAppContext())) {//当前没有网络的请求,才设置cacheControl
                request = request.newBuilder()
                        .cacheControl(requestCacheControl1) //这里如果设置CacheControl.FORCE_CACHE有缓存, 读取缓存成功后直接走onNext回调
                        .build();                             //post请求设置CacheControl.FORCE_CACHE也没有缓存 ,走onError回调
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(MyApplication.getAppContext())) {
                return originalResponse.newBuilder()              //有网络
                        .header("Cache-Control", cacheControl)   //在@header里面配置的cacheControl传到这里了,判断是否需要请求访问服务器,
                        .removeHeader("Pragma")                  //max-age=5 表示当访问此网页后的5秒内再次访问不会去服务器, max-age=0 每次都重新访问服务器 ,走onNext回调
                        .build();                                //无论距离上次请求是否超过max-age 都不会走onError回调
            } else {
                return originalResponse.newBuilder()                                                        //没有网络
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)  //采用GET请求,如果请求在距离上次CACHE_STALE_SEC时间的范围内,则走onNext方法,展示数据
                        .removeHeader("Pragma")                                                           //如果超出时间 ,则走onError方法(没有验证)
                        .build();                                                                         //post没有缓存,没有网络直接走onError
            }

        }
    };

//    private final Interceptor mParamsInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            //            Log.e("kkkk", request.url().toString() +"");
//            Request.Builder requestBuilder = request.newBuilder();
//            FormBody.Builder postData = new FormBody.Builder();
//
//            RequestBody body = request.body();
//            if (body instanceof FormBody) {
//                FormBody oldFormBody = (FormBody) body;
//                //                Map<String, Object> toSignParams = new HashMap<>();
//                for (int i = 0; i < oldFormBody.size(); i++) {
//                    String key = oldFormBody.encodedName(i);
//                    String value = oldFormBody.encodedValue(i);
//                    //                    oldBody.write2file(key,value);
//                    String decode = URLDecoder.decode(value, "UTF-8");
//                    postData.add(key, decode);
//                }
//            }
//            Map<String, String> commParams = RequestUtil.getCommonParams();
//            for (Map.Entry<String, String> entery : commParams.entrySet()) {
//                postData.add(entery.getKey(), entery.getValue());
//            }
//            requestBuilder.method(request.method(), postData.build());
//
//            request = requestBuilder.build();
//            return chain.proceed(request);
//        }
//    };

}