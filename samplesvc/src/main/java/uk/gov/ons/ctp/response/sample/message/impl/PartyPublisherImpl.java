package uk.gov.ons.ctp.response.sample.message.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.ctp.response.party.definition.Party;
import uk.gov.ons.ctp.response.sample.message.PartyPublisher;
/**
 * The publisher to queues
 */
@Slf4j
@Component
public class PartyPublisherImpl implements PartyPublisher {

  @Qualifier("partyRabbitTemplate")
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Override
  public void publish(Party partyDTO) {
    log.debug("send to queue partysvc {}", partyDTO);
    rabbitTemplate.convertAndSend(partyDTO);
  }
}