package cl.previred.infrastructurecross.application;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "applicationmessages")
public class MessageApplicationConfig {
	@Setter
	private List<Message> messages;
	
	@Bean
    public List<Message> getMessages() {
		return this.messages;
    }

	public String getMessageByAppCode(Integer appCode){
		String response = null;
		try {
			List<Message> messageList = messages.stream()                
	        .filter(message -> appCode.equals(message.getAppCode()))     
	        .collect(Collectors.toList()); 
			response = messageList.get(0).getMessage();
		}catch(Exception e) {
			e.printStackTrace();
    	}
		return response;
	}
	
	public Integer getHttpCodeByAppCode(Integer appCode){
		Integer response = null;
		try {
			List<Message> messageList = messages.stream()                
	        .filter(message -> appCode.equals(message.getAppCode()))     
	        .collect(Collectors.toList()); 
			response = messageList.get(0).getHttpCode();
		}catch(Exception e) {
			e.printStackTrace();
    	}
		return response;
	}
}
