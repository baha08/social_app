package com.example.Social_App.services;

import com.example.Social_App.exceptions.ResourceNotFoundException;
import com.example.Social_App.models.Post;
import com.example.Social_App.models.User;
import com.example.Social_App.repository.PostRepository;
import com.example.Social_App.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.Social_App.constants.ErrorConst.ERROR_MESSAGE;

@Service
@Slf4j
public class PostService {

    // remove all @Autowired
    private final PostRepository postRepository;


    public final UserRepository userRepository;

    public PostService(final PostRepository postRepository, final UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(final Long id) {
        return postRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
    }

    public Post createPost(final Post post, final Long idUSer) {
        final User user = userRepository.findById(idUSer).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idUSer);
            return new ResourceNotFoundException();
        });
        final LocalDateTime dateTime = LocalDateTime.now();
        post.setOwner(user);
        post.setCreatedAt(dateTime);
        return postRepository.save(post);
    }

    public Post updatePost(final Post postData, final Long id) {
        final Post post = postRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        final LocalDateTime date = LocalDateTime.now();
        post.setContent(postData.getContent());
        post.setUpdatedAT(date);
        return postRepository.save(post);

    }

    public void deletePost(final Long id) {
        final Post post = postRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        postRepository.delete(post);
    }

    public List<Post> findPostPerUser(final Long id_user) {
        final User user = userRepository.findById(id_user).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id_user);
            return new ResourceNotFoundException();
        });
        return postRepository.finPostPerUser(user);
    }
}
