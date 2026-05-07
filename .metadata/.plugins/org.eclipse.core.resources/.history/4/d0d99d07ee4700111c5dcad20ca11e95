package model.book;

import java.util.Objects;

public class Copy {
	
	
	    private Integer id;
	    private Book book; // referência ao livro
	    private String status; // DISPONIVEL, EMPRESTADO, DANIFICADO
	    private String location;
	    
	    public Copy() {
	    	
	    }
	    
		public Copy(Integer id, Book book, String status, String location) {
			this.id = id;
			this.book = book;
			this.status = status;
			this.location = location;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Copy other = (Copy) obj;
			return Objects.equals(id, other.id);
		}
	    
	    
	    
	    
	}


