package at.htl.trainingproject.model.post;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/posts")
@Consumes(MediaType.APPLICATION_JSON)
public interface PostClient {
    @GET
    List<Post> all();
    @POST
    Post create(PostDto post);
}
