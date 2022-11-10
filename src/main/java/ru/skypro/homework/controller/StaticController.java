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
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.AdsFullDto;
import ru.skypro.homework.model.AdsImage;
import ru.skypro.homework.repository.AdsImageRepository;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/static")
@RequiredArgsConstructor
@Api(tags = {"Статика"})
public class StaticController {

    private final AdsImageRepository adsImageRepository;

    @ApiOperation(
            value = "getAds",
            nickname = "getAdsUsingGET",
            notes = "Получение одного объявления"
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
    public ResponseEntity<byte[]> getAds(
            @ApiParam(value = "name", required = true) @PathVariable("name") String name
    ) {
        AdsImage adsImage = adsImageRepository.findByFileNameEqualsIgnoreCase(name);

        if (null == adsImage) {
            throw new NotFoundException("Не найдено.");
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(adsImage.getData());
    }

}
