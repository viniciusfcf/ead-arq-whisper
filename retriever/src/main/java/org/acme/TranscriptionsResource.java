package org.acme;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/transcriptions")
public class TranscriptionsResource {

    Map<String, String> mapMessageIdToTranscription = new HashMap<>();

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@PathParam("id") String id) {
        String transcription = mapMessageIdToTranscription.get(id);
        if(transcription == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(transcription).build();
    }
}
