package person.ryker.mockit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import person.ryker.mockit.model.ApiInfo;
import person.ryker.mockit.model.CreateApiRequest;
import person.ryker.mockit.service.ApiService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raymondcode raymondcode@outlook.com
 * @date 2017/10/23
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ApiService apiService;

    @RequestMapping("/call/{key}")
    public void call(HttpServletResponse response, @PathVariable String key)
            throws IOException {
        ApiInfo apiInfo = apiService.callApi(key);

        response.setStatus(apiInfo.getStatus());
        response.setContentType(apiInfo.getContentType());
        response.getOutputStream().write(apiInfo.getBody());
    }

    @RequestMapping("/putFile")
    @ResponseBody
    public Object putFile(HttpServletRequest request, Integer status, String contentType, MultipartFile file)
            throws IOException {
        ApiInfo apiInfo = new ApiInfo();

        apiInfo.setStatus(status);
        apiInfo.setContentType(contentType);
        apiInfo.setBody(file.getBytes());

        String rootPath = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        String key = apiService.addApi(apiInfo);
        return rootPath + "/api/call/" + key;
    }

    @RequestMapping("/put")
    @ResponseBody
    public Object put(HttpServletRequest request, CreateApiRequest apiRequest) throws IOException {
        String rootPath = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        String key = apiService.addApi(ApiInfo.fromCreateRequest(apiRequest));

        return rootPath + "/api/call/" + key;
    }
}
