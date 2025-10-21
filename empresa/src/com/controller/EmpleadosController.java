package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet("/NominasController")
public class EmpleadosController extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
 /**
  * @see HttpServlet#HttpServlet()
  */
 public EmpleadosController() {
  super();
  // TODO Auto-generated constructor stub
 }
 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
  *      response)
  */
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
  // TODO Auto-generated method stub
 
  String opcion = request.getParameter("opcion");
 
}
}
  

