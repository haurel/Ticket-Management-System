package com.common.objects.models.jwt;

import lombok.Builder;

@Builder
public record JwtUserModel(String UserId, String Username, String Token) {}
