package kuit.server.temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class TempController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // messageBody를 stream으로 읽음
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}",messageBody);

        // messageBody를 UserData 객체와 매핑
        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("username = {}, age = {}",userData.getUsername(),userData.getAge());
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        // String인 messageBody를 UserData 객체와 매핑
        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("username = {}, age = {}", userData.getUsername(), userData.getAge());

        // @ResponseBody를 통해 "ok"를 view가 아닌 string으로 반환
        // 객체 반환하는 경우에는 자동으로 json으로 변환 후 반환
        return "ok";
    }

    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserData data) {
        log.info("username = {}, age = {}", data.getUsername(), data.getAge());

        // @ResponseBody를 통해 "ok"를 view가 아닌 string으로 반환
        // 객체 반환하는 경우에는 자동으로 json으로 변환 후 반환
        return "ok";
    }

    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<UserData> httpEntity) {
        UserData data = httpEntity.getBody();
        log.info("username = {}, age = {}", data.getUsername(), data.getAge());

        // @ResponseBody를 통해 "ok"를 view가 아닌 string으로 반환
        // 객체 반환하는 경우에는 자동으로 json으로 변환 후 반환
        return "ok";
    }

    @PostMapping("/request-body-json-v5")
    public UserData requestBodyJsonV5(@RequestBody UserData data) {
        log.info("username = {}, age = {}", data.getUsername(), data.getAge());

        // @ResponseBody를 통해 UserData 객체를 자동으로 json으로 변환 후 반환
        return data;
    }
}
