package com.qa.framework.api;

import com.qa.framework.utils.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApiService {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigManager.getApiUrl())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
}
