package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.msoficinas.api.entidades.ServicoEstabelecimento;
import br.com.msoficinas.api.repositories.ServicoRepository;
import br.com.msoficinas.api.services.EspecialidadeService;
import br.com.msoficinas.api.services.EstabelecimentoService;
import br.com.msoficinas.api.services.ServicoEstabelecimentoService;
import br.com.msoficinas.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.msoficinas.api.web.mappers.ServicoEstabelecimentoMapper;
import br.com.msoficinas.api.web.request.ServicoRequest;
import br.com.msoficinas.api.web.response.ServicoEstabelecimentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoEstabelecimentoImpl implements ServicoEstabelecimentoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private ServicoEstabelecimentoMapper servicoEstabelecimentoMapper;

    @Override
    public ServicoEstabelecimento addServico(ServicoRequest request) throws Exception {
        var estabelecimento = estabelecimentoService.buscarEstabelecimentoPeloId(request.idEstabelecimento());
        var servico = ServicoEstabelecimento.builder()
                .valor(request.valor())
                .descricao(request.descricao())
                .estabelecimento(estabelecimento)
                .build();
        return servicoRepository.save(servico);
    }


    @Override
    public void removerServidoDoEstabelecimento(String idEstabelecimento, String idServico) throws Exception {
        var servico = servicoRepository.findByIdAndEstabelecimentoId(idServico, idEstabelecimento)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum serviços encontrado ou atribuidor para este estabelecimento com este idServiço "+idServico));
        servicoRepository.delete(servico);
    }

    @Override
    public void atualizarServidoDoEstabelecimento(ServicoRequest request) throws Exception {
        var servico = servicoRepository.findByIdAndEstabelecimentoId(request.idServico(), request.idEstabelecimento())
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum serviços encontrado ou atribuidor para este estabelecimento com este idServiço "+request.idServico()));
        servico.setValor(request.valor());
        servicoRepository.save(servico);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServicoEstabelecimentoResponse> carregarServicosDoEstabelecimento(String id) {
        return servicoRepository.findByEstabelecimentoId(id)
                .stream()
                .map(servico -> servicoEstabelecimentoMapper.converterModeloEmResponse(servico))
                .collect(Collectors.toList());
    }
}
