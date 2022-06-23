package cn.ykf.service.impl;

import cn.ykf.common.Result;
import cn.ykf.model.User;
import cn.ykf.service.UserService;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.rest.support.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YuKaiFan
 * @date 2022/6/21
 */
@Path("/users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private Map<String, User> db = new ConcurrentHashMap<>();

    /**
     * 路径为 /rest/{id}，.+ 为正则表达式
     * <p>
     * server=“tomcat"或者server=“jetty"或者server=“servlet" 时才能获取到HttpServletRequest，其他的未提供 Servlet 容器
     *
     * @param request
     * @param id
     * @return
     */
    @GET
    @Path("{id: .+}")
    @Override
    public User get(@Context HttpServletRequest request, @PathParam("id") String id) {
        // 另一种方式是通过 Dubbo 的调用上下文 RpcContext，有一定的侵入性
        // 但是使用 RpcContext 可以在 Filter 中拿到 HttpServletResponse 后做额外操作
        LOGGER.info("查询用户: {}，contextPath: {}, servletPath: {},requestUri: {}，IP: {}", id,
                request.getContextPath(), request.getServletPath(), request.getRequestURI(),
                (RpcContext.getContext().getRequest(HttpServletRequest.class)).getRemoteAddr()
        );

        return db.get(id);
    }

    @GET
    @Path("/getUser")
    @Override
    public User get(@Context HttpServletRequest request,
                    @QueryParam("name") @DefaultValue("") String name,
                    @QueryParam("age") Integer age) {

        return db.entrySet().stream().filter(userEntry -> {
            User user = userEntry.getValue();
            return Objects.equals(user.getName(), name) && Objects.equals(user.getAge(), age);
        }).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    /**
     * 无法接收多个入参，需要使用一个包含所有入参大 POJO 接收
     *
     * <a href="https://stackoverflow.com/questions/5553218/jax-rs-post-multiple-objects">jax-rs-post-multiple-objects</a>
     *
     * @param user
     * @return
     */
    @POST
    @Path("/registerUser")
    @Override
    public Result<User> registerUser(final User user) {
        LOGGER.info("注册用户: {}", user);

        if (db.computeIfAbsent(user.getId(), id -> user) != user) {
            return Result.fail("重复添加");
        }

        return Result.succ(user);
    }
}
