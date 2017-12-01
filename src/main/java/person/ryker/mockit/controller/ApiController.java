package person.ryker.mockit.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import person.ryker.mockit.model.ApiInfo;
import person.ryker.mockit.model.CreateApiRequest;
import person.ryker.mockit.service.ApiService;

/**
 * @author raymondcode raymondcode@outlook.com
 * @date 2017/10/23
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ApiService apiService;

    private static final String JS_TYPE = "application/javascript";

    @ResponseBody
    @RequestMapping("/call/{key}")
    public void call(HttpServletResponse response, @PathVariable String key,
                     @RequestParam(name = "callback", required = false) String callback) throws IOException {
        ApiInfo apiInfo = apiService.callApi(key);

        response.setStatus(apiInfo.getStatus());

        if (StringUtils.isEmpty(callback)) {
            response.setContentType(apiInfo.getContentType());
            response.getOutputStream().write(apiInfo.getBody());
        } else {
            String content = new String(apiInfo.getBody());
            String result = String.format(callback + "(%s)", content);

            response.setContentType(JS_TYPE);
            response.getOutputStream().write(result.getBytes());
        }
    }

    @RequestMapping("/putFile")
    @ResponseBody
    public Object putFile(Integer status, String contentType, MultipartFile file) throws IOException {
        ApiInfo apiInfo = new ApiInfo();

        apiInfo.setStatus(status);
        apiInfo.setContentType(contentType);
        apiInfo.setBody(file.getBytes());

        String key = apiService.addApi(apiInfo);
        return "https://mockit.info/api/call/" + key;
    }

    @RequestMapping("/put")
    @ResponseBody
    public Object put(CreateApiRequest apiRequest) throws IOException {
        String key = apiService.addApi(ApiInfo.fromCreateRequest(apiRequest));

        return "https://mockit.info/api/call/" + key;
    }
}
