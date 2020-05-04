package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This method is called when the user post a comment
    //The logic is to save the comment into the database with corresponding values. After persisting the comment into database image details are shown
    //First receive the dynamic parameters in the incoming request URL in string variables 'imageId', 'imageTitle', 'commentText' and httpSession object
    //Call the postComment() method in the business logic to save the comment
    //then control is redirected to 'images/{imageId}/{imageTitle}'
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String postComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String commentText, HttpSession session){
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setCreatedDate(LocalDate.now());
        comment.setImage(image);
        comment.setUser(user);
        commentService.postComment(comment);
        return "redirect:/images/"+imageId+"/"+imageTitle;
    }
}
