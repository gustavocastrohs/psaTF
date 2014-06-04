/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import negocio.EstacionamentoException;
import negocio.TipoDeTicket;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author Gustavo
 */
public class ClasseTelaPrincipal {

    private final CtrlGUI controlador;

    /**
     * cria uma instancia de ClasseTelaPrincipal
     */
    private static ClasseTelaPrincipal instancia;

    /**
     *
     * @return a intancia ativa da ClasseTelaPrincipal
     * @throws FachadaException
     */
    public static ClasseTelaPrincipal getInstance() throws FachadaException {

        if (instancia == null) {
            try {
                instancia = new ClasseTelaPrincipal();
            } catch (FachadaException ex) {
                throw new FachadaException(ex);
            }
        }
        return instancia;

    }

    /**
     * Recupera a instancia ativa de CtrlGUI
     *
     * @throws negocio.EstacionamentoException
     */
    private ClasseTelaPrincipal() throws FachadaException {
        controlador = CtrlGUI.getInstance();
    }

    /**
     * Local Cancela Entrada
     *
     * @param isSelect seleciona o tipo do ticket que está sendo impresso , sem
     * codigo de barras ou com
     * @return retorna o toString() do ticket criado
     * @throws EstacionamentoException
     */
    private String ultimoTicketGerado = "";

    /**
     * Local Cancela Entrada
     *
     * @param texto JTextArea que receberá o ticket impresso
     * @param isSelect tipo do ticket que está sendo impresso , sem codigo de
     * barras ou com
     * @return retorna JTextArea com o ticket adicionado
     * @throws apresentacao.FachadaException
     */
    public JTextArea EmissaoDeTicketAutomatica(JTextArea texto, boolean isSelect) throws FachadaException {
        try {
            if (!isSelect) {
                texto.append(controlador.emissaoDeTicketAutomatica(TipoDeTicket.TicketSimples) + "\n");
            } else {
                ultimoTicketGerado = controlador.emissaoDeTicketAutomatica(TipoDeTicket.TicketCodigoBarras);
                texto.append(ultimoTicketGerado + "\n");

            }
            return texto;
        } catch (EstacionamentoException e) {
            throw new FachadaException(e);
        }

    }

    /**
     * Local Cancela Saida
     *
     * @param label indica o label que está recebendo a ação
     * @param text codigo para buscar ticket no banco de dados
     * @return retorna a sitação do ticket obtida no metodo
     * testaSeTicketEstaLiberado(int codigo) em um Jlabel
     */
    public JLabel validacaoDeTicketCancelaSaida(JLabel label, String text) {
        label.setText(controlador.validacaoDeTicketCancelaSaida(text));
        return label;
    }

    /**
     * Local Operador
     *
     * @param texto JTextArea que recebera o ticket
     * @param isSelect seleciona o tipo do ticket que está sendo impresso , sem
     * codigo de barras ou com
     * @return retorna JTextArea com o toString() do ticket criado
     * @throws apresentacao.FachadaException
     */
    public JTextArea emissaoDeTicketManual(JTextArea texto, boolean isSelect) throws FachadaException {
        try {
            if (!isSelect) {
                texto.append(controlador.emissaoDeTicketManual(TipoDeTicket.TicketSimples) + "\n");
            } else {
                texto.append(controlador.emissaoDeTicketManual(TipoDeTicket.TicketCodigoBarras) + "\n");
            }
        } catch (EstacionamentoException e) {
            texto.append("Ticket não pode ser impresso");
        }
        return texto;
    }

    /**
     *
     * @param label
     * @param text ticket a ser analisado
     * @return label com a validação do ticket
     */

    public JLabel validacaoDeTicketOperador(JLabel label, String text) {
        label.setText(controlador.validacaoDeTicketOperador(text));
        return label;
    }

    /**
     *
     * @param texto JtextArea que recebera o textp do pagamento do ticket
     * @param text ticket a ser pago
     * @return retorna o JtextArea com o pagamento
     * @throws FachadaException
     */

    public JTextArea pagaTicket(JTextArea texto, String text) throws FachadaException {
        try {
            texto.append(controlador.pagaTicket(text));
            return texto;
        } catch (EstacionamentoException ex) {
            texto.append("Problema no pagamento do ticket");
            return texto;
        }

    }
    /**
     * Local Modulo Gerencial
     *
     * dia selecionado para liberar os tickets
     * @return indica se foi bem ou mau sucedida a ação
     * @throws apresentacao.FachadaException
     */
    public String liberaSemPagamento() throws FachadaException {
        Timestamp hoje = new Timestamp(Calendar.getInstance().getTimeInMillis());
        try {
            boolean liberarTicketSemPagamento = controlador.liberarTicketSemPagamento(hoje);
            if (liberarTicketSemPagamento) {
                return "Todos os ticket liberados: " + Calendar.getInstance().get(Calendar.DATE) + "/" + Calendar.getInstance().get(Calendar.MONTH);
            }

        } catch (EstacionamentoException ex) {
            return "Problema na liberação dos tickets";
        }
        return "Tickets não liberado";

    }

    /**
     *
     * @param label local que receberá o codigo de barras
     * @return retorno do label com o codigo de barras
     * @throws FachadaException
     */
    public JLabel labelCodigoDeBarras(JLabel label) throws FachadaException {

        label.setIcon(criaIconeAtravesDaImagem(criaCodigoDeBarras()));

        return label;
    }

    /**
     *
     * @param image recebe uma image que será transformada em icone
     * @return retorna um icone
     */
    public ImageIcon criaIconeAtravesDaImagem(Image image) {
        ImageIcon icon = null;
        try {

            icon = new ImageIcon(image);
            return icon;
        } catch (Exception e) {
            return icon;
        }
    }

    /**
     *
     * @return retorna uma image que é um iconee
     * @throws FachadaException
     */

    public Image criaCodigoDeBarras() throws FachadaException {

        Barcode barcode;
        try {
            barcode = BarcodeFactory.createCode128(ultimoTicketGerado);

            barcode.setDrawingText(false);
            BufferedImage image = new BufferedImage(100, 150, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setBackground(Color.WHITE);

            barcode.draw(g, 10, 56);
            return image;
        } catch (BarcodeException | OutputException ex) {
            throw new FachadaException(ex);
        }

    }

}
