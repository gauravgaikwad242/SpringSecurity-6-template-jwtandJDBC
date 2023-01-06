package blog.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.com.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
