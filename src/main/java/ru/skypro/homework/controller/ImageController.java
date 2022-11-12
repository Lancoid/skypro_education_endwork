package ru.skypro.homework.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsFullDto;
import ru.skypro.homework.model.AdsImage;
import ru.skypro.homework.repository.AdsImageRepository;

import javax.persistence.EntityNotFoundException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Api(tags = {"Изображения"})
public class ImageController {

    private final AdsImageRepository adsImageRepository;

    @ApiOperation(
            value = "getImageByName",
            nickname = "getImageByNameUsingGET",
            notes = "Получение одного изображения по имени"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsFullDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{name}")
    public ResponseEntity<byte[]> getImageByName(
            @ApiParam(value = "name", required = true) @PathVariable("name") String name
    ) {
        AdsImage adsImage = adsImageRepository.findByFileNameEqualsIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("name: " + name));

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(adsImage.getData());
    }

}
