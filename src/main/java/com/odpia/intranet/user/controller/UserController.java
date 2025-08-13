package com.odpia.intranet.user.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odpia.intranet.user.dto.UserCreateRequest;
import com.odpia.intranet.user.dto.UserResponse;
import com.odpia.intranet.user.dto.UserUpdateRequest;
import com.odpia.intranet.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "User", description = "사용자 CRUD API") @RequiredArgsConstructor @RestController @RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Operation(summary = "사용자 생성", description = "이메일, 이름, 상태로 사용자를 생성합니다.", responses = {
	        @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content(schema = @Schema(implementation = UserResponse.class))),
	        @ApiResponse(responseCode = "400", description = "잘못된 요청") }) @PostMapping
	public ResponseEntity<UserResponse> create(
	        @Valid @RequestBody @Parameter(description = "사용자 생성 요청") UserCreateRequest req) {
		UserResponse created = userService.create(req);
		return ResponseEntity.created(URI.create("/api/users/" + created.id())).body(created);
	}

	@Operation(summary = "사용자 단건 조회", description = "ID로 사용자 정보를 조회합니다.", responses = {
	        @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = UserResponse.class))),
	        @ApiResponse(responseCode = "404", description = "대상이 없음") }) @GetMapping("/{id}")
	public UserResponse get(@Parameter(description = "사용자 ID", example = "1") @PathVariable Long id) {
		return userService.get(id);
	}

	@Operation(summary = "사용자 목록 조회", description = "전체 사용자 목록을 반환합니다.", responses = {
	        @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))) }) @GetMapping
	public List<UserResponse> list() {
		return userService.list();
	}

	@Operation(summary = "사용자 수정", description = "ID 기준으로 사용자 정보를 수정합니다.", responses = {
	        @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = UserResponse.class))),
	        @ApiResponse(responseCode = "404", description = "대상이 없음"),
	        @ApiResponse(responseCode = "400", description = "잘못된 요청") }) @PutMapping("/{id}")
	public UserResponse update(@Parameter(description = "사용자 ID", example = "1") @PathVariable Long id,
	        @Valid @RequestBody @Parameter(description = "사용자 수정 요청") UserUpdateRequest req) {
		return userService.update(id, req);
	}

	@Operation(summary = "사용자 삭제", description = "ID 기준으로 사용자를 삭제합니다.", responses = {
	        @ApiResponse(responseCode = "204", description = "삭제 성공"),
	        @ApiResponse(responseCode = "404", description = "대상이 없음") }) @DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Parameter(description = "사용자 ID", example = "1") @PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
