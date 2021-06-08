package com.ifelseelif.responseservice.service;

import com.ifelseelif.responseservice.models.dto.ModeratorAnswer;
import com.ifelseelif.responseservice.service.interfaces.IResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResponseService implements IResponseService {

    @KafkaListener(topics = "tourist-messages")
    public void getModeratorAnswer(ModeratorAnswer moderatorAnswer) {
        log.info("Got moderator answer: " + moderatorAnswer.getRequestStatus() + " - " + moderatorAnswer.getReason());
    }

}
