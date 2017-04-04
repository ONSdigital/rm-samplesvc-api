package uk.gov.ons.ctp.response.sample.message.impl;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.ctp.common.error.CTPException;
import uk.gov.ons.ctp.response.sample.definition.BusinessSurveySample;

@Slf4j
@MessageEndpoint
public class SFTPFileReceiverBusinessSampleImpl implements SFTPFileReceiverSample<BusinessSurveySample> {

  @ServiceActivator(inputChannel = "xmlInvalidBusiness")
  public void invalidXMLProcess(Message<String> message) throws CTPException {
    log.info("xmlInvalidBusiness: " + message.getHeaders().get("file_name"));
  }
  
  /**
   * To process BusinessSurveySample transformed from XML
   * @param BusinessSurveySample to process
   */
  @ServiceActivator(inputChannel = "xmlTransformedBusiness")
  public void transformedXMLProcess(BusinessSurveySample BusinessSurveySample) {
    log.info(String.format("BusinessSurveySample (Collection Exercise Ref: %s) transformed successfully.", BusinessSurveySample.getCollectionExerciseRef()));
  }

  @ServiceActivator(inputChannel = "renameSuccessProcessBusiness")
  public void sftpSuccessProcess(GenericMessage<GenericMessage<byte[]>> message) {
    String filename = (String) message.getPayload().getHeaders().get("file_name");
    log.info("Renaming successful for " + filename);
  }

  @ServiceActivator(inputChannel = "renameFailedProcessBusiness")
  public void sftpFailedProcess(GenericMessage<MessagingException> message) {
    String filename = (String) message.getPayload().getFailedMessage().getHeaders().get("file_name");
    log.info("Renaming failed for" + filename);    
  }

}
