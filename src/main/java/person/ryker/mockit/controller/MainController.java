package person.ryker.mockit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.*;

/**
 * @author raymondcode raymondcode@outlook.com
 * @date 2017/10/23
 */
@Controller
public class MainController {

    private static final List<String> TYPES = Arrays.asList(APPLICATION_JSON_VALUE, APPLICATION_FORM_URLENCODED_VALUE, TEXT_PLAIN_VALUE, MULTIPART_FORM_DATA_VALUE);

    private static final List<String> CHARSETS = Arrays.asList("UTF-8", "ISO-8859-1", "UTF-16");

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("httpStatus", HttpStatus.values());
        model.put("types", TYPES);
        model.put("charsets", CHARSETS);

        return "index";
    }
}
