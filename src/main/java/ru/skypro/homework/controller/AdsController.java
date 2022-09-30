package ru.skypro.homework.controller;

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
public class AdsController {

    private final AdsService adsService;
    private final AdsCommentService adsCommentService;

    @ApiOperation(
            value = "addAds",
            nickname = "addAdsUsingPOST",
            notes = "Добавить объявления",
            response = Ads.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ads.class))),
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping
    public ResponseEntity<Ads> _addAdsUsingPOST(
            @ApiParam(value = "createAds", required = true) @Valid @RequestBody CreateAds createAds
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.create(createAds));
    }

    @ApiOperation(
            value = "updateAds",
            nickname = "updateAdsUsingPATCH",
            notes = "",
            response = Ads.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ads.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(path = "{id}")
    public ResponseEntity<Ads> _updateAdsUsingPATCH(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "ads", required = true) @Valid @RequestBody Ads ads
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.update(id, ads));
    }

    @ApiOperation(
            value = "removeAds",
            nickname = "removeAdsUsingDELETE",
            notes = "",
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> _removeAdsUsingDELETE(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        adsService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(
            value = "getAds",
            nickname = "getAdsUsingGET",
            notes = "",
            response = FullAds.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = FullAds.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<FullAds> _getAdsUsingGET(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.getOne(id));
    }

    @ApiOperation(
            value = "getALLAds",
            nickname = "getALLAdsUsingGET",
            notes = "Получить все",
            response = ResponseWrapperAds.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapperAds.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping
    public ResponseEntity<ResponseWrapperAds> _getALLAdsUsingGET() {
        return ResponseEntity.status(HttpStatus.OK).body(adsService.getAll());
    }

    @ApiOperation(
            value = "getAdsMe",
            nickname = "getAdsMeUsingGET",
            notes = "",
            response = ResponseWrapperAds.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapperAds.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "me")
    public ResponseEntity<ResponseWrapperAds> _getAdsMeUsingGET(
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
            notes = "Добавить комментарий",
            response = AdsComment.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdsComment.class))),
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping(path = "{ad_pk}/comment")
    public ResponseEntity<AdsComment> _addAdsCommentsUsingPOST(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "comment", required = true) @Valid @RequestBody AdsComment comment
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.create(adPk, comment));
    }

    @ApiOperation(
            value = "updateAdsComment",
            nickname = "updateAdsCommentUsingPATCH",
            notes = "",
            response = AdsComment.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdsComment.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(path = "{ad_pk}/comment/{id}")
    public ResponseEntity<AdsComment> _updateAdsCommentUsingPATCH(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "comment", required = true) @Valid @RequestBody AdsComment comment
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.update(adPk, id, comment));
    }

    @ApiOperation(
            value = "deleteAdsComment",
            nickname = "deleteAdsCommentUsingDELETE",
            notes = "",
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping(path = "{ad_pk}/comment/{id}")
    public ResponseEntity<Void> _deleteAdsCommentUsingDELETE(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        adsCommentService.delete(adPk, id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(
            value = "getAdsComments",
            nickname = "getAdsCommentsUsingGET",
            notes = "",
            response = ResponseWrapperAdsComment.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapperAdsComment.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{ad_pk}/comment")
    public ResponseEntity<ResponseWrapperAdsComment> _getAdsCommentsUsingGET(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.getAll(adPk));
    }

    @ApiOperation(
            value = "getAdsComment",
            nickname = "getAdsCommentUsingGET",
            notes = "",
            response = AdsComment.class,
            tags = {"Объявления"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdsComment.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{ad_pk}/comment/{id}")
    public ResponseEntity<AdsComment> _getAdsCommentUsingGET(
            @ApiParam(value = "ad_pk", required = true) @PathVariable("ad_pk") String adPk,
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(adsCommentService.getOne(adPk, id));
    }

}
