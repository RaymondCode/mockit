package person.ryker.mockit.model;

import java.io.Serializable;

public class CreateApiRequest implements Serializable {

    private static final long serialVersionUID = -4871919826259024845L;

    private Integer status;

    private String contentType;

    private String charset;

    private byte[] body;

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

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

}
