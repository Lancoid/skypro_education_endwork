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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.ads.AdsService;
import ru.skypro.homework.service.adsComment.AdsCommentService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Api(tags = {"Объявления"})
public class AdsController {

    private final AdsService adsService;
    private final AdsCommentService adsCommentService;

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
    public ResponseEntity<AdsDto> addAds(
            @ApiParam(value = "createAds", required = true) @Valid @RequestBody AdsCreateDto adsCreateDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.create(adsCreateDto));
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
    public ResponseEntity<AdsDto> updateAds(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "ads", required = true) @Valid @RequestBody AdsDto adsDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.update(id, adsDto));
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
    public ResponseEntity<Void> removeAds(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
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
    public ResponseEntity<AdsFullDto> getAds(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.getOne(id));
    }

    @ApiOperation(
            value = "getALLAds",
            nickname = "getALLAdsUsingGET",
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
    public ResponseEntity<ResponseWrapperAdsDto> getALLAds() {
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
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(
            @ApiParam(value = "") @Valid @RequestParam(value = "authenticated", required = false) Boolean authenticated,
            @ApiParam(value = "") @Valid @RequestParam(value = "authorities[0].authority", required = false) String authorities0Authority,
            @ApiParam(value = "", defaultValue = "null") @Valid @RequestParam(value = "credentials", required = false, defaultValue = "null") Object credentials,
            @ApiParam(value = "", defaultValue = "null") @Valid @RequestParam(value = "details", required = false, defaultValue = "null") Object details,
            @ApiParam(value = "", defaultValue = "null") @Valid @RequestParam(value = "principal", required = false, defaultValue = "null") Object principal
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.getAllMine());
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
    @PostMapping(path = "{ad_pk}/comment")
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
    @PatchMapping(path = "{ad_pk}/comment/{id}")
    public ResponseEntity<AdsCommentDto> updateAdsComment(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "comment", required = true) @Valid @RequestBody AdsCommentDto adsCommentDto
    ) {
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
    @DeleteMapping(path = "{ad_pk}/comment/{id}")
    public ResponseEntity<Void> deleteAdsComment(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
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
    @GetMapping(path = "{ad_pk}/comment/{id}")
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
    @GetMapping(path = "{ad_pk}/comment")
    public ResponseEntity<ResponseWrapperAdsCommentDto> getAdsComments(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.getAll(adPk));
    }

}
