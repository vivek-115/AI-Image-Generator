package in.vivek.gibhliAPI.Service;

import in.vivek.gibhliAPI.DTO.TextToImageRequest;
import in.vivek.gibhliAPI.client.StabilityAIClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GhibliArtService {

    private final StabilityAIClient stabilityAIClient;
    private final String apiKey;


    public GhibliArtService(StabilityAIClient stabilityAIClient, @Value("${stability.api.key}") String apiKey) {
        this.stabilityAIClient = stabilityAIClient;
        this.apiKey = apiKey;
    }

    public byte[] createGhibliArt(MultipartFile image, String prompt){
        String finalPrompt=prompt+", in the beautiful, detailed anime style of studio ghibli.";
        String engineId="stable-diffusion-xl-1024-v1-0";
        String stylePreset="anime";

       return stabilityAIClient.generateImageFromImage(
                "Bearer "+apiKey,
                engineId,
                image,
                finalPrompt,
                stylePreset
        );
    }

    public byte[] createGhibliArtFromText(String prompt,String style){
        String finalPrompt=prompt+", in the beautiful, detailed anime style of studio ghibli.";
       String engineId="stable-diffusion-xl-1024-v1-0";
//        String engineId="stable-diffusion-xl-1024-v1-0";
        String stylePreset=style.equals("general")?"anime":style.replace("_","-");

        TextToImageRequest requestPayload=new TextToImageRequest(finalPrompt,stylePreset);
        requestPayload.setWidth(1024);
        requestPayload.setHeight(1024);
        return stabilityAIClient.generateImageFromText(
                "Bearer "+apiKey,
                engineId,
               requestPayload
        );
    }
}
