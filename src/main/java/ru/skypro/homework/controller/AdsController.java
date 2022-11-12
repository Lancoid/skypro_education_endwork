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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.adsComment.AdsCommentService;
import ru.skypro.homework.service.adsImage.AdsImageService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Api(tags = {"Объявления"})
public class AdsController {

    private final AdsService adsService;
    private final AdsCommentService adsCommentService;
    private final AdsImageService adsImageService;

    @ApiOperation(
            value = "addAds",
            nickname = "addAdsUsingPOST",
            notes = "Добавление объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<AdsDto> addAds(
            @RequestPart("properties") @Valid AdsCreateDto adsCreateDto,
            @RequestPart("image") @Valid @NotNull MultipartFile file
    ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(adsService.create(adsCreateDto, file));
        } catch (Throwable throwable) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new AdsDto());
        }
    }

    @ApiOperation(
            value = "updateAds",
            nickname = "updateAdsUsingPATCH",
            notes = "Редактирование объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<AdsDto> updateAds(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "ads", required = true) @Valid @RequestBody AdsDto adsDto
    ) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.update(id, adsDto));
    }

    @ApiOperation(
            value = "updateAdsImage",
            nickname = "updateAdsImageUsingPATCH",
            notes = "Редактирование изображения объявления"
    )
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<AdsDto> updateAdsImage(
            @PathVariable("id") @Valid Long id,
            @RequestPart("image") @Valid @NotNull MultipartFile file
    ) throws AccessDeniedException {
        return ResponseEntity.ok(adsImageService.update(id, file));
    }

    @ApiOperation(
            value = "removeAds",
            nickname = "removeAdsUsingDELETE",
            notes = "Удаление объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> removeAds(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) throws AccessDeniedException {
        adsService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

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
    @GetMapping(path = "{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<AdsFullDto> getAds(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.getOne(id));
    }

    @ApiOperation(
            value = "getAllAds",
            nickname = "getAllAdsUsingGET",
            notes = "Получение всех объявлений"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperAdsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping
    @PreAuthorize("permitAll")
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.getAll());
    }

    @ApiOperation(
            value = "getAdsMe",
            nickname = "getAdsMeUsingGET",
            notes = "Получение всех своих объявлений"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperAdsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "me")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<AdsDto>> getAdsMe() {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.getAllMine().getResults());
    }

    @ApiOperation(
            value = "addAdsComments",
            nickname = "addAdsCommentsUsingPOST",
            notes = "Добавление комментария объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsCommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping(path = "{ad_pk}/comments")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<AdsCommentDto> addAdsComments(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "comment", required = true) @Valid @RequestBody AdsCommentDto adsCommentDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adsCommentService.create(adPk, adsCommentDto));
    }

    @ApiOperation(
            value = "updateAdsComment",
            nickname = "updateAdsCommentUsingPATCH",
            notes = "Редактирование комментария объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsCommentDto.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(path = "{ad_pk}/comments/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<AdsCommentDto> updateAdsComment(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "comment", required = true) @Valid @RequestBody AdsCommentDto adsCommentDto
    ) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.update(adPk, id, adsCommentDto));
    }

    @ApiOperation(
            value = "deleteAdsComment",
            nickname = "deleteAdsCommentUsingDELETE",
            notes = "Удаление комментария объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping(path = "{ad_pk}/comments/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteAdsComment(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) throws AccessDeniedException {
        adsCommentService.delete(adPk, id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(
            value = "getAdsComment",
            nickname = "getAdsCommentUsingGET",
            notes = "Получение одного комментария объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsCommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{ad_pk}/comments/{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<AdsCommentDto> getAdsComment(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.getOne(adPk, id));
    }

    @ApiOperation(
            value = "getAdsComments",
            nickname = "getAdsCommentsUsingGET",
            notes = "Получение всех комментариев объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperAdsCommentDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{ad_pk}/comments")
    @PreAuthorize("permitAll")
    public ResponseEntity<ResponseWrapperAdsCommentDto> getAdsComments(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.getAll(adPk));
    }

}
