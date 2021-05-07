package cn.ykf.mockito;

import cn.ykf.MockitoTestApplication;
import cn.ykf.service.DemoService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Mockito Demo
 *
 * @author YuKaiFan <yukf@pvc123.com>
 * @date 2021/5/7
 */
@SpringBootTest(classes = MockitoTestApplication.class)
public class MockitoTest {

    @MockBean
    private DemoService demoService;

    @Test
    void test() {
        assertTrue(mockingDetails(demoService).isMock());
    }
}
