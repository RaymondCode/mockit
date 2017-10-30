package person.ryker.mockit.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author raymondcode raymondcode@outlook.com
 * @date 2017/10/23
 */
public class ApiInfo {
    private Integer id;

    private String key;

    private Integer status;

    private String contentType;

    private byte[] body;

    private Date createTime;

    public static ApiInfo fromCreateRequest(CreateApiRequest request) {
        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setStatus(request.getStatus());
        apiInfo.setContentType(request.getContentType() + (StringUtils.isEmpty(request.getCharset()) ? "" : (";charset=" + request.getCharset())));
        apiInfo.setBody(request.getBody());

        return apiInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
