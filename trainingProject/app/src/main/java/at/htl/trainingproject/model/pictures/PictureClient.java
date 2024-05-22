package at.htl.trainingproject.model.pictures;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/photos")
@Consumes(MediaType.APPLICATION_JSON)
public interface PictureClient {
    @GET
    Picture[] all();
}
