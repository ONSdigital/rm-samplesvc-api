package uk.gov.ons.ctp.response.sample.message.impl;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.ctp.common.error.CTPException;
import uk.gov.ons.ctp.response.sample.definition.CensusSurveySample;

@Slf4j
@MessageEndpoint
public class SFTPFileReceiverCensusSampleImpl implements SFTPFileReceiverSample<CensusSurveySample> {

  @ServiceActivator(inputChannel = "xmlInvalidCensus")
  public void invalidXMLProcess(Message<String> message) throws CTPException {
    log.info("xmlInvalidCensus: " + message.getHeaders().get("file_name"));
  }
  
  /**
   * To process CensusSurveySample transformed from XML
   * @param CensusSurveySample to process
   */
  @ServiceActivator(inputChannel = "xmlTransformedCensus")
  public void transformedXMLProcess(CensusSurveySample censusSurveySample) {
    log.info(String.format("CensusSurveySample (Collection Exercise Ref: %s) transformed successfully.", censusSurveySample.getCollectionExerciseRef()));
  }

  @ServiceActivator(inputChannel = "renameSuccessProcessCensus")
  public void sftpSuccessProcess(GenericMessage<GenericMessage<byte[]>> message) {
    String filename = (String) message.getPayload().getHeaders().get("file_name");
    log.info("Renaming successful for " + filename);
  }

  @ServiceActivator(inputChannel = "renameFailedProcessCensus")
  public void sftpFailedProcess(GenericMessage<MessagingException> message) {
    String filename = (String) message.getPayload().getFailedMessage().getHeaders().get("file_name");
    log.info("Renaming failed for" + filename);    
  }

}
