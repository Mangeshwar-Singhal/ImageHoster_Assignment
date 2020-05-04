package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//The annotation is a special type of @Component annotation which describes that the class defines a data service
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //The method calls the postComment() method in the Repository and passes the comment to be persisted in the database
    public void postComment(Comment comment) {
        commentRepository.postComment(comment);
    }
}
