package cn.ykf.extension;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * @author YuKaiFan
 * @date 2022/6/22
 */
public class CacheControlFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
        if (req.getMethod().equals("GET")) {
            res.getHeaders().add("Cache-Control", "someValue");
        }
    }
}
