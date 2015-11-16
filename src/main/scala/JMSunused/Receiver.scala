

package JMSunused

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

class Receiver(applicationContext : ConfigurableApplicationContext) {
  
  val appctxt = applicationContext 
  
  @JmsListener(destination = "SWIFT.OUT", containerFactory = "jmsListenerContainerFactory")
  def receiveMyMessage(msg : String){
    print("Here is your message: <" + msg + ">")
    appctxt close
  }
}