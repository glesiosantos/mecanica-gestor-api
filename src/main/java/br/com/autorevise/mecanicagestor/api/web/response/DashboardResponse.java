package br.com.autorevise.mecanicagestor.api.web.response;

import java.util.List;

public record DashboardResponse(
    List<CardDashboardResponse> cards,
    List<Integer> series
) {}
