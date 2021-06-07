package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.models.dto.ModeratorAnswer;
import com.ifelseelif.blsslab1.service.interfaces.IAnswerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnswerMessageService implements IAnswerMessageService {

    private final KafkaTemplate<Long, ModeratorAnswer> kafkaTemplate;

    @Autowired
    public AnswerMessageService(KafkaTemplate<Long, ModeratorAnswer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendAnswerMessage(ModeratorAnswer moderatorAnswer) {
        System.out.println("Producing moderator answer:\n " + moderatorAnswer);
        kafkaTemplate.send("tourist-messages", moderatorAnswer);
    }
}
