package blog.com.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class PostDto {
	
	private Long id;
	private String content;
	private String title;
	private String username;

}
