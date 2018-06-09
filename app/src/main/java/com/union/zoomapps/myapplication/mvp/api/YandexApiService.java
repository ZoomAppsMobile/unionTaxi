package com.union.zoomapps.myapplication.mvp.api;

import com.union.zoomapps.myapplication.mvp.models.ModelYandex;
import com.union.zoomapps.myapplication.mvp.models.YandexJson;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Asus on 02.03.2018.
 */

public interface YandexApiService {
    @Headers({
            "Content-Type: application/json",
            "Cookie: mda=0; yandexuid=5092728091515342107; my=YwA=; _ym_uid=1515390413833114005; fuid01=5a5305cc1cacc620.umWSHpPC2SVePfgnKrdZ168T4kZXerzxW-hrrsBSCg3qygNeG-Lo_dy4QaoFZykG2P3KjIfxO6Js1fowg0wj8G7XJuCJdSVwZGUXdsxBa93mHTxlTbmAFExhZ95-AWAq; _id=8c686cd308e94f5ca3694395241cd205; _id=8c686cd308e94f5ca3694395241cd205; L=YG9JQUB6Rlx7YmFid3Z1Xl9UUHp8ck8GBiUdRzgtSjQACAIWAAAE.1525498150.13490.363000.5b1ba806bc2aa7a76500642921755aef; yandex_login=kostya.sherba97; yandex_gid=162; i=kquFCWdPpjVQSVQdP4HR/Ijss1WJ7hrdPHht7QuY90nMgEJ2XGUWm36lJhEDiGWr1lGA1ArsqAlZA8D7w7Qpk2trMyI=; zm=m-white_bender.webp.css-https-www%3Awww_hyj9UI8GqACW69FAmYRm17Keflo%3Al; yabs-frequency=/4/10000FKq_Le00000/P7EoRTWl805Fi73PBy01OTqsRTalu0FuIR1msIz00I1Di73OBq3xIR1msIz00OzFi73OBo2mIh1msI-00m00/; yc=1527155892.zen.cach%3A1526900285; ys=mclid.2256436-306; Session_id=3:1527780993.5.0.1515390418233:EqmrHw:4c.1|363943148.0.2|496185649.796546.2.2:3733400|638105314.8545558.2.2:8545558|638274994.8573139.2.2:8573139|5:138255.227135.paTYyZNWYEg2OpCJsqCh-BtoCoM; sessionid2=3:1527780993.5.0.1515390418233:EqmrHw:4c.1|363943148.0.2|496185649.-1.0|638105314.-1.0|638274994.-1.0|5:138255.789516.EpB3AsrxeywOYClzHWSKP_EO_Ns; _ym_isad=2; _ym_wasSynced=%7B%22time%22%3A1527781032710%2C%22params%22%3A%7B%22webvisor%22%3A%7B%22date%22%3A%222011-10-31%2016%3A20%3A50%22%7D%2C%22eu%22%3A0%7D%2C%22bkParams%22%3A%7B%7D%7D; yp=1550227818.as.1#1528038610.clh.2256436-306#1542664689.szm.1_25:1536x864:1536x710#1834481508.udn.cDp6YWthenNhaXRvdi5reg%3D%3D#1531300565.ww.1#1527868327.yu.5092728091515342107"
    })
    @POST("3.0/routestats/")
    Observable<ModelYandex> getYandexPrice(@Body YandexJson json);

}
