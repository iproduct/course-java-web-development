package bookstore.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "title", "publisher", "format", "publishedDate", "isbn", "price"})
public class Book {
	@Id
	@GeneratedValue
	private int id;
	
	@NonNull
	@NotNull
	private String title;
	
//	@NonNull
	@NotNull
	@ManyToOne
	@JoinColumn(name = "publisher", referencedColumnName = "id")
	private Publisher publisher;
	
//	@NonNull
	@NotNull
	@ManyToOne
	@JoinColumn(name = "format", referencedColumnName = "id")
	private Format format; 
	
	@Column(name = "published_date")
	@JsonFormat(pattern = "dd.MM.uuuu")
	@PastOrPresent
	private LocalDate publishedDate;
	
	@Pattern(regexp = "\\d{10}|\\d{13}")
	private String isbn;
	
	@NotNull
	@Min(0)
	private double price;
	
	@ManyToMany
	@JoinTable(name="BOOK_AUTHOR",
	    joinColumns=
	        @JoinColumn(name="BOOK_ID", referencedColumnName="ID"),
	    inverseJoinColumns=
	        @JoinColumn(name="AUTHOR_ID", referencedColumnName="ID")
	)
	private List<Author> authors = new ArrayList<>();
	
	@JsonIgnore
	@Transient
	private Integer publisherId;
	
	@JsonIgnore
	@Transient
	private Integer formatId;
	
	
	@JsonIgnore
	public String getAuthorsAsString() {
		return authors.stream()
			.map(a -> a.getFirstName() + " " + a.getLastName())
			.collect(Collectors.joining(", "));
	}
	
}
