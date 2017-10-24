package person.ryker.mockit.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import person.ryker.mockit.mapper.ApiInfoMapper;
import person.ryker.mockit.model.ApiInfo;

/**
 * @author raymondcode raymondcode@outlook.com
 * @date 2017/10/24
 */
@Service
public class ApiService {

    @Resource
    private ApiInfoMapper apiInfoMapper;

    public ApiInfo callApi(String key) {
        return apiInfoMapper.getByKey(key);
    }

    public String addApi(ApiInfo apiInfo) {
        String key = RandomStringUtils.randomAlphanumeric(16);
        apiInfo.setKey(key);

        apiInfoMapper.insert(apiInfo);

        return key;
    }
}
