package cn.ykf.mockito;

import cn.ykf.MockitoTestApplication;
import cn.ykf.service.DemoService;
import cn.ykf.subject.VerifySubject;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

    @BeforeEach
    void assertMock() {
        assertTrue(mockingDetails(demoService).isMock());
    }

    /**
     * @see <a href="https://www.baeldung.com/mockito-verify">Mockito Verify Cookbook</a>
     */
    @Test
    void testVerify() {
        // real obj
        VerifySubject subject = new VerifySubject();
        // spy obj
        VerifySubject spy = spy(subject);

        // "Verifies certain behavior <b>happened once</b>."
        // methodB without return value, could not assert, thus use verify
        spy.methodB(0);

        // verify when input is 0, will invoke methodA
        verify(spy).methodA();

        // verify calls times
        verify(spy, times(1)).methodB(anyInt());
        verify(spy, atMostOnce()).methodA();

        // verify never calls methodC
        verify(spy, never()).methodC();

        // verify order
        InOrder inOrder = inOrder(spy);
        inOrder.verify(spy).methodB(anyInt());
        inOrder.verify(spy).methodA();

        // verify arguments is 0
        verify(spy).methodB(0);

        // use argument capture
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        // capture argument
        verify(spy).methodB(captor.capture());
        // assert argument is 0
        assertEquals(captor.getValue(), 0);
    }
}
