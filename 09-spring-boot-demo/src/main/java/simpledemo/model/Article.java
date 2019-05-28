package simpledemo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Document(collection = "articles")
@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Article {
	@Id
	private String id;
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	private String authorId;
	private LocalDateTime created = LocalDateTime.now();
	private LocalDateTime modified = LocalDateTime.now();
}
