:star2: 프로젝트 진행과정을 상세하게 아주 상세하게:star2:

## SSR vs SPA

***```SSR(Server side rendering)```***과 ***```SPA(SIngle page application)```*** 이야기를 쓰는 공간을 초기 기획으로 했기에 글이나 그림이 많이 들어간 사이트가 될 것 같다. 초기 페이지의 로딩 시간보다 페이지간 이동이 잦을 때 시간을 절약하는 것이 중요할 듯 싶다. 

-> SPA를 선택한다. 그리고 SPA 개발을 위한 프레임 워크 중 하나인 Vue.js를 활용해 보겠다..! :open_mouth:

Vue.js는 서버와의 통신을 API로 한다. 나는 API를 JSON형태로 응답 처리를 할 것이다. 그렇기 때문에 ***```@RestController```*** 를 먼저 활용해서 진행해보자.



##### PostController

```java
@RestController
public class PostController {

    @GetMapping("/posts")
    public String get() {
        return "Hello World";
    }
}
```



이것을 테스트 해보자.

이것을 테스트 해보기 위해서 ***```@WebMvcTest```*** 를 사용해 보자. 

***```@WebMvcTest```*** 의 참고 사항은 (https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html#appendix.test-auto-configuration, https://velog.io/@woo00oo/SpringBoot-Test-2) 여기의 link를 참조하면되고, 간단한 특징으로는

1. MVC를 위한 테스트
2. 웹에서 테스트하기 힘든 컨트롤러를 테스트 하는 데 적합
3. 웹상에서 요청과 응답에 대해 테스트 할 수 있음
4. 시큐리티, 필터까지 자동으로 테스트하며, 수동으로 추가/삭제 가능.
5. @SpringBootTest 어느테이션보다 가볍게 테스트 가능
6. 다음과 같은 내용만 스캔하도록 제한함.
   @Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, HandlerInterceptor,



예전에 사용한 경험이 있는 ***```MockHttpServletRequest```*** 와 ***```MockHttpServletResponse```*** 와 비슷한 것 같아 검색해보니 이 단위 테스트에서 발전된 형태라는 것을 알게 되었다. 

작성한 코드를 먼저 살펴보자.

##### PostControllerTest

```java
package com.sparkstory.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 Hello World 출력")
    void test() throws Exception {
        // expected
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 Hello World 출력 x")
    void test2() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World2"));
    }
}
```

***```@WebMvcTest```*** 를 사용하면 ***```MockMvc```*** 를 주입받아서 사용할 수 있다.