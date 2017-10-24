package person.ryker.mockit.mapper;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import person.ryker.mockit.MockitApplication;
import person.ryker.mockit.model.ApiInfo;

/**
 * @author raymondcode raymondcode@outlook.com
 * @date 2017/10/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockitApplication.class)
@Transactional
public class ApiInfoMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiInfoMapperTest.class);

    @Resource
    private ApiInfoMapper apiInfoMapper;

    @Test
    public void insert() throws Exception {
        String key = RandomStringUtils.randomAlphanumeric(16);

        LOGGER.info("Generate Api Key={}", key);

        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setKey(key);
        apiInfo.setStatus(HttpStatus.OK.value());
        apiInfo.setContentType(MediaType.TEXT_PLAIN_VALUE);
        apiInfoMapper.insert(apiInfo);
    }

    @Test
    public void getByKey() throws Exception {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        ApiInfo apiInfo = apiInfoMapper.getByKey("1234");
        LOGGER.info(objectMapper.writeValueAsString(apiInfo));
    }
}