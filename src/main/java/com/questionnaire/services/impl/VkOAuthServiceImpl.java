package com.questionnaire.services.impl;

import com.questionnaire.services.VkOAuthService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.stereotype.Service;

/**
 * Created by Igor on 06.12.2016.
 */
@Service
public class VkOAuthServiceImpl implements VkOAuthService {

    @Override
    public VkApiClient getApiClient() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        return vk;
    }
}
