/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.keepautomation.barcode.BarCode;
import com.keepautomation.barcode.IBarCode;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import negocio.ITicket;
import web.FachadaBean;

/**
 *
 * @author Gustavo
 */
public class UsuarioServlet extends HttpServlet {

    private String placa, chave;
    private FachadaBean fachada = new FachadaBean();
    private ITicket usuarioGeraCodigoDeBarras = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Gerador Codigo de barras</title>");
            out.println("</head>");
            out.println("<body>");
            placa = (String) request.getAttribute("placa");
            chave = (String) request.getAttribute("chave");
            fachada.setPlaca(placa);
            fachada.setChave(chave);
            usuarioGeraCodigoDeBarras = fachada.usuarioGeraCodigoDeBarras();
            if (usuarioGeraCodigoDeBarras != null) {
                out.println("Ticket encontrado"+usuarioGeraCodigoDeBarras.toString());
                codigoDeBarras();
                out.println("<BR/>");
                out.println("<a href=\"/showCodeBar\">click for the code</a>");
          
                
            } else {

                out.println("Ticket não encontrado");

            }
            //      out.println("<h1>Servlet usuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void codigoDeBarras() {

        BarCode barcode = new BarCode();
        barcode.setCodeToEncode(usuarioGeraCodigoDeBarras.getId() + "");
        barcode.setSymbology(IBarCode.CODE39);
        barcode.setX(2);
        barcode.setY(50);
        barcode.setRightMargin(0);
        barcode.setLeftMargin(0);
        barcode.setTopMargin(0);
        barcode.setBottomMargin(0);
        barcode.setChecksumEnabled(false);
        barcode.setFnc1(IBarCode.FNC1_NONE);
        try {
            barcode.draw(caminhoDaImagem);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


private String caminhoDaImagem = "D:\\Users\\Gustavo\\Documents\\NetBeansProjects\\psaTF\\fonte\\WWEstacionamento\\image\\code39.gif";







}