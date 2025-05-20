package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<?> obterDashboard(@PathVariable("idEstabelecimento") String idEstabelecimento) {
        return ResponseEntity.ok(dashboardService.obterDadosEstatitiscoDoEstabelecimento(idEstabelecimento));
    }
}
