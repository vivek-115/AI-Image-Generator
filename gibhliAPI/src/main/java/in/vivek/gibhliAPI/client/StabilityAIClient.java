package in.vivek.gibhliAPI.client;

import in.vivek.gibhliAPI.DTO.TextToImageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "stabilityAiClient",
        url = "${stability.api.base-url}",
        configuration = in.vivek.gibhliAPI.Config.FeignConfig.class
)
public interface StabilityAIClient {

    @PostMapping(
            value = "/v1/generation/{engine_id}/text-to-image",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Accept=image/png"}
    )
    byte[] generateImageFromText(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("engine_id") String engine_id,
            @RequestBody TextToImageRequest requestBody
            );

    @PostMapping(
            value = "/v1/generation/{engine_id}/image-to-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            headers = {"Accept=image/png"}
    )
    byte[] generateImageFromImage(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("engine_id") String engine_id,
            @RequestPart("init_image")MultipartFile initImage,
            @RequestPart("text_prompts[0][text]") String textPrompt,
            @RequestPart("style_preset") String stylePreset
            );
}
