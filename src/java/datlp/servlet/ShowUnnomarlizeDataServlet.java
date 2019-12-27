/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datlp.servlet;

import datlp.constants.Constants;
import datlp.dto.UnmappingIngredientDTO;
import datlp.entity.Ingredient;
import datlp.entity.Nutrition;
import datlp.service.DishService;
import datlp.service.DishServiceImpl;
import datlp.service.NutritionService;
import datlp.service.NutritionServiceImpl;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DATLPSE62823
 */
@WebServlet(name = "ShowUnnomarlizeDataServlet", urlPatterns = {"/ShowUnnomarlizeDataServlet"})
public class ShowUnnomarlizeDataServlet extends HttpServlet {
    private final String NORMALIZE_PAGE = "admin-dashboard.jsp";
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
        
        String url = NORMALIZE_PAGE;
        
        try {
            DishService dishService = new DishServiceImpl();
            NutritionService nutritionService = new NutritionServiceImpl();
            
            List<Ingredient> unNomarlizeUnits = dishService.findByUnnormalizedUnit();
            request.setAttribute("UNNORMALIZED_UNIT", unNomarlizeUnits);
            
            List<UnmappingIngredientDTO> unMappingNutritions = dishService.findByUnMappingNutrition();
            unMappingNutritions.sort(new Comparator<UnmappingIngredientDTO>() {

                @Override
                public int compare(UnmappingIngredientDTO o1, UnmappingIngredientDTO o2) {
                    return o1.getIngredient().getName().compareTo(o2.getIngredient().getName());
                }
            });
            
            request.setAttribute("UNMAPPING_NUTRITION", unMappingNutritions);
            
            List<Nutrition> nutritions = nutritionService.getAll();
            nutritions.sort(new Comparator<Nutrition>() {

                @Override
                public int compare(Nutrition o1, Nutrition o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            
            request.setAttribute("NUTRITION", nutritions);
            request.setAttribute("STANDARD_UNIT", Constants.INGREDIENT_STANDARD_UNIT);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
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

}
