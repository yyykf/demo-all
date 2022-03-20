package cn.ykf.principle.srp.better;

import static org.junit.jupiter.api.Assertions.*;

import cn.ykf.principle.srp.better.impl.VipVideoUserService;
import org.junit.jupiter.api.Test;

class VideoUserServiceTest {

    @Test
    void definition() {
        new VipVideoUserService().definition();
    }

    @Test
    void advertisement() {
        new VipVideoUserService().advertisement();
    }
}