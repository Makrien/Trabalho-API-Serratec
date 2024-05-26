package br.gov.serratec.grupo05api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.gov.serratec.grupo05api.model.ItemPedido;
import br.gov.serratec.grupo05api.repository.ItemPedidoRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	@Value("${spring.mail.username}")
	private String remetente;

	public String enviarEmail(String destinatario, String assunto, String mensagem) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(remetente);
			simpleMailMessage.setTo(destinatario);
			simpleMailMessage.setSubject(assunto);
			simpleMailMessage.setText(mensagem);
			javaMailSender.send(simpleMailMessage);
			return "E-mail enviado";
		}catch (Exception e) {
			return "Erro ao enviar e-mail" + e.getLocalizedMessage();
		}
	}
	   public void envioEmail(Long pedidoId) {
	        List<ItemPedido> pedidos = itemPedidoRepo.findByItemIdPedido(pedidoId);
			StringBuilder itensPedidoString = new StringBuilder();
			for (ItemPedido pedido : pedidos) {
				try {
					String pedidoJson = "Produto: " + pedido.getProduto().getNome() +"\n"
										+"Imagem:  " + pedido.getProduto().getImagem()+"\n"
										+"Descrição: " + pedido.getProduto().getDescricao() +"\n";
											
					itensPedidoString.append(pedidoJson).append("\n");
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}


			String emailBody = "Olá,\n\n" + "Segue abaixo o relatório do pedido:\n\n" 
								+ "ID do Pedido: "+ pedidos.get(0).getId() + "\n" 
								+ "Cliente: " + pedidos.get(0).getPedido().getCliente().getNomeCompleto() + "\n" 
								+ "Data do Pedido: " +pedidos.get(0).getPedido().getDataPedido() + "\n"
								+ "Valor Total: "+ pedidos.get(0).getPedido().getValorTotal() + "\n" 
								+ "Status do Pedido: " + pedidos.get(0).getPedido().getStatus() + "\n"
								+ "Itens do pedido:\n" + itensPedidoString.toString() + "\n\n" 
								+ "Atenciosamente,\n"
								+ "Equipe de Suporte";
			
	        enviarEmail(pedidos.get(0).getPedido().getCliente().getEmail(), "Relatorio do pedido realizado", emailBody);
	    }
}
